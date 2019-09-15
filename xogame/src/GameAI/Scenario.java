package GameAI;

import java.util.HashMap;
import grid.Grid;

public class Scenario extends Grid {
	private Scenario[] children;
	public int[] free;
	public static HashMap<Grid,Scenario> scs=new HashMap<Grid,Scenario>();
	private int minmax=Integer.MAX_VALUE;
	private int stratigic=Integer.MAX_VALUE;
	
	Scenario(Grid bord) {
		super(bord);
		if (bord.getSpaces() < 9 && bord.checkWin() == 0) {
			this.children = new Scenario[9 - bord.getSpaces()];
			free = getFree();
			for (int i = 0; i < 9 - bord.getSpaces(); i++) {
				bord.setPos(free[i], bord.getTurn());
				if(scs.containsKey(bord)){
					children[i]=scs.get(bord);
				}else{
					scs.put(bord ,new Scenario(this,free[i]));
					this.children[i] = scs.get(bord);
				}
				bord.freeSpace(free[i]);
			}
		} else {
			this.children = null;
		}
		
	}
	
	Scenario(Scenario parent, int move){
		super(parent);
		this.setPos(move, parent.getTurn());
		if (getSpaces() < 9 && checkWin() == 0) {
			this.children = new Scenario[9 - getSpaces()];
			free = getFree();
			Scenario temp;
			for (int i = 0; i < 9 - getSpaces(); i++) {
				this.setPos(free[i], this.getTurn());
				if(scs.containsKey(this)){
					children[i]=scs.get(this);
					this.freeSpace(free[i]);
				}else{
					this.freeSpace(free[i]);
					temp=new Scenario(this,free[i]);
					scs.put((Grid)temp,temp);
					this.children[i] = scs.get((Grid)temp);
				}
				
			}
		} else {
			this.children = null;
		}
	}

	/**
	 * This function returns the last position that was filled leading up to
	 * this scenario
	 * 
	 * @return position as int
	 */
	public void getLastMoveLeadingToScenario() {
		throw new IllegalStateException(" dont call this");
		//return getLastMove();
	}

	/**
	 * 
	 * @return array of Scenarios that are the children of this
	 */
	public Scenario[] getChildren() {
		return children;
	}

	/**
	 * @param turn
	 *            to be evaluated with respect to as int 1 for x || -1 for o
	 * @return the strategic importance of the scenario as a numerical value the
	 *         highest being most strategic
	 */

	protected int EvaluateStrategicValue(int turn) {
		if (children == null) {
			return (checkWin() * turn);
		}
		if(stratigic!=Integer.MAX_VALUE){
			return stratigic;
		}
		int sum = 0;
		for (Scenario child : children) {
			sum += child.EvaluateStrategicValue(turn);

		}
		stratigic=sum;
		return sum;
	}

	/**
	 * Uses MinMax algorithm to get value of this scenario
	 * 
	 * @param turn
	 *            to be evaluated with respect to as int 1 for x || -1 for o
	 * @return the value -1||0||1
	 */
	protected int MinMax(int turn) {
		if (children == null) {
			return (checkWin() * turn);
		}
		if(minmax!=Integer.MAX_VALUE){
			return minmax;
		}
		int min = 1000, max = -1000, temp;
		for (Scenario child : children) {
			temp = child.MinMax(turn);
			max = Math.max(max, temp);
			min = Math.min(temp, min);

		}
		int minmax=getTurnInt() != turn ? min : max;
		return minmax;
	}
}
