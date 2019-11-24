package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.kc.NegativePlaceTokenException;
import org.pneditor.petrinet.models.kc.Place;

public class PlaceAdapter extends AbstractPlace {
	private Place place;
	
	public PlaceAdapter(String label) {
		super(label);
		this.place = new Place();
	}

	@Override
	public void addToken() {
		this.place.addTokens(1);
	}

	@Override
	public void removeToken() {
		try {
			this.place.removeTokens(1);
		} catch (NegativePlaceTokenException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getTokens() {
		return place.getTokens();
	}

	@Override
	public void setTokens(int tokens) {
		if(tokens > getTokens())
			place.addTokens(tokens - getTokens());
		else {
			try {
				place.removeTokens(tokens - getTokens());
			} catch (NegativePlaceTokenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	Place getModelObject() {
		return this.place;
	}
}
