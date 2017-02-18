package mml.tools.core.event;

import mml.tools.core.event.attributes.MmlPitch;

/**
 * This class stands for a builder which is used to build mml event, a note or a meta.
 * <p>To build a note, you need to instantiate a builder via {@link #newInstance()}, call set methods to set up your builder,
 * and call {@link #makeNote()}.<br/>
 * Set methods will set configuration of the builder instance.<br/>
 * Another option is to use copy set methods. It will return you a new builder instance with the configuration you set. 
 * It's useful when you don't want your original builder configuration to be modified.
 * @author Kexiang Feng
 *
 */
public class MmlEventBuilder{
	//default settings
	private MmlPitch.PitchStep pitchStep = MmlPitch.PitchStep.C;
	private int octave=4;
	private int volume = 12;
	private long tick = 1;
	public static MmlEventBuilder newInstance(){
		return new MmlEventBuilder();
	}


	public static MmlNoteEvent newNoteFromMidi(int midiKey, long midiTick, int midiVelocity) throws InvalidMmlEventException {
		return new MmlNoteEvent(
				MmlPitch.PitchStep.fromInt(midiKey%12),
				Math.floorDiv(midiKey,12)-1,
				midiTick,
				Math.floorDiv(midiVelocity,8)
		);
	}

	private MmlEventBuilder copy(){
		MmlEventBuilder res = new MmlEventBuilder();
		res.pitchStep = this.pitchStep;
		res.octave = this.octave;
		res.volume = this.volume;
		res.tick = this.tick;
		return res;
	}

	public int getOctave(){
		return octave;
	}
	public int getVolume(){
		return volume;
	}
	public long getTick(){
		return tick;
	}
	public MmlEventBuilder setOctave(int octave){
		this.octave = octave;
		return this;
	}
	public MmlEventBuilder setVolume(int volume){
		this.volume = volume;
		return this;
	}
	public MmlEventBuilder setTick(long tick){
		this.tick = tick;
		return this;
	}
	/** Copy&set tick of the note.
	 * @param tick value you want to set
	 * @return a builder copy with tick change taking effect
	 */
	public MmlEventBuilder copyAndSetTick(long tick){
		return this.copy().setTick(tick);
	}
	/** Copy&add a dot to the note, i.e. Tick value gets half longer.
	 * @return a builder copy with dot taking effect
	 */
	public MmlEventBuilder copyAndSetDot(){
		MmlEventBuilder res = this.copy();
		res.setTick((long)(res.tick*1.5));
		return res;
	}
	/** Copy&set the note to a specific midi key value.
	 * @param key midi key value
	 * @return a builder copy with pitch already set.
	 */
	public MmlEventBuilder copyAndSetPitchByMidiKey(int key){
		MmlEventBuilder res = this.copy();
		res.setOctave(key/12);
		String temp="";
		switch(key%12){
		case 0:temp="C";break;
		case 1:temp="CSHARP";break;
		case 2:temp="D";break;
		case 3:temp="DSHARP";break;
		case 4:temp="E";break;
		case 5:temp="F";break;
		case 6:temp="FSHARP";break;
		case 7:temp="G";break;
		case 8:temp="GSHARP";break;
		case 9:temp="A";break;
		case 10:temp="ASHARP";break;
		case 11:temp="B";break;
		}
		res.setPitchStep(temp);
		return this;
	}
	public MmlEventBuilder setPitchStep(String pitchStep){
		this.pitchStep = MmlPitch.PitchStep.valueOf(pitchStep.toUpperCase());
		return this;
	}
	public MmlNoteEvent makeNote() throws InvalidMmlEventException{
		return (pitchStep == MmlPitch.PitchStep.R) ? 
					new MmlNoteEvent(MmlPitch.PitchStep.C, 1, tick, 0)
					 : new MmlNoteEvent(pitchStep,octave,tick,volume);
	}
	
	public MmlMetaEvent makeMeta(String type,int arg) throws InvalidMmlEventException{
		return new MmlMetaEvent(MmlMetaEvent.EventType.valueOf(type.toUpperCase()),arg);
	}
}
