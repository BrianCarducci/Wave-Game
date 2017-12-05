package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainGame.Game.STATE;
/**
 * HUD for the server
 * @author Ryan Hanlon 11/22/17
 */
public class ServerHUD {
	public double health = 500;
	private double healthMax = 100;
	private double greenValue = 500;
	private int score = 00000000000;
	//private int level = 0;
	private int timer = 60;
	private int healthBarWidth = 400;
	private int healthBarModifier = 2;
	private boolean doubleHealth = false;
	private String ability = "";
	private int abilityUses;
	private Color scoreColor = Color.white;
	private int extraLives = 0;
	private STATE state;
	private boolean isServer = false;
	
	public void tick() {
		health = Game.clamp(health, 0, health);
		greenValue = Game.clamp(greenValue, 0, 500);
		greenValue = health * healthBarModifier;

	}

	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 30);
		//g.setColor(Color.GRAY);
		//g.fillRect(Game.WIDTH/2 , 15, 1200, 100);
		//g.setColor(new Color(75, 255, 0));
		g.setColor(new Color(0, 119, 255));
		g.fillRect((int) 15, (int) 15, (int) health * 4, 64);
		g.setColor(scoreColor);
		
		//g.drawRect(15, 15, healthBarWidth, 64);
		g.setFont(font);
		g.drawString("Score: " + score, 15, 115);
		//g.drawString("Extra Lives: " + extraLives, 15, 185);
		
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

	public void setHealth(double d) {
		this.health = d;
	}

	public void resetHealth() {
		doubleHealth = false;
		healthMax = 500;
		this.health = healthMax;
		healthBarModifier = 2;
		healthBarWidth = 400;
	}

	public void restoreHealth() {
		this.health = healthMax;
	}
	
	public void updateScore() {
		score++;
	}
	
	//used to set the variable gamestate to whatever state the game is in
	//used mainly for coop implementation
	public void setState(STATE n) {
		state = n;
	}
	
	//returns the game state
	public STATE getState() {
		return state;
	}
	
	public void setServer(boolean isServer){
		this.isServer = isServer;
	}
	
	public boolean getServer(){
		return this.isServer;
	}
}
