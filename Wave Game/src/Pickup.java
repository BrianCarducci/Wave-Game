package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

/**
 * TO BE IMPLEMENTED - class for pickups that affect the player, such as health
 * boost, speed boost, etc...
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public abstract class Pickup {

	protected double x;
	protected double y;
	protected ID id;
	protected String path;
	protected Image img;
	protected double velX, velY;
	protected boolean isMoving;
	protected int health;

	public Pickup(double x, double y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}

	public Image setImg(String path) {
		Image img = null;
		try {
			URL imageURL = Game.class.getResource(path);
			img = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	public double getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getHealth() {
		return this.health;
	}

}
