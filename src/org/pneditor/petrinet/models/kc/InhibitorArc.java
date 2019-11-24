package org.pneditor.petrinet.models.kc;

/**
 * Inhibitor arcs are outgoing arcs that are only active when the linked place is empty.
 */
public class InhibitorArc extends ArcOut {	
	/**
	 * Represents an inhibitor arc going out from a place.
	 * @param p a place to which the arc is linked 
	 */
	public InhibitorArc(Place p) {
		super(p);
	}

	/**
	 * The arc is fireable if there aren't any tokens in the place
	 * @return a boolean 
	 */
	@Override
	public boolean canFire() {
		return getPlace().getTokens() == 0;
	}
	
	/**
	 * @return a string representing the arc 
	 */
	@Override
	public String toString() {
		return "�| <--" + getValue() + "--- " + getPlace().getTokens();
	}
	

	
	
}
