package org.pneditor.petrinet.models.kc;

/**
 * Represents an arc of a Petri Net.
 */
public abstract class Arc {
	
	/**
	 * The place which is linked to the arc
	 */
	private Place place;
	
	/**
	 * The value of the arc
	 */
	private int value;
	
	/**
	 * Constructs an arc with the given place
	 * @param p a place to which the arc is linked 
	 */
	public Arc(Place p) {
		this.place = p;
	}
	
	/**
	 * Constructs an arc with the given place and the given value
	 * @param p a place to which the arc is linked 
	 * @param val the value of the arc
	 * @throws NegativeArcValueException if the arc's value is negative
	 */
	public Arc(Place p, int val) throws NegativeArcValueException {
		this(p);
		
		if(val < 0)
			throw new NegativeArcValueException();

		this.value = val;
	}
	
	/**
	 * Constructs an arc with the given place
	 * @return a boolean 
	 */
	public abstract boolean canFire();
	
	/**
	 * Fire the tokens
	 * @throws NegativePlaceTokenException if we try to remove too much tokens from the place
	 */
	public abstract void fire() throws NegativePlaceTokenException;
	
	/**
	 * Sets the place to which this arc is linked
	 * @param place the new place to which the arc is linked
	 * @throws NullPointerException if the place is null
	 */
	public void setPlace(Place place) {
		if(place == null)
			throw new NullPointerException();
		this.place = place;
	}
	
	/**
	 * @return the place of the arc
	 */
	public Place getPlace() {
		return place;
	}
	
	/**
	 * Sets the value of the arc
	 * @param val the new value of the arc
	 * @throws NegativeArcValueException if the value of the arc is negative
	 */
	public void setValue(int val) throws NegativeArcValueException {
		if(val < 0)
			throw new NegativeArcValueException();
		this.value = val;
	}
	
	/**
	 * @return the value of the arc
	 */
	public int getValue() {
		return value;
	}
}
