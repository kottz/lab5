package sim;

import java.util.ArrayList;
/**
 * 
 * @author Oskar
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
	 * Returnerar det nästa eventet och tar sedan bort det från listan.
	 * 
	 * @return
	 */
	public Event nextEvent() {
		Event temp = this.eventQueue.get(0);
		this.eventQueue.remove(0);
		return temp;
	}

	/**
	 * Lägger till ett event i listan. Anropas i SortedSequence.
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		this.eventQueue.add(event);

	}
}
