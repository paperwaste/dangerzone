package beng.jumperB;
import java.util.ArrayList;

/**
 * Describes a representation of a board cell contained in a {@link GameState} within the context of the game Jumper.
 * Contains the contents of the cell, references to adjacent cells as defined,
 * the coordinates of the cell in the board, and some constants.
 *  
 * @author Ben
 *
 */
public class Cell {
	/**
	 * Defines the value representing a dead cell.
	 */
	public static final char DEAD = 'X';
	/**
	 * Defines the value representing an empty cell.
	 */
	public static final char EMPTY = '-';
	/**
	 * Defines the value representing a white token.
	 */
	public static final char WHITE = 'W';
	/**
	 * Defines the value representing a black token.
	 */
	public static final char BLACK = 'B';
	
	/**
	 * Defines a list of <code>enum</code> actions that can be passed into methods of class {@link Cell}.
	 * @author Ben
	 *
	 */
	public enum CellAction {
		LINK,
		UNLINK,
		
		ADD,
		DELETE,
		
		INITIALISE,
		APPEND,
		REPLACE
	}
	
	/**
	 * Defines the <code>boolean</code> value representing the intention to induce
	 * reciprocation of a {@link Cell} to <code>Cell</code> interaction. 
	 */
	public static final boolean RECIPROCATE = true;
	/**
	 * Defines the <code>boolean</code> value representing the intention <i>not</i> to induce
	 * reciprocation of a {@link Cell} to <code>Cell</code> link action. 
	 */
	public static final boolean NO_RECIPROCATE = false;
	
	/**
	 * Holds the coordinates of this {@link Coord} within the context of a {@link GameState} representation of a Jumper board.
	 */
	private Coord coords;
	
	/**
	 * The char representation of the cell contents, taking one of the values <code>WHITE, BLACK, DEAD, EMPTY</code>
	 */
	private char contents;
	
	/**
	 * A list of cells adjacent to this cell within the context of a {@link GameState} representation of a Jumper board.
	 */
	private ArrayList<Cell> adjacentCells;	
	
	/**
	 * Private class constructor specifying the coordinates of this cell.
	 * @param x the x-ordinate
	 * @param y the y-ordinate
	 */
	private Cell(int x, int y){
		this.coords = new Coord(x, y);
		this.adjacentCells = new ArrayList<Cell>();
	}
	
	/**
	 * Class constructor specifying the coordinates of this cell and its contents.
	 * @param x the x-ordinate
	 * @param y the y-ordinate
	 * @param contents the contents of the cell
	 */
	public Cell(int x, int y, char contents){
		this(x, y);
		setContents(contents);
	}
	
	/**
	 * Class constructor specifying the coordinates of this cell and its contents, along with a list of neighboring cells.
	 * @param x the x-ordinate
	 * @param y the y-ordinate
	 * @param contents the contents of the cell
	 * @param adjacentCells the list of references to neighboring cells
	 */
	public Cell(int x, int y, char contents, ArrayList<Cell> adjacentCells){
		this(x, y, contents);
		setAdjacentCells(adjacentCells, CellAction.LINK, false);
	}
	


	
	/**
	 * 
	 * @return the x-ordinate of the cell's position.
	 */
	public int getX() {
		return coords.getX();
	}
	
	/**
	 * 
	 * 
	 * @return the y-ordinate of the cell's position.
	 */
	public int getY() {
		return coords.getY();
	}
	
	/**
	 * 
	 * @return true iff this Cell contains a player token.
	 */
	public boolean isPlayer(){
		return(this.isBlack()||this.isWhite());
	}
	
	/**
	 * Returns 
	 * @param mContents the query against which the contents of this cell is tested
	 * @return true iff the cell contains the queried contents
	 */
	public boolean isType(char mContents){
		return(this.contents==mContents);
	}
	
	/**
	 * 
	 * @return true iff the cell contains the token <code>WHITE</code>.
	 */
	public boolean isWhite(){
		return(this.contents==WHITE);
	}
	/**
	 * 
	 * @return true iff the cell contains the token <code>BLACK</code>.
	 */
	public boolean isBlack(){
		return(this.contents==BLACK);
	}
	
	/**
	 * 
	 * @return true iff the cell contents is <code>EMPTY</code>.
	 */
	public boolean isEmpty(){
		return(this.contents==EMPTY);
	}
	/**
	 * 
	 * @return true iff the cell is <code>DEAD</code>
	 */
	public boolean isDead(){
		return(this.contents==DEAD);
	}
	/**
	 * 
	 * @return the contents of the cell.
	 */
	public char getContents(){
		return this.contents;
	}
	
	/**
	 * Sets the contents of this cell.<p>
	 * If the new contents is equal to <code>DEAD</code>,
	 * calls <code>clearAdjacentCells</code>with parameter <code>RECIPROCATE</code>. This has the effect of clearing all links to this node, removing it from searches.   
	 * @param newContents the new contents of the {@link Cell}
	 */
	public void setContents(char newContents){
		this.contents = newContents;
		if(newContents == DEAD){
			clearAdjacentCells(RECIPROCATE);
		}
	}
	
	/**
	 * 
	 * @return a reference to <code>adjacentCells</code>
	 */
	public ArrayList<Cell> getAdjacentCells() {
		return adjacentCells;
	}
	
