package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.kc.PetriNet;
import org.pneditor.petrinet.models.kc.Transition;
import org.pneditor.petrinet.models.kc.exceptions.ArcAlreadyExistsException;
import org.pneditor.petrinet.models.kc.exceptions.TransitionAlreadyExistsException;

/**
 * Adapter for our implementation of Transition
 */
public class TransitionAdapter extends AbstractTransition {
	/** The object that represents this Transition in our model */
	private Transition transition;
	
	/**
	 * Constructs a new Transition
	 * @param pn The PetriNet to which this transition should be added
	 * @param label The transition's label
	 */
	public TransitionAdapter(PetriNet pn, String label) {
		super(label);
		transition = new Transition();
		try {
			pn.addTransition(transition);
		} catch (TransitionAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds an arc to this transition
	 * @param a The Arc to add
	 */
	public void addArc(ArcAdapter a) {
		try {
			transition.addArc(a.getModelObject());
		} catch (ArcAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	

	/** Gets the object associated with this Transition in our model
	 * @return The object that represents this Transition in our model
	 */
	Transition getModelObject() {
		return this.transition;
	}

	/**
	 * Gets the enabled state of this transition
	 * @return Whether or not this transition can fire
	 */
	public boolean isEnabled() {
		return this.transition.canFire();
	}
	
	/**
	 * Fires this transition
	 */
	public void fire() {
		this.transition.fire();
	}
}
