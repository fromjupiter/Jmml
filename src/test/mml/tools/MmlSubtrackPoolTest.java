package mml.tools;

import mml.tools.core.MmlSubtrack;
import mml.tools.util.MmlSubtrackPool;
import org.junit.Test;

import static org.junit.Assert.*;

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
