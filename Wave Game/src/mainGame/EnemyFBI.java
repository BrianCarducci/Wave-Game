package mainGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A new type of enemy added into the game (cycle 3)
 * 
 * @author Eric Kinney 11/6/17
 *
 */

public class EnemyFBI extends GameObject {

	private Handler handler;
	private GameObject player, player2;
	private int speed;
	private int timer;
	private int sizeX;
	private int sizeY;
	private double bulletVelX;
	private double bulletVelY;
	private int bulletSpeed;

	public EnemyFBI(double x, double y, int speed, int bulletSpeed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		this.velX = 0;
		this.velY = 0;
		this.bulletSpeed = bulletSpeed;
		this.timer = 0;
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
		this.x += velX;
		this.y += velY;

		if (this.y <= 0 || this.y >= Game.HEIGHT - 40)
			velY *= -1;
		if (this.x <= 0 || this.x >= Game.WIDTH - 16)
			velX *= -1;

		handler.addObject(new Trail(x, y, ID.Trail, Color.red, this.sizeX, this.sizeY, 0.025, this.handler));

		timer--;
		if (timer <= 0) {
			shoot();
			timer = 55;
		}

		this.x += velX;
		this.y += velY;
		////////////////////////////// pythagorean theorem
		////////////////////////////// below//////////////////////////////////////////////////
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
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

		handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 16, 16, 0.025, this.handler));

	}
	
public void shoot() {
		
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		
		if(player2 != null) {
			double diffX2 = this.x - player2.getX() - 16;
			double diffY2 = this.y - player2.getY() - 16;
			double distance2 = Math.sqrt(((this.x - player2.getX()) * (this.x - player2.getX()))
					+ ((this.y - player2.getY()) * (this.y - player2.getY())));
			
			if(distance < distance2) {
				bulletVelX = ((this.bulletSpeed / distance) * diffX); // numerator affects speed of enemy
				bulletVelY = ((this.bulletSpeed / distance) * diffY);// numerator affects speed of enemy
			}else  {
				bulletVelX = ((this.bulletSpeed / distance2) * diffX2); // numerator affects speed of enemy
				bulletVelY = ((this.bulletSpeed / distance2) * diffY2);// numerator affects speed of enemy
			}
		}
		
		if (player2 == null) {
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		bulletVelX = ((this.bulletSpeed / distance) * diffX); // numerator affects speed of enemy
		bulletVelY = ((this.bulletSpeed / distance) * diffY);// numerator affects speed of enemy
		}
		
		handler.addObject(
				new EnemyShooterBullet(this.x, this.y, bulletVelX, bulletVelY, ID.EnemyShooterBullet, this.handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 16, 16);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}