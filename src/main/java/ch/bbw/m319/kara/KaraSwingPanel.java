package ch.bbw.m319.kara;

import java.awt.*;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class KaraSwingPanel extends JPanel implements KaraEngine.WorldCallback, Closeable {

	private static final int ICON_SIZE = 28 + 28 / 2;

	private static final int BORDER_SIZE = 2;

	private final Map<String, Image> cache = new HashMap<>();

	private final long sleepMillis;

	private transient KaraWorld world;

	private KaraSwingPanel(KaraWorld world, long sleepMillis) {
		this.world = world;
		this.sleepMillis = sleepMillis;
		var size = world.kara().position();
		setPreferredSize(new Dimension(size.xmax() * ICON_SIZE + 1 + BORDER_SIZE * 2, size.ymax() * ICON_SIZE + 1 + BORDER_SIZE * 2));
		setBackground(new Color(0xb4e6b4));
	}

	public static KaraSwingPanel newWindow(KaraWorld world, long sleepMillis) {
		var panel = new KaraSwingPanel(world, sleepMillis);
		System.setProperty("sun.java2d.opengl", "true");
		var mainWindow = new JFrame("KaraTDD");
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setContentPane(panel);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);
		mainWindow.pack();
		return panel;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		world.kara().position().iterateAll()
				.forEach(pos -> {
					g.setColor(Color.GRAY);
					var x = BORDER_SIZE + pos.x() * ICON_SIZE;
					var y = BORDER_SIZE + pos.y() * ICON_SIZE;
					g.drawRect(x, y, ICON_SIZE, ICON_SIZE);
					if (world.leafs().contains(pos)) {
						g.drawImage(loadImage("/object_leaf.gif"), x, y, this);
					}
					if (world.trees().contains(pos)) {
						g.drawImage(loadImage("/object_tree.gif"), x, y, this);
					}
					if (world.mushrooms().contains(pos)) {
						g.drawImage(loadImage("/object_mushroom.gif"), x, y, this);
					}
				});
		g.drawImage(loadImage(switch (world.kara().facing()) {
			case N -> "/bugnorth_r.gif";
			case E -> "/bugeast_r.gif";
			case S -> "/bugsouth_r.gif";
			case W -> "/bugwest_r.gif";
		}), world.kara().position().x() * ICON_SIZE + BORDER_SIZE, world.kara().position().y() * ICON_SIZE + BORDER_SIZE, this);
	}

	private Image loadImage(String path) {
		return cache.computeIfAbsent(path, x -> {
			try {
				return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)))
						.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_SMOOTH);
			} catch (IOException e) {
				throw new RuntimeException("failed loading image from " + path, e);
			}
		});
	}

	private void doSleep() {
		if (System.getProperty("CI") != null || System.getProperty("nosleep") != null) {
			return; // no sleep in pipelines
		}
		try {
			Thread.sleep(sleepMillis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("insomnia", e);
		}
	}

	@Override
	public void update(KaraWorld newWorld) {
		world = newWorld;
		repaint();
		doSleep();
	}

	@Override
	public void notify(KaraWorld.KaraException e) {
		setBackground(new Color(0xe6b4b4));
		doSleep();
		doSleep();
		doSleep();
	}

	@Override
	public void close() {
		SwingUtilities.getWindowAncestor(this).dispose();
	}
}
