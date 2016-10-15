package mml.tools.core;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.event.InvalidMmlEventException;

@SuppressWarnings("unused")
public class MmlSubtrackTest {

	@Test
	public void testFactoryMethod() throws InvalidMmlEventException {
		MmlSubtrack track = MmlSubtrack.newInstance("t200o3l4r.rc>cecfcecd<b>c<afgc2c32");
		assertTrue(track.getTickPerBeat()==8);
		assertFalse(track.eventList.get(0).isNote());
	}
	@Test
	public void testAdaptTickPerBeat() throws InvalidMmlEventException{
		MmlSubtrack track = MmlSubtrack.newInstance("t200o3l4r.rc>cecfcecd<b>c<afgc2c32");
		track.adaptTickPerBeat(16);
		assertTrue(track.getTickPerBeat()==16);
	}
	@Test
	public void testInvalidMmlEventException(){
		MmlSubtrack track;
		try{
			track = MmlSubtrack.newInstance("t200zl4.c>cecfcecd<b>c<afgc2c32");
			assertTrue(false);
		}catch(InvalidMmlEventException e){
			assertTrue(e.getStart()==4);
			assertTrue(e.getEnd()==5);
		}
	}
}
