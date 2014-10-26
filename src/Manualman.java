import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Manualman extends BasicGame {
	// ค่าต่างๆภายในเกม
	public static float SwingJump = 0;// ถ้า = 0 คือ ล่วงลง ถ้า = 1 คือลอยขึ้น
	public static float G = (float) 0.5;
	public static final float PLAYER_JUMP_VY = 15;
	public static final int GAME_WIDTH = 640;
	public static final int GAME_HEIGHT = 480;
	public static final float Ground_VX = -4;
	public static final int NUMBER_OF_THORN = 20;
	public static int Score = 0;
	// ประกาศอ๊อบเจ็ค
	static public Player player;
	static public Ground[] grounds;
	static public Topground[] topGrounds;
	static public Middleground[] middleGrounds;
	static public Thorn[] thorns;
	static public Item WindWalk;

	// boolean ไว้เก็บค่าดช็นชน
	private boolean[] colliressGround;
	private boolean[] colliressTopGround;
	private boolean[] colliressMiddleGround;
	private boolean[] colliressThorn;
	// boolean ลบหนามระหว่างชองว่าง
	private boolean[] colliressForDeleteThornTop;
	private boolean[] colliressForDeleteThornMiddle;
	private boolean[] colliressForDeleteThornBottom;
	// Collider ไว้เช็ตชน
	public static Shape[] rectagle_Ground;
	public static Shape[] rectagle_TopGround;
	public static Shape[] rectagle_MiddleGround;
	public static Shape[] rectagle_Thorn;
	// boolean สำหรับ จบเกมกับรีเซ็ทเกม
	private boolean GAMEISOVER;
	private boolean GAMERESET;
	// Collider gap for collires thorn to delete them
	public static Shape[] rectagle_GapTopGround;
	public static Shape[] rectagle_GapMiddleGround;
	public static Shape[] rectagle_GapGround;
	// boolean check windwalkitem
	public static boolean INVISIBLE;
	public static LinkedList<Entity> entities;
	public static long lastframe;
	private Image Bg = null;

	public Manualman(String title) {
		super(title);
		entities = new LinkedList<Entity>();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GAMEISOVER = false;
		GAMERESET = false;
		INVISIBLE = false;
		Bg = new Image("res/map.png");  
		CREATE_PLAYER(); // กำหนดค่าเริ่มต้นให้ตัวละคร
		INIT_ALLGROUNDANDTHORN(); // กำหนดค่าเริ่มต้นให้ทุกพื้น
		INIT_COLLIRESS_ALL(); // กำหนดค่าบูลีน รับค่าเช็คชนทุกตัว เป็น false
		SHAPE_INITALL(); // กำหนดสร้าง Collider ไว้ตอนเริ่มเกม
		entities.add(new Item(500,60,Ground_VX));
		//WindWalk = new Item(500,60,Ground_VX); 
	}

	// Method in init {
	public static void CREATE_PLAYER() throws SlickException {

		player = new Player(GAME_WIDTH / 4, 300, 0);

	}

	public void INIT_COLLIRESS_ALL() {
		colliressGround = new boolean[3];
		for (int i = 0; i < colliressGround.length; i++) {
			colliressGround[i] = false;

		}
		colliressTopGround = new boolean[3];
		for (int i = 0; i < colliressTopGround.length; i++) {
			colliressTopGround[i] = false;

		}
		colliressMiddleGround = new boolean[3];
		for (int i = 0; i < colliressMiddleGround.length; i++) {
			colliressMiddleGround[i] = false;

		}
		colliressThorn = new boolean[NUMBER_OF_THORN];
		for (int i = 0; i < colliressThorn.length; i++) {
			colliressThorn[i] = false;

		}
		colliressForDeleteThornBottom = new boolean[3];
		for (int i = 0; i < 3; i++) {
			colliressForDeleteThornBottom[i] = false;

		}
		colliressForDeleteThornMiddle = new boolean[3];
		for (int i = 0; i < 3; i++) {
			colliressForDeleteThornMiddle[i] = false;

		}
		colliressForDeleteThornTop = new boolean[3];
		for (int i = 0; i < 3; i++) {
			colliressForDeleteThornTop[i] = false;

		}

	}
	public void checkThornCollideGap() {

		for( int i =0;i<NUMBER_OF_THORN;i++)
		{

		for(int j = 0;j<3;j++)
		{
		if(rectagle_Thorn[i].intersects(rectagle_GapGround[j]))
		{
		thorns[i].x = 100 + 640 + 100 + 640 + 320 + 100;
		System.out.println("kuy");
		return;
		}
		}

		for(int j = 0;j<3;j++)
		{
		if(rectagle_Thorn[i].intersects(rectagle_GapMiddleGround[j]))
		{
		thorns[i].x = 100 + 640 + 100 + 640 + 320 + 100;
		System.out.println("kuy");
		return;
		}
		}

		for(int j = 0;j<3;j++)
		{
		if(rectagle_Thorn[i].intersects(rectagle_GapTopGround[j]))
		{
		thorns[i].x = 100 + 640 + 100 + 640 + 320 + 100;
		System.out.println("kuy");
		return;
		}
		}
		}

		}

	public void SHAPE_INITALL() {
		rectagle_Ground = new Rectangle[3];
		for (int i = 0; i < 3; i++) {
			rectagle_Ground[i] = new Rectangle(GAME_WIDTH / 2 - 50 - 640
					+ ((640 + 100) * i), GAME_HEIGHT - 40, 640, 40);
		}
		rectagle_TopGround = new Rectangle[3];
		for (int i = 0; i < 3; i++) {
			rectagle_TopGround[i] = new Rectangle(0 * (GAME_WIDTH * i),
					Topground.HEIGHT / 2, 640, 40);
		}
		rectagle_MiddleGround = new Rectangle[3];
		for (int i = 0; i < 3; i++) {
			rectagle_MiddleGround[i] = new Rectangle(0 * (GAME_WIDTH * i),
					Middleground.HEIGHT / 2, 640, 40);
		}
		rectagle_Thorn = new Rectangle[NUMBER_OF_THORN];
		for (int i = 0; i < NUMBER_OF_THORN; i++) {
			rectagle_Thorn[i] = new Rectangle(0 * (GAME_WIDTH * i),
					Middleground.HEIGHT / 2, 10, 40);
		}
		rectagle_GapGround = new Rectangle[3];
		for (int i = 0; i < 3; i++) {
			rectagle_GapGround[i] = new Rectangle(-500,400,150,40);
		
		}
		rectagle_GapMiddleGround = new Rectangle[3];
		for (int i = 0; i < 3; i++) {
			rectagle_GapMiddleGround[i] = new Rectangle(-500,400,150,40);
		
		}
		rectagle_GapTopGround = new Rectangle[3];
		for (int i = 0; i < 3; i++) {
			rectagle_GapTopGround[i] = new Rectangle(-500,400,150,40);
		
		}
	}

	public void INIT_ALLGROUNDANDTHORN() throws SlickException {
		grounds = new Ground[3];
		for (int i = 0; i < 3; i++) {
			grounds[i] = new Ground(- 50 + ((640 + 100) * i), 
					GAME_HEIGHT - 20, Ground_VX);

		}
		topGrounds = new Topground[3];
		for (int i = 0; i < 3; i++) {
			topGrounds[i] = new Topground(50 + ((640 + 100) * i),
					Topground.HEIGHT / 2, Ground_VX);

		}
		middleGrounds = new Middleground[3];
		for (int i = 0; i < 3; i++) {
			middleGrounds[i] = new Middleground(-300 + ((640 + 100) * i),
					GAME_HEIGHT / 2, Ground_VX);
		}
		thorns = new Thorn[NUMBER_OF_THORN];
		for (int i = 0; i < NUMBER_OF_THORN; i++) {
			if (i < 5) {
				thorns[i] = new ThornVaryMiddleGround(400 + 240 * i, 203,
						Ground_VX);
			
			}
			if (i >= 5 && i <= 9) {
				thorns[i] = new ThornVaryGround(400 + 350 * (i - 5), 423,
						Ground_VX);
				 
				
			}
			if (i > 9 && i <= 14) {
				thorns[i] = new ThornVaryceillingmiddle(100 + 260 * (i - 10),
						278, Ground_VX);
				
			}
			if (i > 14 && i <= 19) {
				thorns[i] = new ThornVaryceilingTopground(200 + 320 * (i - 15),
						58, Ground_VX);
			
			}
			}
		
		
		}

	

	// }

	@Override
	public void update(GameContainer gc, int dt) throws SlickException {
		GAME_RESTART_BYPRESS_ENTER(gc);
		if (!GAMEISOVER) {
			PLAYER_UPDATE(); // อัพเดธตัวละครเกม
			UPDATE_ALL_GROUND(); // ไว้อัพเดธพื้นทุกพื้น
			CheckCollisionALL_GroundANDThorn(); // ไว้เช็คชน
			RECT_SETXYAUTO(); // ให้ Collider ไล่ตามพื้นต่างๆ อัตโนมัติ
			ENDOFEDGE();
			checkThornCollideGap();
			CheckCollisionALL_GroundANDThorn();
			for(Entity entity : entities) {
			      entity.update(dt);
			    }
			
			Score += 300;
			if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {

				player.jump();
			}
			TimingItem();

		}
	}

	private void TimingItem() {
		if(getTime() - Manualman.lastframe >=3000
				 ){
			lastframe = 0;
			INVISIBLE = false;
			System.out.println(lastframe);
		}
	}

	// Method in update {
	public void CheckCollisionALL_GroundANDThorn() {
		for (int j = 0; j < colliressGround.length; j++) {
			colliressGround[j] = Player.circleplayer.intersects(rectagle_Ground[j]);

			if (colliressGround[0] || colliressGround[1] || colliressGround[2]) {
				SwingJump = 0;
				player.y = 60;
				player.setVy(0);
			}
		}
		for (int j = 0; j < colliressTopGround.length; j++) {
			colliressTopGround[j] = Player.circleplayer
					.intersects(rectagle_TopGround[j]);
			if (colliressTopGround[0] || colliressTopGround[1]
					|| colliressTopGround[2]) {
				SwingJump = 1;
				player.y = 420;
				player.setVy(0);
			}
		}
		for (int j = 0; j < colliressMiddleGround.length; j++) {
			colliressMiddleGround[j] = Player.circleplayer
					.intersects(rectagle_MiddleGround[j]);
			// เช็คเคสชนล่างกับชนบน พื้นกลาง
			if ((player.y <= 280 && player.y >= 250)
					&& (colliressMiddleGround[0] || colliressMiddleGround[1] || colliressMiddleGround[2])) {
				player.y = 280;
				SwingJump = 0;
				player.setVy(0);
			}

			if ((player.y >= 200 && player.y <= 230)
					&& (colliressMiddleGround[0] || colliressMiddleGround[1] || colliressMiddleGround[2])) {
				player.y = 200;
				SwingJump = 1;
				player.setVy(0);
			}
		}
		for (int j = 0; j < colliressThorn.length; j++) {
			colliressThorn[j] = Player.circleplayer.intersects(rectagle_Thorn[j]);
			if (colliressThorn[j]&&!INVISIBLE) {
				
				GAMEISOVER = true;
				
			}
		}
		
	}

	public void UPDATE_ALL_GROUND() {
		for (Ground ground : grounds) {

			ground.update();

		}
		for (Topground topground : topGrounds) {

			topground.update();

		}
		for (Middleground middleground : middleGrounds) {

			middleground.update();

		}
		for (Thorn thorn : thorns) {

			thorn.update();
		}
	}

	public void PLAYER_UPDATE() {
		player.update();

	}

	public void GAME_RESTART_BYPRESS_ENTER(GameContainer gc)
			throws SlickException {
		if (GAMERESET) {
			gc.reinit();
			Score = 0;
		}
	}

	public void ENDOFEDGE() {
		if (player.getY() + Player.HEIGHT / 2 <= -20
				|| player.getY() - Player.HEIGHT / 2 >= 600) {
			GAMEISOVER = true;
		}
	}

	// }
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		 g.drawImage(Bg, 0, 0);
		player.render();
		RENDER_ALLGROUND(); // วาดพื้นล่างกลางบน
		// SETCOLOR_SHAPE(g); // เอาไว้ เซตสีของ colliderที่ไว้ใช้เช็คชน
		// WindWalk.render(g);
		if (GAMEISOVER) {

			g.drawString("GAME OVER", 320 - 40, 240 - 10);
			g.drawString("Score : " + Score, 500, 450);

		}
		 for (Entity entity : entities) {
		      entity.render(g);
		    }
	}

	// Method in render {
	public void RENDER_ALLGROUND() {
		for (Ground ground : grounds) {

			ground.render();

		}
		for (Topground topground : topGrounds) {

			topground.render();

		}
		for (Middleground middleground : middleGrounds) {

			middleground.render();

		}
		for (Thorn thorn : thorns) {

			thorn.render();

		}

	}

	public static void SETCOLOR_SHAPE(Graphics g) {
		g.setColor(Color.yellow);
		g.fill(Player.circleplayer);
		g.setColor(Color.pink);
		g.draw(Player.circleplayer);

		for (Shape rec : rectagle_Ground) {
			g.setColor(Color.yellow);
			g.fill(rec);
			g.setColor(Color.pink);
			g.draw(rec);
		}

		for (Shape rec : rectagle_TopGround) {
			g.setColor(Color.yellow);
			g.fill(rec);
			g.setColor(Color.pink);
			g.draw(rec);
		}
		for (Shape rec : rectagle_MiddleGround) {
			g.setColor(Color.yellow);
			g.fill(rec);
			g.setColor(Color.pink);
			g.draw(rec);
		}
		for (Shape rec : rectagle_Thorn) {
			g.setColor(Color.yellow);
			g.fill(rec);
			g.setColor(Color.pink);
			g.draw(rec);
		}
		for (Shape rec : rectagle_GapGround) {
			g.setColor(Color.red);
			g.fill(rec);
			g.setColor(Color.white);
			g.draw(rec);
		}
		for (Shape rec : rectagle_GapTopGround) {
			g.setColor(Color.red);
			g.fill(rec); 
			g.setColor(Color.white);
			g.draw(rec);
		}
		for (Shape rec : rectagle_GapMiddleGround) {
			g.setColor(Color.red);
			g.fill(rec);
			g.setColor(Color.white);
			g.draw(rec);
		}

	}

	public void RECT_SETXYAUTO() {
		for (int temp = 0; temp < 3; temp++) {
			rectagle_Ground[temp].setCenterX(grounds[temp].getX());
			rectagle_Ground[temp].setCenterY(grounds[temp].getY());
		}
		for (int temp = 0; temp < 3; temp++) {
			rectagle_TopGround[temp].setCenterX(topGrounds[temp].getX());
			rectagle_TopGround[temp].setCenterY(topGrounds[temp].getY());
		}
		for (int temp = 0; temp < 3; temp++) {
			rectagle_MiddleGround[temp].setCenterX(middleGrounds[temp].getX());
			rectagle_MiddleGround[temp].setCenterY(middleGrounds[temp].getY());
		}
		for (int temp = 0; temp < NUMBER_OF_THORN; temp++) {
			rectagle_Thorn[temp].setCenterX(thorns[temp].getX());
			rectagle_Thorn[temp].setCenterY(thorns[temp].getY());
		}
		for (int temp = 0; temp < 3; temp++) {
			rectagle_GapGround[temp].setCenterX(grounds[temp].getX()+370);
			rectagle_GapGround[temp].setCenterY(grounds[temp].getY());
		}
		for (int temp = 0; temp < 3; temp++) {
			rectagle_GapMiddleGround[temp].setCenterX(middleGrounds[temp].getX()+370);
			rectagle_GapMiddleGround[temp].setCenterY(middleGrounds[temp].getY());
		}
		for (int temp = 0; temp < 3; temp++) {
			rectagle_GapTopGround[temp].setCenterX(topGrounds[temp].getX()+370);
			rectagle_GapTopGround[temp].setCenterY(topGrounds[temp].getY());
		}
	}

	// }
	@Override
	public void keyPressed(int key, char c) {
		// if (key == Input.KEY_SPACE) {

		// player.jump();
		// }
		if (key == Input.KEY_ENTER) {
			GAMERESET = true;
		}
	}

	public long getTime() {
	    return (System.nanoTime() /1000000);
	}
	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Manualman("Manual Man Game"));
			appgc.setDisplayMode(640, 480, false);
			appgc.setMinimumLogicUpdateInterval(1000 / 60);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(Manualman.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}
}
