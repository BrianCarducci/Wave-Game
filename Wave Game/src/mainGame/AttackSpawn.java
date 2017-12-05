package mainGame;

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

	// instance variables
	private Handler handler;
	private Game game;
	private Random r = new Random();
	private int spawnTimer;
	private int levelNumber = 0;
	private int introTimer = 0;
	private int tempCounter = 0;
	private int pickupTimer, pickupTimer2;
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
		pickupTimer2 = 0;
	}

	/**
	 * Called once every 60 seconds by the Game loop
	 */
	public void tick() {

		// displays the text at the beginning but only once
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

		// each level works the same way, refer to this to understand
		else if (levelNumber == 1) {
			spawnTimer--; // decrements a timer that spawns the enemies, timer
							// will be decreased as player advances so enemies
							// spawn in quicker

			if (spawnTimer == 0) {// adds the enemy in when timer hits 0

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 300;

			}

			if (hud.getScore() >= 50) {// each level is score based. when a
										// player hits the score for that level
										// do the following
				handler.clearEnemies(); // clear all remaining enemies
				spawnTimer = 200; // set a new spawn timer
				levelNumber++; // increment level
				hud.setLevel(hud.getLevel() + 1); // increase the level
													// displayed on the HUD
			}

		}

		else if (levelNumber == 2) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 200;

			}

			if (hud.getScore() >= 150) {
				handler.clearEnemies();
				spawnTimer = 200;
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

			if (hud.getScore() >= 250) {
				handler.clearEnemies();
				spawnTimer = 200;
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

			if (hud.getScore() >= 350) {
				handler.clearEnemies();
				spawnTimer = 200;
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

			if (hud.getMag() <= 120) {
				pickupTimer2--;

				if (pickupTimer2 == 0) {
					pickupTimer--;

					handler.addPickup(new AmmoPickup(ID.AmmoPickup, handler));

					if (pickupTimer == 0) {
						handler.pickups.clear();
					}
				}
			}

			if (hud.getScore() >= 500) {
				handler.clearEnemies();
				spawnTimer = 200;
				levelNumber = 100;
				hud.setLevel(hud.getLevel() + 1);
				tempCounter = 0;
			}

		}

		else if (levelNumber == 6) {

			spawnTimer--;
			pickupTimer--;

			if (tempCounter < 1) {
				handler.addPickup(new AmmoPickup(ID.AmmoPickup, handler));
				tempCounter++;
			}
			if (pickupTimer == 0) {
				handler.pickups.clear();
			}

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 100;

			}

			if (hud.getScore() >= 650) {
				handler.clearEnemies();
				spawnTimer = 200;
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

			if (hud.getScore() >= 750) {
				handler.clearEnemies();
				spawnTimer = 200;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
				pickupTimer2 = 500;
				pickupTimer = 500;
			}

		}

		else if (levelNumber == 8) {
			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 75;

			}

			if (hud.health <= 50) {
				pickupTimer2--;

				if (pickupTimer2 == 0) {
					pickupTimer--;

					handler.addPickup(new NukePickup(ID.NukePickup, handler));

					if (pickupTimer == 0) {
						handler.pickups.clear();
					}
				}
			}

			if (hud.getScore() >= 950) {
				handler.clearEnemies();
				spawnTimer = 200;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
				pickupTimer2 = 2500;
				pickupTimer = 500;
			}

		}

		else if (levelNumber == 9) {

			spawnTimer--;
			pickupTimer2--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 50;

			}

			if (pickupTimer2 == 0) {
				pickupTimer--;

				handler.addPickup(new NukePickup(ID.NukePickup, handler));

				if (pickupTimer == 0) {
					handler.pickups.clear();
				}
			}

			if (hud.getScore() >= 1150) {
				handler.clearEnemies();
				spawnTimer = 200;
				levelNumber++;
				hud.setLevel(hud.getLevel() + 1);
			}

		}

		else if (levelNumber == 10) {

			spawnTimer--;

			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 50;

			}

			if (hud.getScore() >= 1300) {
				handler.clearEnemies();
				levelNumber = 101;
				tempCounter = 0;
				pickupTimer2 = 500;
				pickupTimer = 500;
			}

		}

		else if (levelNumber == 100) { // arbitrary number for boss 1
			hud.setBossLevel("Boss One");
			hud.setBoss(true);

			if (tempCounter < 1) { // adds the boss once

				handler.addObject(
						new SmartBoss(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.SmartBoss, handler));
				tempCounter++;
			} else {
				for (int i = 0; i < handler.object.size(); i++) { // loop that
																	// checks to
																	// see if
																	// boss is
																	// alive
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getId() == ID.SmartBoss) {
						if (tempObject.getHealth() <= 0) { // if boss is dead
							handler.removeObject(tempObject); // remove boss
							handler.pickups.clear(); // clear all pickups
							hud.setBoss(false); // set boss text
							levelNumber = 6; // puts player back in smart enemy
												// levels
							// hud.setLevel(hud.getLevel() + 1); // sets level
							tempCounter = 0; // sets counter
							pickupTimer = 500; // sets pickup timer
						}
					}
				}

			}
		} else if (levelNumber == 101) { // works same way as boss 1
			hud.setBossLevel("Boss Two");
			hud.setBoss(true);
			spawnTimer--;

			if (tempCounter < 1) {

				handler.addObject(
						new SmartBoss(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -6, ID.SmartBoss, handler));
				tempCounter++;
			}

			// adds in smart enemies as well
			if (spawnTimer == 0) {

				handler.addObject(
						new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
				spawnTimer = 300;

			} else if (hud.health <= 50) {
				pickupTimer2--;

				if (pickupTimer2 == 0) {
					pickupTimer--;

					handler.addPickup(new NukePickup(ID.NukePickup, handler));

					if (pickupTimer == 0) {
						handler.pickups.clear();
					}
				}
			}
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.getId() == ID.SmartBoss) {
					if (tempObject.getHealth() <= 0) {
						handler.removeObject(tempObject);
						handler.clearEnemies();
						handler.pickups.clear();
						hud.setBoss(false);
						game.gameState = STATE.Victory; // sends game to victory
														// screen
					}
				}

			}
		}

	}

	// method used to skip through levels for development purposes only
	public void skipLevel() {

		if (levelNumber == 5) {
			levelNumber = 100;
		} else if (levelNumber == 100) {
			hud.setBoss(false);
			levelNumber = 6;
			tempCounter = 0;
			pickupTimer = 500;
			hud.setLevel(hud.getLevel() + 1);
		} else if (levelNumber == 10) {
			tempCounter = 0;
			levelNumber = 101;
			pickupTimer2 = 500;
			pickupTimer = 500;

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
