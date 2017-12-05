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

public class EnemyDash extends GameObject {

	private Handler handler;
	private GameObject player, player2;
	private int speed;
	private double distance2;
	private boolean dashing;
	private double targetx;
	private double targety;

	public EnemyDash(double x, double y, int speed, ID id, Handler handler) {
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
		dashing = false;
	}
	
	//alter the Algorithm to take the player closest to it to lock on to.
	public void tick() {
		this.x += velX;
		this.y += velY;
		if (dashing == false)
		{
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
			distance2 = Math.sqrt(((this.x - player2.getX()) * (this.x - player2.getX())) + 
					((this.y - player2.getY()) * (this.y - player2.getY())));
			}
			else if(player2 == null) {
			distance2 = 200000000;
			}
			
			// Conditional to pull the enemy to different players
			if (distance < distance2) {
				velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
				velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy
				if(distance <= 300)
				{
					dashing = true;
					targetx = player.getX();
					targety = player.getY();
					this.speed *= 2.5;
				}
				}else {
				//velX = ((this.speed / distance2) * diffx2); // numerator affects speed of enemy
				//velY = ((this.speed / distance2) * diffy2);// numerator affects speed of enemy
				if(distance2 <= 300)
				{
					dashing = true;
					targetx = player2.getX();
					targety = player2.getY();
					this.speed *= 2.5;
				}
			}
		}
		else 
		{ // Second attack phase, the dash
			double diffX = this.x - targetx - 16;
			double diffY = this.y - targety - 16;
			double distance = Math.sqrt(((this.x - targetx) * (this.x - targetx))
					+ ((this.y - targety) * (this.y - targety)));
			
			velX = ((this.speed / distance) * diffX); 
			velY = ((this.speed / distance) * diffY);
			
			if ((this.x - targetx <= 5) && (this.y - targety <= 5))
			{
				dashing = false;
				this.speed = this.speed/2;
			}
		}

		
		handler.addObject(new Trail(x, y, ID.Trail, Color.orange, 32, 32, 0.025, this.handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int) x, (int) y, 32, 32);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}