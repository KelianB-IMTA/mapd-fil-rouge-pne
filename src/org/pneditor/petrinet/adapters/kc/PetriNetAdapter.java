package org.pneditor.petrinet.adapters.kc;

import java.util.ArrayList;
import java.util.List;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.kc.Arc;
import org.pneditor.petrinet.models.kc.PetriNet;
import org.pneditor.petrinet.models.kc.Place;

public class PetriNetAdapter extends PetriNetInterface {
	private final PetriNet petriNet = new PetriNet();
	private int nbPlaces = 0, nbTransitions = 0;
	
	@Override
	public AbstractPlace addPlace() {
		return new PlaceAdapter("place#" + (nbPlaces++));
	}

	@Override
	public AbstractTransition addTransition() {
		return new TransitionAdapter(petriNet, "transition#" + (nbTransitions++));
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if(source instanceof AbstractTransition && destination instanceof AbstractTransition)
			throw new UnimplementedCaseException("Cannot create an arc between two transitions.");
		if(source instanceof AbstractPlace && destination instanceof AbstractPlace)
			throw new UnimplementedCaseException("Cannot create an arc between two places.");
		
		// Find out which node is a transition
		AbstractTransition t = (AbstractTransition) (source instanceof AbstractTransition ? source : destination);
		TransitionAdapter transition = (TransitionAdapter) t;
		// Find out which node is a source
		AbstractPlace p = (AbstractPlace) (source instanceof AbstractPlace ? source : destination);
		PlaceAdapter place = (PlaceAdapter) p;
				
		// In our model, "IN" and "OUT" are defined relative to places
		return new ArcAdapter(place, transition, source.isPlace() ? ArcType.IN : ArcType.OUT);
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		return new ArcAdapter((PlaceAdapter) place, (TransitionAdapter) transition, ArcType.INHIBITOR);
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		return new ArcAdapter((PlaceAdapter) place, (TransitionAdapter) transition, ArcType.RESET);
	}

	@Override
	public void removePlace(AbstractPlace place) {
		this.petriNet.removePlace(((PlaceAdapter) place).getModelObject());
		
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArc(AbstractArc arc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}
	
}

