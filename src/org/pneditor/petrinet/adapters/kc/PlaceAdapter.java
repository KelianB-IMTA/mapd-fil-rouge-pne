package org.pneditor.petrinet.adapters.kc;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.kc.Place;
import org.pneditor.petrinet.models.kc.exceptions.NegativePlaceTokenException;

/**
 * Adapter for our implementation of Place
 */
public class PlaceAdapter extends AbstractPlace {
	/** The object that represents this Place in our model */
	private Place place;
	
	/** 
	 * Constructs a new Place
	 * @param label The place's label
	 */
	public PlaceAdapter(String label) {
		super(label);
		this.place = new Place();
	}

	/**
	 * Adds one token to this place
	 */
	@Override
	public void addToken() {
		this.place.addTokens(1);
	}

	/**
	 * Removes one token from this place
	 */
	@Override
	public void removeToken() {
		try {
			this.place.removeTokens(1);
		} catch (NegativePlaceTokenException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the number of tokens this place has
	 * @return This place's number of tokens
	 */
	@Override
	public int getTokens() {
		return place.getTokens();
	}

	/**
	 * Sets the number of tokens of this place
	 * @param tokens The number of tokens
	 */
	@Override
	public void setTokens(int tokens) {
		if(tokens > getTokens())
			place.addTokens(tokens - getTokens());
		else {
			try {
				place.removeTokens(tokens - getTokens());
			} catch (NegativePlaceTokenException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Gets the object associated with this Place in our model
	 * @return The object that represents this Place in our model
	 */
	Place getModelObject() {
		return this.place;
	}
}
