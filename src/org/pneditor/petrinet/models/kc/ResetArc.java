package org.pneditor.petrinet.models.kc;

/**
 * Reset arcs are outgoing arcs that are only active when the linked place contains at least one token.
 * Their place is emptied upon firing.
 */
public class ResetArc extends ArcOut {
	/**
	 * Represents a reset arc going out from a place.
	 * @param p a place to which the arc is linked 
	 */
	public ResetArc(Place p) {
		super(p);
	}
	
	/**
	 * The arc is fireable if there is more than one token in the place
	 * @return a boolean 
	 */
	@Override
	public boolean canFire() {
		return getPlace().getTokens() >= 1;
	}
	
	/**
	 * Removes place's tokens
	 */
	@Override
	public void fire() {
		getPlace().empty();
	}
	
}
