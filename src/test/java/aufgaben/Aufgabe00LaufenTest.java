package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kara läuft...</h1>
 * <img src="../../resources/doc-files/solo-tree.gif" />
 * <p>KARA steht vor einem Baum, hinter dem sich ein Kleeblatt befindet. KARA soll um
 * den Baum herumgehen, das Kleeblatt aufnehmen, es einen Schritt weiter ablegen, einen Schritt
 * vorgehen und sich umdrehen.</p>
 */
class Aufgabe00LaufenTest extends JavaKaraTestBase {

	/**
	 * <ul>
	 *     <li>Aktionen: {@code move()}, {@code turnRight()}, {@code turnLeft()}, {@code putLeaf()}, {@code removeLeaf()}</li>
	 *     <li>Weiterführende Aufgaben: <a href="https://www.swisseduc.ch/informatik/karatojava/javakara/material/docs/neuemethoden.pdf">Kara lernt neue Kommandos</a></li>
	 * </ul>
	 */
	@Override
	protected void myProgram() {
		// Hier schreiben Sie Ihr Programm, z.B.:
		move();
		move();
	}

	@Test
	void world1() {
		testWorld("     |etl  |", x -> x.sameAs("     | t lw|"));
	}
}
