package mml.tools.core.event.attributes;

import mml.tools.core.event.InvalidMmlEventException;

/**
 * The MmlVolume class is a value range from o to 15 that indicates the volume of a note
 * @author Kexiang Feng
 *
 */
public final class MmlVolume {
	private int value;
	/**
	 * @param value range from 0 to 15
	 * @throws InvalidMmlEventException
	 */
	public MmlVolume(int value) throws InvalidMmlEventException{
		if(value < 0 || value > 15)
			throw new InvalidMmlEventException("volume must range 0-15");
		this.value = value;
	}
	@Override
	public String toString(){return "v"+value;}
	@Override
	public boolean equals(Object o){
		if(this==o) return true;
		if(o==null||this.getClass()!=o.getClass()) return false;
		MmlVolume other = (MmlVolume)o;
		return value==other.value;
	}
	@Override
	public int hashCode(){
		return value;
	}
	public int toMidiVelocity(){
		return value*8;
	}
}
