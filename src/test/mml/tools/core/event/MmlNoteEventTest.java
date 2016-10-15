package mml.tools.core.event;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.event.attributes.MmlPitch;

public class MmlNoteEventTest {
	@Test
	public void testEquals() throws InvalidMmlEventException {
		MmlNoteEvent event = new MmlNoteEvent(MmlPitch.PitchStep.CSHARP,4,1,15);
		assertTrue(event.equals(new MmlNoteEvent(MmlPitch.PitchStep.CSHARP,4,1,15)));
		assertTrue(event.equals(new MmlNoteEvent(MmlPitch.PitchStep.DFLAT,4,1,15)));
	}

}
