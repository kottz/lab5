package sim;
import java.util.Observer;
import java.util.Observable;
/**
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 */
public abstract class SimView implements Observer {

	public abstract void update(Observable o, Object arg);
	
}
