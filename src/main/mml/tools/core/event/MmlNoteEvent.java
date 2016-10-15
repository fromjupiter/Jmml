package mml.tools.core.event;

import mml.tools.core.event.attributes.*;

/**
 * The MmlNoteEvent class stands for an event that stands for a sound unit,a.k.a. note.
 * <p>The length of a note is measured in midi ticks. <br/>
 * A tick can be seen as a small fraction of one beat.
 * @author Kexiang Feng
 *
 */
public class MmlNoteEvent implements MmlEvent{
	private MmlPitch pitch;
	private long tick;
	private MmlVolume volume;
	
	protected MmlNoteEvent(MmlPitch.PitchStep pitchStep,int pitchOctave
					,long tick
					,int volume) throws InvalidMmlEventException{
		this.pitch = new MmlPitch(pitchStep,pitchOctave);
		this.tick= tick;
		this.volume = new MmlVolume(volume);
	}

	public String toString(){
		return this.pitch.toString()+tick;
	}
	public String toDetailString(){
		return this.pitch+" "+volume+" "+tick+"ticks";
	}
	public int getPitchStepValue(){
		return this.pitch.getStepValue();
	}
	public String getPitchStepString(){
		return this.pitch.toString();
	}
	public int getMidiKey(){
		return this.pitch.toMidiNote();
	}
	public long getMidiTicks(){
		return this.tick;
	}
	public int getMidiVelocity(){
		return this.volume.toMidiVelocity();
	}
	public void setTick(long tick){
		this.tick = tick;
	}
	public boolean merge(MmlNoteEvent other){
		if(other.pitch.equals(this.pitch)){
			this.tick+=other.tick;
			return true;
		}else return false;
	}
	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(o==null||this.getClass()!=o.getClass()) return false;
		MmlNoteEvent other = (MmlNoteEvent)o;
		return pitch.equals(other.pitch)&&tick==other.tick&&volume.equals(other.volume);
	}
	@Override
	public int hashCode() {
		return (int)tick%1000+pitch.hashCode()+volume.hashCode();
	}
	@Override
	public boolean isNote(){
		return true;
	}
}
