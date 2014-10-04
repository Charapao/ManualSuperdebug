import org.newdawn.slick.SlickException;


public class ThornVaryceilingTopground extends Thorn{

	public ThornVaryceilingTopground(float x, float y, float vx)
			throws SlickException {
		super(x, y, vx);
		// TODO Auto-generated constructor stub
	}
@Override
public void update() {
	   if(vx >= -6.5)
		   vx -= 0.0004;
	   x += vx;  
     if(this.x < -WIDTH)
		    {
     	this.x=Manualman.GAME_WIDTH+Manualman.GAME_WIDTH/2;
		    }
}
}
