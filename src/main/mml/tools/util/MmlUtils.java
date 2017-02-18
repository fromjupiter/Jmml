package mml.tools.util;

import java.util.regex.Pattern;

/**
 * This class is used to store static constants/methods that are used in the package
 * @author Kexiang Feng
 *
 */
public class MmlUtils {
	public final static Pattern NOTEPATTERN = Pattern.compile("(&?)([ABCDEFGR][+-]?)(\\d*)(\\.?)");
	public final static Pattern METAPATTERN = Pattern.compile("([TVOLMN])(\\d+)(\\.?)");
	public final static Pattern TOKENPATTERN = Pattern.compile("&?[ABCDEFGR][+-]?\\d*\\.?|<|>|&|[TVOLMN]\\d+\\.?");
	
	public static long convertDenominatorToTick(int tickPerBeat,int denom,boolean hasDot){
		long res = tickPerBeat*4/denom;
		return hasDot? (long)(res*1.5) : res;
	}
	public static long convertDenominatorToTick(int tickPerBeat,int denom){
		return convertDenominatorToTick(tickPerBeat, denom,false);
	}


}
