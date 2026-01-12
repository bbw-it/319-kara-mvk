package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * Kara muss einen Pilz horizontal bis auf ein Kleeblatt schieben.
 * Dabei können einzelne Bäume im Weg stehen.
 * Der Pilz liegt zu Beginn immer direkt vor Kara.
 */
class Aufgabe21Pilzsuche2Test extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("       |emt  l |       |", x -> x.sameObjectsAs("       |     M |       |"));
	}

	@Test
	void world2() {
		testWorld("      |em tl |      |", x -> x.sameObjectsAs("      |    M |      |"));
	}

	@Test
	void world3() {
		testWorld("        |emt  tl |        |", x -> x.sameObjectsAs("        |      M |        |"));
	}
}
