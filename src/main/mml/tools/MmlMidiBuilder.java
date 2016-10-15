package mml.tools;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import mml.tools.core.MmlSubtrack;
import mml.tools.core.MmlTrack;
import mml.tools.core.event.MmlEvent;
import mml.tools.core.event.MmlMetaEvent;
import mml.tools.core.event.MmlNoteEvent;

public class MmlMidiBuilder {
	private Sequence sequence;
	private int tickPerBeat;
	private int nextChannel=0;
	protected MmlMidiBuilder(int tickPerBeat){
		try{
			sequence = new Sequence(Sequence.PPQ, tickPerBeat);
			this.tickPerBeat = tickPerBeat;
		}catch (InvalidMidiDataException e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	public boolean makeMidiFile(String fileName){
		try{
			MidiSystem.write(sequence, 1, new File(fileName));
			return true;
		}
		catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}
	public void addTrack(MmlTrack mmlTrack) throws MidiUnavailableException{
		mmlTrack.normalize(tickPerBeat);
		Track track = sequence.createTrack();
		//choose channel
		int nChannel;
		if(mmlTrack.isPercussive()){
			nChannel = 10;
		}else{
			//normal instrument don't use channel 10.
			do{
			nChannel = nextChannel++;
			}while(nChannel==10);
			if(nChannel>15){
				throw new MidiUnavailableException("No available channels.");
			}
		}
		//choose instruments
		track.add(createNoteEvent(ShortMessage.CONTROL_CHANGE,nChannel,0, mmlTrack.getBankNo()>>7, 0));
		track.add(createNoteEvent(ShortMessage.CONTROL_CHANGE,nChannel,32, mmlTrack.getBankNo()&0x7f, 0));
		track.add(createNoteEvent(ShortMessage.PROGRAM_CHANGE,nChannel,mmlTrack.getProgramNo(), 0, 0));
		//add every event in every subtrack
		Iterator<MmlSubtrack> subtrackIter = mmlTrack.iterator();
		while(subtrackIter.hasNext()){
			Iterator<MmlEvent> iter = subtrackIter.next().iterator();
			long timePosition = 0;
			while(iter.hasNext()){
				MmlEvent event = iter.next();
				if(event.isNote()){
					//note event
					MmlNoteEvent note = (MmlNoteEvent)event;
					track.add(createNoteEvent(ShortMessage.NOTE_ON,nChannel,note.getMidiKey(), note.getMidiVelocity()
							,timePosition));
					track.add(createNoteEvent(ShortMessage.NOTE_OFF,nChannel,note.getMidiKey(), note.getMidiVelocity()
							,timePosition+note.getMidiTicks()));
					timePosition+=note.getMidiTicks();
				}else{
					//meta event
					MmlMetaEvent meta = (MmlMetaEvent)event;
					switch(meta.getEventType()){
					case T:
						int mpq = 60000000/meta.getEventArg();
						final int TEMPO = 0x51;
						byte[] data = new byte[3];
						data[0] = (byte)((mpq >> 16) & 0xFF);
						data[1] = (byte)((mpq >> 8) & 0xFF);
						data[2] = (byte)(mpq & 0xFF);
						track.add(createMetaEvent(TEMPO, data, timePosition));
						break;
					default:
						break;
					}
				}
			}
		}
		
	}
	private static MidiEvent createNoteEvent(int nCommand,int nChannel, int nKey, int nVelocity, long lTick) {
		ShortMessage message = new ShortMessage();
		try {
			message.setMessage(nCommand, nChannel,nKey, nVelocity);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}
		MidiEvent event = new MidiEvent(message, lTick);
		return event;
	}
	private static MidiEvent createMetaEvent(int status,byte[] data,long lTick){
		MetaMessage message = new MetaMessage();
		try {
			message.setMessage(status,data,data.length);
			
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.exit(1);
		}
		MidiEvent event = new MidiEvent(message, lTick);
		return event;
	}
	
	//factory method
	public static MmlMidiBuilder newInstance(int tpb){
		return new MmlMidiBuilder(tpb);
	}
	
}
