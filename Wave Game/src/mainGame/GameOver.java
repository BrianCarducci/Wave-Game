package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.AlphaComposite;

import mainGame.Game.STATE;

/**
 * The game over screen
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class GameOver {

	private Game game;
	private Handler handler;
	private HUD hud;
	private CoopHud hud2;
	private int timer;
	private Color retryColor;

	private float alpha = 1;
	private String text,text2;
	private int player;
	
	public GameOver(Game game, Handler handler, HUD hud, CoopHud hud2) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.hud2 = hud2;
		timer = 90;
		this.retryColor = Color.white;
		player = 0;
	}

	public void tick() {
		handler.clearPlayer();
		flash();

	}
	public void setWhoDied(int player) {
		if (player == 1) {
			this.player = 1;
		}
		else if (player == 2) {
			this.player = 2;
		}
	}
	
	public String getwhodied() {
		if (player == 1) {
			return "Player 2 ";
		}
		else {
			return "Player 1 ";
		}
	}
	
	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 100);
		Font font2 = new Font("Amoebic", 1, 60);
		g.setFont(font);
		text = "Game Over";

			text2 = getwhodied() + " Wins!!";
			g.drawString(text2, Game.WIDTH/2 - getTextWidth(font,text2)/2, Game.HEIGHT/2 - 300);
		
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
		g.setFont(font2);
		
		if (hud.getBoss() == true){
			text = "Level: " + hud.getBossLevel();
		} else {
			text = "Level: " + hud.getLevel();
		}
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);
		text = "Score: " + hud.getScore();
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 + 50);
		g.setColor(this.retryColor);
		g.setFont(font2);
		text = "Click anywhere to play again";
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 + 150);
		g2d.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}
	
	public void flash() {
		timer--;
		if (timer == 45) {
			this.alpha = 0;
		} else if (timer == 0) {
			this.alpha = 1;
			timer = 90;
		}
	}

	/**
	 * Function for getting the pixel width of text
	 * 
	 * @param font
	 *            the Font of the test
	 * @param text
	 *            the String of text
	 * @return width in pixels of text
	 */
	public int getTextWidth(Font font, String text) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		int textWidth = (int) (font.getStringBounds(text, frc).getWidth());
		return textWidth;
	}

}
