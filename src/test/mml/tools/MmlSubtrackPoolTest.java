package mml.tools;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.MmlSubtrack;

public class MmlSubtrackPoolTest {

	@Test
	public void test() {
		MmlSubtrackPool pool = MmlSubtrackPool.newInstance();
		MmlSubtrack tOne = pool.checkOut();
		MmlSubtrack tTwo = pool.checkOut();
		pool.checkIn(tOne);
		MmlSubtrack tThree= pool.checkOut();
		pool.checkIn(tTwo);
		MmlSubtrack tFour = pool.checkOut();
		
		assertTrue(tOne==tThree);
		assertFalse(tOne== tTwo);
		assertTrue(tTwo == tFour);
	}
}
