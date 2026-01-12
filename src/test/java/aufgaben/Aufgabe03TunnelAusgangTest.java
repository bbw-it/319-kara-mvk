package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kara, der Tunnelsucher II</h1>
 * <img src="../../resources/doc-files/tunnel_entrance-world.gif" />
 * <p>Kara will den Ausgang des Tunnels finden (Feld 2b). Dazu muss es zun&auml;chst den Tunnel durchqueren.
 * Schreiben Sie ein Programm, das es auf dem ersten Feld nach dem Tunnel anhalten l&auml;sst &ndash;
 * es soll nicht bis zum Ende der Gallerie laufen! </p>
 */
class Aufgabe03TunnelAusgangTest extends JavaKaraTestBase {

	/**
	 * Keine neuen Ausdrücke, aber <a href="https://www.swisseduc.ch/informatik/karatojava/javakara/material/docs/neuemethoden.pdf">Tipps zu eigenen Methoden</a>.
	 */
	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld(" ttttt   |e        |   tttttt|", x -> x.karaOn(6, 1));
	}

	@Test
	void world2() {
		testWorld("   tttttt|e        | ttttt   |", x -> x.karaOn(6, 1));
	}

	@Test
	void world3() {
		testWorld("   tt    |e        |   tt    |", x -> x.karaOn(5, 1));
	}
}
