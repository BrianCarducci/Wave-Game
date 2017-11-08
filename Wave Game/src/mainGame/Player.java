package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * The main player in the game
 *
 * @author Brandon Loehle 5/30/16
 *
 */

public class Player extends GameObject {

	Random r = new Random();
	Handler handler;
	private HUD hud;
	private CoopHud hud2;
	private Game game;
	private int damage;
	private Image img;
	private KeyInput key;
	private int playerWidth, playerHeight;
	public static int playerSpeed = 10;
	private int timer;

	public Player(double x, double y, ID id, Handler handler, HUD hud, CoopHud hud2, Game game) {

		super(x, y, id);
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		this.damage = 1;
		this.hud2 = hud2;
		timer = 60;
		img = getImage("images/TrumpImage.png");

//		playerWidth = 32;
//		playerHeight = 32;
		
		if (this.id == ID.Player)
            img = getImage("images/TrumpImage.png");
        else if (this.id == ID.player2)
            img = getImage("images/HillaryImage.png");

	}


	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;
		x = Game.clamp(x, 0, Game.WIDTH - 95);
		y = Game.clamp(y, 0, Game.HEIGHT - 125);

		// add the trail that follows it
		//handler.addObject(new Trail(x, y, ID.Trail, Color.white, playerWidth, playerHeight, 0.05, this.handler));

		collision();
		
		if (timer == 0){
			timer = 60;
			playerSpeed = 10;
		}

		if (game.gameState == STATE.Game)
			checkIfDead();
		if (game.gameState == STATE.Coop)
			checkIfDeadCoop();

	}

	public void checkIfDead() {
		if (hud.health <= 0) {// player is dead, game over!
			if (hud.getExtraLives() == 0) {
				game.gameState = STATE.GameOver;
			}

			else if (hud.getExtraLives() > 0) {// has an extra life, game continues

				hud.setExtraLives(hud.getExtraLives() - 1);
				hud.restoreHealth();
			}
		}
	}

	public void checkIfDeadCoop() {
		if (hud.health <= 0 || hud2.health <= 0) {// player is dead, game over!
				if (hud.health <= 0) {
					if (hud.getExtraLives() == 0) {
						game.getGameOver().setWhoDied(1);
						game.renderGameOver();
						game.gameState = STATE.GameOver;
					}
				}if (hud2.health <= 0) {
					if (hud2.getExtraLives() == 0) {
						game.getGameOver().setWhoDied(2);
						game.renderGameOver();
						game.gameState = STATE.GameOver;
					}
				}

				if (hud.getExtraLives() > 0) {// has an extra life, game continues
					hud.setExtraLives(hud.getExtraLives() - 1);
					hud.setHealth(100);
				}
				if (hud2.getExtraLives() > 0) {
					hud2.setExtraLives(hud2.getExtraLives() - 1);
					hud2.setHealth(100);
				}
		}
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
					|| tempObject.getId() == ID.BossEye || tempObject.getId() == ID.HillaryBoss) {// tempObject is an
															// enemy

				// collision code

				if (getBounds().intersects(tempObject.getBounds())) {// player hit an enemy
					if(this.id == ID.Player) {
						hud.health -= damage;
						hud.updateScoreColor(Color.red);
					}
					else {
						hud2.health -= damage;
						hud2.updateScoreColor(Color.red);
					}

				}

			}
			if (tempObject.getId() == ID.EnemyBoss) {
				// Allows player time to get out of upper area where they will
				// get hurt once the
				// boss starts moving
				if (this.y <= 138 && tempObject.isMoving) {
					if (this.id == ID.Player) {
						hud.health -= 2;
						hud.updateScoreColor(Color.red);
					}else {
						hud2.health -= 2;
						hud2.updateScoreColor(Color.red);
					}
				}
			}

		}

		// for loop that checks to see if player runs into pickup
		// if player does, affect player, remove item from array
		for (int i = 0; i < handler.pickups.size(); i++) {
			Pickup tempObject = handler.pickups.get(i);
			if (tempObject.getId() == ID.PutinHealth) {
				if (getBounds().intersects(tempObject.getBounds())) {

					if (hud.health >= 60) {
						hud.setHealth(100);
					} else {
						hud.setHealth(hud.health + 40);
					}
					handler.removePickup(tempObject);
				}
			}
			if (tempObject.getId() == ID.EminemHealth) {
				if (getBounds().intersects(tempObject.getBounds())) {

					if (hud.health <= 40) {
						hud.setHealth(10);
					} else {
						hud.setHealth(hud.health - 20);

					}
					handler.removePickup(tempObject);
				}
			}
			if (tempObject.getId() == ID.TwitterSpeed) {
				if (getBounds().intersects(tempObject.getBounds())) {
					playerSpeed = 20;
					handler.removePickup(tempObject);
				}
			}
			if (tempObject.getId() == ID.NFLSpeed) {
				if (getBounds().intersects(tempObject.getBounds())) {
					playerSpeed = 5;
					handler.removePickup(tempObject);
				}
			}
			
			if (tempObject.getId() == ID.HillaryEmail) {
				if (getBounds().intersects(tempObject.getBounds())) {
					
					if (playerSpeed > 0){
					playerSpeed--;
					} 
					
					handler.removePickup(tempObject);
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
