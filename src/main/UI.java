package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	
	Font arial_30;
	Font arial_40;
	Font arial_80B;
	Font arial_90;
	Font arial_90B;

	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameOver = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: first scrren , 1: second scren etc
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_30 = new Font("Arial", Font.PLAIN, 30);
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		arial_90 = new Font("Arial", Font.BOLD, 90);
		arial_90B = new Font("Arial", Font.PLAIN, 90);


	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		// Title State
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// Play State
		if(gp.gameState == gp.playState) {
			// Do play state stuff
		}
		// Pause State
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// Dialogue State 
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			g2.setColor(new Color(25, 100, 200));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			// Title Name
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
			String text = "Adventure Land";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			
			//Shadowing
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			
			// Main Color
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// Draw Milky Dog
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			// Menu
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
			
			text = "NEW GAME";
			x = getXForCenteredText(text);
			y += gp.tileSize*3.2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize+16, y);
			}
			
			text = "LOAD GAME";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize+16, y);
			}
			
			text = "QUIT";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize+16, y);
			}
		}
		
		else if(titleScreenState == 1) {
			
			// Class Selection 
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			String text = "Select your play style!";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Bark Addict";
			x = getXForCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);

			}

			text = "Curious Sniffs";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);

			}
			
			text = "Playful";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);

			}
			
			text = "Back";
			x = getXForCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);

			}
		}
		
		
		
	}
	public void drawDialogueScreen() {
		// Window
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0, 220);
		g2.setColor(c);
		
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);

		
	}
	
	public void drawPauseScreen() { 
		
		String text = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		
		int x = getXForCenteredText(text);
		int y = gp.screenHeight /  2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXForCenteredText(String text) {
		
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length /2;
		return x;
	}
}








