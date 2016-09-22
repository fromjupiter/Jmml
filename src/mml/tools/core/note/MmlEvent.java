package mml.tools.core.note;

/***
 * 
 * @author fkxcole
 * 
 * Non-note token. e.g. "V5" "O4" "T155" "L8"
 */
public class MmlEvent{
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
				case 2: info ="ChangeBeatTime";break;
				case 3: info ="ChangeDefaultBeat";break;
				default: info = "UnknownType";
			}
			return info;
		}
	}
	private EventType eventType;
	private int eventArg;
}