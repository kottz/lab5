package sim;

import java.util.Observable;

public abstract class SimulatorStateADT extends Observable {
	
	protected boolean started = false;
	protected double calculatedTime = 0;
	
	abstract protected void start();
	
	abstract protected void stop();
}
