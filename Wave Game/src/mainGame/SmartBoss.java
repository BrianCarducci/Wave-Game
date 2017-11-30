package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartBoss extends GameObject {
	
	private Handler handler;
	private GameObject player, player2;
	private int speed;

	public SmartBoss(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		player = null;
		this.health = 100;
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}

	}

	
	//alter the Algorithm to take the player closest to it to lock on to.
	public void tick() {
		////////////////////////////// pythagorean theorem below//////////////////////////////////////////////////
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem above//////////////////////////////////////////////////
		this.x += velX;
		this.y += velY;

		velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy

		handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 200, 200, 0.025, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, 200, 200);
		
		// HEALTH BAR
		g.setColor(Color.GRAY);
		g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);
		g.setColor(Color.RED);
		g.fillRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, this.health, 50);
		g.setColor(Color.WHITE);
		g.drawRect(Game.WIDTH / 2 - 500, Game.HEIGHT - 150, 1000, 50);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 200, 200);
	}

}
