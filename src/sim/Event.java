package sim;


/**
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 */
public abstract class Event {

	// Tidsvariabel
	public double time;

	// Utför något med hjälp av sekvensen och ett state.
	public abstract void execute();
	
	public abstract double getTime();
	
}
