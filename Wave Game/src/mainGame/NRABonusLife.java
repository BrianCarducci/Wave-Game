package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
/**
 * 
 * @author Kyle Horton
 * 
 * Pickup that adds a bonus life.
 *
 */
public class NRABonusLife extends Pickup{
	
	private Handler handler;

	public NRABonusLife(ID id, Handler handler) {
		super((Game.WIDTH - 90)*Math.random(), (Game.HEIGHT - 120)*Math.random(), id);
		this.handler = handler;
		velX = Math.random()*5;
		velY = Math.random()*5;
		img = getImage("images/NRAImage.png");
	}


	public void tick() {
		
		this.x += velX;
		this.y += velY;
		
		if (this.x <= 0 || this.x >= Game.WIDTH - 100){
			velX *= -1;
		}
		if (this.y<= 0 || this.y >= Game.HEIGHT - 100){
			velY *= -1;
		}
		
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


	public void render(Graphics g) {
		g.drawImage(img, (int) this.x, (int) this.y, 90, 90, null);
		
	}


	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 90, 90);
	}

}


