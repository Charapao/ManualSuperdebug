import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
 
public class Manualman extends BasicGame {
	
	public static float G = (float) 0.5;
    public static final float PLAYER_JUMP_VY = 10;
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    public static final float Ground_VX = -4;
    static public Player player;
	public Manualman(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}


 
  @Override
  public void init(GameContainer gc) throws SlickException { 
	  
	  player = new Player(GAME_WIDTH/4, 300, 0);
  
  
  }
  
 
  @Override
  public void update(GameContainer gc, int dt) throws SlickException {
  player.update();
  }
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
	  player.render();
	  SETCOLOR_SHAPE(g);
  }
  public static void SETCOLOR_SHAPE(Graphics g){
	  g.setColor(Color.yellow);
      g.fill(Player.circleplayer);
      g.setColor(Color.pink);
      g.draw(Player.circleplayer);
	  
	  
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
