package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Ball extends SuperObject{

	public OBJ_Ball() {
		name = "Ball";
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/Ball.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
