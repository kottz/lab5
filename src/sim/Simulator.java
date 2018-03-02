package sim;

/**
 * Simulator som kör event i en EventQueue
 *
 */
public class Simulator {


	EventQueue eq;
	SortedSequence seq;
	SimulatorStateADT state;
	
	public Simulator(EventQueue eq, SimulatorStateADT state) {
		this.eq = eq;
		this.state = state;
		this.seq = new SortedSequence(eq);
	}
	
	
	public void run() {
		state.start();
		while(!eq.getArray().isEmpty() && state.isRunning) {	
			//System.out.println(eq.getArray().get(0).getTime()); //debug print
			//System.out.println(eq.getArray().toString());
			eq.nextEvent().execute();
		}
	}
	
}