	/**
	 * Initialises, appends or replaces <code>adjacentCells</code> with a new list.
	 * @param adjacentCells the new list
	 * @param action one of <code>INITIALISE, APPEND, REPLACE</code> 
	 * @param reciprocate <code>true</code> induces linking of <i>this</i> Cell from each Cell in <b>adjacentCells</b>  
	 */
	private void setAdjacentCells(ArrayList<Cell> adjacentCells, CellAction action, boolean reciprocate){
		switch(action){
		case INITIALISE:
			if(!this.adjacentCells.isEmpty()){
				System.err.println("ERROR in setAdjacentCells(): adjacentCells non-empty");
				System.exit(4);
			}
			break;
		case APPEND:
			break;
		case REPLACE:
			this.clearAdjacentCells(reciprocate);
			break;
		default:
			System.err.println("ERROR in induceLinkReciprocation(): invalid action");
			System.exit(3);
			break;
		}
		
		this.adjacentCells.addAll(adjacentCells);
		if(reciprocate==RECIPROCATE){
			for(Cell neighbour : adjacentCells){
				neighbour.addAdjacentCell(this, NO_RECIPROCATE);
			}
		}
	}
	
	/**
	 * Clears <code>adjacentCells</code>. If <b>reciprocateLinkage</b> is true, indices cells that existed in <code>adjacentCells</code> to delete their reference to this Cell.
	 * @param reciprocateLinkage
	 */
	private void clearAdjacentCells(boolean reciprocateLinkage){
		//Iterate over adjacentCells list for cell
		if(this.adjacentCells==null){
			System.err.println("ERROR in clearAdjacentCells(): adjacentCells is null");
			System.exit(4);
		}
		if(reciprocateLinkage==RECIPROCATE){
			for(Cell toDelete : adjacentCells){
				toDelete.deleteAdjacentCell(this, NO_RECIPROCATE);
			}
		}
		adjacentCells.clear();
	}
	
	/**
	 * Adds a {@link Cell} to the list of <code>Cell</code>s adjacent to this <code>Cell</code>.<p>
	 * If <b>reciprocate</b> is set, causes the cell <code>toAdd</code> to create a link to this cell.
	 * @param toAdd the <code>Cell</code> to add
	 * @param reciprocate
	 */
	public void addAdjacentCell(Cell toAdd, boolean reciprocate){
		if(adjacentCells.contains(toAdd)){
			System.err.println("ERROR in addAdjacentCell(): cell already present");
			System.exit(4);
		} else if(this.contents==DEAD){
			System.err.println("ERROR in addAdjacentCell(): cell is DEAD");
			System.exit(5);
		} else {
			this.adjacentCells.add(toAdd);
			if(reciprocate==RECIPROCATE){
					toAdd.addAdjacentCell(this, NO_RECIPROCATE);
			}
		}
	}
	
	/**
	 * Removes <code>toDelete</code> from the list of <code>Cell</code>s adjacent to this <code>Cell</code>.<p>
	 * If <b>reciprocate</b> is set, causes the cell <code>toAdd</code> to delete its link to this cell. 
	 * @param toDelete the <code>Cell</code> to delete
	 * @param reciprocateLinkage
	 */
	private void deleteAdjacentCell(Cell toDelete, boolean reciprocateLinkage){
		if(!adjacentCells.contains(toDelete)){
			System.err.println("ERROR in deleteAdjacentCell(): cell not present");
			System.exit(5);
		}
		if(reciprocateLinkage==RECIPROCATE){
			toDelete.deleteAdjacentCell(this, NO_RECIPROCATE);
		}
		adjacentCells.remove(toDelete);
	}
	
	/**
	 * Removes the <cell>Cell</code> specified by <b>x</b> and <b>y</b> from the list of <code>Cell</code>s adjacent to this <code>Cell</code>, if it exists.<p>
	 * If <b>reciprocate</b> is set, causes the cell <code>toAdd</code> to delete its link to this cell. 
	 * @param x the x-ordinate of the <code>Cell</code> targeted for deletion
	 * @param y the y-ordinate of the <code>Cell</code> targeted for deletion
	 * @param reciprocateLinkage
	 */
	public void deleteAdjacentCell(int x, int y, boolean reciprocateLinkage){
		
			// Iterate over this.adjacentCells to find match
			for(Cell match : adjacentCells){
				if(match.coords.getX() == x && match.coords.getY() == y){
					if(reciprocateLinkage==RECIPROCATE){ // Stops infinite call pairs / unnecessary duplicate invocation
						match.deleteAdjacentCell(this, NO_RECIPROCATE);
					}
					this.adjacentCells.remove(match);
					break;		
				}
			}
		}

	/**
	 * 
	 * @return a formatted string containing the coordinates of this <code>Cell</code>.
	 */
	public String getCoords(){
		String strCoords = new String(Integer.toString(this.coords.getX())+" "+Integer.toString(this.coords.getY()));
		return strCoords;
	}
	
	/**
	 * 
	 * @param other the other Object to test
	 * @return {@link true} if and only if the members of this cell are equal to that of the argument cell, ignoring their <code>adjacentCells</code> lists.
	 */
	@Override
	public boolean equals(Object other){
		if(other == null) return false;
		if(other == this) return true;
		if(!(other instanceof Cell)) return false;
		
		Cell otherCell = (Cell)other;
		if(this.getContents() != otherCell.getContents()) return false;
		if(!this.coords.equals(otherCell.coords)) return false;
		return true;		
	}
	
	/**
	 * Converts the coordinates and contents of this cell to <code>String</code> format.
	 */
	@Override
	public String toString(){
		return "[" + this.coords.getX() + "," + this.coords.getY() + "]: " + this.getContents();
	}
}
