package sim;
import java.util.Observer;
import java.util.Observable;
/**
 * 
 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
 *
 */
public abstract class SimView implements Observer {

	public abstract void update(Observable o, Object arg);
	
}
