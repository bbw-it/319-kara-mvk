package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kleeblattsuche im Wald III</h1>
 * <img src="../../resources/doc-files/walk-world.gif" />
 * <p>In Karas Welt gibt es W&auml;lder mit Rundg&auml;ngen, in denen Kara auf Kleebl&auml;ttersuche geht.
 * Jedes Feld in einem Rundgang hat genau zwei freie benachbarte Felder. Eines davon liegt hinter Kara, von diesem Feld
 * aus ist auf das aktuelle Feld gekommen. Das heisst, genau hinter einem der drei Fragezeichen in der folgenden
 * Abbildung ist ein leeres Feld, hinter den beiden anderen Fragezeichen liegen Felder mit B&auml;umen:</p>
 * <img src="../../resources/doc-files/walk-invariant.gif" />
 */
class Aufgabe06KleeblattFinden3Test extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("""
				ttttttttt|
				tttt    t|
				t  e tt t|
				t ttttt t|
				t tttt  t|
				t  ttt tt|
				tt t   tt|
				tt l tttt|
				ttttttttt|""", x -> x.karaOn(3, 7));
	}

	@Test
	void world2() {
		testWorld("""
				ttttttttttt|
				t   ttttttt|
				tnt  tttttt|
				t lt  ttttt|
				tt  t  tttt|
				ttt  t  ttt|
				tttt  t  tt|
				ttttt  t  t|
				tttttt    t|
				ttttttttttt|""", x -> x.karaOn(2, 3));
	}

	@Test
	void world3() {
		testWorld("""
				tttttttttt|
				tt e    tt|
				tt tttt tt|
				ttltttt tt|
				tt  tt  tt|
				ttt tt ttt|
				tt  tt  tt|
				tt tttt tt|
				tt tttt tt|
				tt      tt|
				tttttttttt|""", x -> x.karaOn(2, 3));
	}
}
