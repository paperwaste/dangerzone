package beng.jumperB;


public class GameState {
	
	private Cell[] board;
	private int size;

	public GameState(int size, Cell[] board) {
		super();
		this.board = board;
		this.size = size;
	}

	public Cell[] getBoard() {
		return board;
	}

	public int getSize() {
		return size;
	}
	public Cell getCell(int x, int y){
		int i = this.size * y + x % this.size;
		return board[i];
	}
	public void setCell(int x, int y, Cell newCell){
		int i = this.size * y + x % this.size;
		board[i] = newCell;
	}
}