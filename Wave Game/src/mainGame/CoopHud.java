package mainGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import mainGame.Game.STATE;

/**
 * Coop Heads up display for the game, similar to the normal hud just functions for player 2
 * 
 * @author Ryan Hanlon 10/23/17
 *
 */

public class CoopHud {

	public double health = 100;
	private double healthMax = 100;
	private double greenValue = 255;
	private int score = 00000000000;
	private int level = 0;
	private String boss = "";
	private boolean isBoss = false;
	private boolean mode = false;
	private int timer = 60;
	private int healthBarWidth = 400;
	private int healthBarModifier = 2;
	private boolean doubleHealth = false;
	private String ability = "";
	private int abilityUses;
	private Color scoreColor = Color.white;
	private int extraLives = 0;
	private STATE state = null;
	private int voteCount = 0;
	
	//hud begins ticking
	public void tick() {
		health = Game.clamp(health, 0, health);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = health * healthBarModifier;
		score++;
	}


	//renders the coophud
	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 30);
		g.setColor(Color.GRAY);
		g.fillRect(1485, 15, healthBarWidth, 64);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect((int) 1485, (int) 15, (int) health * 4, 64);
		g.setColor(scoreColor);
		g.drawRect(1485, 15, healthBarWidth, 64);
		g.setFont(font);
		if (state != STATE.Coop) {
			g.drawString("Score: " + score, 1485, 115);
		} else {
			g.drawString("Vote Count: " + voteCount, 1485, 115);
		}
	}

	public void setAbility(String ability) {
		this.ability = ability;
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
	
	public void setCoop(boolean mode){
		this.mode = mode;
	}
	
	public boolean getCoop(){
		return this.mode;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setExtraLives(int lives) {
		this.extraLives = lives;
	}

	public int getExtraLives() {
		return this.extraLives;
	}
	
	public void healthIncrease() {
		doubleHealth = true;
		healthMax = 200;
		this.health = healthMax;
		healthBarModifier = 1;
		healthBarWidth = 800;
	}

	public void resetHealth() {
		doubleHealth = false;
		healthMax = 100;
		this.health = healthMax;
		healthBarModifier = 2;
		healthBarWidth = 400;
	}

	public void restoreHealth() {
		this.health = healthMax;
	}
	
	//used to set the variable gamestate to whatever state the game is in
	//used mainly for coop implementation
	public void setState(STATE n) {
		state = n;
	}
	
	//used to update vote count to track in coop
	public void updateVote() {
		voteCount++;
	}
	//gets the vote count
	public int getVote() {
		return voteCount;
	}
	//resets the vote count
	public void resetVote() {
		voteCount = 0;
	}
	//returns the game state
	public STATE getState() {
		return state;
	}
}
