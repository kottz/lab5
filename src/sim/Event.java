package sim;


/**
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 */
public abstract class Event {

	// Tidsvariabel
	protected double time;
	protected SortedSequence seq;
	
	// Utf�r n�got med hj�lp av sekvensen och ett state.
	public abstract void execute();
	
	// Getter f�r time
	public double getTime() {
		return this.time;
	}
	
}
