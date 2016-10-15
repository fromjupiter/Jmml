package mml.tools.core.event;

/**
 * An MmlEvent can be either {@link MmlMetaEvent} or {@link MmlNoteEvent}.
 * @author Kexiang Feng
 *
 */
public interface MmlEvent {
	boolean isNote();
}
