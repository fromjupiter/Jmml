package mml.tools.core;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.event.InvalidMmlEventException;

@SuppressWarnings("unused")
public class MmlTrackTest {

	@Test
	public void testThrowInvalidMmlEventException() {
		String[] str_list = new String[] {
				"t200o3l4r.rc>cecfcecd<b>c<afgc2c32",
				"t200o3l4r.rc>cecfcecd<b>c<afgc2c32",
				"t200zl4.c>cecfcecd<b>c<afgc2c32"};
		try{
			MmlTrack t = MmlTrack.newInstance(str_list);
			assertTrue(false);
		}catch(InvalidMmlEventException e){
			assertTrue(e.getIndex()==2);
		}
	}

}
