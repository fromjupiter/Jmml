package mml.tools.core.event;

/**
 * This exception happens when the mml event you created is invalid, i.e., your mml file isn't right.
 * <p/> Index - the track index the exception happens
 * <p/> Start - the start position of the erroneous token.
 * <p/> End   - the offset after the last character of the erroneous token.
 * @author Kexiang Feng
 *
 */
public class InvalidMmlEventException extends Exception {
	private static final long serialVersionUID = 1351179695646884185L;
	private int index;
	private int start;
	private int end;
	public InvalidMmlEventException(String msg){
		super(msg);
	}
	public InvalidMmlEventException(int start, int end,String msg){
		super(msg);
		this.start = start;
		this.end = end;
	}
	public InvalidMmlEventException(int index,int start, int end,String msg){
		super(msg);
		this.index = index;
		this.start = start;
		this.end = end;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
