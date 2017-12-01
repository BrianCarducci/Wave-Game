package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import mainGame.Game.STATE;

import java.awt.AlphaComposite;

/**
 * Victory Screen
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Victory {

	Sound sound = new Sound();
	private Game game;
	private Handler handler;
	private HUD hud;
	private CoopHud coopHUD;
	private AttackHUD attackHUD;
	private int timer;
	private Color retryColor;
	private String text, text2;
	private float alpha = 1;
	private int player, coopPlayer;
	

	public Victory(Game game, Handler handler, HUD hud, CoopHud coopHUD, AttackHUD attackHUD) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.coopHUD = coopHUD;
		this.attackHUD = attackHUD;
		timer = 90;
		this.retryColor = Color.white;
		player = 0;
		
	}
	


	public void tick() {
		flash();
		
	}

	
	
	public void render(Graphics g) {
		
		Font font = new Font("Stencil", 1, 100);
		Font font2 = new Font("Stencil", 1, 60);
		g.setFont(font);

		text = "You Won";
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
		g.setFont(font2);

		if (attackHUD.getAttack() == true) {
			text = "Score: " + attackHUD.getScore();
			g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);
		} else {
			text = "Score: " + hud.getScore();
			g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);

		}

		g.setColor(this.retryColor);
		g.setFont(font2);
		text = "Click anywhere to play again";
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 + 150);

	}
	
	public void flash() {
		timer--;
		if (timer == 45) {
			this.retryColor = Color.black;
		} else if (timer == 0) {
			this.retryColor = Color.white;
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
