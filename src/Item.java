import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Item implements Entity{
	private float x;
	private float y;
	private float vx;
	public Shape Rec_Windwalk;
	public static final int WIDTH = 64;
	public static final int HEIGHT = 64;
	Image windwalk;

	public Item(float x, float y, float vx) throws SlickException {

		this.x = x;
		this.y = y;
		this.vx = vx;
		windwalk = new Image("res/lothar.png");
		Rec_Windwalk = new Rectangle(-500, 200, 64, 64);
		
	}
	@Override
	public void render(Graphics g) {
		windwalk.draw(x - WIDTH / 2, y - HEIGHT / 2);
		
		//g.setColor(Color.red);
		//g.fill(Rec_Windwalk);
		//g.setColor(Color.white);
	//	g.draw(Rec_Windwalk);
	}
	
	

		public float getX() {
			return x;
		}

		public float getY() {
			return y;
		
	}
		@Override
		public void update(int delta) {
			Rec_Windwalk.setCenterX(x);
			Rec_Windwalk.setCenterY(y);
			if (vx >= -8)
				vx -= 0.002;
			x += vx;
		
			if(Player.circleplayer.intersects(Rec_Windwalk)){
			 Manualman.INVISIBLE = true;
			 System.out.print("abc");
			Manualman.lastframe = getTime();
			
			}
			if(Manualman.INVISIBLE==true){
				 
				
				Manualman.entities.remove();
			}
			if(getTime() - Manualman.lastframe >=300
					 ){
				Manualman.lastframe = 0;
				Manualman.INVISIBLE = false;
				System.out.println(Manualman.lastframe);
			}
			

			
		}
		
		public long getTime() {
		    return (System.nanoTime() /1000000);
		}
}
