package org.pneditor.petrinet.models.kc;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IPetriNet. Represents a basic Petri Net.
 */
public class PetriNet implements IPetriNet {
	/**
	 * The transitions of this network
	 */
	private List<Transition> transitions;
	
	/**
	 * Constructs an empty Petri net (with no transition)
	 */
	public PetriNet() {
		this.transitions = new ArrayList<Transition>();
	}
	
	/**
	 * Constructs a Petri net with the given transitions
	 * @param transitions the transitions to add
	 * @throws TransitionAlreadyExistsException if one of the transitions is added twice
	 */
	public PetriNet(Transition...transitions) throws TransitionAlreadyExistsException {
		this();
		for(int i = 0; i < transitions.length; i++)
			this.addTransition(transitions[i]);
	}
	
	/**
	 * See IPetriNet.getTransitions
	 */
	@Override
	public List<Transition> getTransitions() {
		return transitions;
	}
	

	/**
	 * See IPetriNet.addTransition
	 */
	@Override
	public void addTransition(Transition t) throws TransitionAlreadyExistsException {
		if(getTransitions().contains(t))
			throw new TransitionAlreadyExistsException();
		if(t == null)
			throw new NullPointerException();
		getTransitions().add(t);
	}
	
	/**
	 * See IPetriNet.removeTransition
	 */
	@Override
	public boolean removeTransition(Transition t) {
		return getTransitions().remove(t);
	}
	
	/**
	 * See IPetriNet.removePlace
	 */
	@Override
	public boolean removePlace(Place p) {
		boolean wasRemoved = true;
		for(Transition t : this.getTransitions()) {
			for(int i = 0; i < t.getArcs().size(); i++) {
				Arc arc = t.getArcs().get(i);
				if(arc.getPlace().equals(p)) {
					arc.getPlace().empty();
					if(t.removeArc(arc))
						i--;
					else
						wasRemoved = false;
				}
			}
		}
		return wasRemoved;
	}

	/**
	 * See IPetriNet.removeArc
	 */
	@Override
	public boolean removeArc(Arc a) {
		boolean wasRemoved = true;
		for(Transition t : this.getTransitions()) {
			for(int i = 0; i < t.getArcs().size(); i++) {
				Arc arc = t.getArcs().get(i);
				if(arc.equals(a)) {
					if(t.removeArc(arc))
						i--;
					else
						wasRemoved = false;
				}
			}
		}
		return wasRemoved;
	}
	
	/**
	 * See IPetriNet.fireAll
	 */
	@Override
	public void fireAll() {
		for(int i = 0; i < getTransitions().size(); i++) {
			getTransitions().get(i).fire();
		}
	}
	
	/**
	 * Converts this network into a readable String.
	 * @return a readable representation of this network
	 */
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < getTransitions().size(); i++) {
			str += "t" + (i+1) + "\n" + getTransitions().get(i).toString();
		}
		return str;
	}
}
