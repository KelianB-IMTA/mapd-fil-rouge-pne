package org.pneditor.petrinet.models.JCR;

public abstract class Edge {
	protected int weight;

	protected Edge() {
		this.weight = 1;
	}

	protected Edge(int w) {
		if (w < 1) {
			this.weight = 1;
		} else {
			this.weight = w;
		}

	}

	public abstract void delete();

	abstract boolean isEnable();

	abstract void fire();

	public int getWeight() {
		return this.weight;
	}

	public abstract Node getFrom();

	public abstract Node getTo();
}