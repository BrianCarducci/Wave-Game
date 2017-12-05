package mainGame;

import java.util.ArrayList;
import java.util.Random;

import mainGame.Game.STATE;

/**
 * 
 * @author Eric Kinney
 * New Class to have levels 1 to 5 and then spawn CNN boss
 * Added 11/27/17
 *
 */

public class Spawn1to5 {
	public static int LEVEL_SET = 1;
	private Handler handler;
	private HUD hud;
	private Game game;
	private CoopHud hud2; 
	private int scoreKeep = 0;
	private Random r = new Random();
	private int spawnTimer;
	private int levelTimer;
	private int voteTimer;
	private String[] side = { "left", "right", "top", "bottom" };
	ArrayList<Integer> levels = new ArrayList<Integer>(); // MAKE THIS AN ARRAY LIST SO I CAN REMOVE OBJECTS
	private int index;
	private int levelsRemaining;
	private int levelNumber = 0;
	private int tempCounter = 0;
	private int timer = 0;
	
	public Spawn1to5(Handler handler, HUD hud, CoopHud hud2, Game game) {
		this.handler = handler;
		this.hud = hud;
		this.hud2 = hud2;
		this.game = game;
		handler.object.clear();
		hud.health = 100;
		hud.setScore(0);
		hud2.setScore(0);
		hud.setLevel(1);
		spawnTimer = 10;
		voteTimer = 20;
		levelTimer = 150;
		levelsRemaining = 5;
		hud.setLevel(1);
		tempCounter = 0;
		addLevels();
		index = r.nextInt(levelsRemaining);
		levelNumber = 0;
		timer = 120;

	}
	// Pre loads every level
	public void addLevels() {
		for (int i = 1; i <= 10; i++) {
			levels.add(i);
		}
	}
	//Called once every 60 seconds by the Game Loop
	
