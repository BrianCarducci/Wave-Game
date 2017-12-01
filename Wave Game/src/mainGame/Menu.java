package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainGame.Game.STATE;

/**
 * The main menu
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class Menu {

	private Game game;
	private Handler handler;
	private HUD hud;
	private BufferedImage img;
	private int timer;
	private Random r;
	private ArrayList<Color> colorPick = new ArrayList<Color>();
	private int colorIndex;
	private Spawn1to5 spawner;
	public Image image,image2;

	public Menu(Game game, Handler handler, HUD hud, Spawn1to5 spawner) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawner = spawner;
		timer = 10;
		r = new Random();
		addColors();
		//Old method to read images *Changed since does not support gifs!*
		/*
		img = null;
		try {
			img = ImageIO.read(new File("images/background2.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 */
			
		//List of new potential menu screens
		//image = new ImageIcon("images/background1.jpg").getImage();
	    image = new ImageIcon("images/background.gif").getImage();
	    image2 = new ImageIcon("images/Wave_Game_menu.png").getImage();
	    //image = new ImageIcon("images/background2.gif").getImage();
	    
	    //removed fireworks
		//handler.addObject(new MenuFireworks((r.nextInt(Game.WIDTH) - 25), 500, 50, 50, 0, -2,
		//	colorPick.get(r.nextInt(6)), ID.Firework, this.handler));
	}

	public void addColors() {
		colorPick.add(Color.blue);
		colorPick.add(Color.white);
		colorPick.add(Color.green);
		colorPick.add(Color.red);
		colorPick.add(Color.cyan);
		colorPick.add(Color.magenta);
		colorPick.add(Color.yellow);
		colorPick.add(Color.pink);
	}

	public void tick() {
		timer--;
		if (timer <= 0) {
			handler.object.clear();
			colorIndex = r.nextInt(6);
			//Removed fireworks
			//handler.addObject(new MenuFireworks((r.nextInt(Game.WIDTH) - 25), 1080, 100, 100, 0, -4,
			//	colorPick.get(colorIndex), ID.Firework, this.handler));
			timer = 300;
		}
		handler.tick();
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			g.drawImage(image, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			handler.render(g);
			Font font = new Font("Amoebic", 1, 100);
			Font font2 = new Font("Amoebic", 1, 60);
			Color color1 = new Color(255, 255, 225);

			g.setFont(font);
			g.setColor(color1);
			g.drawString("Game Modes", 1140, 100);

			g.setFont(font);
			g.setColor(color1);
			//g.drawString("Wave Game", 75, 100);
			g.drawImage(image2, 75, 25, 600, 100, null);

			g.setColor(color1);
			g.drawRect(990, 135, 400, 400);
			g.setFont(font2);
			g.setColor(color1);
			g.drawString("Waves", 1110, 215);

			g.setColor(color1);
			g.drawRect(1440, 135, 400, 400);
			g.setFont(font2);
			g.setColor(color1);
			g.drawString("Server", 1550, 215);

			g.setColor(color1);
			g.drawRect(990, 585, 400, 400);
			g.setFont(font2);
			g.setColor(color1);
			g.drawString("Co-op", 1095, 665);

			g.setColor(color1);
			g.drawRect(1440, 585, 400, 400);
			g.setFont(font2);
			g.setColor(color1);
			g.drawString("Attack", 1550, 665);

			g.setColor(color1);
			g.drawRect(80, 135, 850, 250);
			g.setFont(font);
			g.setColor(color1);
			g.drawString("Help", 400, 280);

			g.setColor(color1);
			g.drawRect(80, 435, 850, 250);
			g.setFont(font);
			g.setColor(color1);
			g.drawString("Credits", 340, 600);

			g.setColor(color1);
			g.drawRect(80, 735, 850, 250);
			g.setFont(font);
			g.setColor(color1);
			g.drawString("Quit", 400, 900);

		} else if (game.gameState == STATE.Help) {// if the user clicks on "help"
			Font font = new Font("impact", 1, 50);
			Font font2 = new Font("impact", 70, 30);
			Font font3 = new Font("Amoebic", 1,50);
			
			g.setFont(font3);
			g.setColor(Color.red);
			g.drawString("Help", 900, 50);

			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("Controls:", 40, 200);// + " \n"
			
			g.setFont((font2));
			g.setColor(Color.green);
			g.drawString("Use WASD Or The Arrow Keys To Move Around And Avoid The Enemeies", 160, 200);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("Waves:", 40, 250);
					
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Avoid The Enemies That Appear.  After Every 10 Levels,  A Boss Level Will Appear.  Beat All The Bosses To Win The Game", 135, 250);		//+ " long enough, a new batch will spawn in! Defeat each boss to win!", 40, 200);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("CO-OP:", 40, 300);
			g.setFont(font2);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Player 1 Moves By Using The WASD Keys, Where Player 2 Moves By Using The Arrow Keys. The First Player To Collect 50 Votes Wins.", 130, 300);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("Pick Ups:", 40, 350);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Description Of What Each Pickup In The Game Does", 160, 350);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("Putin-", 120, 400);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Replenishes A Portion Of The Players Health", 200, 400);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("Twitter Symbol-", 120, 440);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Increases The Players Speed For The Rest Of That Level", 315, 440);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("Eminem-", 120, 480);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Takes Away Portion Of The Players Health ", 230, 480);
			
			g.setFont(font2);
			g.setColor(Color.red);
			g.drawString("NFL Logo-", 120, 520);
			
			g.setFont(font2);
			g.setColor(Color.green);
			g.drawString("Decreases The Players Speed For The Rest Of That Level", 235, 520);
			
			g.setColor(Color.white);
			g.drawRect(850, 600, 200, 64);
			g.drawString("Back", 918, 642);
		}

	}

}
