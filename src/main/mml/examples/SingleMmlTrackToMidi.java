package mml.examples;

import javax.sound.midi.MidiUnavailableException;

import mml.tools.MmlMidiBuilder;
import mml.tools.core.MmlTrack;
import mml.tools.core.event.InvalidMmlEventException;


/**
 * This application use hard-coded mml strings to build the song Brave Heart.
 * @author Kexiang Feng
 *
 */
public class SingleMmlTrackToMidi {
	public static void main(String[] args){
		MmlMidiBuilder builder = MmlMidiBuilder.newInstance(64);
		String[] melody = new String[]{
				"t72l32v15<g16&g64g16.g16&g64g4.&g16.dc<gdc<g>cdg>cdg1&g4.&g16f2g2e2f1>f8.&fa>e4<b4>g4<g4&gd<bge8<<a8>>e64a>c64e4&e16>c4a4g4>f4<b8.g8>e16<b16e4&e16.c16.<c8>e8e16d8>a+8a+16>c8c16<a+8<<f16d16<a+16>>e8e16d8>a+8a+16>c8c16<a+2o2f16<f>f<g>g16.>>c8<<a>ea16<a16<g16>g16<g8f16>f8.<f>f<g>g16.c8>cg>c16g16>>c16<b16a16g16a8<a8a16b8>c8c16c16d16<b16a16g16a4a16a16b8>c8c16c16<b8.>g8>f8.e16e8.d16d16d16d16g8d16d16e16d8.c16c8.<a16b8b16>e8.<b16a16a4a16.b16.>c16d8<a8b8>c8<b4>c8.d16<g+4g+2o2g4>>g8.>e16g16.f16.e16d8.o2b16>d16<b16>d16<b16>>g+8.>d16f16.e16.d16d8.<<e16<g16>e16g16e16f8>>a8b8>c8<a8>e16d8c8<g8.<c16e16g16>c16<g16e16<<g16>g16b16>d16g16d16<g8c.o6c<g.e.<ge.c.>g<g.>g.<g>g.o2b.o5bg.<b.gd.<b.>dg.b.gd.<<g+.o5bg+.e.<d<b.e.>>g+<e.>g+.<e>g+.<<a.>>ae.c.<ae.<g.>eg.>c.<ge.>a16&a64g16.f16&f64e8d8b16&b64a16.g16&g64f8e8a16&a64g16.f16&f64e8d8d+8&d+.g+8.d+8&d+64f8&f.a+8.f8&f64a8>c8<<a4b4b4g4&g16c16d16e2&e16f4a4b4b8.g16>d16.g8.&g<e16<b16>e2&e16>>e8e16d8<<efdfa+>c<fa+>cd<a+>cdefd<a+fed<a+fo6e8e16d8<<efdfa+>c<fa+>cd<a+>cdefd<a+fed<a+f>>a8>e16<a8&aa16.a16a16.a16.a16a16.a16.a1&a1&a16",
				"t72l32v15g16&g64g16.g16&g64g4.d2&dg16.>g>cdg>cdg1&g16<<agcfagcfagcfagcfgfcdgfcdgfcdgfcdge<b>dge<b>dge<b>dge<b>dge<a>cge<a>cge<a>cge<a>cgec<aec16.g4c8.f16>c4<g8.&gb>d4<e4&e16>c16d16<e4&e64a.>e64c<a64e64c<a64e8>a4>e4d4a4g4d<b>e16<b16>c4&c16e8c16d16<e8e16d8>a+8a+16>c8c16<a+8<f16a+16>f16<e8e16d8>a+8a+16>c8c16<a+4&a+16o1f16>f16>a16g16a16b8a8>c16c16d16<b16a16g16a4<f16>a16g16e16e4&e16>>e16d16c16<b16>c8<c8c16d8e8e16e16f16d16c16<b16>c4c16c16d8e8e16e16g8<<b16>>g8>f8.e16e8.d16d16d16d16g8d16d16e16d8.c16c8.<a16b8b16>e8.<b16a16a4a16.b16.>c16d8<a8b8>c8<b4>c8.d16d4e4>gd64c<g64dc<g64dc64<g<<g4o4e8.e16g16.f16.e16d8.g16g8b16>c16<d8.d16f16.e16.d16d8.>c16c4o2f16>c16>a8b8>c8f8e16d8c8e8.cdefga64b>c64def64ga64b>c<b64ag64fed64c<b64ag8>c16d16e8&e.<g>e.g16&g64f16.e16&e64d8&d.<dg.g8.b>cd8&d.<g+>d.f16&f64e16.d16&d64d8&d.<e>c.c8.<g16f16&f64e16.d16&d64c8<b8>g16&g64f16.e16&e64d8c8f16&f64e16.d16&d64c8<b8>c8&c64d+8.&d+.c8d8&d64f8.&f.d8e16.a8&a>>ec<aec<ae16<g4o7d<bgd<bg16.<e4o7d<bgd16.<<c2&c16c4o7ec<aec<ae16<g4o7d<bgd<bgd16<b4o7d<bgd16.<<c2&c16>e8e16d8a+8a+16>c8c16<a+8f16a+16>f16<e8e16d8a+8a+16>c8c16<a+4&a+16d8e16c+2.&c+16",
				"t72l16v15r64>c.c&c64c&c64c4&c&c64<g2&g.>c1&c4&c<c2c2<b2a2&a8.&a32>c8<a8&a32>a4&a32a32f32c32<a32f32c>>d4&d32<b32g32d32<b32g32d>b4.&ba4&a32.>c4&c64f4>e4d4a4d4&d<<g32d32<b32g32>>a2&ao1a+>fa+a+f>d<a+>f<a+>fa+a+f4<<a+>fa+a+f>d<a+>f<a+>fa+a+f>fd<a+2<<a1>>c4&c>>c<gc8o1f>f8f<f32>f32<g32>g.<a8>a32>e32a<a<g>g<g8f>f8f<f32>f32<g32>g.<a8>a32>e32a>>d8.<<g>d<c>cegc>c<ge<<b>b>dg<b>bgd<<a>a>ce<a>aec<<g>gb>e<g>ge<b<f>caf>c<a>fc<<f+>daf+>d<a>f+d<<g>dbg>d<b>gd>b4b2&b8<cd>c8.<<cecec<<b>b>d8g8g8>b8.<e<e>eg+e<a>ea8g8>c4<fcaf>c<fo6c8o3gdbg>d>>c1&c.&c64o3g32>c32.e8&e32.e&e64>c&c64<e.<g32b32.>d4.&d32.<e32g+32.b8&b32.b&b64>g+&g+64<b.>e32a32.>c4.>f&f64e.d&d64c8<b8>g&g64f.e&e64d8c8f&f64e.d&d64c8<b8g+8.>c8&c64<g+8&g+32.a+8.>d8&d64<a+8&a+32.>c8&c32e.<c4d.g32b32>d32g32b32<d8.&d32<b32b4&b>cd<a2&aa4>c4d.g32b32>d32g32b32<d8.&d32<b32>g8b32>d32g32b.<e<ba2&a<<a+32>f32a+32>c32<f32a+32>c32d32<a+32>d2&d8.&d32<<a+32>f32a+32>c32<f32a+32>c32d32<a+32>d2&d8.&d32>>d8o2ao6c+2.&c+",
				"t72l32v15r64c16.c16&c64c16&c64c4.&c.<g4.&g>c1&c4.&ca4a4d2d2c2&c8.<a8.ec<a>c<<f16>>cfa>c4&c16.o1g16>>dgb>dg4&g16o1e8>>egb>dg8.>c2&c16a4>c16.<b16.g16>c16.<b16.g16>c16.<b16.g16>g4.&g16c2&c16o2a+8.>d8f8.a+8.>d4&d16<<a+8.>d8f8.a+8.>d2.<<a1&a1a1a4<b1&b1&b1&b2.&b16o6d4e2&e8<c16d16e4<<g8g8<b8>g8b4>>d4<<e8b8a8>c2<a8>c8f8>>f8o3b8>d8g16>>e1&e1&e1&e16o3f.>cf.a.fc.<g.b>d.g.d<b.e.b>e.g.e<b.a.>ce.a.ec.<<f>cfa>fc<a16<g>dgb>gd<b16o6c8.&c64d+g+64>c64<g+d+64c8.d8.&d64fa+64>d64<a+f64d8.e8.a>c<<e8.&eco2g4o5g8.d16o2e4.&e16o5c2&c16o2f4o5e8.&eco2g4o5a4o2e4.&e16o5c2d1&d1&d16<ae<ae16.<<a1&a1&a2.&a16",
				"t72l16v15r32.>d&d64d&d64d.d4&d64c2&c8&c32d1&d2.&d32<g4g4g2g2.&g32e32c8.<<f2g2e2a2f>c32>>b.gc32<a32f32c32<a32f32c<g>d32g32b32>d32g32b32>d32<b32g32d32<b32g32d<e>e32>>b4&b.<<e<a>e32>>b.g8<a32e1&e1&e1&e1&e1&e2.&e.<<b1&b1&b1&b2.&bg+>eg+b>eg+b>e2&eo1c>>ce8>c2.<<g+>eg+1&g+4.<g>d4.gcg1&g1&g1&g1&g4.&g<<g+o4c64d+32g+64>c4&c64<g+32d+64co1a+o4d64f32a+64>d4&d64<a+32f64d<<f2>d2<b2a2>f4&f.>b.g<g4&g.>b8&b32<e2<a1&a1&a2&a8.a1&a1&a2.&a",
				"t72l32v13r.d16&d64d16.d16&d64d4&d16.&d64c4.&c16.d1&d1&d1&d1&d1&d2.&d8<f16.fa>cfa4&a<g2e16.gb>dgb4&b<a16.a>cea>c1&c1&c1&c1&c1&c1&c1&c1&c1&c1&c2.&co2c8>g2.&g8g+8b1&b4.g1&g1&g1&g1&g1<g+16g+16>c16g+16>c16<g+16c16<g+16a+16a+16>d16a+16>d16<a+16d16<a+16>c2b2g2a2&a16>>ea>cea>c4&c16.<<d2d4.&d16<<a1&a1&a2.&a16>>a16a16.a16.a16a16.a16.a16a8",
				"t72l32v13r16>g16&g64g16.g16&g64g8.&gd1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d1&d2&d16.<<d+8>d+8<d+4f8>f8<f8a2&a16>>d2dgb>dgb4&b16.<ea>cea>cec<aec<aec8<<b16.g4.&gb16.g4.&gb16.g4.>>ea>cea>cec<ae<c8",
				"t72v13r4.o6g32.d32c32.<g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g1&g2&g8<<g+8>g+8<g+4a+8>a+8<a+8.>>e1&e1&e1&e2&e32<b16.g8.>>c16d16",
		};
		MmlTrack mmlTrack;
		try {
			mmlTrack = MmlTrack.newInstance(melody);
			mmlTrack.setInstrument(0, 1);
			builder.addTrack(mmlTrack);
		} catch (InvalidMmlEventException | MidiUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		}
		builder.makeMidiFile("BraveHeart.mid");
	}
}
