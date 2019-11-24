package org.pneditor.petrinet.models.JCR;

public class InEdge extends Edge {
	Transition from;
	Place to;

	InEdge(Transition t, Place p) {
		this.from = t;
		this.to = p;
	}

	InEdge(Transition t, Place p, int w) {
		super(w);
		this.from = t;
		this.to = p;
	}

	public void delete() {
		this.from.removeInEdge(this);
		this.to.removeInEdge(this);
		this.from = null;
		this.to = null;
	}

	boolean isEnable() {
		return true;
	}

	void fire() {
		this.to.addTokens(this.weight);
	}

	public Node getFrom() {
		return this.from;
	}

	public Node getTo() {
		return this.to;
	}
}