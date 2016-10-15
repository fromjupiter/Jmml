package mml.tools.core.event;

import static org.junit.Assert.*;

import org.junit.Test;

public class MmlMetaEventTest {
	MmlMetaEvent me;
	@Test(expected=InvalidMmlEventException.class)
	public void testTLeftBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.T,32);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.T,31);
		assertTrue(false);
	}
	@Test(expected=InvalidMmlEventException.class)
	public void testTRightBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.T,255);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.T,256);
		assertTrue(false);
	}
	@Test(expected=InvalidMmlEventException.class)
	public void testVLeftBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.V,0);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.V,-1);
		assertTrue(false);
	}
	@Test(expected=InvalidMmlEventException.class)
	public void testVRightBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.V,15);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.V,16);
		assertTrue(false);
	}
	@Test(expected=InvalidMmlEventException.class)
	public void testOLeftBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.O,1);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.O,0);
		assertTrue(false);
	}
	@Test(expected=InvalidMmlEventException.class)
	public void testORightBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.O,8);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.O,9);
		assertTrue(false);
	}
	
	@Test(expected=InvalidMmlEventException.class)
	public void testLBorder() throws InvalidMmlEventException {
		me = new MmlMetaEvent(MmlMetaEvent.EventType.L,2);
		assertTrue(true);
		me = new MmlMetaEvent(MmlMetaEvent.EventType.L,3);
		assertTrue(false);
	}

}
