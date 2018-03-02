package sim;
import java.util.Observer;
import java.util.Observable;

public abstract class SimView implements Observer {

	public abstract void update(Observable o, Object arg);
	
}
