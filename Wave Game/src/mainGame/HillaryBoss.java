package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;
/**
 * 
 *  Kyle Horton
 *  Class that creates the Hillary boss. 
 *
 */
public class HillaryBoss extends GameObject {

	private Handler handler;
	private int timer;
	private int size;
	private String side;
	private Random r = new Random();
	private Image img;
	private Image img2;

	// constructor that takes in location and velocity factors
	HillaryBoss(double x, double y, double velX, double velY, int size, String side, ID id, Handler handler) {
		super(x, y, id);

		img = getImage("images/HillaryImage.png");
		img2 = getImage("images/SnakeBody.png");
		this.timer = 60;
		this.handler = handler;
		this.velX = velX;
		this.velY = velY;
		this.side = side;
		this.size = size;
		
		// checks to see which direction the boss is coming from
		if (this.side.equals("left")) {
			setPos();
			setVel();
		} else if (this.side.equals("right")) {
			setPos();
			setVel();

		} else if (this.side.equals("top")) {
			setPos();
			setVel();

		} else if (this.side.equals("bottom")) {
			setPos();
			setVel();
		}
	}

	@Override
	public void tick() {
		
		// updates timer and location with velocity
		timer--;
		
		if (timer <= 0) {
			this.x += velX;
			this.y += velY;

		}

	}

	// sets the starting point of the snake based on which string is pulled from the array in spawn class
	public void setPos() {
		if (this.side.equals("left")) {
			this.y = r.nextInt(((Game.HEIGHT - size) - 0) + 1) + 0;
		} else if (this.side.equals("right")) {
			this.x = Game.WIDTH + 200;
			this.y = r.nextInt(((Game.HEIGHT - size) - 0) + 1) + 0;

		} else if (this.side.equals("top")) {
			this.y = -(size);
			this.x = r.nextInt(((Game.WIDTH - size) - 0) + 1) + 0;

		} else if (this.side.equals("bottom")) {
			this.y = Game.HEIGHT + 200;
			this.x = r.nextInt(((Game.WIDTH - size) - 0) + 1) + 0;

		}
	}

	// sets the velocity based on which string is pulled from array in spawn class
	public void setVel() {
		if (this.side.equals("left")) {
			this.velY = 0;
		} else if (this.side.equals("right")) {
			this.velX = -(this.velX);
			this.velY = 0;

		} else if (this.side.equals("top")) {
			this.velX = 0;

		} else if (this.side.equals("bottom")) {
			this.velX = 0;
			this.velY = -(this.velY);
		}
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

	@Override
	public void render(Graphics g) {
		
		// draws head and body based on direction
		if (this.side.equals("left")) {
			g.drawImage(img, (int) this.x, (int) this.y, 200, 200, null);
			g.drawImage(img2, (int) (this.x) - 40, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 110, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 180, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 250, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 320, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 390, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 460, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 530, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 600, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) - 670, (int) (this.y) + 100, 70, 70, null);

		} else if (this.side.equals("right")) {

			g.drawImage(img, (int) this.x, (int) this.y, 200, 200, null);
			g.drawImage(img2, (int) (this.x) + 170, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 240, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 310, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 380, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 450, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 520, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 590, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 660, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 730, (int) (this.y) + 100, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 800, (int) (this.y) + 100, 70, 70, null);

		} else if (this.side.equals("top")) {

			g.drawImage(img, (int) this.x, (int) this.y, 200, 200, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 80, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 170, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 230, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 290, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 350, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 410, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 470, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 530, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 590, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) - 650, 70, 70, null);

		} else if (this.side.equals("bottom")) {

			g.drawImage(img, (int) this.x, (int) this.y, 200, 200, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 200, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 270, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 340, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 410, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 480, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 550, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 620, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 690, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 760, 70, 70, null);
			g.drawImage(img2, (int) (this.x) + 60, (int) (this.y) + 830, 70, 70, null);

		}

	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) this.x, (int) this.y, 200, 180);
	}
}

