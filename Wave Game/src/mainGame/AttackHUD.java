package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainGame.Game.STATE;

public class AttackHUD {
	
	public double health = 100;
	private double healthMax = 100;
	private double greenValue = 255;

	private int score = 00000000000;

	private int level = 0;
	private String boss = "";
	private boolean isBoss = false;
	private boolean regen = false;
	private boolean isAttack = false;
	private int timer = 60;
	private int healthBarWidth = 400;
	private int healthBarModifier = 2;
	private int abilityUses;
	private Color scoreColor = Color.white;
	private int ammo = 30;
	private int mag = 360;
	
	public void tick() {
		health = Game.clamp(health, 0, health);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = health * healthBarModifier;

		
		if (regen) {// regenerates health if that ability has been unlocked
			timer--;
			if (timer == 0 && health < 100) {
				health += 1;
				timer = 60;
			}
		}
	}

	public void render(Graphics g) {
		Font font = new Font("Stencil", 1, 30);

		g.setColor(Color.GRAY);
		g.fillRect(15, 15, healthBarWidth, 64);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect((int) 15, (int) 15, (int) health * 4, 64);
		g.setColor(scoreColor);
		
		g.drawRect(15, 15, healthBarWidth, 64);
		g.setFont(font);
		
		g.drawString("Score: " + score, 15, 115);
		
		if (ammo > 0){
		g.drawString("Ammo: " + ammo + "/" + mag , 1600, 60);
		} if (ammo == 0 && mag > 0) {
			g.drawString("PRESS ENTER TO RELOAD!" , 1450, 60);
		} if (ammo == 0 && mag == 0) {
			g.drawString("OUT OF AMMO!!!" , 1600, 60);
		}
		
		
		
		if (isBoss == false) {
		g.drawString("WAVE " + level, 15, 150);
		} else {
			g.drawString("" + boss, 15, 150);
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

	public void setRegen() {
		regen = true;
	}

	public void resetRegen() {
		regen = false;
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
	


	public void healthIncrease() {
		healthMax = 200;
		this.health = healthMax;
		healthBarModifier = 1;
		healthBarWidth = 800;
	}

	public void resetHealth() {
		healthMax = 100;
		this.health = healthMax;
		healthBarModifier = 2;
		healthBarWidth = 400;
	}

	public void restoreHealth() {
		this.health = healthMax;
	}

}
