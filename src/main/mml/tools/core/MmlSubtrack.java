package mml.tools.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mml.tools.core.event.*;

/***
 * 
 * @author fkxcole
 *
 * An mml track is composed by a note sequence.
 */
public class MmlSubtrack implements Iterable<MmlEvent>{
	List<MmlEvent> eventList;
	int tickPerBeat = 16;
	//can only get instance from factory method
	protected MmlSubtrack(){
		eventList = new ArrayList<>();
	}
	public boolean addEvent(MmlEvent e){
		return eventList.add(e);
	}
	
	@Override
	public Iterator<MmlEvent> iterator() {
		return eventList.iterator();
	}
	
	public int getTickPerBeat(){
		return tickPerBeat;
	}
	
	public void adaptTickPerBeat(int newTickPerBeat){
		int ratio = newTickPerBeat/tickPerBeat;
		for(MmlEvent e:eventList){
			if(e.isNote()){
				MmlNoteEvent note = (MmlNoteEvent)e;
				note.setTick(note.getMidiTicks()*ratio);
			}
		}
		tickPerBeat = newTickPerBeat;
	}
	
	public static MmlSubtrack newInstance(){
		return new MmlSubtrack();
	}
	public static final MmlSubtrack newInstance(String trackStr) throws InvalidMmlEventException{
		MmlSubtrack result = new MmlSubtrack();
		
		MmlEventBuilder eventBuilder = MmlEventBuilder.newInstance();
		MmlTokenizer tokenizer = MmlTokenizer.newInstance(trackStr);
		/**
		 * After we calculate tick per beat, we should first set tick of event builder to the value of a 4th note.
		 * i.e. We trigger an 'l4' event.
		 */
		int tickPerBeat = tokenizer.calculateTickPerBeat();
		eventBuilder.setTick(MmlHelper.convertDenominatorToTick(tickPerBeat,4,false));
		result.tickPerBeat=tickPerBeat;
		
		MmlNoteEvent lastNote = null;
		/**
		 * The end point of previous token
		 */
		int oldEnd = 0;
		while(tokenizer.hasNext()){
			String token = tokenizer.next();
			int currentStart = tokenizer.currentStart();
			int currentEnd  = tokenizer.currentEnd();
			if(currentStart > oldEnd){ //there is gap between previous token and current token
				throw new InvalidMmlEventException(oldEnd, currentStart, "Unrecognized mml segment");
			}
			oldEnd = currentEnd;
			//catch the invalid mml event exception and add more information in it.
			try{
				if(token.equals("<")){
					eventBuilder.setOctave(eventBuilder.getOctave()-1);
				}else if(token.equals(">")){
					eventBuilder.setOctave(eventBuilder.getOctave()+1);
				}else{
					Pattern p = MmlHelper.NOTEPATTERN;
					Matcher m = p.matcher(token);
					if(m.find()){
						//note event
						boolean hasTenuto = m.group(1).equals("&")?true:false;
						String noteStr = m.group(2).replace("+", "SHARP").replace("-","FLAT");
						String denominatorStr = m.group(3);
						boolean hasDot = m.group(4).equals(".")?true:false;
						MmlEventBuilder worker=eventBuilder;
						//set up worker
						//Be aware that worker won't be the same object as eventBuilder unless there is no dot or denominator.
						worker.setPitchStep(noteStr);
						
						if(!denominatorStr.equals("")){
							//validate denominator
							int denominator = Integer.parseInt(denominatorStr);
							if(denominator!=1 && denominator!=2 && denominator!=4 && denominator!=8 && denominator!=16
									&&denominator!=32 && denominator!=64 && denominator!=128)
								throw new IllegalArgumentException("Note length can't be "+denominatorStr);
							
							worker=worker.copyAndSetTick(MmlHelper.convertDenominatorToTick(tickPerBeat, denominator));
						}
						//dot condition must be judged after denominator because dot will time tick value by 1.5
						if(hasDot){
							worker=worker.copyAndSetDot();
						}
						MmlNoteEvent note = worker.makeNote();
						if(!hasTenuto || !lastNote.merge(note)){
							lastNote = note;
							result.addEvent(lastNote);
						}
					}else{
						//Or meta event
						p = MmlHelper.METAPATTERN;
						m = p.matcher(token);
						if(m.find()){
							String metaStr = m.group(1);
							int arg = Integer.parseInt(m.group(2));
							boolean hasDot = m.group(3).equals(".")?true:false;
							if(metaStr.equals("V")){
								result.addEvent(eventBuilder.setVolume(arg).makeMeta("V", arg));
							}else if(metaStr.equals("O")){
								result.addEvent(eventBuilder.setOctave(arg).makeMeta("O", arg));
							}else if(metaStr.equals("L")){
								result.addEvent(eventBuilder.setTick(MmlHelper.convertDenominatorToTick(tickPerBeat,arg,hasDot)).makeMeta("L", arg));
							}else if(metaStr.equals("T")){
								result.addEvent(eventBuilder.makeMeta("T", arg));
							}else if(metaStr.equals("N")){
								/**
								 * N is a special meta event that will generate a note event. the arg is the midi key value.
								 * We don't really want to implement this feature, the workaround now is to create a rest note.
								 */
								result.addEvent(eventBuilder.copyAndSetPitchByMidiKey(arg).makeNote());
							}
						}else{
							//Or invalid token
							throw new InvalidMmlEventException("Unrecognized mml segment");
						}
					}
				}
			}catch(InvalidMmlEventException ex){
				ex.setStart(currentStart);
				ex.setEnd(currentEnd);
				throw ex;
			}
		}
		
		return result;
	}
}
