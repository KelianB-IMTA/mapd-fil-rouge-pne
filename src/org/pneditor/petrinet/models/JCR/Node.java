package org.pneditor.petrinet.models.JCR;

public abstract class Node {
	private String name;

	protected Node(String n) {
		this.name = n;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String n) {
		this.name = n;
	}
}