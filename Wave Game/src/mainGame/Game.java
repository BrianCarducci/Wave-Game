package mainGame;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

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

	// using the imported tool api, Java automatically gets screen width and
	// height to dynamically adjust
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = (int) screenSize.getWidth(), HEIGHT = (int) screenSize.getHeight();
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private CoopHud hud2;
	private AttackHUD attackHUD;
	private ServerHUD serverHUD;
	private Spawn1to5 spawner;
	private Spawn5to10 spawner2;
	private Spawn10to15 spawner3;
	private AttackSpawn attackSpawn;
	private DefenseSpawner dSpawner;
	private Menu menu;
	private GameOver gameOver;
	private Victory victory;
	private UpgradeScreen upgradeScreen;
	private MouseListener mouseListener;
	private Server server;
	private Upgrades upgrades;
	private Player player, player2;
	public STATE gameState = STATE.Menu;
	public static int TEMP_COUNTER;
	public String temp;
	Sound sound = new Sound();

	/**
	 * Used to switch between each of the screens shown to the user
	 */
	public enum STATE {
		Menu, Help, Game, GameOver, Upgrade, Coop, Attack, Victory, Defense
	};

	/**
	 * Initialize the core mechanics of the game
	 */
	public Game() {
		handler = new Handler();
		hud = new HUD();
		hud2 = new CoopHud();
		attackHUD = new AttackHUD();
		spawner = new Spawn1to5(this.handler, this.hud, this.hud2, this);
		spawner2 = new Spawn5to10(this.handler, this.hud, this.hud2, this.spawner, this);
		attackSpawn = new AttackSpawn(this.handler, this.attackHUD, this);
		menu = new Menu(this, this.handler, this.hud, this.spawner);
		upgradeScreen = new UpgradeScreen(this, this.handler, this.hud);
		player = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler, this.hud, this.hud2, this.attackHUD, this);
		player2 = new Player(WIDTH / 2 + 100, HEIGHT / 2 - 32, ID.Player2, handler, this.hud, this.hud2, this.attackHUD, this);
		upgrades = new Upgrades(this, this.handler, this.hud, this.upgradeScreen, this.player, this.spawner,
				this.spawner2, this.spawner3, this.attackHUD, this.attackSpawn);
		gameOver = new GameOver(this, this.handler, this.hud, this.hud2, this.serverHUD, this.attackHUD);
		victory = new Victory(this, this.handler, this.hud, this.hud2, this.attackHUD);
		mouseListener = new MouseListener(this, this.handler, this.hud, this.hud2, this.serverHUD, this.attackHUD, this.spawner, this.spawner2, this.spawner3, this.attackSpawn,
				this.upgradeScreen, this.player, this.player2, this.upgrades, this.server);
		this.addKeyListener(
				new KeyInput(this.handler, this, this.hud, this.attackHUD, this.player, this.player2, this.spawner, this.upgrades));
		this.addMouseListener(mouseListener);
		new Window((int) WIDTH, (int) HEIGHT, "Wave Game", this);
	}

	/**
	 * The thread is simply a programs path of execution. This method ensures
	 * that this thread starts properly.
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
				System.out.println(Spawn1to5.LEVEL_SET);
				frames = 0;
			}
		}
		stop();

	}

	/**
	 * Constantly ticking (60 times per second, used for updating smoothly).
	 * Used for updating the instance variables (DATA) of each entity (location,
	 * health, appearance, etc).
	 */
	public void tick() {
		handler.tick();// ALWAYS TICK HANDLER, NO MATTER IF MENU OR GAME SCREEN
		if (gameState == STATE.Game) {// game is running
			hud.tick();
			if (Spawn1to5.LEVEL_SET == 1) {// user is on levels 1 thru 10,
											// update them
				spawner.tick();
			} else if (Spawn1to5.LEVEL_SET == 2) {// user is on levels 10 thru
													// 20, update them
				spawner2.tick();
			}
		} else if (gameState == STATE.Defense) {
			//hud2.tick();
			serverHUD.setState(STATE.Defense);
			serverHUD.tick();
			dSpawner.tick();
		} else if (gameState == STATE.Attack) {
			attackHUD.tick();
			attackSpawn.tick();

		} else if (gameState == STATE.Coop) { //changes game state to different game mode for coop
			hud.tick();
			hud.setState(STATE.Coop);
			hud2.tick();
			hud2.setState(STATE.Coop);

			if (Spawn1to5.LEVEL_SET == 1) {// user is on levels 1 thru 10,
											// update them
				spawner.tick();
			} else if (Spawn1to5.LEVEL_SET == 2) {// user is on levels 10 thru
													// 20, update them
				spawner2.tick();
			}
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {// user
																		// is on
																		// menu,
																		// update
																		// the
																		// menu
																		// items
			menu.tick();
		} else if (gameState == STATE.Upgrade) {// user is on upgrade screen,
												// update the upgrade screen
			upgradeScreen.tick();
		} else if (gameState == STATE.GameOver) {// game is over, update the
													// game over screen
			gameOver.tick();
		} else if (gameState == STATE.Victory){
			victory.tick();
		}

	}

	/**
	 * Constantly drawing to the many buffer screens of each entity requiring
	 * the Graphics objects (entities, screens, HUD's, etc).
	 */
	private void render() {
		/*
		 * BufferStrategies are used to prevent screen tearing. In other words,
		 * this allows for all objects to be redrawn at the same time, and not
		 * individually
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

		handler.render(g); // ALWAYS RENDER HANDLER, NO MATTER IF MENU OR GAME
							// SCREEN

		if (gameState == STATE.Game) {// user is playing game, draw game objects
			hud.render(g);
		} else if (gameState == STATE.Coop) {
			hud.render(g);
			hud2.render(g);
		} else if (gameState == STATE.Attack) {
			attackHUD.render(g);
		} else if (gameState == STATE.Defense) {
			serverHUD.render(g);
			//hud2.render(g);
			} else if (gameState == STATE.Menu || gameState == STATE.Help) {// user
																		// is in
																		// help
																		// or
																		// the
																		// menu,
																		// draw
																		// the
																		// menu//
																		// and
																		// help
																		// objects
			menu.render(g);
		} else if (gameState == STATE.Upgrade) {// user is on the upgrade
												// screen, draw the upgrade
												// screen
			upgradeScreen.render(g);
		} else if (gameState == STATE.GameOver) {// game is over, draw the game
													// over screen
			gameOver.render(g);
		} else if (gameState == STATE.Victory){
			victory.render(g);
		}

		///////// Draw things above this//////////////
		g.dispose();
		bs.show();
	}

	/**
	 *
	 * Constantly checks bounds, makes sure players, enemies, and info doesn't
	 * leave screen
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

		// Screen size debug printout
		System.out.println("Screensize: " + screenSize);
		// Plays background music
		Thread thread = new Thread(new Sound(), "music");
		thread.start();

	}

	public void renderGameOver() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		///////// Draw things bellow this/////////////

		g.setColor(Color.black);
		g.fillRect(0, 0, (int) WIDTH, (int) HEIGHT);
		gameOver.render(g);
	}

	public GameOver getGameOver() {
		return gameOver;
	}

}
