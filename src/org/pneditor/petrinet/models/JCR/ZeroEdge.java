package org.pneditor.petrinet.models.JCR;

/**
 * Actif si la place est vide
 * @author jroyer
 *
 */
public class ZeroEdge extends OutEdge {
	ZeroEdge(Place p, Transition t) {
		super(p, t);
	}

	ZeroEdge(Place p, Transition t, int w) {
		super(p, t, w);
	}

	boolean isEnable() {
		return this.from.tokens() == 0;
	}

	void fire() {
	}
}