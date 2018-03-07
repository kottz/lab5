package sim;
/**
 * 
 * Klassen tar en EventQueue som dess parameter och sedan sorterar den efter
 * tid. D�r tidigast �r f�rst osv.
 * 
 *
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
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
		// Listan tom? L�gg bara till eventet
		if (this.eq.getArray().size() == 0) {
			this.eq.getArray().add(e);
		} else {
			//Fler en ett element? J�mf�r dom enligt tid.
			for (int i = 0; i < this.eq.getArray().size(); i++) {

				// J�mf�r tiden hos inparametern med tiden hos listans index i.
				if ((e.getTime()) < this.eq.getArray().get(i).getTime()) {
					this.eq.getArray().add(i, e);
					break;
					
					// I slutet listan ? D� �r elementet st�rst, l�gg till det.
				} else if (i == this.eq.getArray().size() - 1) { //
					this.eq.getArray().add(e);
					break;
				}
			}

		}

	}
}
