import org.newdawn.slick.SlickException;


public class ThornVaryceillingmiddle extends Thorn{

	public ThornVaryceillingmiddle(float x, float y, float vx)
			throws SlickException {
		super(x, y, vx);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void update() {
		 if(vx >= -5)
			   vx -= 0.0007;
	       x += vx;  
	       if(this.x < -WIDTH)
			    {
			    	this.x=100+640+100+640+320+100;
			    }
	        
	  }

}
