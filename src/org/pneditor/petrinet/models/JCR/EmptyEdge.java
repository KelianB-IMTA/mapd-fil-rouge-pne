package org.pneditor.petrinet.models.JCR;

public class EmptyEdge extends OutEdge {
	EmptyEdge(Place p, Transition t) {
		super(p, t);
	}

	EmptyEdge(Place p, Transition t, int w) {
		super(p, t, w);
	}

	boolean isEnable() {
		return this.from.tokens() > 0;
	}

	void fire() {
		this.from.removeTokens(this.from.tokens());
	}
}