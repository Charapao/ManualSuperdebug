import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Topground {
	private float x;
    private float y;
    private float vx;
    public static final int WIDTH = 640;
  public static final int HEIGHT = 40;
  Image ground;
  public Topground(float x, float y,float vx) throws SlickException {
   
    this.x = x;
    this.y = y;
    this.vx = vx;
    ground = new Image("res/ground.png");
    
    
  }
  public void render() {
    ground.draw(x - WIDTH/2 , y - HEIGHT/2);
    
  }
   public void update() {
    x += vx;  
        if(this.x < -WIDTH)
		    {
        	this.x=Manualman.GAME_WIDTH+Manualman.GAME_WIDTH/2;
		    }
      
       
      
  }
    public float getX(){
    return x;
    }
    public float getY(){
    return y;
    }
}
