package ch.bbw.m319.kara;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record KaraWorld(Kara kara, ImmutableSet leafs, ImmutableSet trees, ImmutableSet mushrooms) {

	public static KaraWorld fromString(String worldStr) {
		worldStr = worldStr.replace("\n", "");
		var width = worldStr.indexOf("|");
		if (width == -1 || worldStr.length() % (width + 1) != 0) {
			throw new IllegalArgumentException("strange world");
		}
		var height = worldStr.length() / (width + 1);
		var world = new KaraWorld(null, new ImmutableSet(), new ImmutableSet(), new ImmutableSet());
		var pos = new Coordinate(0, 0, width, height);
		var eol = false;
		for (var c : worldStr.chars().toArray()) {
			if (eol && c != '|') {
				throw new IllegalArgumentException("expected pipe at EOL");
			} else if (eol) {
				eol = false;
				continue;
			}
			world = switch (c) {
				case ' ' -> world; // nop
				case 't', 'T' -> world.withTree(world.trees.put(pos));
				case 'l', 'L' -> world.withLeaf(world.leafs.put(pos));
				case 'M' -> world.withLeaf(world.leafs.put(pos)).withMushroom(world.mushrooms.put(pos));
				case 'm' -> world.withMushroom(world.mushrooms.put(pos));
				case 'N' -> world.withLeaf(world.leafs.put(pos)).withKara(new Kara(pos, Direction.N));
				case 'n' -> world.withKara(new Kara(pos, Direction.N));
				case 'E' -> world.withLeaf(world.leafs.put(pos)).withKara(new Kara(pos, Direction.E));
				case 'e' -> world.withKara(new Kara(pos, Direction.E));
				case 'S' -> world.withLeaf(world.leafs.put(pos)).withKara(new Kara(pos, Direction.S));
				case 's' -> world.withKara(new Kara(pos, Direction.S));
				case 'W' -> world.withLeaf(world.leafs.put(pos)).withKara(new Kara(pos, Direction.W));
				case 'w' -> world.withKara(new Kara(pos, Direction.W));
				default -> throw new IllegalArgumentException("unexpected character \"" + Character.toString(c) + "\"");
			};
			pos = pos.next();
			eol = pos.x == 0;
		}
		return world;
	}

	private KaraWorld withLeaf(ImmutableSet leafs) {
		return new KaraWorld(kara, leafs, trees, mushrooms);
	}

	private KaraWorld withTree(ImmutableSet trees) {
		return new KaraWorld(kara, leafs, trees, mushrooms);
	}

	private KaraWorld withMushroom(ImmutableSet mushrooms) {
		return new KaraWorld(kara, leafs, trees, mushrooms);
	}

	private KaraWorld withKara(Kara kara) {
		return new KaraWorld(kara, leafs, trees, mushrooms);
	}

	KaraWorld move() {
		if (treeFront()) {
			throw new KaraException("There is a tree in front of me");
		}
		var newWorld = this;
		if (mushroomFront()) {
			var inTwo = kara.move().move().position;
			if (mushrooms.contains(inTwo) || trees.contains(inTwo)) {
				throw new KaraException("There is an object behind the mushroom");
			}
			newWorld = newWorld.withMushroom(mushrooms.remove(kara.move().position).put(inTwo));
		}
		return newWorld.withKara(kara.move());
	}

	KaraWorld turnLeft() {
		return withKara(kara.left());
	}

	boolean mushroomFront() {
		return mushrooms.contains(kara.move().position);
	}

	KaraWorld turnRight() {
		return withKara(kara.right());
	}

	boolean onLeaf() {
		return leafs.contains(kara.position);
	}

	KaraWorld putLeaf() {
		if (onLeaf()) {
			throw new KaraException("There is already a leaf");
		}
		return withLeaf(leafs.put(kara.position));
	}

	KaraWorld removeLeaf() {
		if (!onLeaf()) {
			throw new KaraException("There is no leaf");
		}
		return withLeaf(leafs.remove(kara.position));
	}

	boolean treeFront() {
		return trees.contains(kara.move().position);
	}

	/**
	 * Kara schaut nach, ob sich ein Baum links von ihm befindet
	 */
	public boolean treeLeft() {
		return trees.contains(kara.left().move().position);
	}

	/**
	 * Kara schaut nach, ob sich ein Baum rechts von ihm befindet
	 */
	public boolean treeRight() {
		return trees.contains(kara.right().move().position);
	}

	public enum Direction {
		N, E, S, W;

		Direction left() {
			return switch (this) {
				case N -> Direction.W;
				case W -> Direction.S;
				case S -> Direction.E;
				case E -> Direction.N;
			};
		}

		Direction right() {
			return left().left().left();
		}
	}

	public record ImmutableSet(Set<Coordinate> thingie) {

		ImmutableSet() {
			this(Collections.emptySet());
		}

		public boolean contains(Coordinate pos) {
			return thingie.contains(pos);
		}

		public ImmutableSet put(Coordinate pos) {
			return new ImmutableSet(Stream.concat(thingie.stream(), Stream.of(pos)).collect(Collectors.toUnmodifiableSet()));
		}

		public ImmutableSet remove(Coordinate pos) {
			return new ImmutableSet(thingie.stream().filter(x -> !x.equals(pos)).collect(Collectors.toUnmodifiableSet()));
		}
	}

	public static class KaraException extends RuntimeException {

		public KaraException(String msg) {
			super(msg);
		}
	}

	public record Coordinate(int x, int y, int xmax, int ymax) {

		Coordinate move(Direction direction) {
			return switch (direction) {
				case E -> byXY((x + 1) % xmax, y);
				case W -> byXY((x - 1 + xmax) % xmax, y);
				case S -> byXY(x, (y + 1) % ymax);
				case N -> byXY(x, (y - 1 + ymax) % ymax);
			};
		}

		Coordinate byXY(int x, int y) {
			return new Coordinate(x, y, xmax, ymax);
		}

		public Stream<Coordinate> iterateAll() {
			return IntStream.range(0, xmax * ymax).mapToObj(id -> byXY(id % xmax, id / xmax));
		}

		public Coordinate next() {
			var id = x + y * xmax + 1;
			return new Coordinate(id % xmax, id / xmax, xmax, ymax);
		}
	}

	public record Kara(Coordinate position, Direction facing) {

		Kara move() {
			return new Kara(position.move(facing), facing);
		}

		Kara left() {
			return new Kara(position, facing.left());
		}

		Kara right() {
			return new Kara(position, facing.right());
		}
	}
}
