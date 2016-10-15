package mml.tools.core;

import java.util.regex.Pattern;

/**
 * This class is used to store static constants/methods that are used in the package
 * @author Kexiang Feng
 *
 */
class MmlHelper {
	final static Pattern NOTEPATTERN = Pattern.compile("(&?)([ABCDEFGR][+-]?)(\\d*)(\\.?)");
	final static Pattern METAPATTERN = Pattern.compile("([TVOLM])(\\d+)");
	final static Pattern TOKENPATTERN = Pattern.compile("&?[ABCDEFGR][+-]?\\d*\\.?|<|>|&|[TVOLM]\\d+");
	
	static long convertDenominatorToTick(int tickPerBeat,int denom){
		return tickPerBeat*4/denom;
	}
}
