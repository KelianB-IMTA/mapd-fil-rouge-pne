package org.pneditor.petrinet.models.JCR;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Place extends Node {
	// to count place numbering
	private static int NB_PLACE;
	private int tokens;
	private Collection<OutEdge> outEdges;
	private Collection<InEdge> inEdges;

	Place() {
		this(0);
	}

	Place(int n) {
		this("Place_" + NB_PLACE, n);
	}

	Place(String name, int n) {
		super(name);
		++NB_PLACE;
		this.tokens = n;
		this.outEdges = ConcurrentHashMap.newKeySet();
		this.inEdges = ConcurrentHashMap.newKeySet();
	}

	void addEdgeTo(Transition t, int w) {
		t.addEdgeFrom(this, w);
	}

	void addZeroEdgeTo(Transition t) {
		t.addZeroEdgeFrom(this);
	}

	void addEmptyEdgeTo(Transition t) {
		t.addEmptyEdgeFrom(this);
	}

	void addEdgeFrom(Transition t, int w) {
		t.addEdgeTo(this, w);
	}

	void addTokens(int n) {
		this.tokens += n;
	}

	void removeTokens(int n) {
		if (this.tokens >= n) {
			this.tokens -= n;
		}
	}

	int tokens() {
		return this.tokens;
	}

	protected void addSimpleEdgeTo(OutEdge o) {
		this.outEdges.add(o);
	}

	protected void addSimpleEdgeFrom(InEdge i) {
		this.inEdges.add(i);
	}

	void removeInEdge(InEdge e) {
		this.inEdges.remove(e);
	}

	void removeOutEdge(OutEdge e) {
		this.outEdges.remove(e);
	}

	void removeEdges() {
		Iterator<?> var2 = this.inEdges.iterator();

		while (var2.hasNext()) {
			InEdge e = (InEdge) var2.next();
			e.delete();
		}

		var2 = this.outEdges.iterator();

		while (var2.hasNext()) {
			OutEdge e = (OutEdge) var2.next();
			e.delete();
		}

	}

	Collection<InEdge> getInEdges() {
		return this.inEdges;
	}

	Collection<OutEdge> getOutEdges() {
		return this.outEdges;
	}
}