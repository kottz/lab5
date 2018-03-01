package supermarket;

import sim.Event;
import sim.SortedSequence;
import supermarket.customer.Customer;
import random.*;

public class TidTest {

	// Proof-of-concept tidräkning
		public static void main(String[] args) {
			ExponentialRandomStream eps1 = new ExponentialRandomStream(1.0, 1234); //Håkans parametrar från sim1.txt
			UniformRandomStream urs1 = new UniformRandomStream(0.5, 1.0, 1234); //Håkans parametrar från sim1.txt
			double kund0kommerin = eps1.next(); //Kund 0 kommer in
			double kund1kommerin = kund0kommerin + eps1.next(); //Kund 1 kommer in (triggat av kund0, så dess tid+en ny random)
			double kund2kommerin =  kund1kommerin + eps1.next(); //Kund 2 kommer in(triggat av kund1, så dess tid+en ny random)
			
			
			double kund0betalar = kund0kommerin + urs1.next(); // Kund 0 går till kassan och betalar.(Triggat av arrivalEvent, så tiden kunden
																//kom in + tiden att plocka. tiden att plocka räknas ut i arrival.
			System.out.println(kund0kommerin);
			System.out.println(kund1kommerin);
			System.out.println(kund2kommerin);
			System.out.println(kund0betalar);
			//Samma siffror som i Håkans simulering, måste vara såhär han har gjort.
			
			//Eftersom alla events sorteras efter den tid de inträffar så kommer det randomnummer man får från eps.next() alltid komma i rätt
			//ordning i simuleringen. Det löser sig själv om man bara låter event hålla reda på sin egen tid.
		}
		
		/* Håkans första tider:
		 * 
		 * 0,44 Ankomst
		 * 0,49 Ankomst
		 * 0,64 Ankomst
		 * 1,26 Plock
		 * osv.
		 * osv.
		 * Samma som denna kod genererar.
		 */
		
}
