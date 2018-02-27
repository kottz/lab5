package sim;
import supermarket.event.*;

/**
 * Mainmetod för att köra simulering
 *
 */
public class Main {

	private int numOfCashiers = 2;
	private int maximumCapacity = 5;
	private double lambda = 1.0; //ankomsthastighet
	private double openingHours; //Öppettider (tid S ur labspecen)
	
	private double minPickTime = 0.5;
	private double maxPickTime = 1.0;
	private double pickTime = (minPickTime+maxPickTime)/2;
	private double minPayTime = 2.0;
	private double maxPayTime = 3.0;
	private double payTime = (minPayTime+maxPayTime)/2;
	
	private long seed = 1234; //För randomobjektet
	
	
	EventQueue eq = new EventQueue();
	ettsupermarketState state = new ettsupermarketState(); //Denna konstruktor måste ta några parametrar
	emsupermarketView view = new ensupermarketView();
	state.addObserver(view);
	
	//Lägger till ett startEvent för att dra igång simuleringen.
	Event start = new StartEvent(); //TO-DO, konstruktorerna kommer ta några argument.
	Event close = new CloseEvent();
	Event stop = new StopEvent();
	eq.addEvent(start);
	eq.addEvent(close);
	eq.addEvent(stop);
	
	Simulator simulator = new Simulator(eq, state);
	
	public static void main(String[] args) {
		Simulator sim1 = new Simulator(eq, state);
		sim1.run();
	}
}
