package org.pneditor.petrinet.models.kc.exceptions;

/**
 * Exception thrown when trying to add the same arc twice to the same transition.
 */
@SuppressWarnings("serial")
public class ArcAlreadyExistsException extends Exception {
	@Override
	public String getMessage() {
		return "Attempted to add the same arc twice to a transition.";
	}
}
