package org.pneditor.petrinet.models.kc;

/**
 * Exception thrown when trying to add the same transition twice to a Petri Net.
 */
@SuppressWarnings("serial")
public class TransitionAlreadyExistsException extends Exception {
	@Override
	public String getMessage() {
		return "Attempted to add the same transition twice to the Petri net.";
	}
}
