package mainSate;

import java.util.Observable;

public abstract class SimulatorStateADT extends Observable {
	
	boolean started = false;
	int calculatedTime = 0;
	
	abstract protected void start();
	
	abstract protected void stop();
	
	abstract int calculateTime();
}
