package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		up1    = setup("/npc/OldMan_Up1");
		up2    = setup("/npc/OldMan_Up2");
		down1  = setup("/npc/OldMan_Down1");
		down2  = setup("/npc/OldMan_Down2");
		left1  = setup("/npc/OldMan_Left1");
		left2  = setup("/npc/OldMan_Left2");
		right1 = setup("/npc/OldMan_Right1");
		right2 = setup("/npc/OldMan_Right2");
	}
	
	public void setDialogue() {
		dialogues[0] = "Hello there, Traveler...";
		dialogues[1] = "You must be here for the Treasure...Or at least,\n for the view...";
		dialogues[2] = "Wait.. I meant to say there is definetly no \nTreasure here...";
		dialogues[3] = "... Crap.";

	}
	
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) { 
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i> 50 && i <= 75) {
				direction = "left";
			}
			if(i> 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter =0;
		}
			
		
	}
	
	public void speak() {
		super.speak();
	}
	
}
