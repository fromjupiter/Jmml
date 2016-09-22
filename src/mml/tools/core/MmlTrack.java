package mml.tools.core;

import java.util.List;

import mml.tools.core.note.*;
/***
 * 
 * @author fkxcole
 *
 * An mml track is composed by a note sequence.
 */
public class MmlTrack {
	String instrument;
	MmlBeatTable beatTable;
	List<MmlNote> noteList;
}
