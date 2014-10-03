import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
 
public class Manualman extends BasicGame {
	// ค่าต่างๆภายในเกม
	public static float Swingjump = 0;// ถ้า = 0 คือ ล่วงลง ถ้า = 1 คือลอยขึ้น
	public static float G = (float) 0.5;
    public static final float PLAYER_JUMP_VY = 20;
    public static final int GAME_WIDTH = 640;
    public static final int GAME_HEIGHT = 480;
    public static final float Ground_VX = -4;
    // ประกาศอ๊อบเจ็ค
    static public Player player;
    static public Ground[] grounds;
    static public Topground[] topgrounds;
    static public Middleground[] middlegrounds;
    // boolean ไว้เก็บค่าดช็นชน
    private boolean[] colliressground;
    private boolean[] colliresstopground;
    private boolean[] colliressmiddleground;
    // Collider ไว้เช็ตชน
    public static Shape[] rectground;
    public static Shape[] recttopground;
    public static Shape[] rectmiddleground;
    //boolean สำหรับ จบเกมกับรีเซ็ทเกม
    private boolean GAMEISOVER ;
    private boolean GAMERESET ;
    public Manualman(String title) {
		super(title);
		
	}


 
  @Override
  public void init(GameContainer gc) throws SlickException { 
	  GAMEISOVER = false;
      GAMERESET= false;
	  CREATE_PLAYER(); //กำหนดค่าเริ่มต้นให้ตัวละคร
	  INIT_ALLGROUND(); //กำหนดค่าเริ่มต้นให้ทุกพื้น
	  INIT_COLLIRESS_ALL(); //กำหนดค่าบูลีน รับค่าเช็คชนทุกตัว เป็น false
	  SHAPE_INITALL(); //กำหนดสร้าง Collider ไว้ตอนเริ่มเกม
	  }
  // Method in init {
 public static void CREATE_PLAYER() throws SlickException{

	  
	  player = new Player(GAME_WIDTH/4+200, 300, 0);
	  
  }
  public void INIT_COLLIRESS_ALL(){
	    	colliressground = new boolean[3];
	        for(int i = 0;i< colliressground.length;i++){
	        colliressground[i]= false;
	        
	        }
	        colliresstopground = new boolean[3];
	        for(int i = 0;i< colliresstopground.length;i++){
		        colliresstopground[i]= false;
		        
		        }
	        colliressmiddleground = new boolean[3];
	        for(int i = 0;i< colliressmiddleground.length;i++){
	        colliressmiddleground[i]= false;
	        
	        }
	    
	    }
  public void SHAPE_INITALL(){
	  rectground = new Rectangle[3];
	  for(int i=0; i < 3; i++){
	        rectground[i] = new Rectangle(GAME_WIDTH/2-50-640+((640+100)*i),GAME_HEIGHT-40,640,40);
	        }
	  recttopground = new Rectangle[3];
	  for(int i=0; i < 3; i++){
	        recttopground[i] = new Rectangle(0*(GAME_WIDTH*i), Topground.HEIGHT/2,640,40);
	        }
	  rectmiddleground = new Rectangle[3];
	  for(int i=0; i < 3; i++){
	        rectmiddleground[i] = new Rectangle(0*(GAME_WIDTH*i), Middleground.HEIGHT/2,640,40);
	        }
  }
  public void INIT_ALLGROUND() throws SlickException{
	  grounds = new Ground[3]; 
      for (int i = 0; i < 3; i++) {
      grounds[i] = new Ground(GAME_WIDTH-320-50-GAME_WIDTH/2+((640+100)*i), GAME_HEIGHT-20,Ground_VX);
      
      }
      topgrounds = new Topground[3]; 
      for (int i = 0; i < 3; i++) {
      topgrounds[i] = new Topground(50+((640+100)*i), Topground.HEIGHT/2,Ground_VX);
      
      }
      middlegrounds = new Middleground[3];
      for (int i = 0; i < 3; i++) {
     middlegrounds[i] = new Middleground(-300+((640+100)*i), GAME_HEIGHT/2,Ground_VX);
    
    }
	  
  }
  //}
  
  
 
