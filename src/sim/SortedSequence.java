package sim;
import supermarket.event.*;
/**
 * 
 * Klassen tar en EventQueue som dess parameter och sedan sorterar den efter
 * tid. Där tidigast är först osv.
 * 
 * @author Oskar
 *
 */
public class SortedSequence {
	EventQueue eq;

	/**
	 * Konstruktor
	 * 
	 * @param eq
	 */
	public SortedSequence(EventQueue eq) {
		this.eq = eq;
	}

	/**
	 * Bygger upp en EventQueue, sorterad efter tiden.
	 * 
	 * @param e
	 */
	public void sortEventQueue(Event e) {
		// Basfall
		if (this.eq.getArray().size() == 0) {
			this.eq.getArray().add(e);
		} else {
			for (int i = 0; i < this.eq.getArray().size(); i++) {

				// Jämför tiden hos inparametern med tiden hos listans index i.
				if ((e.getTime()) < this.eq.getArray().get(i).getTime()) {
					this.eq.getArray().add(i, e);
					break;

				} else if (i == this.eq.getArray().size() - 1) { // Slutvillkor.
					this.eq.getArray().add(e);
					break;
				}
			}

		}

	}
}
