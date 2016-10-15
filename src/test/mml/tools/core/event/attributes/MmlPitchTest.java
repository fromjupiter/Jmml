package mml.tools.core.event.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.event.InvalidMmlEventException;
import mml.tools.core.event.attributes.MmlPitch;

@SuppressWarnings("unused")
public class MmlPitchTest {

	@Test
	public void testPitchAliases() throws InvalidMmlEventException {
		MmlPitch pitchCSharp = new MmlPitch(MmlPitch.PitchStep.CSHARP, 1);
		MmlPitch pitchDFlat =  new MmlPitch(MmlPitch.PitchStep.DFLAT, 1);
		assertTrue(pitchCSharp.equals(pitchDFlat));
	}
	@Test
	public void testToString() throws InvalidMmlEventException {
		MmlPitch pitchEFlat = new MmlPitch(MmlPitch.PitchStep.EFLAT, 1);
		MmlPitch pitchBFlat = new MmlPitch(MmlPitch.PitchStep.BFLAT, 5);
		assertTrue(pitchEFlat.toDetailString().equals("EFLATo1"));
		assertTrue(pitchBFlat.toDetailString().equals("BFLATo5"));
	}
	@Test
	public void testToMidiNote() throws InvalidMmlEventException{
		MmlPitch pitchC = new MmlPitch(MmlPitch.PitchStep.C, 4);
		assertTrue(pitchC.toMidiNote()==60);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testOctaveLeftBorder() throws InvalidMmlEventException{
		MmlPitch pitchC = new MmlPitch(MmlPitch.PitchStep.C, 1);
		assertTrue(true);
		pitchC = new MmlPitch(MmlPitch.PitchStep.C, 0);
		assertTrue(false);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testOctaveRightBorder() throws InvalidMmlEventException{
		MmlPitch pitchC = new MmlPitch(MmlPitch.PitchStep.C, 8);
		assertTrue(true);
		pitchC = new MmlPitch(MmlPitch.PitchStep.C, 9);
		assertTrue(false);
	}
}
