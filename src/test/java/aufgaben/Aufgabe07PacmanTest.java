package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kleeblatt-PacMan</h1>
 * <img src="../../resources/doc-files/pacman.gif" />
 * <p>Programmieren Sie Kara so, dass er die Spur von Kleebl&auml;ttern &quot;auffrisst&quot;! Da Sie wissen, dass die
 * Spur nie entlang eines Baumes geht, kann das Programm beendet werden, sobald Kara auf einem Kleeblatt vor einem Baum
 * steht. Kara started jeweils auf einem Kleeblatt.</p>
 */
class Aufgabe07PacmanTest extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("""
				t        |
				tlllllll |
				t      l |
				 lllll l |
				 l   l l |
				 lll l l |
				   l lll |
				 Ell     |
				         |""", x -> x.karaOn(1, 1).sameObjectsAs(" |"));
	}

	@Test
	void world2() {
		testWorld("""
				         |
				  El     |
				   l lll |
				 lll l l |
				 l   l l |
				 lllll l |
				t      l |
				tll   ll |
				t lllll  |
				""", x -> x.karaOn(1, 7).sameObjectsAs(" |"));
	}
}
