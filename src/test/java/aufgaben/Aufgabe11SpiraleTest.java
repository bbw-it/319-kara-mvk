package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Spiralen zeichnen</h1>
 * <img src="../../resources/doc-files/spiral.gif" />
 * <p>Programmieren Sie Kara so, dass er eine Kleeblatt-Spirale wie die obige zeichnet.
 * Von innen nach aussen ist jede Kante der Spirale um eins länger als die vorangehende.</p>
 */
class Aufgabe11SpiraleTest extends JavaKaraTestBase {

	@Override
	protected long sleepMillis() {
		return 20;
	}

	@Override
	protected void myProgram() {
		int d = 0;
		while (d < 21) {
			// ...
			d++;
		}
	}

	@Test
	void world1() {
		testWorld("""
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				          e          |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				                     |
				""");
	}
}
