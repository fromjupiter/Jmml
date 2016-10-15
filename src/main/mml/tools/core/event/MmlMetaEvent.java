package mml.tools.core.event;

/***
 * The MmlMetaEvent class stands for Non-note event which changes the track status
 * and controls the flow. 
 * <p>e.g. "V5" "O4" "T155" "L8"
 * @author fkxcole
 * 
 * 
 */
public class MmlMetaEvent implements MmlEvent{
	public static enum EventType{
		V(0),
		O(1),
		T(2),
		L(3);
		private int nCode;
		private EventType(int n){
			nCode = n;
		}
		
		@Override
		public String toString(){
			String info;
			switch(nCode){
				case 0: info ="ChangeVolume";break;
				case 1: info ="ChangePitchBase";break;
				case 2: info ="ChangeBpm";break;
				case 3: info ="ChangeDefaultBeat";break;
				default: info = "UnknownType";
			}
			return info;
		}
	}
	private EventType eventType;
	private int eventArg;
	
	/**
	 * different types of meta event allow different ranges of args.
	 * <p>V event controls volume. valid arg ranges from 0 to 15.<br>
	 * T event controls tempo. valid arg ranges from 32 to 255.<br>
	 * O event controls default octave. valid arg ranges from 1 to 8.<br>
	 * L event controls default length of a note. valid arg must be a power of two.<br>
	 * @param type the meta event type
	 * @param arg the value which meta event uses to change status.
	 * @throws InvalidMmlEventException Invalid mml event exception
	 */
	public MmlMetaEvent(EventType type,int arg) throws InvalidMmlEventException {
		eventType = type;
		//validate arguments
		switch(eventType){
		case T:
			if(arg<32 || arg>255)
				throw new InvalidMmlEventException("Arg for meta event T can't be "+arg);
			break;
		case V:
			if(arg<0 || arg>15)
				throw new InvalidMmlEventException("Arg for meta event V can't be "+arg);
			break;
		case O:
			if(arg<1 || arg>8)
				throw new InvalidMmlEventException("Arg for meta event O can't be "+arg);
			break;
		case L:
			if(arg!=1 && arg!=2 && arg!=3 && arg!=4 && arg!=6 && arg!=8 && arg!=12 && arg!=16 && arg!=24 && arg != 32 && arg!=48 && arg!=64 && arg!=96)
				throw new InvalidMmlEventException("Arg for meta event L can't be "+arg);
			break;
		}
		eventArg = arg;
	}
	@Override
	public boolean isNote(){
		return false;
	}
	public EventType getEventType(){
		return eventType;
	}
	public int getEventArg(){
		return eventArg;
	}
	@Override
	public String toString(){
		return eventType.name()+eventArg;
	}
}