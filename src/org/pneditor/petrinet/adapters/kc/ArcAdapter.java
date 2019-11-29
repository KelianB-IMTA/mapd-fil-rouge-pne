package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.kc.Arc;
import org.pneditor.petrinet.models.kc.ArcIn;
import org.pneditor.petrinet.models.kc.ArcOut;
import org.pneditor.petrinet.models.kc.InhibitorArc;
import org.pneditor.petrinet.models.kc.ResetArc;
import org.pneditor.petrinet.models.kc.exceptions.NegativeArcValueException;

/**
 * Adapter for our implementation of Arcs
 */
public class ArcAdapter extends AbstractArc {
	/** The object that represents this arc in our model */
	private Arc arc;
	
	/** The type of this arc */
	private ArcType type;
	
	/** The place involved in this arc */
	private PlaceAdapter place;
	
	/** The transition involved in this arc */
	private TransitionAdapter transition;
	
	/*
	 * Creates an ArcAdapter
	 * @param pl The place involved in this arc
	 * @param tr The transition involved in this arc
	 * @param type The type of arc
	 */
	public ArcAdapter(PlaceAdapter pl, TransitionAdapter tr, ArcType type) {
		this.type = type;
		this.place = pl;
		this.transition = tr;
		try {
			if(type == ArcType.IN)
				this.arc = new ArcIn(pl.getModelObject(), 0);
			else if(type == ArcType.OUT)
				this.arc = new ArcOut(pl.getModelObject(), 0);
			else if(type == ArcType.INHIBITOR)
				this.arc = new InhibitorArc(pl.getModelObject());
			else if(type == ArcType.RESET)
				this.arc = new ResetArc(pl.getModelObject());
		} catch (NegativeArcValueException e) {
			e.printStackTrace();
		}
		this.transition.addArc(this);
	}
	
	/**
	 * @return The source node of this arc
	 */
	@Override
	public AbstractNode getSource() {
		return this.type == ArcType.IN ? transition : place;
	}

	/**
	 * @return The destination node of this arc
	 */
	@Override
	public AbstractNode getDestination() {
		return this.type == ArcType.IN ? place: transition;
	}

	/**
	 * @return Whether this arc is a Reset arc or not
	 */
	@Override
	public boolean isReset() {
		return this.type == ArcType.RESET;
	}

	/**
	 * @return Whether this arc is a regular arc or not
	 */
	@Override
	public boolean isRegular() {
		return this.type == ArcType.IN || this.type == ArcType.OUT;
	}

	/**
	 * @return Whether this arc is an Inhibitor arc or not
	 */
	@Override
	public boolean isInhibitory() {
		return this.type == ArcType.INHIBITOR;
	}

	/**
	 * @return The multiplicity (value) of this arc
	 * @throws ResetArcMultiplicityException If this arc is a Reset Arc
	 */
	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		if(this.type == ArcType.RESET)
			throw new ResetArcMultiplicityException();
		return arc.getValue();
	}

	/**
	 * Sets the multiplicity of this arc
	 * @param multiplicity The new multiplicity
	 */
	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if(this.type == ArcType.RESET)
			throw new ResetArcMultiplicityException();
		try {
			arc.setValue(multiplicity);
		} catch (NegativeArcValueException e) {
			e.printStackTrace();
		}
	}

	/** Gets the object associated with this Arc in our model
	 * @return The object that represents this Arc in our model
	 */
	Arc getModelObject() {
		return arc;
	}
	
}
