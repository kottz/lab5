package sim;

import java.util.Observable;

public abstract class SimulatorStateADT extends Observable {
	
	protected boolean started = false;
	protected int calculatedTime = 0;
	
	abstract protected void start();
	
	abstract protected void stop();
	
	protected abstract int calculateTime();
}
