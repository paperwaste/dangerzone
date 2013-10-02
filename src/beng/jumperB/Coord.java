package beng.jumperB;
/**
 * Defines the coordinates of a {@link Node} on a Jumper board.
 * @author Ben Gray 521247
 * @author
 *
 */
public class Coord {
	private int x, y;
	
	/**
	 * Intialises the coordinate point
	 * @param x the input x-ordinate
	 * @param y the input y-ordinate
	 */
	Coord(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x-ordinate of this coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x-ordinate to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y-ordinate of this coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y-ordinate to set
	 */
	public void setY(int y) {
		this.y = y;
	}
}
