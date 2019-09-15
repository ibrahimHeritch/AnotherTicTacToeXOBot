package grid;

import java.util.Arrays;

public class Grid {
	/*needs to be rewriten and split in 2 classes
	 * class grid first term= number of taken spaces x=1 o=-1 free space=0
	 */

	private int[] grid;
	private int[] moves;
	private Judge judge;
	/*
	 * constructor
	 */

	public Grid() {// Default grid used for xo 3x3
		grid = new int[10];// all are free
        moves= new int[9];
        judge=new Judge();
	}
	/*
	 * copy object
	 */
	public Grid(Grid g){
		this.grid=g.getArr();
		moves= g.getArrMoves();
		judge=new Judge();
	}
	/**
	 * get current value at position as int
	 */
	public int getLastMove(){
		if(getSpaces()==0)return -1;
		return moves[getSpaces()-1];
	}
	public int getPosInt(int key) {
		return grid[key];
	}
	/*
	 * gets current at position as char(* if empty)
	 */
	public char getPosChar(int key) {
		int i = grid[key];
		if (i != 0) {
			return (i == 1 ? 'X' : 'O');
		} else {
			return '*';
		}

	}

	/*
	 * get number of taken spaces
	 */
	public int getSpaces() {
		return grid[0];
	}
	/*
	 * get an array of all free spases
	 */
	public int[] getFree(){
		int[] free=new int[9-grid[0]];
		int j=0;
		for(int i=1;i<10;i++){
			if(checkAvb(i)){
				free[j]=i;
				j++;
			}
		}
		return free;
	}

	/*
	 * gets next player to play (whose turn it is)
	 */
	public char getTurn() {
		if (grid[0] % 2 == 0) {
			return 'x';
		} else {
			return 'o';
		}
	}
	
	public int getTurnInt(){
		if (grid[0] % 2 == 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/*
	 * get grid in array form
	 */
	public int[] getArr() {
		return grid.clone();
	}
	
	public int[] getArrMoves() {
		return moves.clone();
	}
	/**
	 * 
	 * frees the position (sets it back to 0)
	 * @param i
	 */
    public void freeSpace(int i){
    	if(i>0 && i<10){
    		grid[i]=0;
    		grid[0]--;
    	}
    }
	/**
	 * sets a position as x or o
	 * only accepts x or o
	 */
	public Grid setPos(int key, char c) {
		if(grid[key]==0 && key>0 && key<10){
			if (Character.toLowerCase(c) == 'x') {
				grid[key] = 1;
			} else if (Character.toLowerCase(c) == 'o') {
				grid[key] = -1;
			}else {
				throw new IllegalArgumentException(c + " must be x or o");
			}
			moves[grid[0]++]=key;
			return this;
		}else{
			throw new IllegalArgumentException(key + " not in range or taken");
		}
	}

	
	
	public boolean checkAvb(int pos){
		if(getPosInt(pos)==0){
			return true;
		}
		return false;
	}
	public int checkWin() {
		return judge.checkWin(this);
	}
	/**
	 * checks for possible wins aka x*x xx* *xx or o equivalent
	 */
	public int checkPosWin() {
		return judge.checkPosWin(this);
	}
	
	
	/*
	 * Prints grid
	 */
	public void printGrid(){
		for(int i=1;i<10;i+=3){
			System.out.println(""+this.getPosChar(i)+" "+this.getPosChar(i+1)+" "+this.getPosChar(i+2));
		}
	}

	@Override
	public String toString() {
		return "Grid [grid=" + Arrays.toString(grid) + ", pos_win=" + "]";
	}
	@Override
	public int hashCode() {
		return Arrays.hashCode(Arrays.copyOfRange(grid, 1, 10));
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grid other = (Grid) obj;
		if (!Arrays.equals(grid, other.grid))
			return false;
		return true;
	}

}
