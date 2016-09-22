package mml.tools.parser;

import java.util.Iterator;
/***
 * 
 * @author fkxcole
 * 
 * This class is used to tokenize mml text file into events
 */

final class MmlTokenizer implements Iterator<String> {
	
	int currentBegin = 0;
	int currentEnd = 0;
	String mmlText;
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return null;
	}

}
