package mml.tools.core.event.attributes;

import mml.tools.core.event.InvalidMmlEventException;

/**
 * The MmlPitch class stands for pitches in musical field.
 * <p>An MmlPitch object has two components:<br/>
 * pitch step, e.g. C,D,E,F,G,A,B<br/>
 * octave field, the widely-known term "standard C" is a C step in octave field 4.<br/>
 *
 * @author Kexiang Feng
 */
public final class MmlPitch {
	public static enum PitchStep{
		C (0),
		CSHARP(1),DFLAT (1),
		D(2),
		DSHARP(3),EFLAT (3),
		E(4), FFLAT(4),
		F(5), ESHARP(5),
		FSHARP(6),GFLAT(6),
		G(7),
		GSHARP(8),AFLAT(8),
		A(9),
		ASHARP(10),BFLAT(10),
		B(11),
		R(12)//Rest
		;
		private int value;
		private PitchStep(int i){this.value = i;}
		public int toInt(){return value;}
	}
	private int pitchOctave; //1-8
	private PitchStep pitchStep; // 0-12
	/**
	 * @param step
	 * @param octave can only range from 1 to 8
	 * @throws InvalidMmlEventException
	 */
	public MmlPitch(PitchStep step,int octave) throws InvalidMmlEventException{
		if(octave > 8 || octave<1)
			throw new InvalidMmlEventException("pitch base must be integer in 1-8");
		pitchStep = step;
		pitchOctave = octave;
	}
	public int getStepValue(){
		return pitchStep.value;
	}
	@Override
	public String toString(){
		return pitchStep.name();
	}
	public String toDetailString(){
		return pitchStep.name()+"o"+pitchOctave;
	}
	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(o==null||this.getClass()!=o.getClass()) return false;
		MmlPitch other = (MmlPitch)o;
		return pitchOctave==other.pitchOctave 
				&& pitchStep.toInt()==other.pitchStep.toInt();
	}
	@Override
	public int hashCode(){
		return pitchOctave * 20 + pitchStep.toInt();
	}
	/**
	 * Get the midi key value of the object
	 * @return midi key value
	 */
	public int toMidiNote(){
		return (pitchOctave+1) * 12 + pitchStep.toInt();
	}
	
}
