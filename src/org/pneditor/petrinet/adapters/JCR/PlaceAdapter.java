package org.pneditor.petrinet.adapters.JCR;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.JCR.PetriNet;
import org.pneditor.petrinet.models.JCR.Place;

public class PlaceAdapter extends AbstractPlace {
	private final Place place;
	private final PetriNet petriNet;

	public PlaceAdapter(Place place, PetriNet petriNet) {
		super(place.getName());
		this.petriNet = petriNet;
		this.place = place;
	}

	public void addToken() {
		this.petriNet.addTokens(this.place, 1);
	}

	public void removeToken() {
		this.petriNet.removeTokens(this.place, 1);
	}

	public int getTokens() {
		return this.petriNet.tokens(this.place);
	}

	public void setTokens(int tokens) {
		int difference = tokens - this.getTokens();
		if (difference > 0) {
			this.petriNet.addTokens(this.place, difference);
		} else {
			this.petriNet.removeTokens(this.place, -difference);
		}

	}

	Place getModelObject() {
		return this.place;
	}

	public String getLabel() {
		return this.place.getName();
	}

	public void setLabel(String label) {
		super.setLabel(label);
		if (this.place != null) {
			this.place.setName(label);
		}

	}
}