package supermarket;
import sim.SimView;
import java.util.Observable;

public class ShoppingView extends SimView{

	private ShoppingState state;
	
	public ShoppingView(ShoppingState state) {
		this.state = state;
	}
	
	
	public void update(Observable o, Object arg) {
		System.out.println("Tid: " + state.getCurrentTime());
	}
}
