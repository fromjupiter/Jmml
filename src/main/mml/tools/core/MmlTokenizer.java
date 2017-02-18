package mml.tools.core;

import mml.tools.util.MmlUtils;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * This class is used to tokenize mml text file and split it into small segments.
 * <br>Here are some examples of mml tokens:
 * <p/> C4, T120, R., O5
 * @author fkxcole
 * 
 */

public final class MmlTokenizer implements Iterator<String> {
	/*
	 * TODO: we may not want to skip invalid tokens.
	 *       If we encounter invalid tokens, the better solution is to throw an exception.
	 */
	private String mmlText;
	private Matcher match;
	protected MmlTokenizer(String mmlText) {
		this.mmlText = mmlText.toUpperCase().replaceAll("[ \\t\\r\\n]","");
		match = MmlUtils.TOKENPATTERN.matcher(this.mmlText);
	}
	public static MmlTokenizer newInstance(String text){
		return new MmlTokenizer(text);
	}
	
	/**
	 * Reset to the start of text.
	 */
	public void reset(){
		match = MmlUtils.TOKENPATTERN.matcher(this.mmlText);
	}
	
	@Override
	public boolean hasNext() {
		return match.find();
	}

	@Override
	public String next(){
		return match.group();
	}
	
	/** The index of the first character matched 
	 * @return The index of the first character matched 
	 */
	int currentStart(){
		return match.start();
	}
	/** The offset after the last character matched
	 * @return The offset after the last character matched
	 */
	int currentEnd(){
		return match.end();
	}
	/** find the smallest proper TickPerBeat value.
	 * @return proper tick per beat
	 */
	public int calculateTickPerBeat(){
		//find the note event with largest number(likely to be 64), and divide by four.
		/*
		 * This pattern is slightly different from NOTEPATTERN because:
		 *   we care about L meta event.
		 *   we don't care about note that has no length info. e.g. 'C'
		 */
		Pattern p = Pattern.compile("[ABCDEFGRL]\\+?(\\d+)(\\.?)");
		Matcher m = p.matcher(mmlText);
		int max = 1;
		while(m.find()){
			int candidate  = Integer.parseInt(m.group(1));
			//If there is a tenuto
			if(m.group(2).equals("."))
				candidate=(int)(candidate*1.5);
			max = candidate>max? candidate:max;
		}
		return max/4;
	}

}
