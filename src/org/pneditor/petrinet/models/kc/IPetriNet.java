package org.pneditor.petrinet.models.kc;

import java.util.List;

/**
 * The main interface for representing a Petri Net.
 */
public interface IPetriNet {
	/**
	 * Fires all transitions within this network
	 */
	public void fireAll();
	
	/**
	 * Gets the transitions of this network
	 * @return the transitions
	 */
	public List<Transition> getTransitions();
	
	/**
	 * Adds a transition to this network
	 * @param t the transition to add
	 * @throws TransitionAlreadyExistsException if the transition was already added to this network
	 * @throws NullPointerException if the new transition is null
	 */
	public void addTransition(Transition t) throws TransitionAlreadyExistsException;

	/**
	 * Removes the given transition from this network
	 * @param t the transition to remove
	 * @return whether or not the transition was successfully removed
	 */
	public boolean removeTransition(Transition t);
	
}
