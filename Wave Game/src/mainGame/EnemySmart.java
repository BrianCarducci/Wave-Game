package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemySmart extends GameObject {

	private Handler handler;
	private GameObject player, player2;
	private int speed;

	public EnemySmart(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		player = null;
		player2 = null;
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
			if (handler.object.get(i).getId() == ID.Player2)
				player2 = handler.object.get(i);
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
		
		if(player2 != null) {
		double diffx2 = this.x - player2.getX() - 16;
		double diffy2 = this.y - player2.getY() - 16;
		double distance2 = Math.sqrt(((this.x - player2.getX()) * (this.x - player2.getX())) + 
				((this.y - player2.getY()) * (this.y - player2.getY())));
		// Conditional to pull the enemy to different players
		if (distance < distance2) {
			velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
			velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy
		} else {
			velX = ((this.speed / distance2) * diffx2); // numerator affects speed of enemy
			velY = ((this.speed / distance2) * diffy2);// numerator affects speed of enemy
		} 
		
		}
		if(player2 == null) {
		velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy
		}

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.025, this.handler));

	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
