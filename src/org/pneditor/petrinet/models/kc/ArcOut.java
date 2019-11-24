package org.pneditor.petrinet.models.kc;

/**
 * Represents an arc going out from place.
 */
public class ArcOut extends Arc {
	/**
	 * Constructs an outgoing arc
	 * @param p a place to which the arc is linked 
	 */
	public ArcOut(Place p) {
		super(p);
	}
	
	/**
	 * Constructs an output arc with the given place and the given value
	 * @param p a place to which the arc is linked 
	 * @param val the value of the arc
	 * @throws NegativeArcValueException if we try to remove too many tokens
	 */
	public ArcOut(Place p, int val) throws NegativeArcValueException {
		super(p, val);
	}

	/**
	 * Removes the arc's value to its place's tokens if the arc is fireable
	 * @throws NegativePlaceTokenException if trying to remove too many tokens
	 */
	@Override
	public void fire() throws NegativePlaceTokenException {
		this.getPlace().removeTokens(getValue());
	}
	
	/**
	 * If the arc's value is inferior to the number of tokens in its place, the arc is fireable
	 * @return whether or not this arc can be fired
	 */
	@Override
	public boolean canFire() {
		return this.getValue() <= this.getPlace().getTokens();
	}
	
	/**
	 * Converts this arc into a readable string
	 * @return a string representing this arc 
	 */
	@Override
	public String toString() {
		return " | <--" + getValue() + "--- " + getPlace().getTokens();
	}
}
