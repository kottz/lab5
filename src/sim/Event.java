package sim;


/**
 * 
 * @author Oskar
 *
 */
public abstract class Event {

	// Tidsvariabel
	public double time;

	// Utf�r n�got med hj�lp av sekvensen och ett state.
	public abstract void execute();
	
	public abstract double getTime();
	
}
