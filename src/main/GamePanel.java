package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	// Screen Settings
	final int originalTileSize = 16; //16x16 pixel tile
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale;  // 48x48 tile size
	
	// ratio 4:3 for screen
	final int maxScreenCol = 16; // 16 tiles horizontally
	final int maxScreenRow = 12; // 12 tiles vertically 
	final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// FPS 
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	// set players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() { 
		
		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//		
//		// Game Loop
//		while(gameThread != null) {
//			
//			double drawInterval = 1000000000/FPS; //0.01666 seconds
//			double nextDrawTime = System.nanoTime() + drawInterval;
//			
//			update();
//			repaint();
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime / 1000000; // convert from nano to milli
//				
//				if(remainingTime < 0 )
//					remainingTime = 0;
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		
//		
//	}
	
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0; // used to display FPS
		int drawCount = 0;// used to display FPS
		
		while(gameThread !=null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
					
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	
	// In Java the upper left corner of screen is X:0, Y:0. 
	//   X value increases to the right, Y value increases down.
	public void update() {
		
		if(keyH.upPressed) {
			playerY -= playerSpeed;
		}
		else if(keyH.downPressed) {
			playerY += playerSpeed;
		}
		else if(keyH.leftPressed) {
			playerX -= playerSpeed;
		}
		else if(keyH.rightPressed) {
			playerX += playerSpeed;
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
		
	}
	
	
	
	
	
}
