import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
 
public class Manualman extends BasicGame {
  public Manualman(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}


 
  @Override
  public void init(GameContainer gc) throws SlickException {}
 
  @Override
  public void update(GameContainer gc, int dt) throws SlickException {}
 
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
  }
 
  public static void main(String[] args) {
    try {
      AppGameContainer appgc;
      appgc = new AppGameContainer(new Manualman("Manual Man Game"));
      appgc.setDisplayMode(640, 480, false);
      appgc.start();
    } catch (SlickException ex) {
      Logger.getLogger(Manualman.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
