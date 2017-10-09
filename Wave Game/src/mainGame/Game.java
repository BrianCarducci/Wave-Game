package mainGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * Main game class. This class is the driver class and it follows the Holder
 * pattern. It houses references to ALL of the components of the game
 * 
 * @author Brandon Loehle 5/30/16
 */

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	//using the imported tool api, Java automatically gets screen width and height to dynamically adjust
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = (int)screenSize.getWidth(), HEIGHT = (int) screenSize.getHeight();
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private Spawn1to10 spawner;
	private Spawn10to20 spawner2;
	private Menu menu;
	private GameOver gameOver;
	private UpgradeScreen upgradeScreen;
	private MouseListener mouseListener;
	private Upgrades upgrades;
	private Player player;
	public STATE gameState = STATE.Menu;
	public static int TEMP_COUNTER;

	/**
	 * Used to switch between each of the screens shown to the user
	 */
	public enum STATE {
		Menu, Help, Game, GameOver, Upgrade,
	};

	/**
	 * Initialize the core mechanics of the game
	 */
	public Game() {
		handler = new Handler();
		hud = new HUD();
		spawner = new Spawn1to10(this.handler, this.hud, this);
		spawner2 = new Spawn10to20(this.handler, this.hud, this.spawner, this);
		menu = new Menu(this, this.handler, this.hud, this.spawner);
		upgradeScreen = new UpgradeScreen(this, this.handler, this.hud);
		player = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler, this.hud, this);
		upgrades = new Upgrades(this, this.handler, this.hud, this.upgradeScreen, this.player, this.spawner,
				this.spawner2);
		gameOver = new GameOver(this, this.handler, this.hud);
		mouseListener = new MouseListener(this, this.handler, this.hud, this.spawner, this.spawner2, this.upgradeScreen,
				this.player, this.upgrades);
		this.addKeyListener(new KeyInput(this.handler, this, this.hud, this.player, this.spawner, this.upgrades));
		this.addMouseListener(mouseListener);
		new Window((int) WIDTH, (int) HEIGHT, "Wave Game", this);
	}

	/**
	 * The thread is simply a programs path of execution. This method ensures that
	 * this thread starts properly.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Best Java game loop out there (used by Notch!)
	 */
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();// 60 times a second, objects are being updated
				delta--;
			}
			if (running)
				render();// 60 times a second, objects are being drawn
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println(gameState);
				System.out.println(Spawn1to10.LEVEL_SET);
				frames = 0;
			}
		}
		stop();

	}

	/**
	 * Constantly ticking (60 times per second, used for updating smoothly). Used
	 * for updating the instance variables (DATA) of each entity (location, health,
	 * appearance, etc).
	 */
	private void tick() {
		handler.tick();// ALWAYS TICK HANDLER, NO MATTER IF MENU OR GAME SCREEN
		if (gameState == STATE.Game) {// game is running
			hud.tick();
			if (Spawn1to10.LEVEL_SET == 1) {// user is on levels 1 thru 10, update them
				spawner.tick();
			} else if (Spawn1to10.LEVEL_SET == 2) {// user is on levels 10 thru 20, update them
				spawner2.tick();
			}
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {// user is on menu, update the menu items
			menu.tick();
		} else if (gameState == STATE.Upgrade) {// user is on upgrade screen, update the upgrade screen
			upgradeScreen.tick();
		} else if (gameState == STATE.GameOver) {// game is over, update the game over screen
			gameOver.tick();
		}

	}

	/**
	 * Constantly drawing to the many buffer screens of each entity requiring the
	 * Graphics objects (entities, screens, HUD's, etc).
	 */
	private void render() {

		/*
		 * BufferStrategies are used to prevent screen tearing. In other words, this
		 * allows for all objects to be redrawn at the same time, and not individually
		 */
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		///////// Draw things bellow this/////////////

		g.setColor(Color.black);
		g.fillRect(0, 0, (int) WIDTH, (int) HEIGHT);

		handler.render(g); // ALWAYS RENDER HANDLER, NO MATTER IF MENU OR GAME SCREEN

		if (gameState == STATE.Game) {// user is playing game, draw game objects
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {// user is in help or the menu, draw the menu
																		// and help objects
			menu.render(g);
		} else if (gameState == STATE.Upgrade) {// user is on the upgrade screen, draw the upgrade screen
			upgradeScreen.render(g);
		} else if (gameState == STATE.GameOver) {// game is over, draw the game over screen
			gameOver.render(g);
		}

		///////// Draw things above this//////////////
		g.dispose();
		bs.show();
	}

	/**
	 * 
	 * Constantly checks bounds, makes sure players, enemies, and info doesn't leave
	 * screen
	 * 
	 * @param var
	 *            x or y location of entity
	 * @param min
	 *            minimum value still on the screen
	 * @param max
	 *            maximum value still on the screen
	 * @return value of the new position (x or y)
	 */
	public static double clamp(double var, double min, double max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {
		new Game();
		System.out.println("Screensize:" + screenSize);
	}

}
