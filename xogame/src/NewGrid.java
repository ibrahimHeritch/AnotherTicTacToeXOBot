import grid.Grid;
import grid.Judge;

public class NewGrid {
	public int grid;
	public int taken;
	/**
	 * default constructor makes empty grid
	 */
	NewGrid(){
		this.grid=0;
		this.taken=0;
	}
	/**
	 * copy the object
	 * @param g
	 */
	NewGrid(NewGrid g){
		this.grid=g.getGrid();
		this.taken=g.getTaken();
	}
	public int getGrid() {
		return grid;
	}
	public int getTaken() {
		return taken;
	}
	
	
}
