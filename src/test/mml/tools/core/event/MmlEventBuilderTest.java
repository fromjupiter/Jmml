package mml.tools.core.event;

import static org.junit.Assert.*;

import org.junit.Test;

public class MmlEventBuilderTest {
	
	MmlEventBuilder eb;
	@Test
	public void testSetOctave() {
		eb = new MmlEventBuilder();
		MmlEventBuilder other = eb.setOctave(5);
		assertTrue(eb.equals(other));
		assertTrue(eb==other);
	}

	@Test
	public void testSetVolume() {
		eb = new MmlEventBuilder();
		MmlEventBuilder other = eb.setVolume(5);
		assertTrue(eb.equals(other));
		assertTrue(eb==other);
	}

	@Test
	public void testSetTick() {
		eb = new MmlEventBuilder();
		MmlEventBuilder other = eb.setTick(5);
		assertTrue(eb.equals(other));
		assertTrue(eb==other);
	}

	@Test
	public void testCopySetTick() {
		eb = new MmlEventBuilder();
		MmlEventBuilder other = eb.copyAndSetTick(5);
		assertTrue(eb.equals(other));
		assertFalse(eb==other);
	}

	@Test
	public void testSetPitchStep() {
		eb = new MmlEventBuilder();
		MmlEventBuilder other = eb.setPitchStep("ESHARP");
		assertTrue(eb.equals(other));
		assertTrue(eb==other);
	}
}
