package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.kc.PetriNet;
import org.pneditor.petrinet.models.kc.Transition;
import org.pneditor.petrinet.models.kc.exceptions.ArcAlreadyExistsException;
import org.pneditor.petrinet.models.kc.exceptions.TransitionAlreadyExistsException;

public class TransitionAdapter extends AbstractTransition {
	private Transition transition;
	
	public TransitionAdapter(PetriNet pn, String label) {
		super(label);
		transition = new Transition();
		try {
			pn.addTransition(transition);
		} catch (TransitionAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	public void addArc(ArcAdapter a) {
		try {
			transition.addArc(a.getModelObject());
		} catch (ArcAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	Transition getModelObject() {
		return this.transition;
	}

	public boolean isEnabled() {
		return this.transition.canFire();
	}
	
	public void fire() {
		this.transition.fire();
	}
	
}
