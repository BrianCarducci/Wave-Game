package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * Server class object
 * @author Ryan Hanlon 11/22/17
 */

public class Server extends GameObject {
	private Thread healthThread;
	Random r = new Random();
	Handler handler;
	private ServerHUD hud;
	private Game game;
	private int damage;
	private Image img;
	private int playerWidth, playerHeight;
	private int timer;
	public int voteCount;

	public Server(double x, double y, ID id, Handler handler, ServerHUD hud, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		this.damage = 1;
		timer = 60;
		
		img = getImage("images/server.png");

	}


	@Override
	public void tick() {

		collision();
		if (this.checkIfDead() == false) {
			hud.updateScore();
		}
		checkIfDead();
		
	}

	public boolean checkIfDead() {
		if (hud.health <= 0) {// player is dead, game over!
				game.renderGameOver();
				game.getGameOver().setWhoDied(0);
				game.gameState = STATE.GameOver;
				handler.removeObject(this);
				return true;
			}
		else
			return false;
		}

	/**
	 * Checks for collisions with all of the enemies, and handles it accordingly
	 */
	public void collision() {

		hud.updateScoreColor(Color.white);
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.EnemyBasic || tempObject.getId() == ID.EnemyFast
					|| tempObject.getId() == ID.EnemySmart || tempObject.getId() == ID.EnemyBossBullet
					|| tempObject.getId() == ID.EnemySweep || tempObject.getId() == ID.EnemyShooterBullet
					|| tempObject.getId() == ID.EnemyBurst || tempObject.getId() == ID.EnemyShooter
					|| tempObject.getId() == ID.BossEye || tempObject.getId() == ID.HillaryBoss || tempObject.getId() == ID.EnemyFBI) {// tempObject is an enemy collision code

				if (getBounds().intersects(tempObject.getBounds())) {// player hit an enemy
					if(this.id == ID.Server) {
						hud.health -= damage;
						hud.updateScoreColor(Color.red);
					}


				}

			}
			

		}
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.white);
		g.drawImage(img, (int) this.x, (int) this.y, 75, 85, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 75, 85);
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setPlayerSize(int size) {
		this.playerWidth = size;
		this.playerHeight = size;
	}


	public Image getImage(String path) {
		Image image = null;
		try {
			URL imageURL = Game.class.getResource(path);
			image = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return image;
	}
}