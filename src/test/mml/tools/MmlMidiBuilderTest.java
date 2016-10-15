package mml.tools;

import static org.junit.Assert.*;

import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import mml.tools.core.MmlTrack;
import mml.tools.core.event.InvalidMmlEventException;

public class MmlMidiBuilderTest {

	@Test
	public void testFunctionality() {
		MmlMidiBuilder builder = MmlMidiBuilder.newInstance(64);
		String[] melody = new String[]{"t120v15o5l4c.c8g.g8a.a8grf.f8e.e8d.d8e16c2"};
		String[] chords = new String[]{"v9o3l4c>cecfcerd<b>c<afgc2c32"};
		MmlTrack mmlTrack;
		try {
			mmlTrack = MmlTrack.newInstance(chords);
			mmlTrack.setInstrument(1152, 14);
			builder.addTrack(mmlTrack);
			mmlTrack = MmlTrack.newInstance(melody);
			mmlTrack.setInstrument(0, 1);
			builder.addTrack(mmlTrack);
		} catch (InvalidMmlEventException | MidiUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		}
		builder.makeMidiFile("RockStar.mid");
		assertTrue(true);
	}
}
