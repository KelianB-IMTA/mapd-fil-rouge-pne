package org.pneditor.petrinet.adapters.JCR;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.JCR.PetriNet;

public class ArcAdapter extends AbstractArc {
	private final AbstractNode from;
	private final AbstractNode to;
	private final EdgeType type;
	private int mult = 1;
	private final PetriNet petriNet;

	public ArcAdapter(AbstractNode from, AbstractNode to, EdgeType type, PetriNet petriNet) {
		this.from = from;
		this.to = to;
		this.type = type;
		this.petriNet = petriNet;
	}

	public boolean isReset() {
		return this.type == EdgeType.EMPTY;
	}

	public boolean isRegular() {
		return !this.isReset() && !this.isInhibitory();
	}

	public boolean isInhibitory() {
		return this.type == EdgeType.ZERO;
	}

	public int getMultiplicity() throws ResetArcMultiplicityException {
		return this.mult;
	}

	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if (this.type == EdgeType.EMPTY) {
			throw new ResetArcMultiplicityException();
		} else {
			this.removeArc();
			if (this.type == EdgeType.IN) {
				this.mult = multiplicity;
				this.petriNet.addTPEdge(((TransitionAdapter) this.from).getModelObject(),
						((PlaceAdapter) this.to).getModelObject(), multiplicity);
			}

			if (this.type == EdgeType.OUT) {
				this.mult = multiplicity;
				this.petriNet.addPTEdge(((PlaceAdapter) this.from).getModelObject(),
						((TransitionAdapter) this.to).getModelObject(), multiplicity);
			}

		}
	}

	public AbstractNode getSource() {
		return this.from;
	}

	public AbstractNode getDestination() {
		return this.to;
	}

	void removeArc() {
		if (this.from.isPlace()) {
			this.petriNet.deleteEdge(((TransitionAdapter) this.to).getModelObject(),
					((PlaceAdapter) this.from).getModelObject());
		} else {
			this.petriNet.deleteEdge(((TransitionAdapter) this.from).getModelObject(),
					((PlaceAdapter) this.to).getModelObject());
		}

	}
}