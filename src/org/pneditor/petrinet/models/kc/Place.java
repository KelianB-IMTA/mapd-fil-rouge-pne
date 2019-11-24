package org.pneditor.petrinet.models.kc;

/**
 * Represents a place in a Petri Net. Places contain a token value, which can increase or decrease when adjacent transitions are fired.
 */
public class Place {
	/**
	 * The current amount of tokens in this place
	 */
	private int tokens;
	
	/**
	 * Constructs a place with no token
	 */
	public Place() {
		this(0);
	}
	
	/**
	 * Contructs a place with a given amount of tokens
	 * @param tokens the amount of tokens to initiate this place with
	 */
	public Place(int tokens) {
		this.tokens = tokens;
	}
	
	/**
	 * Sets the amount of tokens in this place
	 * @param tokens the new amount of tokens
	 * @throws NegativePlaceTokenException if the new value is negative
	 */
	private void setTokens(int tokens) throws NegativePlaceTokenException {
		if(tokens < 0)
			throw new NegativePlaceTokenException();
		this.tokens = tokens;
	}
	
	/**
	 * Gets the amount of tokens in this place
	 * @return the tokens
	 */
	public int getTokens() {
		return tokens;
	}
	
	/**
	 * Adds a given amount of tokens to this place
	 * @param tokens the amount of tokens to add
	 * @throws IllegalArgumentException if trying to add a negative quantity of tokens
	 */
	public void addTokens(int tokens) {
		if(tokens < 0)
			new IllegalArgumentException("Cannot add a negative amount of tokens.");
		try {
			this.setTokens(getTokens() + tokens);
		} catch (NegativePlaceTokenException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Removes a given amount of tokens from this place
	 * @param tokens the amount of tokens to remove
	 * @throws NegativePlaceTokenException if this place will now have a negative quantity of tokens
	 * @throws IllegalArgumentException if trying to remove a negative quantity of tokens
	 */
	public void removeTokens(int tokens) throws NegativePlaceTokenException {
		if(tokens < 0)
			new IllegalArgumentException("Cannot remove a negative amount of tokens.");
		this.setTokens(this.getTokens() - tokens);
	}
	
	/**
	 * Removes all the tokens from this place.
	 */
	public void empty() {
		try {
			this.setTokens(0);
		} catch (NegativePlaceTokenException e) {
			e.printStackTrace();
		}
	}
}
