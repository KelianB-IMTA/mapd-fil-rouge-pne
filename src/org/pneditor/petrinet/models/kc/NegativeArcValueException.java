package org.pneditor.petrinet.models.kc;

/**
 * Exception thrown when trying to create an arc with a negative value.
 */
@SuppressWarnings("serial")
public class NegativeArcValueException extends Exception {
	@Override
	public String getMessage() {
		return "Arcs should have a positive value.";
	}
}
