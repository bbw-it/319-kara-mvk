package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kara, der Tunnelsucher I</h1>
 * <img src="../../resources/doc-files/tunnel_entrance-world.gif" />
 * <p>Kara sucht den Eingang eines geraden Tunnels (Feld 2a). Schreiben Sie ein Programm, das ihn auf dem ersten Feld
 * im Tunnelinnern anhalten l&auml;sst. Aber Achtung: manche Tunnels haben zun&auml;chst eine einseitige Wand, manche
 * links, manche rechts.</p>
 */
class Aufgabe02TunnelEingangTest extends JavaKaraTestBase {

	/**
	 * Neu:
	 * <ul>
	 *     <li>Aktionen: {@code move()}, {@code putLeaf()}, {@code removeLeaf()}</li>
	 *     <li>Sensoren: {@code treeLeft()}, {@code treeRight()}</li>
	 *     <li>Kontrollstrukturen: <a href="https://javabeginners.de/Grundlagen/Datentypen/boolsche_Ausdruecke.php">boolsche Ausdrücke</a></li>
	 *     <li>Zusatzaufgaben: <a href="https://www.swisseduc.ch/informatik/karatojava/javakara/material/docs/logik.pdf">Logik</a></li>
	 * </ul>
	 */
	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("   ttttt |e        |     tttt|", x -> x.karaOn(5, 1));
	}

	@Test
	void world2() {
		testWorld("   ttttt |e        |   tttttt|", x -> x.karaOn(3, 1));
	}

	@Test
	void world3() {
		testWorld("   ttttt |   e     |   tttttt|", x -> x.karaOn(3, 1));
	}
}
