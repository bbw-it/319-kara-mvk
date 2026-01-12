package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Wand entlang</h1>
 * <img src="../../resources/doc-files/followwall.gif" />
 * <p>Kara möchte einen Wald im Uhrzeigersinn patrouillieren.
 * Programmieren Sie Kara so, dass er im Uhrzeigersinn um diesen Wald läuft
 * und an derselben Stelle wider stoppt.</p>
 */
class Aufgabe08WandEntlangTest extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("""
				         |
				    tttt |
				   tlllts|
				  tllllt |
				 tlllllt |
				  tlltt  |
				 tllt    |
				  ttt    |
				         |
				""", x -> x.karaOn(8, 2));
	}

	@Test
	void world2() {
		testWorld("""
				   |
				 t |
				n  |""", x -> x.karaOn(0, 2));
	}

	@Test
	void world3() {
		testWorld("""
				         |
				 ttttttt |
				 tlllllt |
				 tltttlt |
				 tlt tlt |
				 tlt tlt |
				 tlt tlt |
				 ttt ttt |
				   w     |
				""", x -> x.karaOn(3, 8));
	}
}
