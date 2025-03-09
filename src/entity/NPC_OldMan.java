package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
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
	
	
}
