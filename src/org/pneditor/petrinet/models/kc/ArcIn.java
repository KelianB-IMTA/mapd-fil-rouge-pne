package org.pneditor.petrinet.models.kc;

import org.pneditor.petrinet.models.kc.exceptions.NegativeArcValueException;

/**
 * Represents an arc going into a place.
 */
public class ArcIn extends Arc {

	/**
	 * Constructs an input arc with the given place and the given value
	 * @param p a place to which the arc is linked 
	 * @param val the value of the arc
	 * @throws NegativeArcValueException if the arc's value is negative
	 */
	public ArcIn(Place p, int val) throws NegativeArcValueException {
		super(p, val);
	}
	
	/**
	 * Adds the arc's value to its place's tokens
	 */
	@Override
	public void fire() {
		this.getPlace().addTokens(getValue());
	}
	
	/**
	 * The arc is always fireable since it enters the place
	 * @return true
	 */
	@Override
	public boolean canFire() {
		return true;
	}
	
	/**
	 * @return a string representing the arc 
	 */
	@Override
	public String toString() {
		return " | --" + getValue() + "---> " + getPlace().getTokens();
	}
}
