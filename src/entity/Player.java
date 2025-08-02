package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

	KeyHandler keyH;	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp); 
		
		this.keyH = keyH;
		screenX = gp.screenWidth / 2 - (gp.tileSize/2);
		screenY = gp.screenHeight / 2  - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() { 
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
		// Player Status
		maxLife = 6;
		life = maxLife;
	}
	
	public void getPlayerImage() {

		up1    = setup("/player/Milky_Up1");
		up2    = setup("/player/Milky_Up2");
		down1  = setup("/player/Milky_Down1");
		down2  = setup("/player/Milky_Down2");
		left1  = setup("/player/Milky_Left1");
		left2  = setup("/player/Milky_Left2");
		right1 = setup("/player/Milky_Right1");
		right2 = setup("/player/Milky_Right2");
	}
	
	
	
	// In Java the upper left corner of screen is X:0, Y:0. 
		//   X value increases to the right, Y value increases down.
	public void update() { 
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed) {
				direction = "up";
			}
			else if(keyH.downPressed) {
				direction = "down";
			}
			else if(keyH.leftPressed) {
				direction = "left";
			}
			else if(keyH.rightPressed) {
				direction = "right";
			}
			
			// Check Tile Collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// Check Object Collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// Check NPC Collision
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
					
			// Check Event
			gp.eHandler.checkEvent();
			
			gp.keyH.enterPressed = false;
			
			// If Collision is false, Player can move
			if(collisionOn == false) {
				switch(direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum ==1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		if(i != 999) {
			
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			if(gp.keyH.enterPressed) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		//g2.setColor(Color.white);
		//g2.fillRect(x, y, gp.tileSize, gp.tileSize);	
		BufferedImage image = null;
		switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if(spriteNum == 1) { 
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;
		}
		g2.drawImage(image, screenX, screenY, null);
	}
}