package sim;

import java.util.ArrayList;
/**
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 */

public class EventQueue {

	public ArrayList<Event> eventQueue = new ArrayList<Event>();

	/**
	 * Returnerar arrayen.
	 * 
	 * @return
	 */
	public ArrayList<Event> getArray() {
		return this.eventQueue;

	}

	/**
	 * Returnerar det n�sta eventet och tar sedan bort det fr�n listan.
	 * 
	 * @return
	 */
	public Event nextEvent() {
		Event temp = this.eventQueue.get(0);
		this.eventQueue.remove(0);
		return temp;
	}

	/**
	 * L�gger till ett event i listan. Anropas i SortedSequence.
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		this.eventQueue.add(event);

	}
}
