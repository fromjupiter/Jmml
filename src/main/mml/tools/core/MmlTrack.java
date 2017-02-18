package mml.tools.core;

import mml.tools.core.event.InvalidMmlEventException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class stands for an mml track, which is the counterpart of midi track in mml world.
 * An mml track contains the instrument information, along with one or many sub-tracks which are mml sequences.
 * @author Kexiang Feng
 *
 */
public class MmlTrack implements Iterable<MmlSubtrack>{
	/**
	 * (bankNo,programNo) can decide an instrument
	 */
	int bankNo = 0;
	/**
	 * (bankNo,programNo) can decide an instrument
	 */
	int programNo = 1;
	/**
	 * percussion instrument should be played on channel 10
	 */
	boolean percussive=false;
	int tickPerBeat=1;
	List<MmlSubtrack> subtracks;
	
	protected MmlTrack(boolean percussion){
		subtracks = new ArrayList<>();
		this.percussive = percussion;
	}
	/**
	 * Normalize with specific tpb(tick per beat). 
	 * Normalizing is to set all sub-tracks' tpb to the same value without changing how the music sounds. <br/>
	 * Note that the parameter should be larger than the max tpb value among subtracks, or it will lose precision.
	 * @param newTickPerBeat
	 */
	public void normalize(int newTickPerBeat){
		tickPerBeat = newTickPerBeat;
		//apply new tickPerBeat to every subtrack
		for(MmlSubtrack child:subtracks){
			child.adaptTickPerBeat(tickPerBeat);
		}
	}
	/**
	 * Normalize with the max tpb value among subtracks.
	 */
	private void normalize(){
		//set tickPerBeat of track to the max number among subtracks
		for(MmlSubtrack child:subtracks){
			tickPerBeat = child.tickPerBeat>tickPerBeat?child.tickPerBeat:tickPerBeat;
		}
		normalize(tickPerBeat);
	}
	public String getInstrumentName(){
		//TODO:get name based on (bankNo,instrumentNo)
		return "Guitar";
	}
	public int getBankNo(){
		return bankNo;
	}
	public int getProgramNo(){
		return programNo;
	}
	public void setInstrument(int bankNo,int programNo){
		this.bankNo = bankNo;
		this.programNo = programNo;
	}
	public int getTickPerBeat(){
		return tickPerBeat;
	}
	public boolean isPercussive(){
		return percussive;
	}
	/**
	 * Adds in a sub-track.
	 * @param st sub-track you want to add
	 */
	public void addSubtrack(MmlSubtrack st){
		subtracks.add(st);
		tickPerBeat = st.tickPerBeat>tickPerBeat?st.tickPerBeat:tickPerBeat;
		normalize();
	}
	
	/**
	 * factory method to generate an mmlTrack
	 * @param str_list A list of strings in mml format.
	 * @param percussion Does the track use percussion instrument.
	 * @return an MmlTrack that doesn't have instrument information yet.
	 * @throws InvalidMmlEventException The strings can't be parsed to mml track. The exception object gives you more information about where the error is.
	 */
	public static final MmlTrack newInstance(String[] str_list,boolean percussion) throws InvalidMmlEventException {
		
		MmlTrack res = new MmlTrack(percussion);
		for(int i=0;i<str_list.length;i++){
			MmlSubtrack temp;
			try {
				temp = MmlSubtrack.newInstance(str_list[i]);
			}catch(InvalidMmlEventException e){
				e.setIndex(i);
				throw e;
			}
			res.subtracks.add(temp);
			res.tickPerBeat = temp.tickPerBeat>res.tickPerBeat?temp.tickPerBeat:res.tickPerBeat;
		}
		res.normalize();
		return res;
	}
	
	/** 
	 * factory method to generate an non-percussive mmlTrack
	 * @param str_list str_list A list of strings in mml format.
	 * @return an MmlTrack that doesn't have instrument information yet.
	 * @throws InvalidMmlEventException The strings can't be parsed to mml track. The exception object gives you more information about where the error is.
	 */
	public static final MmlTrack newInstance(String[] str_list) throws InvalidMmlEventException {
		return newInstance(str_list,false);
	}
	/**
	 * factory method to generate an mmlTrack
	 * @param str_list A list of strings in mml format.
	 * @param percussion Does the track use percussion instrument.
	 * @return an MmlTrack that doesn't have instrument information yet.
	 * @throws InvalidMmlEventException The strings can't be parsed to mml track. The exception object gives you more information about where the error is.
	 */
	public static final MmlTrack newInstance(List<String> str_list,boolean percussion) throws InvalidMmlEventException {
		return MmlTrack.newInstance(str_list.toArray(new String[0]),percussion);
	}
	/** 
	 * factory method to generate an non-percussive mmlTrack
	 * @param str_list str_list A list of strings in mml format.
	 * @return an MmlTrack that doesn't have instrument information yet.
	 * @throws InvalidMmlEventException The strings can't be parsed to mml track. The exception object gives you more information about where the error is.
	 */
	public static final MmlTrack newInstance(List<String> str_list) throws InvalidMmlEventException {
		return MmlTrack.newInstance(str_list,false);
	}

	public static final MmlTrack newInstance(Boolean percussion) throws InvalidMmlEventException {
		return new MmlTrack(percussion);
	}

	public static final MmlTrack newInstance() throws InvalidMmlEventException {
		return new MmlTrack(false);
	}
	@Override
	public Iterator<MmlSubtrack> iterator() {
		return subtracks.iterator();
	}
}