  @Override
  public void update(GameContainer gc, int dt) throws SlickException {
	  GAME_RESTART_BYPRESS_ENTER(gc);
	  if(!GAMEISOVER){
	  PLAYER_UPDATE(); //อัพเดธตัวละครเกม
	  UPDATE_ALL_GROUND(); //ไว้อัพเดธพื้นทุกพื้น
	  CheckCollisionALL_Ground(); //ไว้เช็คชน
	  RECT_SETXYAUTO(); //ให้ Collider ไล่ตามพื้นต่างๆ อัตโนมัติ
	  ENDOFEDGE();
	  }
  }
  // Method in update {
  public void CheckCollisionALL_Ground(){
	  for(int j= 0;j<colliressground.length;j++){
	      colliressground[j]=Player.circleplayer.intersects(rectground[j]);
	      
	      if(colliressground[0]||colliressground[1]||colliressground[2]){
	    	  Swingjump=0;
	        player.y=61;
	        player.setVy(0); 
	        }
	      }
	  for(int j= 0;j<colliresstopground.length;j++){
	      colliresstopground[j]=Player.circleplayer.intersects(recttopground[j]);
	      if(colliresstopground[0]||colliresstopground[1]||colliresstopground[2]){
	    	  Swingjump=1;
	        player.y=420;
	        player.setVy(0); 
	        }
	      }
	  for(int j= 0;j<colliressmiddleground.length;j++){
		  colliressmiddleground[j]=Player.circleplayer.intersects(rectmiddleground[j]);
		  //เช็คเคสชนล่างกับชนบน พื้นกลาง
		  if((player.y<=280&&player.y>=250)&&(colliressmiddleground[0]||colliressmiddleground[1]||colliressmiddleground[2])){
	          player.y=280;
	          Swingjump=0;
	          player.setVy(0); }
	            
	            if((player.y>=200&&player.y<=230)&&(colliressmiddleground[0]||colliressmiddleground[1]||colliressmiddleground[2])){
	          player.y=200;
	          Swingjump=1;
	          player.setVy(0); }
	  }
	  
	  
  }
  public void UPDATE_ALL_GROUND(){
	  for (Ground ground : grounds) {
      
      ground.update();
      
 }
 for (Topground topground : topgrounds) {
      
      topground.update();
      
 }
 for (Middleground middleground : middlegrounds) {
     
     middleground.update();
     
}
 }
  public void PLAYER_UPDATE(){
	  player.update();
	  
  }
  public void GAME_RESTART_BYPRESS_ENTER(GameContainer gc) throws SlickException{ if(GAMERESET){
      gc.reinit();
      }}
  public void ENDOFEDGE(){
      if(player.getY()+Player.HEIGHT/2<=-20||player.getY()-Player.HEIGHT/2>=600){
      GAMEISOVER= true;
      }
      }
  // }
  @Override
  public void render(GameContainer gc, Graphics g) throws SlickException {
	  player.render();
	  RENDER_ALLGROUND(); // วาดพื้นล่างกลางบน
	  SETCOLOR_SHAPE(g); // เอาไว้  เซตสีของ colliderที่ไว้ใช้เช็คชน
	  if(GAMEISOVER){
          g.drawString("GAME OVER", 320-40, 240-10);
          }
  }
  //Method in render {
  public void RENDER_ALLGROUND(){
 for (Ground ground : grounds) {
          
          ground.render();
          
     }
  for (Topground topground : topgrounds) {
          
          topground.render();
          
     }
  for (Middleground middleground : middlegrounds) {
      
      middleground.render();
      
 }
  
	  
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
      
      for(Shape rec : recttopground){
          g.setColor(Color.yellow);
          g.fill(rec);
          g.setColor(Color.pink);
          g.draw(rec);
          }
      for(Shape rec : rectmiddleground){
          g.setColor(Color.yellow);
          g.fill(rec);
          g.setColor(Color.pink);
          g.draw(rec);
          }
	  
  }
  public void RECT_SETXYAUTO(){  
	  for(int temp = 0; temp < 3; temp++){
      rectground[temp].setCenterX(grounds[temp].getX());
      rectground[temp].setCenterY(grounds[temp].getY()); 
      }
	  for(int temp = 0; temp < 3; temp++){
	        recttopground[temp].setCenterX(topgrounds[temp].getX());
	        recttopground[temp].setCenterY(topgrounds[temp].getY()); 
	        }
	  for(int temp = 0; temp < 3; temp++){
	        rectmiddleground[temp].setCenterX(middlegrounds[temp].getX());
	        rectmiddleground[temp].setCenterY(middlegrounds[temp].getY()); 
	        }
  }
 
  // }
  @Override
  public void keyPressed(int key, char c) {
          if (key == Input.KEY_SPACE) {
        	
        	  player.jump();  
          }
          if (key == Input.KEY_ENTER) {
              GAMERESET = true;
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
