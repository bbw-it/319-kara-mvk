package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Kleeblattsuche im Wald II</h1>
 * <img src="../../resources/doc-files/findleaf2-world.gif" />
 * <p>Erweitern Sie Ihr Programm von Aufgabe 4 so, dass Kara auch mit mehreren nebeneinander stehenden B&auml;umen
 * fertig wird! Hinweis: Die L&ouml;sung dieser Aufgabe erfordert zwei Zust&auml;nde.
 */
class Aufgabe05KleeblattFinden2Test extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("         |ett tttl |", x -> x.karaOn(7, 1));
	}

	@Test
	void world2() {
		testWorld("         |e tttt l |", x -> x.karaOn(7, 1));
	}

	@Test
	void world3() {
		testWorld("         |e t  tl  |", x -> x.karaOn(6, 1));
	}
}
