import java.awt.Image;
import java.util.ArrayList;

public enum Piece {
	PAWN (70, 30, PieceData.pawnMovement(), PieceData.pawnImage()),
	ROOK (500, 50, PieceData.rookMovement(), PieceData.rookImage()),
	KNIGHT (150, 50, PieceData.knightMovement(), PieceData.knightImage()),
	BISHOP (200, 40, PieceData.bishopMovement(), PieceData.bishopImage()),
	QUEEN (500, 80, PieceData.queenMovement(), PieceData.queenImage()),
	KING (1500, 40, PieceData.kingMovement(), PieceData.kingImage());
	
	private int player;
	private int maxHealth;
	private int curHealth;
	private int attack;
	private Image[] image;
	private ArrayList<Point> moves;
	private Point curPos = null;
	
	private Piece (int health, int attack, ArrayList<Point> moves, Image[] image){
		this.maxHealth = health;
		this.curHealth = health;
		this.attack = attack;
		this.moves = moves;
		this.player = 0;
	}
	
	//accessors
	public int getMaxHealth(){
		return this.maxHealth;
	}
	public int getCurrentHealth(){
		return this.curHealth;
	}
	public double getPercentageHealth(){
		return this.curHealth/this.maxHealth;
	}
	public int getAttack(){
		return this.attack;
	}
	public ArrayList<Point> getMoves(){
		return this.moves;
	}
	public Point getPos(){
		return this.curPos;
	}
	public boolean isDead(){
		return this.curHealth >= 0;
	}
	public int getPlayer(){
		return this.player;
	}
	
	
	//relative move
	public boolean validRelMove(Point p){
		return validAbsMove(this.getPos().add(p));
	}
	//absolute position/move
	public boolean validAbsMove(Point p){
		boolean temp = p.getX() < 8;
		temp = temp && p.getX() > -1;
		temp = temp && p.getY() < 8;
		return temp && p.getX() > -1;
	}
	public Image getImage(){
		if (!(this.getPlayer() == 1 || this.getPlayer() == 2)){
			return null;
		}
		return image[this.getPlayer() - 1];
	}
	
	
	//modifiers
	public void setPlayer(int i) throws Exception{
		if (!(i == 1 || i == 2)){
			throw new Exception ("Bad argument passed to setPlayer(int i) in Piece.java");
		}
		this.player = i;
	}
	public void setPos (int x, int y){
		this.curPos = new Point (x, y);
	}
	public void setPos (Point p){
		this.curPos = p;
	}
	public void setHealth (int h){
		this.curHealth = h;
	}
	public void doDamage (int d){
		this.curHealth = this.curHealth - d;
	}
	public void doHeal (int h){
		this.curHealth = this.curHealth + h;
	}
}