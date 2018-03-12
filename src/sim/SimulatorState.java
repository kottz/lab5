package sim;

import java.util.Observable;
/**
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 */
public abstract class SimulatorState extends Observable {
	
	protected boolean isRunning = false;
	protected double calculatedTime = 0;

	abstract protected void start();
	
	abstract protected void stop();
}
