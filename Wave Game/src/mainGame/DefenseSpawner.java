package mainGame;

import java.util.ArrayList;
import java.util.Random;

import mainGame.Game.STATE;
/**
 * Level spawner designed for the defense game mode
 * @author Ryan Hanlon 11/22/17
 */
public class DefenseSpawner {
	public static int LEVEL_SET = 1;
	private Handler handler;
	private ServerHUD serverHud;
	private Game game;
	private CoopHud hud2;
	private int scoreKeep = 0;
	private Random r = new Random();
	private int spawnTimer;
	private int levelTimer;
	private String[] side = { "left", "right", "top", "bottom" };
	ArrayList<Integer> levels = new ArrayList<Integer>(); // MAKE THIS AN ARRAY LIST SO I CAN REMOVE OBJECTS
	private int index;
	private int levelsRemaining;
	private int levelNumber = 0;
	private int tempCounter = 0;
	private int timer = 0;

	public DefenseSpawner(Handler handler, ServerHUD serverHud, CoopHud hud2, Game game) {
		this.handler = handler;
		this.serverHud = serverHud;
		this.hud2 = hud2;
		this.game = game;
		handler.object.clear();
		serverHud.health = 500;
		hud2.setScore(0);
		spawnTimer = 10;
		levelTimer = 150;
		levelsRemaining = 10;
		tempCounter = 0;
		index = r.nextInt(levelsRemaining);
		levelNumber = 0;
		timer = 120;

	}


	/**
	 * Called once every 60 seconds by the Game loop
	 */
	public void tick() {
		if (levelNumber <= 0) {
			levelTimer--;
			if (tempCounter < 1) {// display intro game message ONE time
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Defend The Email Server!!", ID.Levels1to10Text));
				tempCounter++;
			}
			if (levelTimer <= 0) {// time to play!
				handler.clearEnemies();
				tempCounter = 0;
				levelNumber = 1;
			}

		}
		
		 if (serverHud.health > 0 && hud2.health > 0 && levelNumber == 1) {
			spawnTimer--;// keep decrementing the spawning spawnTimer 60 times a second
			if (spawnTimer == 0) {// time to spawn another enemy
				//levelNumber++;
				handler.addObject(
				new EnemyFBI2(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, -10, ID.EnemyFBI, handler));// add them to the handler, which handles all game objects

				spawnTimer = 100;// reset the spawn timer
			}
		}

	}


	public void restart() {
		levelNumber = -10;
		tempCounter = 0;
		levelTimer = 150;
		levelsRemaining = 10;
		index = r.nextInt(levelsRemaining);
	}
}
