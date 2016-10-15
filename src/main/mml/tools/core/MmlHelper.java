package mml.tools.core;

import java.util.regex.Pattern;

/**
 * This class is used to store static constants/methods that are used in the package
 * @author Kexiang Feng
 *
 */
class MmlHelper {
	final static Pattern NOTEPATTERN = Pattern.compile("(&?)([ABCDEFGR][+-]?)(\\d*)(\\.?)");
	final static Pattern METAPATTERN = Pattern.compile("([TVOLMN])(\\d+)(\\.?)");
	final static Pattern TOKENPATTERN = Pattern.compile("&?[ABCDEFGR][+-]?\\d*\\.?|<|>|&|[TVOLMN]\\d+\\.?");
	
	static long convertDenominatorToTick(int tickPerBeat,int denom,boolean hasDot){
		long res = tickPerBeat*4/denom;
		return hasDot? (long)(res*1.5) : res;
	}
	static long convertDenominatorToTick(int tickPerBeat,int denom){
		return convertDenominatorToTick(tickPerBeat, denom,false);
	}
}
