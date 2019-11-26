package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.kc.Arc;
import org.pneditor.petrinet.models.kc.ArcIn;
import org.pneditor.petrinet.models.kc.ArcOut;
import org.pneditor.petrinet.models.kc.InhibitorArc;
import org.pneditor.petrinet.models.kc.NegativeArcValueException;
import org.pneditor.petrinet.models.kc.ResetArc;

public class ArcAdapter extends AbstractArc {
	private Arc arc;
	private ArcType type;
	private PlaceAdapter place;
	private TransitionAdapter transition;
	
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
	
	@Override
	public AbstractNode getSource() {
		return this.type == ArcType.IN ? transition : place;
	}

	@Override
	public AbstractNode getDestination() {
		return this.type == ArcType.IN ? place: transition;
	}

	@Override
	public boolean isReset() {
		return this.type == ArcType.RESET;
	}

	@Override
	public boolean isRegular() {
		return this.type == ArcType.IN || this.type == ArcType.OUT;
	}

	@Override
	public boolean isInhibitory() {
		return this.type == ArcType.INHIBITOR;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		if(this.type == ArcType.RESET)
			throw new ResetArcMultiplicityException();
		return arc.getValue();
	}

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

	Arc getModelObject() {
		return arc;
	}
	
}
