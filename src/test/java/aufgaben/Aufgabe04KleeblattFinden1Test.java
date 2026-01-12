package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kleeblattsuche im Wald I</h1>
 * <img src="../../resources/doc-files/findleaf1-world.gif" />
 * <p>Kara sucht ein Kleeblatt. Es weiss, dass eines geradeaus vor ihm liegt - es muss nur um die B&auml;ume
 * herumlaufen. Gl&uuml;cklicherweise stehen nie zwei B&auml;ume nebeneinander. Schreiben Sie ein Programm, das ihn bis
 * zum Kleeblatt f&uuml;hrt.
 */
class Aufgabe04KleeblattFinden1Test extends JavaKaraTestBase {

	/**
	 * Neu:
	 * <ul>
	 *     <li>Sensoren: {@code treeFront()}</li>
	 * </ul>
	 */
	@Override
	protected void myProgram() {

	}

	// Tipp: folgende Funktion beschleunigt Kara
	@Override
	protected long sleepMillis() {
		return 20;
	}

	@Test
	void world1() {
		testWorld("         |e t t t l|", x -> x.karaOn(8, 1));
	}

	@Test
	void world2() {
		testWorld("         | et t tl |", x -> x.karaOn(7, 1));
	}

	@Test
	void world3() {
		testWorld("     |   E |", x -> x.karaOn(3, 1));
	}
}
