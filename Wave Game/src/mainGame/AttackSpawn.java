package mainGame;

import java.util.ArrayList;
import java.util.Random;
import mainGame.Game.STATE;

/**
 * 
 * @author Kyle Horton
 * 
 *         This class creates the levels for the attack game mode.
 *
 */

public class AttackSpawn {
	private Handler handler;
	private Game game;
	private Random r = new Random();
	private int spawnTimer;
	private int levelNumber = 0;
	private int introTimer = 0;
	private int tempCounter = 0;
	private int pickupTimer;
	private AttackHUD hud;

	public AttackSpawn(Handler handler, AttackHUD hud, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
		handler.object.clear();
		hud.health = 100;
		hud.setLevel(1);
		spawnTimer = 10;
		levelNumber = 0;
		introTimer = 100;
		tempCounter = 0;
		pickupTimer = 0;
	}

	/**
	 * Called once every 60 seconds by the Game loop
	 */
	public void tick() {

		if (levelNumber == 0) {
			introTimer--;
			if (tempCounter < 1) {// display intro game message ONE time
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200,
						"Shoot and kill the enemies!", ID.Levels1to5Text));
				tempCounter++;
			}

			if (introTimer <= 0) {
				handler.clearEnemies();
				tempCounter = 0;
				levelNumber++;
			}

		}

		else if (levelNumber == 1) {
			spawnTimer--;
			

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 300;

			}

			if (hud.getScore() == 50) {
				handler.clearEnemies();
				spawnTimer = 100;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 2) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 200;

			}

			if (hud.getScore() == 150) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 3) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 150;

			}

			if (hud.getScore() == 250) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 4) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 150;

			}

			if (hud.getScore() == 350) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 5) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 120;

			}

			if (hud.getScore() == 500) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber = 100;
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
			}

		}

		else if (levelNumber == 6) {

			spawnTimer--;
			
			if (pickupTimer == 0) {

				handler.addPickup(new AmmoPickup(ID.AmmoPickup, handler));
				handler.addPickup(new NukePickup(ID.NukePickup, handler));
				pickupTimer = 1000;
			} 

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 100;

			}

			if (hud.getScore() == 650) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 7) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 75;

			}

			if (hud.getScore() == 750) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 8) {
			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 75;

			}

			if (hud.getScore() == 950) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 9) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 40;

			}

			if (hud.getScore() == 1150) {
				handler.clearEnemies();
				spawnTimer = 40;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 10) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 30;

			}

			if (hud.getScore() == 1400) {
				handler.clearEnemies();
				levelNumber = 101;
				tempCounter = 0;
			}

		}

		else if (levelNumber == 100) {
			hud.setBossLevel("Boss One");
			hud.setBoss(true);

			if (tempCounter < 1) {

				handler.addObject(
						new SmartBoss(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.SmartBoss, handler));
				tempCounter++;
			} else {
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getId() == ID.SmartBoss) {
						if (tempObject.getHealth() <= 0) {
							handler.removeObject(tempObject);
							handler.pickups.clear();
							hud.setBoss(false);
							levelNumber = 6;
							hud.setLevel(hud.getLevel() + 1);
							tempCounter = 0;
						}
					}
				}

			}
		} else if (levelNumber == 101) {
			hud.setBossLevel("Boss Two");
			hud.setBoss(true);
			spawnTimer--;

			if (tempCounter < 1) {

				handler.addObject(
						new SmartBoss(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -6, ID.SmartBoss, handler));
				tempCounter++;
			} 
			
			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 300;

			} else {
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getId() == ID.SmartBoss) {
						if (tempObject.getHealth() <= 0) {
							handler.removeObject(tempObject);
							handler.clearEnemies();
							handler.pickups.clear();
							hud.setBoss(false);
							game.gameState = STATE.Victory;
						}
					}
				}

			}
		}

	}

	public void skipLevel() {

		if (levelNumber == 5) {
			levelNumber = 100;
		} else if (levelNumber == 100) {
			hud.setBoss(false);
			levelNumber = 6;
			hud.setLevel(hud.getLevel() + 1);
			tempCounter = 0;
		} else if (levelNumber == 10) {
			tempCounter = 0;
			levelNumber = 101;
		} else {
			levelNumber++;
			hud.setLevel(hud.getLevel() + 1);
		}
	}

	public void restart() {
		levelNumber = 0;
		tempCounter = 0;
		introTimer = 100;

	}
}
