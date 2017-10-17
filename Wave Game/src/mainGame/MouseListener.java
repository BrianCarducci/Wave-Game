package mainGame;

import java.awt.Dimension;
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
	private Spawn1to10 spawner;
	private Spawn10to20 spawner2;
	private UpgradeScreen upgradeScreen;
	private Upgrades upgrades;
	private Player player, player2;
	private String upgradeText;
	private double width;
	private double height;

	//this constructor not really necessary but I am leaving it just in case
	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to10 spawner, Spawn10to20 spawner2,
			UpgradeScreen upgradeScreen, Player player, Upgrades upgrades) {
		this.game 			= game;
		this.handler 		= handler;
		this.hud 			= hud;
		this.spawner 		= spawner;
		this.spawner2 		= spawner2;
		this.upgradeScreen 	= upgradeScreen;
		this.player 		= player;
		this.upgrades 		= upgrades;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (double) screenSize.getWidth();
		height = (double) screenSize.getHeight();
	}
	//added second constructor in case of multiplayer
	public MouseListener(Game game, Handler handler, HUD hud, Spawn1to10 spawner, Spawn10to20 spawner2,
			UpgradeScreen upgradeScreen, Player player, Player player2, Upgrades upgrades) {
		this.game 			= game;
		this.handler 		= handler;
		this.hud 			= hud;
		this.spawner 		= spawner;
		this.spawner2 		= spawner2;
		this.upgradeScreen 	= upgradeScreen;
		this.player 		= player;
		this.player2 		= player2;
		this.upgrades 		= upgrades;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (double) screenSize.getWidth();
		height = (double) screenSize.getHeight();
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		//mouselistener that checks if the game state is gameover and resets variables on restart
		if (game.gameState == STATE.GameOver) {
			handler.object.clear();
			upgrades.resetUpgrades();
			hud.health = 100;
			hud.setScore(0);
			hud.setLevel(1);
			spawner.restart();
			spawner.addLevels();
			spawner2.restart();
			spawner2.addLevels();
			player = new Player(width / 2 - 32, height/ 2 - 32, ID.Player, handler, this.hud, game);
			Spawn1to10.LEVEL_SET = 1;
			game.gameState = STATE.Menu;
			hud.setBoss(false);
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

		else if (game.gameState == STATE.Menu) {
			// Waves Button
			if (mouseOver(mx, my, 990, 135, 400, 400)) {
				handler.object.clear();
				game.gameState = STATE.Game;
				handler.addObject(player);
				// handler.addPickup(new PickupHealth(100, 100, ID.PickupHealth,
				// "images/PickupHealth.png", handler));
			}
			//multiplayer coop button
			else if(mouseOver(mx,my,990,635,400,400)) {
				handler.object.clear();
				game.gameState = STATE.Coop;
				handler.addObject(player);
				handler.addObject(player2);
			}

			// Help Button
			else if (mouseOver(mx, my, 80, 135, 850, 250)) {
				game.gameState = STATE.Help;
			}

			// Credits
			else if (mouseOver(mx, my, 80, 435, 850, 250)) {
				JOptionPane.showMessageDialog(game,
						"Made by Brandon Loehle for his "
								+ "final project in AP Computer Science senior year, 2015 - 2016."
								+ "\n\nThis game is grossly unfinished with minor bugs. However,"
								+ " it is 100% playable, enjoy!");
			}

			// Quit Button
			else if (mouseOver(mx, my, 80, 735, 850, 250)) {
				System.exit(1);
			}
		}

		// Back Button for Help screen
		else if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 850, 300, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Helper method to detect is the mouse is over a "button" drawn via Graphics
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
