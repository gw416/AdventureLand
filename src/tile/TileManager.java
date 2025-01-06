package tile;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		tile = new Tile[10];
		
		getTileImage();
		
	}
	
	
	public void getTileImage() {
		
		
		try { 
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));
			//BufferedImage image ;
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
