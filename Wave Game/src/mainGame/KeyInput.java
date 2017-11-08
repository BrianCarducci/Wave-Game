package mainGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import mainGame.Game.STATE;

/**
 * Handles key input from the user
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[5];
	private int speed;
	private Game game;
	private HUD hud;
	private Player player,player2;
	private Spawn1to10 spawner;
	private Upgrades upgrades;
	private String ability = "";

	// uses current handler created in Game as parameter
	public KeyInput(Handler handler, Game game, HUD hud, Player player, Player player2, Spawn1to10 spawner, Upgrades upgrades) {
		this.handler = handler;
		this.speed = Player.playerSpeed;
		this.game = game;
		this.player = player;
		this.player2 = player2;
		this.hud = hud;
		this.spawner = spawner;
		this.upgrades = upgrades;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown[4] = false;

	}
	
	public void stopPlayer(Boolean move){
		keyDown[0] = move;
		keyDown[1] = move;
		keyDown[2] = move;
		keyDown[3] = move;
		keyDown[4] = move;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		this.speed = Player.playerSpeed;

		// finds what key strokes associate with Player
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (game.gameState == STATE.Game) {
				// using only if's allows multiple keys to be triggered at once
				if (tempObject.getId() == ID.Player) {// find the player object, as he is the only one the user can control
					// key events for player 1
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
						tempObject.setVelY(-(this.speed));
						keyDown[0] = true;
					}
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						tempObject.setVelX(-(this.speed));
						keyDown[1] = true;
					}
					if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
						tempObject.setVelY(this.speed);
						keyDown[2] = true;
					}
					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						tempObject.setVelX(this.speed);
						keyDown[3] = true;
					}
					if (key == KeyEvent.VK_SPACE) {
						upgrades.levelSkipAbility();
					}
					if (key == KeyEvent.VK_ENTER) {
						ability = upgrades.getAbility();
						if (ability.equals("clearScreen")) {
							upgrades.clearScreenAbility();
						} else if (ability.equals("levelSkip")) {
							upgrades.levelSkipAbility();
						} else if (ability.equals("freezeTime")) {
							upgrades.freezeTimeAbility();
						}
					}
				}
			}
		}
		// finds what key strokes associate with Player
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			//if coop then assign the keys to different values
			 if( game.gameState == STATE.Coop) {
				if (tempObject.getId() == ID.Player) {// find the player object, as he is the only one the user can control
					// key events for player 1
					if (key == KeyEvent.VK_W) {
						tempObject.setVelY(-(this.speed));
						keyDown[0] = true;
					}
					if (key == KeyEvent.VK_A) {
						tempObject.setVelX(-(this.speed));
						keyDown[1] = true;
					}
					if (key == KeyEvent.VK_S) {
						tempObject.setVelY(this.speed);
						keyDown[2] = true;
					}
					if (key == KeyEvent.VK_D) {
						tempObject.setVelX(this.speed);
						keyDown[3] = true;
					}
					if (key == KeyEvent.VK_SPACE) {
						upgrades.levelSkipAbility();
					}
					if (key == KeyEvent.VK_ENTER) {
						ability = upgrades.getAbility();
						if (ability.equals("clearScreen")) {
							upgrades.clearScreenAbility();
						} else if (ability.equals("levelSkip")) {
							upgrades.levelSkipAbility();
						} else if (ability.equals("freezeTime")) {
							upgrades.freezeTimeAbility();
						}
					}	
				}
				else if(tempObject.getId() == ID.player2 ){
					if (key == KeyEvent.VK_UP) {
						tempObject.setVelY(-(this.speed));
						keyDown[0] = true;
					}
					if (key == KeyEvent.VK_LEFT) {
						tempObject.setVelX(-(this.speed));
						keyDown[1] = true;
					}
					if (key == KeyEvent.VK_DOWN) {
						tempObject.setVelY(this.speed);
						keyDown[2] = true;
					}
					if (key == KeyEvent.VK_RIGHT) {
						tempObject.setVelX(this.speed);
						keyDown[3] = true;
					}
				}
			}

		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(game.gameState == STATE.Game) {
					if (tempObject.getId() == ID.Player) {
					// key events for player 1
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
						keyDown[0] = false;// tempObject.setVelY(0);
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
						keyDown[1] = false;// tempObject.setVelX(0);
					if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
						keyDown[2] = false;// tempObject.setVelY(0);
					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						keyDown[3] = false;// tempObject.setVelX(0);
						keyDown[4] = false;
					}

					// vertical movement
					if (!keyDown[0] && !keyDown[2])
						tempObject.setVelY(0);
					// horizontal movement
					if (!keyDown[1] && !keyDown[3])
					tempObject.setVelX(0);
				}
			}
			
			if(game.gameState == STATE.Coop) {
				if (tempObject.getId() == ID.Player) {
					// key events for player 1
					if (key == KeyEvent.VK_W)
						keyDown[0] = false;// tempObject.setVelY(0);
					if (key == KeyEvent.VK_A)
						keyDown[1] = false;// tempObject.setVelX(0);
					if (key == KeyEvent.VK_S)
						keyDown[2] = false;// tempObject.setVelY(0);
					if (key == KeyEvent.VK_RIGHT) {
						keyDown[3] = false;// tempObject.setVelX(0);
						keyDown[4] = false;
					}

					// vertical movement
					if (!keyDown[0] && !keyDown[2])
						tempObject.setVelY(0);
					// horizontal movement
					if (!keyDown[1] && !keyDown[3])
						tempObject.setVelX(0);
				}
				
				if (tempObject.getId() == ID.player2) {
					// key events for player 1
					if (key == KeyEvent.VK_UP)
						keyDown[0] = false;// tempObject.setVelY(0);
					if (key == KeyEvent.VK_LEFT)
						keyDown[1] = false;// tempObject.setVelX(0);
					if (key == KeyEvent.VK_DOWN)
						keyDown[2] = false;// tempObject.setVelY(0);
					if (key == KeyEvent.VK_RIGHT) {
						keyDown[3] = false;// tempObject.setVelX(0);
						keyDown[4] = false;
					}

					// vertical movement
					if (!keyDown[0] && !keyDown[2])
						tempObject.setVelY(0);
					// horizontal movement
					if (!keyDown[1] && !keyDown[3])
						tempObject.setVelX(0);
				}

			}
		}

		// if (key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

}
