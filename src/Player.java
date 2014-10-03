import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;


public class Player {
	public static Shape circleplayer;
	public static float x;
	public static float y;
	public static float vy;
	private float vjump;
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;
    private Image image;
    public Player(float x, float y,float vjump) throws SlickException {
        this.x = x;
        this.y = y;
        this.vy = vjump;
        this.vjump = Manualman.PLAYER_JUMP_VY;
        image = new Image("res/Manaul.png");
        circleplayer = new Circle(0,0,20);
        
      }
    public void render() {
        image.draw(x - WIDTH/2, Manualman.GAME_HEIGHT - (y + HEIGHT/2));
       
      }
        public void update() {
        	  circleplayer.setCenterX(x);
              circleplayer.setCenterY(Manualman.GAME_HEIGHT-y);
              if(Manualman.Swingjump==0){
              y += vy;
              vy -= Manualman.G;
              }
              else if(Manualman.Swingjump==1){
              y -= -vy;
              vy += Manualman.G;
            	  
              }
             
       }
        public void jump(){
        	if(Manualman.Swingjump==0)
        	vy = vjump;
        	else vy = -vjump;
        	
        }
        public void setVy(float vy) {
            this.vy = vy;
          }
            public float getX(){
            return x;
            }
            public float getY(){
            return y;
            }
}
