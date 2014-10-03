import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
 
public class Manualman extends BasicGame {
	
	public static float G = (float) 0.5;
    public static final float PLAYER_JUMP_VY = 10;
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    public static final float Ground_VX = -4;
    static public Player player;
    static public Ground[] grounds;
    private boolean[] colliressground ;
    public static Shape[] rectground;
	public Manualman(String title) {
		super(title);
		
	}


 
  @Override
  public void init(GameContainer gc) throws SlickException { 
	  
	  player = new Player(GAME_WIDTH/4, 300, 0);
	  grounds = new Ground[3]; 
      for (int i = 0; i < 3; i++) {
      grounds[i] = new Ground(GAME_WIDTH-320-50-GAME_WIDTH/2+((640+100)*i), GAME_HEIGHT-20,Ground_VX);
      
      }
	  INIT_COLLIRESS_ALL();
	  rectground = new Rectangle[3];
	  for(int i=0; i < 3; i++){
	      rectground[i] = new Rectangle(GAME_WIDTH/2-50-640+((640+100)*i),GAME_HEIGHT-40,640,40);
	       
	  }
  }
  public void INIT_COLLIRESS_ALL(){
	    	colliressground = new boolean[3];
	        for(int i = 0;i< colliressground.length;i++){
	        colliressground[i]= false;
	        
	        }
	         
	    
	    }
 
  
  
  
 
  @Override
  public void update(GameContainer gc, int dt) throws SlickException {
  player.update();
  for (Ground ground : grounds) {
      
      ground.update();
      
 }
  for(int temp = 0; temp < 3; temp++){
      rectground[temp].setCenterX(grounds[temp].getX());
      rectground[temp].setCenterY(grounds[temp].getY()); 
      }
  }
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
	  player.render();
	  for (Ground ground : grounds) {
          
          ground.render();
          
     }
	  SETCOLOR_SHAPE(g);
  }
  public static void SETCOLOR_SHAPE(Graphics g){
	  g.setColor(Color.yellow);
      g.fill(Player.circleplayer);
      g.setColor(Color.pink);
      g.draw(Player.circleplayer);
	  
      for(Shape rec : rectground){
          g.setColor(Color.yellow);
          g.fill(rec);
          g.setColor(Color.pink);
          g.draw(rec);
          }
	  
  }
 
  public static void main(String[] args) {
    try {
      AppGameContainer appgc;
      appgc = new AppGameContainer(new Manualman("Manual Man Game"));
      appgc.setDisplayMode(640, 480, false);
      appgc.setMinimumLogicUpdateInterval(1000 / 60);
      appgc.start();
    } catch (SlickException ex) {
      Logger.getLogger(Manualman.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
