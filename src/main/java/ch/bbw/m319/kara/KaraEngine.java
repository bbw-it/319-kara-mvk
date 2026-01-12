package ch.bbw.m319.kara;

import java.util.function.Supplier;

public class KaraEngine {

	private static final int MAX_EXECUTION_COUNT = 10000;

	private KaraWorld world;

	private WorldCallback callback = WorldCallback.NOOP;

	private int executionCounter = 0;

	public KaraEngine(KaraWorld world) {
		if (world.kara() == null) {
			throw new IllegalArgumentException("Kara is missing");
		}
		this.world = world;
	}

	public KaraWorld getWorld() {
		return world;
	}

	public KaraEngine setCallback(WorldCallback callback) {
		this.callback = callback;
		callback.update(world);
		return this;
	}

	public void move() {
		world = wrapUpdate(() -> world.move());
	}

	public boolean mushroomFront() {
		return world.mushroomFront();
	}

	public boolean onLeaf() {
		return world.onLeaf();
	}

	public void putLeaf() {
		world = wrapUpdate(() -> world.putLeaf());
	}

	public void removeLeaf() {
		world = wrapUpdate(() -> world.removeLeaf());
	}

	public boolean treeFront() {
		return world.treeFront();
	}

	public boolean treeLeft() {
		return world.treeLeft();
	}

	public boolean treeRight() {
		return world.treeRight();
	}

	public void turnLeft() {
		world = wrapUpdate(() -> world.turnLeft());
	}

	public void turnRight() {
		world = wrapUpdate(() -> world.turnRight());
	}

	private KaraWorld wrapUpdate(Supplier<KaraWorld> work) {
		executionCounter++;
		if (executionCounter > MAX_EXECUTION_COUNT) {
			throw new KaraWorld.KaraException("too many steps");
		}
		try {
			var newWorld = work.get();
			callback.update(newWorld);
			return newWorld;
		} catch (KaraWorld.KaraException e) {
			callback.notify(e);
			throw e;
		}
	}

	public interface WorldCallback {

		WorldCallback NOOP = new WorldCallback() {
			@Override
			public void update(KaraWorld newWorld) {
				// nop
			}

			@Override
			public void notify(KaraWorld.KaraException e) {
				// nop
			}
		};

		void update(KaraWorld newWorld);

		void notify(KaraWorld.KaraException e);
	}
}
