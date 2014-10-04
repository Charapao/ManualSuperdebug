

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Thorn {
private Image thornimage;
  
  protected float x;
  protected float y;
  protected float vx;
 public static final int WIDTH = 20;
  public static final int HEIGHT = 40;
 
public Thorn(float x, float y,float vx) throws SlickException {
    this.x = x;
    this.y = y;
    this.vx=vx;
 
     
    thornimage = new Image("res/thorn.png");
   
  }

public void update() {
        
       x += vx;  
       if(this.x < -WIDTH/2)
		    {
		    	this.x=100+640+100+640+320+100;
		    }
        
  }

public void render(){
  thornimage.draw(x - WIDTH/2, y - HEIGHT/2);



}
public float getX() {
    return x;
  }
   public float getY() {
    return y;
  }
}
