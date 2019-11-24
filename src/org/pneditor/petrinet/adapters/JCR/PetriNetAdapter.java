package org.pneditor.petrinet.adapters.JCR;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.JCR.PetriNet;

public class PetriNetAdapter extends PetriNetInterface {
	private final PetriNet petriNet = new PetriNet();
	
	public AbstractPlace addPlace() {
		return new PlaceAdapter(this.petriNet.addPlace(0), this.petriNet);
	}

	public AbstractTransition addTransition() {
		return new TransitionAdapter(this.petriNet.addTransition());
	}

	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) {
		if (source.isPlace()) {
			this.petriNet.addPTEdge(((PlaceAdapter) source).getModelObject(),
					((TransitionAdapter) destination).getModelObject(), 1);
			return new ArcAdapter(source, destination, EdgeType.OUT, this.petriNet);
		} else {
			this.petriNet.addTPEdge(((TransitionAdapter) source).getModelObject(),
					((PlaceAdapter) destination).getModelObject(), 1);
			return new ArcAdapter(source, destination, EdgeType.IN, this.petriNet);
		}
	}

	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) {
		this.petriNet.addZeroPTEdge(((PlaceAdapter) place).getModelObject(),
				((TransitionAdapter) transition).getModelObject());
		return new ArcAdapter(place, transition, EdgeType.ZERO, this.petriNet);
	}

	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) {
		this.petriNet.addEmptyPTEdge(((PlaceAdapter) place).getModelObject(),
				((TransitionAdapter) transition).getModelObject());
		return new ArcAdapter(place, transition, EdgeType.EMPTY, this.petriNet);
	}

	public void removeArc(AbstractArc arc) {
		((ArcAdapter) arc).removeArc();
	}

	public void removePlace(AbstractPlace place) {
		this.petriNet.removePlace(((PlaceAdapter) place).getModelObject());
	}

	public void removeTransition(AbstractTransition transition) {
		this.petriNet.removeTransition(((TransitionAdapter) transition).getModelObject());
	}

	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		return ((TransitionAdapter) transition).getModelObject().isTriggerable();
	}

	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		this.petriNet.trigger(((TransitionAdapter) transition).getModelObject());
	}
}

