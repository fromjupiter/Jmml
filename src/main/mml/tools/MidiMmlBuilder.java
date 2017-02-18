package mml.tools;

import mml.tools.core.MmlTrack;
import mml.tools.core.event.InvalidMmlEventException;
import mml.tools.util.MmlSubtrackPool;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MidiMmlBuilder {
	Sequence sequence=null;
	
	
	public MidiMmlBuilder setSequence(String filename) throws InvalidMidiDataException,IOException{
		File midiFile = new File(filename);
		sequence = MidiSystem.getSequence(midiFile);
		return this;
	}
	public int getTotalTracks(){
		return sequence.getTracks().length;
	}
	public MmlTrack makeMmlTrack(int index) throws InvalidMmlEventException {
		MmlSubtrackPool pool = new MmlSubtrackPool();
		MmlTrack res=MmlTrack.newInstance();
		Track track = sequence.getTracks()[index];
		Map<Integer,Long> notesOn = new HashMap<>();
		for (int nEvent = 0; nEvent < track.size(); nEvent++){
			MidiEvent	event = track.get(nEvent);
			/**
			 * parse all event to form a Mml track.
			 */
		}
		//get all subtrack from pool
		pool.checkOutAll().stream().forEach(res::addSubtrack);
		return res;
	}
}
