package mainGame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

/**
 * The second boss in the game.
 *
 * @author Kevin Maeder 11/28/17
 *
 */

public class BossPong extends GameObject {

	private Image img;
	private float alpha = 1;
	private int tempCounter = 0;
	private double speed;
	private GameObject player,player2;
	private Handler handler;
	private double destX;
	private double destY;

	public BossPong(double x, double y, ID id, Handler handler) {
		super(x, y, id);
		this.img = getImage("images/KimmelImage.png");
		this.velX = 12;
		this.velY = 0;
		this.handler = handler;
		player = null;
		player2 = null;
		this.speed = 30;
		this.health = 1000;
	}

	public void tick() {
		if (tempCounter == 0) {
			tempCounter++;
			for (int i = 0; i < handler.object.size(); i++) {
				if (handler.object.get(i).getId() == ID.Player)
					this.player = handler.object.get(i);
				if (handler.object.get(i).getId() == ID.Player2)
					this.player2 = handler.object.get(i);
			}
		} 
		else if (tempCounter == 1) 
		{
			if (this.x < -15 || this.x > Game.WIDTH + 15)
			{
				calculate();
				this.health -=50;
			}
			move();
		}
	}

	public void move() 
	{		
		if (this.y >= Game.HEIGHT + 15 || this.y <= -15)
		{
			this.velY = -this.velY;
			this.y += this.velY;
			this.y += this.velY;
		}
		this.x += this.velX;
		this.y += this.velY;
	}
	
	public void calculate()
	{
		double diffY = (player.getY() - this.y);
		double diffX = (player.getX() - this.x);
		double ratio = (diffX / diffY);
		
		if (diffX > 0) 
		{
			diffX = Game.WIDTH - 240;
		}
		else 
		{
			diffX = -Game.WIDTH + 240; 
		}
		
		diffY = ((diffX - this.x)/ratio);
		diffY += this.y;
		
		destY = diffY;
		destX = diffX;	
		
		
		diffY = (destY - this.y);
		diffX = (destX - this.x);
		double distance = Math.sqrt(((this.x - destX) * (this.x - destX))
					+ ((this.y - destY) * (this.y - destY)));
		this.velX = (this.speed / distance) * diffX;
		this.velY = (this.speed / distance) * diffY;
	}

	public void render(Graphics g) {
		if (g.getColor() == Color.BLACK) {// prevent black text from showing "Game Over" if the player dies here, or
											// "Winner!" if the player survives
			g.setColor(Color.GREEN);
		}
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.drawImage(img, (int) this.x, (int) this.y, 300, 250, null);
		g2d.setComposite(makeTransparent(1));
		
		g.setColor(Color.GRAY);
		g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
		g.setColor(Color.RED);
		g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, this.health, 50);
		g.setColor(Color.WHITE);
		g.drawRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));

	}

	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 200, 200);
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
}