package org.pneditor.petrinet.models.JCR;

public class OutEdge extends Edge {
	Place from;
	Transition to;

	OutEdge(Place p, Transition t) {
		this.from = p;
		this.to = t;
	}

	OutEdge(Place p, Transition t, int w) {
		super(w);
		this.from = p;
		this.to = t;
	}

	boolean isEnable() {
		return this.from.tokens() >= this.weight;
	}

	void fire() {
		this.from.removeTokens(this.weight);
	}

	public void delete() {
		this.to.removeOutEdge(this);
		this.from.removeOutEdge(this);
		this.from = null;
		this.to = null;
	}

	public Node getFrom() {
		return this.from;
	}

	public Node getTo() {
		return this.to;
	}
}