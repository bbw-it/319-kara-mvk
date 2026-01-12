package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * <h1>Schachbrettmuster</h1>
 * <p>Kara soll mit Kleeblättern ein Schachbrettmuster legen.
 * Auf dem Startfeld soll jeweils ein Blatt liegen.
 * Es gibt in dieser Welt keinen Rand und initial keine Blätter.
 * Wenn Kara die Welt z.B. nach oben hin verlässt, betritt er die Welt automatisch wider von unten.
 * Ein Baum kann den Weg versperren, dieser wird jedoch nie auf Kleeblattplatz liegen.</p>
 */
class Aufgabe18SchachbrettTest extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("""
				    |
				 e  |
				   t|
				    |
				""", x -> x.sameObjectsAs("""
				l l |
				 l l|
				l lt|
				 l l|
				"""));
	}

	@Test
	void world2() {
		testWorld("""
				      |
				 e  t |
				      |
				      |
				""", x -> x.sameObjectsAs("""
				l l l |
				 l ltl|
				l l l |
				 l l l|
				"""));
	}

	@Test
	void world3() {
		testWorld("""
				e |
				t |
				""", x -> x.sameObjectsAs("""
				l |
				 l|
				"""));
	}
}
