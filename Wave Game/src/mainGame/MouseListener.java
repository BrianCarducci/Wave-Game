package mainGame;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import mainGame.Game.STATE;

/**
 * Handles all mouse input
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class MouseListener extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private CoopHud hud2;
	private AttackHUD attackHUD;
	private ServerHUD serverHUD;
	private Spawn1to5 spawner;
	private Spawn5to10 spawner2;
	private Spawn10to15 spawner3;
	private Spawn15to20 spawner4;
	private AttackSpawn attackSpawn;
	private UpgradeScreen upgradeScreen;
	private Upgrades upgrades;
	private Player player, player2;
	private Server server;
	private String upgradeText;
	private double width;
	private double height;
	private double bulletX;
	private double bulletY;
	private int bulletSpeed;

	// this constructor not really necessary but I am leaving it just in case
	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to5 spawner, Spawn10to15 spawner2, Spawn15to20 spawner3,
			UpgradeScreen upgradeScreen, Player player, Upgrades upgrades) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		this.spawner3 = spawner2;
		this.spawner4 = spawner3;
		this.upgradeScreen = upgradeScreen;
		this.player = player;
		this.upgrades = upgrades;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (double) screenSize.getWidth();
		height = (double) screenSize.getHeight();
	}

	// added second constructor in case of multiplayer
	public MouseListener(Game game, Handler handler, HUD hud, CoopHud hud2, ServerHUD serverHUD, AttackHUD attackHUD, Spawn1to5 spawner, Spawn5to10 spawner2,
			Spawn10to15 spawner3, Spawn15to20 spawner4, AttackSpawn attackSpawn, UpgradeScreen upgradeScreen, Player player, Player player2, Server server,
			Upgrades upgrades) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.hud2 = hud2;
		this.attackHUD = attackHUD;
		this.serverHUD = serverHUD;
		this.spawner = spawner;
		this.spawner2 = spawner2;
		this.spawner3 = spawner3;
		this.spawner4 = spawner4;
		this.attackSpawn = attackSpawn;
		this.upgradeScreen = upgradeScreen;
		this.player = player;
		this.player2 = player2;
		this.server = server;
		this.upgrades = upgrades;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (double) screenSize.getWidth();
		height = (double) screenSize.getHeight();
		bulletX = 0;
		bulletY = 0;
		bulletSpeed = -10;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		// mouselistener that checks if the game state is gameover and resets
		// variables on restart
		if (game.gameState == STATE.GameOver) {
			handler.object.clear();
			handler.pickups.clear();
			upgrades.resetUpgrades();
			hud.health = 100;
			player.playerSpeed = 10;
			hud.setScore(0);
			hud.setLevel(1);
			hud2.health = 100;
			hud2.setScore(0);
			attackHUD.setScore(0);
			attackHUD.setLevel(1);
			attackHUD.health = 100;
			attackHUD.setAmmo(30);
			attackHUD.setMag(300);
			attackSpawn.restart();
			spawner.restart();
			spawner.addLevels();
			spawner2.restart();
			spawner2.addLevels();
			spawner3.restart();
			spawner3.addLevels();
			spawner4.restart();
			spawner4.addLevels();
			hud.resetVote();
			hud.setState(STATE.Menu);
			hud2.resetVote();
			player = new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler, this.hud, this.hud2,
					this.attackHUD, this.serverHUD, game);
			player2 = new Player(width / 2 + 100, height / 2 - 32, ID.Player2, handler, this.hud, this.hud2,
					this.attackHUD, this.serverHUD, game);
			server		= new Server(width / 2 - 32, height / 2 - 32, ID.Server, handler, this.serverHUD, game);
			Spawn1to5.LEVEL_SET = 1;
			game.gameState = STATE.Menu;
			hud.setBoss(false);
			attackHUD.setBoss(false);
			player.setShooting(false);
			attackHUD.setAttack(false);
			hud.setWave(false);
			hud2.setCoop(false);
			serverHUD.setServer(false);
		}
		
		else if (game.gameState == STATE.Victory){
			handler.object.clear();
			handler.pickups.clear();
			upgrades.resetUpgrades();
			hud.health = 100;
			player.playerSpeed = 10;
			hud.setScore(0);
			hud.setLevel(1);
			hud2.health = 100;
			hud2.setScore(0);
			attackHUD.setScore(0);
			attackHUD.setLevel(1);
			attackHUD.health = 100;
			attackHUD.setAmmo(30);
			attackHUD.setMag(300);
			attackSpawn.restart();
			spawner.restart();
			spawner.addLevels();
			spawner2.restart();
			spawner2.addLevels();
			spawner3.restart();
			spawner3.addLevels();
			spawner4.restart();
			spawner4.addLevels();
			hud.resetVote();
			hud2.resetVote();
			player = new Player(width / 2 - 32, height / 2 - 32, ID.Player, handler, this.hud, this.hud2,
					this.attackHUD, this.serverHUD, game);
			player2 = new Player(width / 2 + 100, height / 2 - 32, ID.Player2, handler, this.hud, this.hud2,
					this.attackHUD, this.serverHUD, game);
			Spawn1to5.LEVEL_SET = 1;
			game.gameState = STATE.Menu;
			hud.setBoss(false);
			attackHUD.setBoss(false);
			attackHUD.setAttack(false);
			player.setShooting(false);
		}

		else if (game.gameState == STATE.Game) {

		}

		else if (game.gameState == STATE.Upgrade) {
			if (mouseOver(mx, my, 100, 300, 1721, 174)) {
				upgradeText = upgradeScreen.getPath(1);
				upgrades.activateUpgrade(upgradeText);
				upgradeScreen.removeUpgradeOption(1);
				game.gameState = STATE.Game;

			} else if (mouseOver(mx, my, 100, 300 + (60 + Game.HEIGHT / 6), 1721, 174)) {
				upgradeText = upgradeScreen.getPath(2);
				upgrades.activateUpgrade(upgradeText);
				upgradeScreen.removeUpgradeOption(2);
				game.gameState = STATE.Game;

			} else if (mouseOver(mx, my, 100, 300 + 2 * (60 + Game.HEIGHT / 6), 1721, 174)) {
				upgradeText = upgradeScreen.getPath(3);
				upgrades.activateUpgrade(upgradeText);
				upgradeScreen.removeUpgradeOption(3);
				game.gameState = STATE.Game;
			}

		}

		// mouse actions for attack mode
		// only allows player to shoot if ammo available
		else if (game.gameState == STATE.Attack) {

			if (attackHUD.getAmmo() > 0) {
				player.setShooting(true);

				// pythagorean theorem to direct where the bullets go
				double diffX = player.getX() - mx - 16;
				double diffY = player.getY() - my - 16;
				double distance = Math.sqrt(
						((player.getX() - mx) * (player.getX() - mx)) + ((player.getY() - my) * (player.getY() - my)));
				bulletX = ((this.bulletSpeed / distance) * diffX);
				bulletY = ((this.bulletSpeed / distance) * diffY);
				
				player.setBulletX(bulletX);
				player.setBulletY(bulletY);

			}
		}

		else if (game.gameState == STATE.Menu) {
			// Waves Button
			if (mouseOver(mx, my, 990, 135, 400, 400)) {
				handler.object.clear();
				game.gameState = STATE.Game;
				handler.addObject(player);
				hud.setWave(true);

			}
			// multiplayer coop button
			else if (mouseOver(mx, my, 990, 535, 400, 400)) {
				handler.object.clear();
				game.gameState = STATE.Coop;
				handler.addObject(player);
				handler.addObject(player2);
				hud2.setCoop(true);
			}

			else if (mouseOver(mx, my, 1440, 585, 400, 400)) {
				handler.object.clear();
				game.gameState = STATE.Attack;
				handler.addObject(player);
				attackHUD.setAttack(true);
			}
			
			else if (mouseOver(mx, my, 1440, 135, 400, 400)){
				handler.object.clear();
				game.gameState = STATE.Defense;
				handler.addObject(player2);
				handler.addObject(server);
				serverHUD.setServer(true);
			}

			// Help Button
			else if (mouseOver(mx, my, 80, 135, 850, 250)) {
				game.gameState = STATE.Help;
			}

			// Credits
			else if (mouseOver(mx, my, 80, 435, 850, 250)) {
				JOptionPane.showMessageDialog(game,
						"Game made by Brandon Loehle in 2016." 
								+ "\n\nContributions of debugging and enhancements made by Kyle Horton, Rob Laudadio, Ryan Hanlon, "
								+ "Eric Kinney and Kevin Maeder for"
								+ "SER225 fall semester 2017."
								+ "\n\nThis game has minor bugs. However,"
								+ " it is 100% playable, enjoy!");
			}

			// Quit Button
			else if (mouseOver(mx, my, 80, 735, 850, 250)) {
				System.exit(1);
			}
		}

		// Back Button for Help screen
		else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 850, 800, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

		if (game.gameState == STATE.Attack) {
			player.setShooting(false);
			player.setBulletX(0);
			player.setBulletY(0);
		}

	}

	/**
	 * Helper method to detect is the mouse is over a "button" drawn via
	 * Graphics
	 * 
	 * @param mx
	 *            mouse x position
	 * @param my
	 *            mouse y position
	 * @param x
	 *            button x position
	 * @param y
	 *            button y position
	 * @param width
	 *            button width
	 * @param height
	 *            button height
	 * @return boolean, true if the mouse is contained within the button
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}