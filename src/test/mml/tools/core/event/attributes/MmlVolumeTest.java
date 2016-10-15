package mml.tools.core.event.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.event.InvalidMmlEventException;
import mml.tools.core.event.attributes.MmlVolume;

@SuppressWarnings("unused")
public class MmlVolumeTest {
	@Test(expected=InvalidMmlEventException.class)
	public void testLeftBorder() throws InvalidMmlEventException {
		MmlVolume v = new MmlVolume(0);
		assertTrue(true);
		v = new MmlVolume(-1);
		assertTrue(false);
	}
	@Test(expected=InvalidMmlEventException.class)
	public void testRightBorder() throws InvalidMmlEventException {
		MmlVolume v = new MmlVolume(15);
		assertTrue(true);
		v = new MmlVolume(16);
		assertTrue(false);
	}
}
