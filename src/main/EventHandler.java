package main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 2;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}
	
	public void checkEvent() {
		
		//if(hit(27,16,"right")) { damagePit(gp.dialogueState); }
		if(hit(27,16,"right")) { teleport(gp.dialogueState); }
		if(hit(23,12,"up"))    { healingPool(gp.dialogueState);	}
				
		
	}

	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventRow * gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.equals(reqDirection) || reqDirection.equals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		
		return hit;
	}
	
	private void teleport(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "teleporting!";
		gp.player.worldX = gp.tileSize*37;
		gp.player.worldY = gp.tileSize*10;

		
	}
	private void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fell into a pit!";
		gp.player.life -= 1;
	}
	
	public void healingPool(int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "You have taken a sip of Water.\nYou have been healed by the nectar of the Gods...";
			gp.player.life = gp.player.maxLife;
		}
		
	}
	
}