	public void tick() {
		if (levelNumber <= 0) {
			levelTimer--;
			if (tempCounter < 1) {// display intro game message ONE time
				handler.addObject(new LevelText(Game.WIDTH / 2 - 675, Game.HEIGHT / 2 - 200, "Let's start off easy...",
						ID.Levels1to5Text));
				tempCounter++;
			}
			if (levelTimer <= 0) {// time to play!
				handler.clearEnemies();
				tempCounter = 0;
				levelNumber = levels.get(index);
			}

		}
		
		/*
		 * 
		* Each level works the same way
		* 
		* To keep comments on code to a minimum only the first level will be fully commented
		*
		*/
		else if (levelNumber == 1) {
			spawnTimer--;
			levelTimer--;
			voteTimer--;
			
			if (tempCounter < 1) {// called only once, but sets the levelTimer to how long we want this level to run for
				levelTimer = 2000;// 2000 / 60 method calls a second = 33.33 seconds long
				tempCounter++;// ensures the method is only called once
			}
			
			//voteTimer spawns another vote after the timer reaches 0
			if (game.gameState == STATE.Coop) {
				if (voteTimer == 0) {
					handler.addPickup(new PickupVote(ID.Vote, handler));
					voteTimer = timer;
				}
			}
			
			if (spawnTimer == 0) {// time to spawn another enemy
				handler.addObject(
				new EnemyFBI(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, -10, ID.EnemyFBI, handler));// add them to the handler, which handles all game objects

				spawnTimer = 100;// reset the spawn timer
			}
			if (levelTimer == 0) {// level is over
				handler.clearEnemies();// clear the enemies
				hud.setLevel(hud.getLevel() + 1);// Increment level number on HUD
				spawnTimer = 40;
				tempCounter = 0;// reset tempCounter
				if (levelsRemaining == 1) {// time for the boss!
					levelNumber = 101;// arbitrary number for the boss level
				} else {// not time for the boss, just go to the next level
					levels.remove(index);// remove the current level from being selected
					levelsRemaining--;
					index = r.nextInt(levelsRemaining);// pick another level at random
					levelNumber = levels.get(index);// set levelNumber to whatever index was randomly selected
				}
			}
		} else if (levelNumber == 2) {
				spawnTimer--;
				levelTimer--;
				voteTimer--;
				if (tempCounter < 1) {
					//handler.addPickup(new PickupVote(ID.Vote, handler));
					levelTimer = 2000;
					tempCounter++;
				}
				if (game.gameState == STATE.Coop) {
					if (voteTimer == 0) {
						handler.addPickup(new PickupVote(ID.Vote, handler));
						voteTimer = timer;
					}
				}
				if (spawnTimer == 30) {
					handler.addObject(
							new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 2, ID.EnemySweep, handler));
				} else if (spawnTimer == 20) {
					handler.addObject(
							new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -2, ID.EnemySweep, handler));
					
				} else if (spawnTimer == 10) {
					handler.addObject(
							new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, 4, ID.EnemySweep, handler));
				} else if (spawnTimer == 0) {
					handler.addObject(
							new EnemySweep(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), 20, -4, ID.EnemySweep, handler));
					spawnTimer = 80;
				}

				if (levelTimer == 0) {
					handler.clearEnemies();
					hud.setLevel(hud.getLevel() + 1);
					tempCounter = 0;
					if (levelsRemaining == 1) {
						levelNumber = 101;
					} else {
						levels.remove(index);
						levelsRemaining--;
						index = r.nextInt(levelsRemaining);
						levelNumber = levels.get(index);
					}
				}
			} else if (levelNumber == 3) {

				spawnTimer--;
				levelTimer--;
				voteTimer--;
				if (tempCounter < 1) {
					levelTimer = 1500;
					tempCounter++;
					handler.addPickup(new NRABonusLife(ID.NRABonusLife, handler));
				}
				if (game.gameState == STATE.Coop) {
					if (voteTimer == 0) {
						handler.addPickup(new PickupVote(ID.Vote, handler));
						voteTimer = timer;
					}
				}
				if (spawnTimer == 0) {
					handler.addObject(
							new EnemySmart(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), -3, ID.EnemySmart, handler));
					spawnTimer = 100;
				}
				if (levelTimer == 0) {
					handler.clearEnemies();
					hud.setLevel(hud.getLevel() + 1);
					spawnTimer = 10;
					tempCounter = 0;
					if (levelsRemaining == 1) {
						levelNumber = 101;
					} else {
						levels.remove(index);
						levelsRemaining--;
						index = r.nextInt(levelsRemaining);
						levelNumber = levels.get(index);
					}
				}
			} else if (levelNumber == 4) {
				levelTimer--;
				voteTimer--;
				if (tempCounter < 1) {
					handler.addObject(new EnemyShooter(r.nextInt(Game.WIDTH) - 35, r.nextInt(Game.HEIGHT) - 75, 100, 100,
							-20, ID.EnemyShooter, this.handler));

					if (hud.health <= 50){
						handler.addPickup(new PutinHealth(ID.PutinHealth, handler));
					} else {
						handler.addPickup(new EminemHealth(ID.EminemHealth, handler));
					}

					levelTimer = 1300;
					tempCounter++;
				}
				if (game.gameState == STATE.Coop) {
					if (voteTimer == 0) {
						handler.addPickup(new PickupVote(ID.Vote, handler));
						voteTimer = timer;
					}
				}

				if (levelTimer == 0) {
					handler.clearEnemies();
					handler.pickups.clear();
					hud.setLevel(hud.getLevel() + 1);
					spawnTimer = 10;
					tempCounter = 0;
					if (levelsRemaining == 1) {
						levelNumber = 101;
					} else {
						levels.remove(index);
						levelsRemaining--;
						index = r.nextInt(levelsRemaining);
						levelNumber = levels.get(index);
					}
				}
			} else if (levelNumber == 5) {
				spawnTimer--;
				levelTimer--;
				voteTimer--;
				if (tempCounter < 1) {
					levelTimer = 1400;
					tempCounter++;
				}
				if (game.gameState == STATE.Coop) {
					if (voteTimer == 0) {
						handler.addPickup(new PickupVote(ID.Vote, handler));
						voteTimer = timer;
					}
				}
				if (spawnTimer <= 0) {
					handler.addObject(new EnemyBurst(-200, 200, 40, 40, 200, side[r.nextInt(4)], ID.EnemyBurst, handler));
					spawnTimer = 180;
				}

				if (levelTimer == 0) {
					handler.clearEnemies();
					hud.setLevel(hud.getLevel() + 1);
					spawnTimer = 10;
					tempCounter = 0;
					if (levelsRemaining == 1) {
						levelNumber = 101;
					} else {
						levels.remove(index);
						levelsRemaining--;
						index = r.nextInt(levelsRemaining);
						levelNumber = levels.get(index);
					}
				}
			}
		
			else if (levelNumber == 101) {// arbitrary number for the boss
				if (tempCounter < 1) {
					handler.addObject(new EnemyBoss(ID.EnemyBoss, handler));

					if (hud.health <= 50){
						handler.addPickup(new TwitterSpeed(ID.TwitterSpeed, handler));
					}

					tempCounter++;
					hud.setBossLevel("Boss One");
					hud.setBoss(true);
				} else if (tempCounter >= 1) {
					for (int i = 0; i < handler.object.size(); i++) {
						GameObject tempObject = handler.object.get(i);
						if (tempObject.getId() == ID.EnemyBoss) {
							if (tempObject.getHealth() <= 0) {
								handler.removeObject(tempObject);
								handler.pickups.clear();
								hud.setBoss(false);
								LEVEL_SET++;
								//game.gameState = STATE.Upgrade;
								Player.playerSpeed = 10;
							}
						}
					}
				}

			}
		}
	public void skipLevel() {
		if (levelsRemaining == 1) {
			handler.pickups.clear();
			tempCounter = 0;
			levelNumber = 101;
		} else if (levelsRemaining > 1) {
			handler.pickups.clear();
			Player.playerSpeed = 10;
			levels.remove(index);
			levelsRemaining--;
			System.out.println(levelsRemaining);
			tempCounter = 0;
			index = r.nextInt(levelsRemaining);
			levelNumber = levels.get(index);
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
