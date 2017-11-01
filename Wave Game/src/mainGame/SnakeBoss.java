package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Random;

public class SnakeBoss extends GameObject {

	private Image head;
	private Image body;
	private Random r = new Random();
	private double speed;
	private GameObject player, player2;
	private Handler handler;
	private int bodyVelX;
	private int bodyVelY;

	public SnakeBoss(double x, double y, ID id, Handler handler) {
		super(x, y, id);

		this.velX = 0;
		this.velY = 0;
		bodyVelX = 0;
		bodyVelY = 0;
		this.speed = -5;
		player = null;
		this.handler = handler;
		this.head = getImage("images/HillaryImage.png");
		this.body = getImage("images/SnakeBody.png");

	}

	@Override
	public void tick() {

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				this.player = handler.object.get(i);
		}

			attackHead(this.head);

	}
	
	public void attackHead(Image image){
		double diffY = (this.y - player.getY());
		double diffX = (this.x - player.getX());
		double distance = Math.sqrt(((this.x - this.player.getX()) * (this.x - this.player.getX()))
				+ ((this.y - this.player.getY()) * (this.y - this.player.getY())));
		this.velX = (this.speed / distance) * diffX;
		this.velY = (this.speed / distance) * diffY;
		this.x += this.velX;
		this.y += this.velY;
	}

	public void render(Graphics g) {
		g.drawImage(head, (int) this.x, (int) this.y, 100, 100, null);
		g.drawImage(body, (int) this.x - 40, (int) this.y + 20, 50, 50, null);
		g.drawImage(body, (int) this.x - 90, (int) this.y + 20, 50, 50, null);
		g.drawImage(body, (int) this.x - 140, (int) this.y + 20, 50, 50, null);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 100, 100);
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
