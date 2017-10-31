package mainGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

public class SnakeBoss extends GameObject {
	
	private Image img;
	private double speed;
	private GameObject player, player2;
	private Handler handler;
	
	public SnakeBoss(double x, double y, ID id, Handler handler){
		super(x, y, id);
		
		this.velX = 0;
		this.velY = 0;
		this.player = player;
		this.handler = handler;
		this.img = getImage("images/HillaryImage.png");
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				this.player = handler.object.get(i);
		}
	}

	@Override
	public void tick() {
		
		double diffY = (this.y - player.getY());
		double diffX = (this.x - player.getX());
		double distance = Math.sqrt(((this.x - this.player.getX()) * (this.x - this.player.getX()))
				+ ((this.y - this.player.getY()) * (this.y - this.player.getY())));
		this.velX = (this.speed / distance) * diffX;
		this.velY = (this.speed / distance) * diffY;
		this.x += this.velX;
		this.y += this.velY;
		
	}
	
//	public void attackPlayer(){
//		double diffY = (this.y - player.getY());
//		double diffX = (this.x - player.getX());
//		double distance = Math.sqrt(((this.x - this.player.getX()) * (this.x - this.player.getX()))
//				+ ((this.y - this.player.getY()) * (this.y - this.player.getY())));
//		this.velX = (this.speed / distance) * diffX;
//		this.velY = (this.speed / distance) * diffY;
//		this.x += this.velX;
//		this.y += this.velY;
//	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, (int) this.x, (int) this.y, 100, 100, null);
		
	}

	@Override
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
