package mml.tools;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

import mml.tools.core.MmlTrack;

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
	public MmlTrack makeMmlTrack(int index){
		MmlTrack res=null;
		Track track = sequence.getTracks()[index];
		for (int nEvent = 0; nEvent < track.size(); nEvent++){
			MidiEvent	event = track.get(nEvent);
			/**
			 * parse all event to form a Mml track.
			 */
		}
		return res;
	}
}
