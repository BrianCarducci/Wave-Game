package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.AlphaComposite;

/**
 * Victory Screen
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Victory {

	private Game game;
	private Handler handler;
	private HUD hud;
	private int timer;
	private Color menuColor;
	private String text;
	private float alpha = 1;

	public Victory(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		timer = 90;
		this.menuColor = Color.white;
	}

	public void tick() {
		flash();
	}

	public void render(Graphics g) {
		Font font = new Font("Amoebic", 1, 100);
		Font font2 = new Font("Amoebic", 1, 60);
		g.setFont(font);
		text = "You Won";
		g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
		
		text = "Score: " + hud.getScore();
		g.drawString(text, (Game.WIDTH / 2) - (getTextWidth(font2, text) / 2) - 150, Game.HEIGHT / 2 + 25);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));

		g.setColor(Color.white);
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
