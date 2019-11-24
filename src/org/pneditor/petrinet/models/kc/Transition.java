package org.pneditor.petrinet.models.kc;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a transition in a Petri Net. Transitions contain input and output arcs and can be fired.
 */
public class Transition {
	/**
	 * The arcs that are linked to this transition
	 */
	private List<Arc> arcs;
	
	/**
	 * Constructs a transition with no arcs
	 */
	public Transition() {
		this.arcs = new ArrayList<Arc>();
	}

	/**
	 * Constructs a transition with the given arcs
	 * @param arcs the arcs to add to the new transition
	 * @throws ArcAlreadyExistsException if one of the arcs is added twice
	 */
	public Transition(Arc... arcs) throws ArcAlreadyExistsException {
		this();
		for(int i = 0; i < arcs.length; i++)
			this.addArc(arcs[i]);
	}
	
	/**
	 * Gets the arcs that are linked to this transition
	 * @return the arcs
	 */
	public List<Arc> getArcs() {
		return arcs;
	}
	
	/**
	 * Adds an arc to the transition
	 * @param a an arc to add
	 * @throws ArcAlreadyExistsException if the new arc was already added to this transition
	 */
	public void addArc(Arc a) throws ArcAlreadyExistsException {
		if(getArcs().contains(a))
			throw new ArcAlreadyExistsException();
		if(a == null)
			throw new NullPointerException();
		getArcs().add(a);
	}
	
	/**
	 * Removes an arc from this transition
	 * @param a the arc to remove
	 * @return whether or not the arc was successfully removed
	 */
	public boolean removeArc(Arc a) {
		return getArcs().remove(a);
	}
	
	/**
	 * Checks whether this transition can be fired or not
	 * @return whether or not this transition can be fired
	 */
	public boolean canFire() {
		int i = getArcs().size() - 1;
		while(i >= 0  && getArcs().get(i).canFire()) {
			i--;
		}
		return i == -1;
	}
	
	/**
	 * Fires the transition
	 */
	public void fire() {
		if(canFire()) {
			try {				
				for(int i = 0; i < getArcs().size(); i++) {
					getArcs().get(i).fire();
				}
			}
			catch(NegativePlaceTokenException e) {
				// Shouldn't append because we made sure we can fire this transition
				e.printStackTrace();
			}
		}
	}
	
	/** 
	 * Converts this transition into a readable String.
	 * @return a readable representation of this transition
	 */
	@Override
	public String toString() {
		String str = "";
		for(int i = 0; i < getArcs().size(); i++) {
			str += getArcs().get(i).toString() + "\n";
		}
		return str;
	}
}
