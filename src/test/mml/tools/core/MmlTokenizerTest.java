package mml.tools.core;

import static org.junit.Assert.*;

import org.junit.Test;

import mml.tools.core.MmlTokenizer;

public class MmlTokenizerTest {
	
	String str;
	
	@Test
	public void testIteratorOnNormalSequence() {
		str = "t120o3l4c>cecfcecd<b>c<afgc2c32";
		StringBuilder res=new StringBuilder();
		MmlTokenizer tokenizer = new MmlTokenizer(str);
		while(tokenizer.hasNext()){
			String temp = tokenizer.next();
			res.append(temp);
		}
		assertTrue(res.toString().equals(str.toUpperCase().replaceAll("[ \\t\\r\\n]","")));
	}
	@Test
	public void testIteratorOnRestSequence() {
		str = "t120o3l4cRR>rcecfrcecd<b>c<afgc2c32";
		StringBuilder res=new StringBuilder();
		MmlTokenizer tokenizer = new MmlTokenizer(str);
		while(tokenizer.hasNext()){
			String temp = tokenizer.next();
			res.append(temp);
		}
		assertTrue(res.toString().equals(str.toUpperCase().replaceAll("[ \\t\\r\\n]","")));
	}
	@Test
	public void testIteratorOnDotSequence() {
		str = "t120o3l4c>cecf32.c.ecd<b>c<afgc2c32";
		StringBuilder res=new StringBuilder();
		MmlTokenizer tokenizer = new MmlTokenizer(str);
		while(tokenizer.hasNext()){
			String temp = tokenizer.next();
			res.append(temp);
		}
		assertTrue(res.toString().equals(str.toUpperCase().replaceAll("[ \\t\\r\\n]","")));
	}
	@Test
	public void testIteratorOnTenutoSequence() {
		str = "t120o3l4c>c&e&c&f32cecd<b>c<afgc2c32";
		StringBuilder res=new StringBuilder();
		MmlTokenizer tokenizer = new MmlTokenizer(str);
		while(tokenizer.hasNext()){
			String temp = tokenizer.next();
			res.append(temp);
		}
		assertTrue(res.toString().equals(str.toUpperCase().replaceAll("[ \\t\\r\\n]","")));
	}
	@Test
	public void testCalculateTickPerBeat() {
		str = "t120o3l4crr>rcecf.cecd<b>c<afgc2c32";
		MmlTokenizer tokenizer = new MmlTokenizer(str);
		assertTrue(tokenizer.calculateTickPerBeat()==8);
	}

}
