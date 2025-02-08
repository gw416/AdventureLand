package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Ball extends SuperObject{

	GamePanel gp;
	
	public OBJ_Ball(GamePanel gp) {
		this.gp = gp;
		
		name = "Ball";
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Ball.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
