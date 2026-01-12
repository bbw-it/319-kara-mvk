package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Bild invertieren</h1>
 * <img src="../../resources/doc-files/invertimage.gif" />
 * <p>Programmieren Sie Kara so, dass er ein "Negativbild" von dem Kleeblattbild
 * innerhalb des Baumrechtecks erstellt. Wo ein Kleeblatt liegt, soll er es aufnehmen,
 * und wo keines liegt, soll er eines hinlegen.
 * Kara startet immer oben links in der Ecke mit Blick nach rechts.</p>
 */
class Aufgabe10InvertierenTest extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("""
				ttttttttt|
				tEll lllt|
				tl     lt|
				tl  l  lt|
				t  lll  t|
				tl  l  lt|
				tl     lt|
				tlll lllt|
				ttttttttt|
				""", x -> x.sameObjectsAs("""
				ttttttttt|
				t   l   t|
				t lllll t|
				t ll ll t|
				tll   llt|
				t ll ll t|
				t lllll t|
				t   l   t|
				ttttttttt|
				"""));
	}

	@Test
	void world2() {
		testWorld("""
				ttt|
				tEt|
				ttt|""", x -> x.sameObjectsAs("""
				ttt|
				t t|
				ttt|"""));
	}

	@Test
	void world3() {
		testWorld("""
				ttttt|
				te  t|
				t   t|
				t   t|
				ttttt|
				""", x -> x.sameObjectsAs("""
				ttttt|
				tlllt|
				tlllt|
				tlllt|
				ttttt|
				"""));
	}
}
