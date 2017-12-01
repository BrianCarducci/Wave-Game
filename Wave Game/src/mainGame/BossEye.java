package mainGame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

/**
 * The last boss in the game, shown in a 3x3 grid of 9 instances of BossEye
 *
 * @author Brandon Loehle 5/30/16
 *
 */

public class BossEye extends GameObject {

	private Image img;
	private Random r = new Random();
	private float alpha = 0;
	private double life = 0.005;
	private int tempCounter = 0;
	private int tempCounter2 = 2;
	private int timerA;
	private int timerB;
	private int spawnOrder = 1;// make them begin moving from left to right, top to bottom
	private int placement;// where they are in the 3x3 grid of eyes
	private double speed;
	private double[] speedTypes = { -5, -6, -7, -8, -9 };
	private GameObject player,player2;
	private Handler handler;
	private int max = 5;
	private int min = -5;
	private Color color = Color.red;

	public BossEye(double x, double y, ID id, Handler handler, int placement) {
		super(x, y, id);
		this.img = getImage("images/KimImage.png");
		this.velX = 0;
		this.velY = 0;
		this.speed = speedTypes[r.nextInt(4)];
		this.handler = handler;
		this.placement = placement;
		this.timerA = 200;
		this.timerB = 0;
		player = null;
		player2 = null;
	}

	public void tick() {
		if (tempCounter == 0) {
			if (alpha < 0.995) {// this handles each eye fading in to the game
				alpha += life + 0.001;
			} else {
				tempCounter++;
				for (int i = 0; i < handler.object.size(); i++) {
					if (handler.object.get(i).getId() == ID.Player)
						this.player = handler.object.get(i);
					if (handler.object.get(i).getId() == ID.Player2)
						this.player2 = handler.object.get(i);
				}
			}
		} else if (tempCounter == 1) {
			spawn();
			if (this.placement == 1 && this.spawnOrder >= 1) {
				attackPlayer();
			} else if (this.placement == 2 && this.spawnOrder >= 2) {
				attackPlayer();
			} else if (this.placement == 3 && this.spawnOrder >= 3) {
				attackPlayer();
			} else if (this.placement == 4 && this.spawnOrder >= 4) {
				attackPlayer();
			} else if (this.placement == 5 && this.spawnOrder >= 5) {
				attackPlayer();
			} else if (this.placement == 6 && this.spawnOrder >= 6) {
				attackPlayer();
			} else if (this.placement == 7 && this.spawnOrder >= 7) {
				attackPlayer();
			} else if (this.placement == 8 && this.spawnOrder >= 8) {
				attackPlayer();
			} else if (this.placement == 9 && this.spawnOrder >= 9) {
				attackPlayer();
			} else {
				this.health = 0;
			}
		}

	}

	public void spawn() {
		timerA--;
		timerB++;
		if (timerA == 0) {
			this.spawnOrder++;
			timerA = 100;
		}

		if (timerB == 1000) {
			this.speed-=2;
		}
		if (timerB == 2025) {
			GameObject tempObject = this;
			if (this.placement == tempCounter2) {
				sparks(tempObject);
			}

			tempCounter2 ++;
			timerB -= 200;
		}

	}

	public void attackPlayer() {
			double diffY = (this.y - player.getY());
			double diffX = (this.x - player.getX());
			double distance = Math.sqrt(((this.x - this.player.getX()) * (this.x - this.player.getX()))
					+ ((this.y - this.player.getY()) * (this.y - this.player.getY())));
			this.velX = (this.speed / distance) * diffX;
			this.velY = (this.speed / distance) * diffY;
			this.x += this.velX;
			this.y += this.velY;
		
		if (player2 != null) {
			double diffY2 = (this.y - player2.getY());
			double diffX2 = (this.x - player2.getX());
			double distance2 = Math.sqrt(((this.x - this.player2.getX()) * (this.x - this.player2.getX()))
					+ ((this.y - this.player2.getY()) * (this.y - this.player2.getY())));
			//change the enemy that is attacked in coop if one is closer than the other
			if(distance < distance2) {
				this.velX = (this.speed / distance) * diffX;
				this.velY = (this.speed / distance) * diffY;
				this.x += this.velX;
				this.y += this.velY;
			} else {
				this.velX = (this.speed / distance2) * diffX2;
				this.velY = (this.speed / distance2) * diffY2;
				this.x += this.velX;
				this.y += this.velY;
			}
		}
		
	}

	public void render(Graphics g) {
		if (g.getColor() == Color.BLACK) {// prevent black text from showing "Game Over" if the player dies here, or
											// "Winner!" if the player survives
			g.setColor(Color.GREEN);
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.drawImage(img, (int) this.x, (int) this.y, 100, 150, null);
		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));

	}

	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 100, 110);
	}

	public Image getImage(String path) {
		Image image = null;
		try {
			URL imageURL = Game.class.getResource(path);
			image = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;

	}

	public void sparks(GameObject tempObject) {// when the big circle breaks into a bunch of smaller ones
		for (int ii = 0; ii < 3; ii++) {
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), -5,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), -4,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), -3,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), -2,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), -1,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt(4) + 1), 0, this.color,
					ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, -(r.nextInt(4) + 1), 0, this.color,
					ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), 1,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), 2,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), 3,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), 4,
					this.color, ID.FireworkSpark, handler));
			handler.addObject(new MenuFireworks(this.x, this.y, 20, 20, (r.nextInt((max - min) + 1) + min), 5,
					this.color, ID.FireworkSpark, handler));
		}
	}

}
