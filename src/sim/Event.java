package sim;


/**
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 */
public abstract class Event {

	// Tidsvariabel
	protected double time;
	protected SortedSequence seq;
	
	// Utför något med hjälp av sekvensen och ett state.
	public abstract void execute();
	
	// Getter för time
	public double getTime() {
		return this.time;
	}
	
}
