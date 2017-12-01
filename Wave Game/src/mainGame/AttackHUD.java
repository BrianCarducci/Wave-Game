package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainGame.Game.STATE;

/**
 * 
 * @author Kyle Horton
 * 
 * The heads up display for the Attack mode.
 *
 */
public class AttackHUD {
	
	// instance variables
	public double health;
	private double greenValue = 255;
	private int score = 0;
	private int level = 0;
	private String boss = "";
	private boolean isBoss = false;
	private boolean isAttack = false;
	private int healthBarWidth = 600;
	private int healthBarModifier = 2;
	private int abilityUses;
	private Color scoreColor = Color.white;
	private int ammo = 30;
	private int mag = 360;
	
	public void tick() {
		health = Game.clamp(health, 0, health);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = health * healthBarModifier;
	}

	public void render(Graphics g) {
		Font font = new Font("Stencil", 1, 30);
		Font font2 = new Font("Stencil", 1, 100);

		g.setColor(Color.GRAY);
		g.fillRect(15, 15, healthBarWidth, 64);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect((int) 15, (int) 15, (int) health *6, 64);
		g.setColor(scoreColor);
		
		g.drawRect(15, 15, healthBarWidth, 64);
		g.setFont(font);
		
		g.drawString("Score: " + score, 15, 115);
		
		
		// switches display based on if player has ammo or needs to reload
		if (ammo > 0){
		g.drawString("Ammo: " + ammo + "/" + mag , 1600, 60);
		} if (ammo == 0 && mag > 0) {
			g.drawString("PRESS 'ENTER' OR 'R' TO RELOAD!" , 1300, 60);
		} if (ammo == 0 && mag == 0) {
			g.drawString("OUT OF AMMO!!!" , 1600, 60);
		}
		
		// switches display based on if player is on boss or not
		g.setFont(font2);
		if (isBoss == false) {
		g.drawString("WAVE " + level, Game.WIDTH/2 - 150, 100);
		} else {
			g.drawString("" + boss, Game.WIDTH/2 - 200, 100);
		}
	}

	public int getAbilityUses() {
		return this.abilityUses;
	}

	public void setAbilityUses(int abilityUses) {
		this.abilityUses = abilityUses;
	}

	public void updateScoreColor(Color color) {
		this.scoreColor = color;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getBossLevel(){
		return boss;
	}

	public void setBossLevel(String boss){
		this.boss = boss;
	}

	public boolean getBoss(){
		return isBoss;
	}

	public void setBoss(boolean isBoss){
		this.isBoss = isBoss;
	}
	
	public boolean getAttack(){
		return isAttack;
	}
	
	public void setAttack(boolean isAttack){
		this.isAttack = isAttack;
	}

	public void setHealth(double d) {
		this.health = d;
	}
	
	public void setAmmo(int ammo){
		this.ammo = ammo;
	}
	
	public int getAmmo(){
		return ammo;
	}
	
	public void setMag(int mag){
		this.mag = mag;
	}
	
	public int getMag(){
		return mag;
	}

}
