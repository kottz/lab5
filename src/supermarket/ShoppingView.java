package supermarket;
import sim.SimView;
import java.util.Observable;
import supermarket.event.*;

public class ShoppingView extends SimView{

	private ShoppingState state;
	private boolean startFlag = true;
	
	public ShoppingView(ShoppingState state) {
		this.state = state;
	}
	
	
	public void update(Observable o, Object arg) {
		//Printas i b�rjan
		if(startFlag) {
			System.out.println("Parametrar\n========");
			System.out.println("Antal Kassor, N:\t" + state.getNumberOfCustomers());
			System.out.println("Max som ryms, M:\t" + state.getMaxCustomers());
			System.out.println("Ankomsthastighet, lamba:\t" + state.getLambda());
			System.out.println("Plocktider, [P_min..Pmax]:\t" + "[" + state.getPmin() + ".." + state.getPmax() + "]");
			System.out.println("Betaltider, [K_min..Kmax]:\t" + "[" + state.getKmin() + ".." + state.getKmax() + "]");
			System.out.println("Fr�, f:\t" + state.getSeed());
			System.out.println("\nF�RLOPP\n=====");
			System.out.println("Tid\tH�ndelse\tKund\t?  led\tledT\tI\t$\t:-(   k�at    k�T   k�ar  [Kassak�..]");
			startFlag = false;
		}
		
		//Start & Stop
		if(state.getCurrentEvent() instanceof StartEvent || state.getCurrentEvent() instanceof StopEvent) {
			System.out.printf("%.0f\t%s\n", state.getCurrentTime(), state.getCurrentEvent().toString());
			
		} else { //Alla andra event
			
			System.out.printf("%.2f\t%s\t\t%s\t%s  %s\t%s\t%s\t%s\t%s\n", state.getCurrentTime(), state.getCurrentEvent().toString(),
					state.getCurrentEvent() instanceof CloseEvent ? "---": state.getCurrentCustomer().getId(),state.isOpen() ? "�":"S",
							state.idleCheckouts, state.timeCheckoutsHaveBeenIdle,state.getNumberOfCustomers(),state.getCompletedCheckouts(),
							state.missedCustomers);
		}
	
		
		if(!state.isRunning()) {
			System.out.println("\nRESULTAT\n======");
		}
	}
}
