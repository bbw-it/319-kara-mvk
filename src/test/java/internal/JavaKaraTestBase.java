package internal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import ch.bbw.m319.kara.KaraEngine;
import ch.bbw.m319.kara.KaraSwingPanel;
import ch.bbw.m319.kara.KaraWorld;

/**
 * Test-base class to squeeze Kara into a TDD design and expose only minimal Kara methods.
 */
public abstract class JavaKaraTestBase {

	private KaraEngine engine;

	protected abstract void myProgram();

	protected long sleepMillis() {
		return 1000;
	}

	protected void testWorld(String filePath) {
		testWorld(filePath, x -> {
		});
	}

	protected void testWorld(String worldStr, Consumer<KaraAssert> check) {
		var world = KaraWorld.fromString(worldStr);
		engine = new KaraEngine(world);
		KaraSwingPanel ui = null;
		try {
			if (!GraphicsEnvironment.isHeadless()) {
				ui = KaraSwingPanel.newWindow(world, sleepMillis());
				engine.setCallback(ui);
			}
			myProgram();
			check.accept(new KaraAssert(engine.getWorld()));
		} finally {
			if (ui != null) {
				ui.close();
			}
		}
	}

	/**
	 * Kara macht einen Schritt in die aktuelle Richtung.
	 */
	public void move() {
		engine.move();
	}

	/**
	 * Kara schaut nach, ob es einen Pilz vor sich hat.
	 */
	public boolean mushroomFront() {
		return engine.mushroomFront();
	}

	/**
	 * Kara schaut nach, ob es sich auf einem Kleeblatt befindet.
	 */
	public boolean onLeaf() {
		return engine.onLeaf();
	}

	/**
	 * Kara legt ein Kleeblatt an die Position, auf der es sich befindet.
	 */
	public void putLeaf() {
		engine.putLeaf();
	}

	/**
	 * Kara entfernt ein unter ihm liegendes Kleeblatt.
	 */
	public void removeLeaf() {
		engine.removeLeaf();
	}

	/**
	 * Kara schaut nach, ob sich ein Baum vor ihm befindet.
	 */
	public boolean treeFront() {
		return engine.treeFront();
	}

	/**
	 * Kara schaut nach, ob sich ein Baum links von ihm befindet.
	 */
	public boolean treeLeft() {
		return engine.treeLeft();
	}

	/**
	 * Kara schaut nach, ob sich ein Baum rechts von ihm befindet.
	 */
	public boolean treeRight() {
		return engine.treeRight();
	}

	/**
	 * Kara dreht sich um 90° nach links.
	 */
	public void turnLeft() {
		engine.turnLeft();
	}

	/**
	 * Kara dreht sich um 90° nach rechts.
	 */
	public void turnRight() {
		engine.turnRight();
	}

	public record KaraAssert(KaraWorld actual) {

		public KaraAssert karaOn(int x, int y) {
			assertEquals(x, actual.kara().position().x(), "\uD83D\uDC1E Kara is on the wrong x-position");
			assertEquals(y, actual.kara().position().y(), "\uD83D\uDC1E Kara is on the wrong y-position");
			return this;
		}

		public KaraAssert sameObjectsAs(String expected) {
			var expectedWorld = KaraWorld.fromString(expected);
			// don't care about trees nor Kara
			assertEquals(pretty(expectedWorld.leafs()), pretty(actual.leafs()), "☘ leafs in unexpected places");
			assertEquals(pretty(expectedWorld.mushrooms()), pretty(actual.mushrooms()), "\uD83C\uDF44 mushrooms in unexpected places");
			return this;
		}

		private String pretty(KaraWorld.ImmutableSet coordinates) {
			return coordinates.thingie()
					.stream()
					.sorted(Comparator.comparing(KaraWorld.Coordinate::x).thenComparing(KaraWorld.Coordinate::y))
					.map(x -> "(" + x.x() + "," + x.y() + ")")
					.collect(Collectors.joining(" "));
		}
	}
}
