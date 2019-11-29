package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.kc.PetriNet;

/**
 * Adapter for our implementation of PetriNet
 */
public class PetriNetAdapter extends PetriNetInterface {
	/** The object that represents this PetriNet in our model */
	private final PetriNet petriNet = new PetriNet();
	
	/** Variables used to keep in the number of places in our net for naming purposes */
	private int nbPlaces = 0;
	
	/**
	 * Adds a new place to the net
	 * @return The new place
	 */
	@Override
	public AbstractPlace addPlace() {
		return new PlaceAdapter("place#" + (nbPlaces++));
	}

	/**
	 * Adds a new transition to the net
	 * @return The new transition
	 */
	@Override
	public AbstractTransition addTransition() {
		return new TransitionAdapter(petriNet, "transition#" + petriNet.getTransitions().size());
	}

	/**
	 * Adds a regular arc between a given source and destination
	 * @param source The arc's source
	 * @param destination The arc's destination
	 * @throws UnimplementedCaseException e.g. if trying to add an arc between two places or two transitions
	 */
	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if(source instanceof AbstractTransition && destination instanceof AbstractTransition)
			throw new UnimplementedCaseException("Cannot create an arc between two transitions.");
		if(source instanceof AbstractPlace && destination instanceof AbstractPlace)
			throw new UnimplementedCaseException("Cannot create an arc between two places.");
		
		// Find out which node is a transition
		AbstractTransition t = (AbstractTransition) (source instanceof AbstractTransition ? source : destination);
		TransitionAdapter transition = (TransitionAdapter) t;
		// Find out which node is a place
		AbstractPlace p = (AbstractPlace) (source instanceof AbstractPlace ? source : destination);
		PlaceAdapter place = (PlaceAdapter) p;
				
		// In our model, "IN" and "OUT" are defined relative to places
		return new ArcAdapter(place, transition, source.isPlace() ? ArcType.OUT : ArcType.IN);
	}

	/**
	 * Adds an inhibitor arc between a given source and destination
	 * @param source The arc's source
	 * @param destination The arc's destination
	 * @throws UnimplementedCaseException e.g. if trying to add an arc between two places or two transitions
	 */
	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		return new ArcAdapter((PlaceAdapter) place, (TransitionAdapter) transition, ArcType.INHIBITOR);
	}

	/**
	 * Adds a reset arc between a given source and destination
	 * @param source The arc's source
	 * @param destination The arc's destination
	 * @throws UnimplementedCaseException e.g. if trying to add an arc between two places or two transitions
	 */
	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		return new ArcAdapter((PlaceAdapter) place, (TransitionAdapter) transition, ArcType.RESET);
	}

	/**
	 * Removes a given place
	 * @param place The place to remove
	 */
	@Override
	public void removePlace(AbstractPlace place) {
		this.petriNet.removePlace(((PlaceAdapter) place).getModelObject());	
	}

	/**
	 * Removes a given transition
	 * @param place The transition to remove
	 */
	@Override
	public void removeTransition(AbstractTransition transition) {
		this.petriNet.removeTransition(((TransitionAdapter) transition).getModelObject());		
	}

	/**
	 * Removes a given arc
	 * @param arc The arc to remove
	 */
	@Override
	public void removeArc(AbstractArc arc) {
		this.petriNet.removeArc(((ArcAdapter) arc).getModelObject());		
	}

	/**
	 * Checks whether or not a transition is enabled
	 * @param transition The transition to test
	 */
	@Override
	public boolean isEnabled(AbstractTransition transition) {
		return ((TransitionAdapter) transition).isEnabled();
	}
	
	/**
	 * Fires a given transition
	 * @param transition The transition to fire
	 */
	@Override
	public void fire(AbstractTransition transition) {
		((TransitionAdapter) transition).fire();
	}	
}

