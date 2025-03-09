package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// Screen Settings
	final int originalTileSize = 16; //16x16 pixel tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;  // 48x48 tile size
	
	// ratio 4:3 for screen
	public final int maxScreenCol = 16; // 16 tiles horizontally
	public final int maxScreenRow = 12; // 12 tiles vertically 
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FPS 
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	
	Sound music = new Sound();
	Sound se = new Sound();

	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	// Entity and Object
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[10];
	
	// Game State
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);		
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		playMusic(0);
		
		gameState = playState;
	}
	
	public void startGameThread() { 
		
		gameThread = new Thread(this);
		gameThread.start();
	}

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
	
	public void update() {
		
		if(gameState == playState) {
			// player updates
			player.update();
			
			//npc updates
			for(int i=0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			
		}
		if(gameState == pauseState) {
			// nothing for now
			
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// Debug
		long drawStart = 0;
		if(keyH.checkDrawTime) {
			drawStart = System.nanoTime();
		}
		
		
		// Draw Tiles
		tileM.draw(g2);
		
		// Draw Objects
		for(int i=0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// Draw NPC
		for(int i=0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		
		// Draw Player
		player.draw(g2);
		
		// Draw UI
		ui.draw(g2);
		
		// Debug
		if(keyH.checkDrawTime) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw time: " + passed);
		}
		
		g2.dispose();
	}
	
	
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	
	// sound effect
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
		
	}
	
	
	
	
}
