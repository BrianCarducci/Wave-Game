package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

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
	private AttackHUD attackHUD;
	private ServerHUD serverHUD;
	private int timer;
	private Color retryColor;
	private String text, text2;
	private int player, coopPlayer;

	public GameOver(Game game, Handler handler, HUD hud, CoopHud hud2, AttackHUD attackHUD, ServerHUD serverHUD) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.hud2 = hud2;
		this.attackHUD = attackHUD;
		this.serverHUD = serverHUD;
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
		} else if (player == 2) {
			this.player = 2;
		}
	}

	public String getWhoWon() {
		return "Player " + coopPlayer;
	}

	// string method that uses variable to calculate who died
	public String getWhoDied() {
		if (player == 1) {
			return "Player 2 ";
		} else if (player == 2) {
			return "Player 1 ";
		} else {
			return null;
		}
	}

	public void render(Graphics g) {
		Font font = new Font("Stencil", 1, 100);
		Font font2 = new Font("Stencil", 1, 60);
		g.setFont(font);
		g.setColor(Color.red);

		if (attackHUD.getAttack() == true) {
			
			System.out.println("Hello World");

			// resets the hud level text
			if (attackHUD.getBoss() == true) {
				text = "Game Over";
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
				g.setFont(font2);
				text = "" + attackHUD.getBossLevel();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);

			} else {
				text = "Game Over";
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
				g.setFont(font2);
				text = "Wave " + attackHUD.getLevel();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2);
				text = "Score: " + attackHUD.getScore();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);
			}

		} else if (hud.getWave() == true) {

			if (hud.getBoss() == true) {
				text = "Game Over";
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
				g.setFont(font2);
				text = "" + hud.getBossLevel();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2);
				text = "Score: " + hud.getScore();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);
			} else {
				text = "Game Over";
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);
				g.setFont(font2);
				text = "Level: " + hud.getLevel();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2);
				text = "Score: " + hud.getScore();
				g.drawString(text, Game.WIDTH / 2 - getTextWidth(font2, text) / 2, Game.HEIGHT / 2 - 50);

			}

		} else if (hud2.getCoop() == true) {

			if (coopPlayer != 0 && getWhoWon() != null) {
				text2 = getWhoWon() + " Wins!!";
				g.drawString(text2, Game.WIDTH / 2 - getTextWidth(font, text2) / 2, Game.HEIGHT / 2 - 300);
			}
			if (player != 0 && getWhoDied() != null) {
				text2 = getWhoDied() + " Wins!!";
				g.drawString(text2, Game.WIDTH / 2 - getTextWidth(font, text2) / 2, Game.HEIGHT / 2 - 300);
			}

		} else if (serverHUD.getServer() == true) {

			text = "Game Over";
			g.drawString(text, Game.WIDTH / 2 - getTextWidth(font, text) / 2, Game.HEIGHT / 2 - 150);

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

	public void setWinner(int n) {
		coopPlayer = n;
	}

}