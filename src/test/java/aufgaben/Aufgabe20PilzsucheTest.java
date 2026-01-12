package aufgaben;

import internal.JavaKaraTestBase;
import org.junit.jupiter.api.Test;

/**
 * Kara will den Pilz am nächsten an der Startposition finden und zurückbringen.
 * Pilze liegen immer horizontal und/oder vertikal zu Karas Startposition.
 */
class Aufgabe20PilzsucheTest extends JavaKaraTestBase {

	@Override
	protected void myProgram() {

	}

	@Test
	void world1() {
		testWorld("""
				         |
				    m    |
				         |
				         |
				  m E   m|
				         |
				         |
				         |
				    m    |
				""", x -> x.sameObjectsAs("""
				         |
				    m    |
				         |
				         |
				    M   m|
				         |
				         |
				         |
				    m    |
				"""));
	}

	@Test
	void world2() {
		testWorld("""
				   |
				 W |
				 m |
				""", x -> x.sameObjectsAs("""
				   |
				 M |
				   |
				"""));
	}

	@Test
	void world3() {
		testWorld("""
				t         t|
				 t   m   t |
				  t     t  |
				   t   t   |
				    t t    |
				m    S    m|
				    t t    |
				   t   t   |
				  t     t  |
				 t       t |
				t    m    t|
				""", x -> x.sameObjectsAs("""
				           |
				           |
				           |
				           |
				           |
				m    M    m|
				           |
				           |
				           |
				           |
				     m     |
				"""));
	}
}
