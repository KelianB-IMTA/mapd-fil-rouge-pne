package org.pneditor.petrinet.models.JCR;

public interface PetriNetInterface {
	Place addPlace(int var1);

	Place addPlace(String var1, int var2);

	boolean removePlace(Place var1);

	Transition addTransition();

	Transition addTransition(String var1);

	boolean removeTransition(Transition var1);

	void addPTEdge(Place var1, Transition var2, int var3);

	void addZeroPTEdge(Place var1, Transition var2);

	void addEmptyPTEdge(Place var1, Transition var2);

	void addTPEdge(Transition var1, Place var2, int var3);

	void trigger(Transition var1);
}