package sim;

/**
 * 
 * 
 * @author Olof Bourghardt, August Brunns�ter, Oskar Havo , Edward K�llstedt
 *
 * Simulator som k�r event i en EventQueue
 *
 */
public class Simulator {


	EventQueue eq;
	SortedSequence seq;
	SimulatorState state;
	
	public Simulator(EventQueue eq, SimulatorState state) {
		this.eq = eq;
		this.state = state;
		this.seq = new SortedSequence(eq);
	}
	
	/**
	 * Startar simulationen.
	 */
	public void run() {
		state.start();
		while(!eq.getArray().isEmpty() && state.isRunning) {	
			//System.out.println(eq.getArray().get(0).getTime()); //debug print
			//System.out.println(eq.getArray().toString());
			eq.nextEvent().execute();
		}
	}
	
}
