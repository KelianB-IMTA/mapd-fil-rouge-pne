package org.pneditor.petrinet.models.kc;

import org.pneditor.petrinet.models.kc.exceptions.ArcAlreadyExistsException;
import org.pneditor.petrinet.models.kc.exceptions.NegativeArcValueException;
import org.pneditor.petrinet.models.kc.exceptions.TransitionAlreadyExistsException;

/**
 * Contains a few non-exhaustive tests for our implementation
 */
public class Tests {
	public static void main(String[] args) {
		try {
			
			Place pA = new Place(10);
			Place pB = new Place(5);
			Place pC = new Place(0);
			Place pD = new Place(2);
			
			Arc a1 = new ResetArc(pA);		
			Arc a2 = new ArcOut(pB, 4);
			Arc a3 = new ArcIn(pC, 5);
			Arc a4 = new ArcIn(pC, 6);
			Arc a5 = new ArcIn(pB, 2);
			Arc a6 = new ArcOut(pD, 2);
			Arc a7 = new ArcOut(pD, 2);
	
			Transition t1 = new Transition(a1, a3, a2);
			Transition t2 = new Transition(a5, a6);
			Transition t3 = new Transition(a7, a4);
			
			PetriNet net = new PetriNet(t1, t2, t3);
			
			System.out.println(net.toString());
			System.out.println("-----------------------");
			t1.fire();
			System.out.println(net.toString());		
			
			// #############################
			
			Place pE = new Place(0);
			Place pF = new Place(0);
			
			Arc a8 = new ArcOut(pE, 1);
			Arc a9 = new InhibitorArc(pE);
			Arc a10 = new ArcIn(pE, 10);
			Arc a11 = new ArcIn(pF, 1);
			
			Transition t4 = new Transition(a9, a10, a11);
			Transition t5 = new Transition(a8);
		
			PetriNet net2 = new PetriNet(t4, t5);
		
			for(int i = 0; i < 30; i++) {
				System.out.println(net2.getTransitions().get(0).getArcs().get(0).getPlace().getTokens());
				net.fireAll();
			}
		}
		catch(NegativeArcValueException | ArcAlreadyExistsException | TransitionAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
}