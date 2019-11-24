package org.pneditor.petrinet.models.JCR;

// TODO transformer en  test 
import logger.PNEditorLogger;

public class SimplePNTest {
	public static void main(String[] args) {
		pn1P1T();
		simple1P1T();
		simple2P1T();
		//pn1P1TremoveP();
		//pn1P1TremoveT();
	}

	public static void pn1P1T() {
		//PNEditorLogger.infoLogs("--- 1P1T");
		PetriNetInterface pn = new PetriNet();
		Place p1 = pn.addPlace(1);
		Transition t1 = pn.addTransition();
		pn.addTPEdge(t1, p1, 1);
		System.out.println(" p1.tokens() == 1 " + (p1.tokens() == 1));
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (1)");
		//PNEditorLogger.infoLogs("Trigger t1");
		pn.trigger(t1);
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (2)");
		System.out.println(" p1.tokens() == 2 " + (p1.tokens() == 2));
		//PNEditorLogger.infoLogs("Trigger t1");
		pn.trigger(t1);
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (3)");
		System.out.println(" p1.tokens() == 3 " + (p1.tokens() == 3));
	}

	public static void simple1P1T() {
		//PNEditorLogger.infoLogs("--- simple1P1T");
		Place p1 = new Place(1);
		Transition t1 = new Transition();
		t1.addEdgeTo(p1, 1);
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (1)");
		System.out.println(" p1.tokens() == 1 " + (p1.tokens() == 1));
		//PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (2)");
		System.out.println(" p1.tokens() == 2 " + (p1.tokens() == 2));
		//PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (3)");
		System.out.println(" p1.tokens() == 3 " + (p1.tokens() == 3));
	}

	public static void simple2P1T() {
		//PNEditorLogger.infoLogs("--- simple2P1T");
		Place p1 = new Place(1);
		Place p2 = new Place();
		Transition t1 = new Transition();
		p1.addEdgeTo(t1, 1);
		t1.addEdgeTo(p2, 2);
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (1, 0)");
		System.out.println("(p1.tokens() == 1)  (p2.tokens() == 0) " + (p1.tokens() == 1) + " " + (p2.tokens() == 0));
		//PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (0, 2)");
		System.out.println("(p1.tokens() == 0)  (p2.tokens() == 2) " + (p1.tokens() == 0) + " " + (p2.tokens() == 2));
		t1.trigger();
		//PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		//PNEditorLogger.infoLogs("Expected Marking: (0, 2)");
		System.out.println("(p1.tokens() == 0)  (p2.tokens() == 2) " + (p1.tokens() == 0) + " " + (p2.tokens() == 2));
	}

	public static void simple2P2T() {
		PNEditorLogger.infoLogs("--- simple2P2T");
		Place p1 = new Place(4);
		Place p2 = new Place();
		Transition t1 = new Transition();
		Transition t2 = new Transition();
		p1.addEdgeTo(t1, 1);
		t1.addEdgeTo(p2, 2);
		p1.addEdgeTo(t2, 2);
		t2.addEdgeTo(p2, 1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (4, 0)");
		PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (3, 2)");
		t2.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (1, 3)");
	}

	public static void simple4P2T() {
		PNEditorLogger.infoLogs("--- simple2P2T");
		Place p1 = new Place(4);
		Place p2 = new Place(2);
		Place p3 = new Place(1);
		Place p4 = new Place(0);
		Transition t1 = new Transition();
		Transition t2 = new Transition();
		p1.addEdgeTo(t1, 1);
		t1.addEdgeTo(p3, 1);
		t1.addEdgeTo(p4, 2);
		p1.addEdgeTo(t2, 2);
		p2.addEdgeTo(t2, 1);
		t2.addEdgeTo(p4, 1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ", " + p3.tokens() + ", "
				+ p4.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (4, 2, 1, 0)");
		PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ", " + p3.tokens() + ", "
				+ p4.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (3, 2, 2, 2)");
		PNEditorLogger.infoLogs("Trigger t2");
		t2.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ", " + p3.tokens() + ", "
				+ p4.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (1, 1, 2, 3)");
		PNEditorLogger.infoLogs("Trigger t2 (again) - no effect");
		t2.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ", " + p3.tokens() + ", "
				+ p4.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (1, 1, 2, 3)");
	}

	public static void simple2P1ZT() {
		PNEditorLogger.infoLogs("--- simple2P1ZT");
		Place p1 = new Place(0);
		Place p2 = new Place();
		Transition t1 = new Transition();
		p1.addZeroEdgeTo(t1);
		t1.addEdgeTo(p2, 2);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (0, 0)");
		PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (0, 2)");
		t1.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (0, 4)");
	}

	public static void simple2P1ET() {
		PNEditorLogger.infoLogs("--- simple2P1ET");
		Place p1 = new Place(10);
		Place p2 = new Place();
		Transition t1 = new Transition();
		p1.addEmptyEdgeTo(t1);
		t1.addEdgeTo(p2, 2);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (10, 0)");
		PNEditorLogger.infoLogs("Trigger t1");
		t1.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (0, 2)");
		t1.trigger();
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ", " + p2.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (0, 2)");
	}

	public static void pn1P1TremoveP() {
		PNEditorLogger.infoLogs("--- pn1P1TremoveP");
		PetriNetInterface pn = new PetriNet();
		Place p1 = pn.addPlace(1);
		Transition t1 = pn.addTransition();
		pn.addTPEdge(t1, p1, 1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (1)");
		PNEditorLogger.infoLogs("Trigger t1");
		pn.trigger(t1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (2)");
		PNEditorLogger.infoLogs("Trigger t1");
		pn.removePlace(p1);
		pn.trigger(t1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (2)");
	}

	public static void pn1P1TremoveT() {
		PNEditorLogger.infoLogs("--- pn1P1TremoveT");
		PetriNetInterface pn = new PetriNet();
		Place p1 = pn.addPlace(1);
		Transition t1 = pn.addTransition();
		pn.addTPEdge(t1, p1, 1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (1)");
		PNEditorLogger.infoLogs("Trigger t1");
		pn.trigger(t1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (2)");
		PNEditorLogger.infoLogs("Trigger t1");
		pn.removeTransition(t1);
		pn.trigger(t1);
		PNEditorLogger.infoLogs("Computed Marking: (" + p1.tokens() + ")");
		PNEditorLogger.infoLogs("Expected Marking: (2)");
	}
}