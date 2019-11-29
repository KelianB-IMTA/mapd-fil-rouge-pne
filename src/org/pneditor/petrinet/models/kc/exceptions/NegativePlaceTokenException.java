package org.pneditor.petrinet.models.kc.exceptions;

/**
 * Exception thrown when trying to set a place's tokens to a negative value.
 */
@SuppressWarnings("serial")
public class NegativePlaceTokenException extends Exception {
	@Override
	public String getMessage() {
		return "Attempted to set a place's tokens to a negative value in the PetriNet.";
	}
}
