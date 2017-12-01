package mainGame;
/**
 * Kyle Horton
 * 
 * Class that creates the ammo pickup in the Attack mode.
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

/**
 * 
 * @author Kyle Horton
 * 
 * Pickup that adds ammo in Attack mode.
 *
 */
public class AmmoPickup extends Pickup{
	
	private Handler handler;

	public AmmoPickup(ID id, Handler handler) {
		super((Game.WIDTH - 70)*Math.random(), (Game.HEIGHT - 120)*Math.random(), id);
		this.handler = handler;
		velX = 0;
		velY = 0;
		img = getImage("images/MaxAmmo.png");
	}


	public void tick() {
		
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

