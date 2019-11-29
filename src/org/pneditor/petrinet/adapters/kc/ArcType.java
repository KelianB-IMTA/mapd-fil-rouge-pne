package org.pneditor.petrinet.adapters.kc;

/**
 * The various types of arcs in our model (Note: ArcType.IN describes an arc going from a transition to a place)
 */
public enum ArcType {
	INHIBITOR, RESET, IN, OUT
}
