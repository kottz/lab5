package supermarket;

import sim.Event;
import sim.SortedSequence;
import supermarket.customer.Customer;
import random.*;

public class TidTest {

	// Proof-of-concept tidr�kning
		public static void main(String[] args) {
			ExponentialRandomStream eps1 = new ExponentialRandomStream(1.0, 1234); //H�kans parametrar fr�n sim1.txt
			UniformRandomStream urs1 = new UniformRandomStream(0.5, 1.0, 1234); //H�kans parametrar fr�n sim1.txt
			double kund0kommerin = eps1.next(); //Kund 0 kommer in
			double kund1kommerin = kund0kommerin + eps1.next(); //Kund 1 kommer in (triggat av kund0, s� dess tid+en ny random)
			double kund2kommerin =  kund1kommerin + eps1.next(); //Kund 2 kommer in(triggat av kund1, s� dess tid+en ny random)
			
			
			double kund0betalar = kund0kommerin + urs1.next(); // Kund 0 g�r till kassan och betalar.(Triggat av arrivalEvent, s� tiden kunden
																//kom in + tiden att plocka. tiden att plocka r�knas ut i arrival.
			System.out.println(kund0kommerin);
			System.out.println(kund1kommerin);
			System.out.println(kund2kommerin);
			System.out.println(kund0betalar);
			//Samma siffror som i H�kans simulering, m�ste vara s�h�r han har gjort.
			
			//Eftersom alla events sorteras efter den tid de intr�ffar s� kommer det randomnummer man f�r fr�n eps.next() alltid komma i r�tt
			//ordning i simuleringen. Det l�ser sig sj�lv om man bara l�ter event h�lla reda p� sin egen tid.
		}
		
		/* H�kans f�rsta tider:
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
