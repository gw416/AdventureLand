package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	//Debug
	public boolean checkDrawTime = false;
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		// Title State
		if(gp.gameState == gp.titleState) {
			
			if(gp.ui.titleScreenState == 0) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 2;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 2) {
						gp.ui.commandNum = 0;
					}
				}
				
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
					}
					
					if(gp.ui.commandNum == 1) {
						// TODO
					}
					
					if(gp.ui.commandNum == 2) {
						System.exit(0);
					}
				}
			}
			
			else if(gp.ui.titleScreenState == 1) {
				if(code == KeyEvent.VK_W) {
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
				if(code == KeyEvent.VK_S) {
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}
				
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						System.out.println("Add bark addict class details later!");
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					
					if(gp.ui.commandNum == 1) {
						System.out.println("Add curious sniff class details later!");
						gp.gameState = gp.playState;
						gp.playMusic(0);

					}
					
					if(gp.ui.commandNum == 2) {
						System.out.println("Add playful class details later!");
						gp.gameState = gp.playState;
						gp.playMusic(0);

					}
					
					if(gp.ui.commandNum == 3) {
						System.out.println("Go Back");
						gp.ui.titleScreenState = 0;

					}
				}
			}
		}

		// Play State 
		if(gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_W)
				upPressed = true;
			if(code == KeyEvent.VK_S) 
				downPressed = true;
			if(code == KeyEvent.VK_A)
				leftPressed =  true;
			if(code == KeyEvent.VK_D)
				rightPressed = true;
			
			// Pause
			if(code == KeyEvent.VK_P) 
				gp.gameState = gp.pauseState;
			
			// Interact with NPC
			if(code == KeyEvent.VK_ENTER) 
				enterPressed = true;
			
			// Debug
			if(code == KeyEvent.VK_T) {
				if(checkDrawTime == false) {
					checkDrawTime = true;
				}
				else if( checkDrawTime) {
					checkDrawTime = false;
				}
			}
		}
		
		// Pause State
		else if(gp.gameState == gp.pauseState) {
			// UnPause
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}
		}
		
		// Dialogue State
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
				
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed =  false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
}
