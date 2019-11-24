package org.pneditor.petrinet.adapters.JCR;


import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.JCR.Transition;

public class TransitionAdapter extends AbstractTransition {
	private final Transition transition;

	public TransitionAdapter(Transition transition) {
		super(transition.getName());
		this.transition = transition;
	}

	Transition getModelObject() {
		return this.transition;
	}

	public String getLabel() {
		return this.transition.getName();
	}

	public void setLabel(String label) {
		super.setLabel(label);
		if (this.transition != null) {
			this.transition.setName(label);
		}

	}
}