package sim;

public class Main {

	EventQueue eventQ = new EventQueue();
	Simulator simulator = new Simulator(eventQ);
	
	public static void main(String[] args) {
		
		simulator.simulate();
	}
}
