package supermarket;
import sim.SimView;
import java.util.Observable;
import supermarket.event.*;

public class ShoppingView extends SimView{

	private ShoppingState state;
	private boolean startFlag = true;
	
	
	/**
	 * 
	 * @author Olof Bourghardt, August Brunnsäter, Oskar Havo , Edward Källstedt
	 *
	 */
	
	public ShoppingView(ShoppingState state) {
		this.state = state;
	}
	
	
	 /**
	  * @param o,arg
	  */
	public void update(Observable o, Object arg) {
		//Printas i början
		if(startFlag) {
			System.out.printf("Parametrar\n========\n"
					+ "Antal Kassor, N:\t%s\n"
					+ "Max som ryms, M:\t%s\n"
					+ "Ankomsthastighet, lamba:\t%s\n"
					+ "Plocktider, [P_min..Pmax]:\t[%s..%s]\n"
					+ "Betaltider, [K_min..Kmax]:\t[%s..%s]\n"
					+ "Frö, f:\t%s\n"
					+ "\nFÖRLOPP\n=====\n"
					+ "Tid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]\n",
					state.getNumberOfCustomers(),
					state.getMaxCustomers(),
					state.getLambda(),
					state.getPmin(),state.getPmax(),
					state.getKmin(),state.getKmax(),
					state.getSeed());
			
			startFlag = false;
		}
		
		//Start & Stop
		if(state.getCurrentEvent() instanceof StartEvent || state.getCurrentEvent() instanceof StopEvent) {
			System.out.printf("%.0f\t%s\n", state.getCurrentTime(), state.getCurrentEvent().toString());
			
		} else { //Alla andra event
			
			System.out.printf("%.2f\t%s\t\t%s\t%s\t%s\t%.2f\t%s\t%s\t%s\t%s\t%.2f\t%s\t%s\n",
					state.getCurrentTime(),
					state.getCurrentEvent().toString(),
					state.getCurrentEvent() instanceof CloseEvent ? "---": state.getCurrentCustomer().getId(),
					state.isOpen() ? "Ö":"S",
					state.idleCheckouts,
					state.timeCheckoutsHaveBeenIdle,
					state.getNumberOfCustomers(),
					state.getCompletedCheckouts(),
					state.missedCustomers,
					state.queue.totalEntries(),
					state.getTotalQueueTime(),
					state.queue.size(),
					state.queue.toString());
		}
	
		//Resultatutskrifter
		if(!state.isRunning()) {
			System.out.println("\nRESULTAT\n======\n");
			// 1)
			System.out.printf("1) Av %s kunder handlade %s medan %s missades.\n\n",
					state.factory.lastCustomerId(),
					state.factory.lastCustomerId()-state.missedCustomers,
					state.missedCustomers);
			// 2)
			System.out.printf("2) Total tid %s kassor varit lediga: %.2f te.\n",
					state.numberOfCheckouts,
					state.timeCheckoutsHaveBeenIdle);
			// 2b)
			System.out.printf("   Genomsnittlig ledig kassatid: %.2f te (dvs %.2f%% av tiden från öppning tills sista kunden betalat).\n\n",
					state.timeCheckoutsHaveBeenIdle/2,
					state.timeCheckoutsHaveBeenIdle/state.timeOpen);
			// 3)
			System.out.printf("3) Total tid %s kunder tvingats köa: %.2f te.\n   Genomsnittlig kötid: %.2f te.",
					state.queue.totalEntries(),
					state.getTotalQueueTime(),
					state.getTotalQueueTime()/state.getCompletedCheckouts());
			
		}
	}
}
