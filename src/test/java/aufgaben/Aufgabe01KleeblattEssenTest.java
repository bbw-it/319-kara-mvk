package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kara und die Blätter...</h1>
 * <img src="../../resources/doc-files/collectleaves-world.gif" />
 * <p>Schreiben Sie ein Programm, das Kara bis zum nächsten Baum
 * führt. Liegt auf einem Feld ein Blatt, soll Kara es aufnehmen; liegt auf einem Feld kein Blatt, eines hinlegen. Bei
 * dem Baum angekommen ist das Programm zu beenden.</p>
 */
class Aufgabe01KleeblattEssenTest extends JavaKaraTestBase {

	/**
	 * Neu:
	 * <ul>
	 *     <li>Aktionen: {@code move()}, {@code putLeaf()}, {@code removeLeaf()}</li>
	 *     <li>Sensoren: {@code onLeaf()}</li>
	 *     <li>Kontrollstrukturen:
	 *     <a href="https://javabeginners.de/Schleifen_und_Verzweigungen/while_-Schleife.php">while-Schleifen</a>,
	 *     <a href="https://javabeginners.de/Schleifen_und_Verzweigungen/if_-Verzweigung.php">If-Verzweigung</a>
	 *     </li>
	 *     <li><a href="https://www.swisseduc.ch/informatik/karatojava/javakara/material/docs/whileschleife.pdf">Lösung und weitere Aufgaben</a></li>
	 * </ul>
	 */
	@Override
	protected void myProgram() {

	}

	// Die folgenden Zeilen sollen nicht verändert werden
	@Test
	void world1() {
		testWorld("E l l llt|", x -> x.sameObjectsAs(" l l l  t|"));
	}

	@Test
	void world2() {
		testWorld("e lll l t|", x -> x.sameObjectsAs("ll   l lt|"));
	}

	@Test
	void world3() {
		testWorld("Et|", x -> x.sameObjectsAs(" t|"));
	}
}
