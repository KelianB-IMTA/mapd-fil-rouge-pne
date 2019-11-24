package org.pneditor.petrinet.models.JCR;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class PetriNet implements PetriNetInterface {
	private Collection<Place> places = new HashSet<Place>();
	private Collection<Transition> transitions = new HashSet<Transition>();

	public Place addPlace(int n) {
		Place place = new Place(n);
		this.places.add(place);
		return place;
	}

	public Place addPlace(String name, int n) {
		Place place = new Place(name, n);
		this.places.add(place);
		return place;
	}

	public boolean removePlace(Place p) {
		p.removeEdges();
		return this.places.remove(p);
	}

	public Transition addTransition() {
		Transition transition = new Transition();
		this.transitions.add(transition);
		return transition;
	}

	public Transition addTransition(String name) {
		Transition transition = new Transition(name);
		this.transitions.add(transition);
		return transition;
	}

	public boolean removeTransition(Transition t) {
		t.removeEdges();
		return this.transitions.remove(t);
	}

	/**
	 * PT: transition -> place
	 * TP: place -> transition
	 */
	public void addPTEdge(Place p, Transition t, int w) {
		t.addEdgeFrom(p, w);
	}

	public void addZeroPTEdge(Place p, Transition t) {
		t.addZeroEdgeFrom(p);
	}

	public void addEmptyPTEdge(Place p, Transition t) {
		t.addEmptyEdgeFrom(p);
	}

	public void addTPEdge(Transition t, Place p, int w) {
		t.addEdgeTo(p, w);
	}

	public void trigger(Transition t) {
		t.trigger();
	}

	public Collection<Transition> getTransitions() {
		return this.transitions;
	}

	public Collection<Place> getPlaces() {
		return this.places;
	}

	public void deleteEdge(Transition t, Place p) {
		Collection<InEdge> inEdges = p.getInEdges();
		Iterator<InEdge> var5 = inEdges.iterator();

		while (var5.hasNext()) {
			InEdge in = (InEdge) var5.next();
			if (in.getFrom() == t) {
				in.delete();
			}
		}

		Collection<OutEdge> toEdges = p.getOutEdges();
		Iterator<OutEdge> var6 = toEdges.iterator();

		while (var6.hasNext()) {
			OutEdge out = (OutEdge) var6.next();
			if (out.getTo() == t) {
				out.delete();
			}
		}

	}

	public int tokens(Place place) {
		return place.tokens();
	}

	public void addTokens(Place place, int n) {
		place.addTokens(n);
	}

	public void removeTokens(Place place, int n) {
		place.removeTokens(n);
	}
}