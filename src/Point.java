
public class Point {
	private int x;
	private int y;
	
	public Point (int x, int y){
		this.x = x;
		this.y = y;
	}
	public Point (){
		this (0, 0);
	}
	
	//accessors
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	//modifiers
	public void setX(int i){
		this.x = i;
	}
	
	public void setY(int i){
		this.x = i;
	}
	
	//inter-point relations
	public Point add (Point p){
		return new Point(this.getX() + p.getX(), this.getY() + p.getY());
	}
	
	public Point subtract (Point p){
		return new Point(this.getX() - p.getX(), this.getY() - p.getY());
	}
	
	public boolean equals (Object o){
		if (o instanceof Point){
			Point p = (Point)o;
			return p.getX() == this.getX() && p.getY() == this.getY();
		}
		return false;
	}
	
	//we're only doing comparison to a known value, no need to work with square roots
	public double distSquared (Point p){
		return Math.pow(p.getX() - this.getX(), 2) + Math.pow(p.getY() - this.getY(), 2);
	}
	
	//standard object methods
	public Object clone(){
		return new Point (this.x, this.y);
	}
	
	public String toString(){
		return "X: " + this.x + ", Y: " + this.y;
	}
}
