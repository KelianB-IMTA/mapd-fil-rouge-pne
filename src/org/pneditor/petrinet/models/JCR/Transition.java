package org.pneditor.petrinet.models.JCR;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Transition extends Node {
	private static int NB_TRANSITION;
	private Collection<OutEdge> fromEdges;
	private Collection<InEdge> toEdges;

	Transition() {
		this("Transition_" + NB_TRANSITION);
	}

	Transition(String n) {
		super(n);
		++NB_TRANSITION;
		this.fromEdges = ConcurrentHashMap.newKeySet();
		this.toEdges = ConcurrentHashMap.newKeySet();
	}

	void addEdgeTo(Place p, int w) {
		InEdge e = new InEdge(this, p, w);
		this.toEdges.add(e);
		p.addSimpleEdgeFrom(e);
	}

	void addEdgeFrom(Place p, int w) {
		OutEdge e = new OutEdge(p, this, w);
		this.fromEdges.add(e);
		p.addSimpleEdgeTo(e);
	}

	void addZeroEdgeFrom(Place p) {
		ZeroEdge e = new ZeroEdge(p, this);
		this.fromEdges.add(e);
		p.addSimpleEdgeTo(e);
	}

	void addEmptyEdgeFrom(Place p) {
		EmptyEdge e = new EmptyEdge(p, this);
		this.fromEdges.add(e);
		p.addSimpleEdgeTo(e);
	}

	public boolean isTriggerable() {
		Iterator<?> var2 = this.fromEdges.iterator();

		while (var2.hasNext()) {
			OutEdge e = (OutEdge) var2.next();
			if (!e.isEnable()) {
				return false;
			}
		}

		return true;
	}

	/** 
	 * Transition triggering.
	 */
	void trigger() {
		if (this.isTriggerable()) {
			Iterator<?> var2 = this.fromEdges.iterator();
			// activate incoming edges
			while (var2.hasNext()) {
				OutEdge e = (OutEdge) var2.next();
				e.fire();
			}

			var2 = this.toEdges.iterator();
			// activate outgoing edges
			while (var2.hasNext()) {
				InEdge e = (InEdge) var2.next();
				e.fire();
			}

		}
	}

	void removeInEdge(InEdge e) {
		this.toEdges.remove(e);
	}

	void removeOutEdge(OutEdge e) {
		this.fromEdges.remove(e);
	}

	void removeEdges() {
		Iterator<?> var2 = this.toEdges.iterator();

		while (var2.hasNext()) {
			InEdge e = (InEdge) var2.next();
			e.delete();
		}

		var2 = this.fromEdges.iterator();

		while (var2.hasNext()) {
			OutEdge e = (OutEdge) var2.next();
			e.delete();
		}

	}
}