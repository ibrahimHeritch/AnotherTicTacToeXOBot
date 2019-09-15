package GameAI;

import java.util.List;

import grid.Grid;

public class GeniusAI {
	private TreeOfScenarios tree;
	private int playingAs;
	/*
	 * Computer ai that plays XO
	 */
	public GeniusAI() {
		
		tree= new TreeOfScenarios();
		
		playingAs=0;
	}
	/**
	 * sets the side the ai is playing as
	 * @param turn
	 */
	public void setPlayAs(int turn){
		playingAs=turn;
	}
	/**
	 * 
	 * @param grid
	 * @return the position the ai has choosen
	 */
	public int play(Grid g) {
		if(playingAs==0) throw new IllegalStateException("playingAs not set for ai");
		//if(playingAs==-1)
		if(g.getLastMove()!=-1)tree.updateRoot(g.getLastMove());
		List<Scenario> plays= tree.getListOfBestMovesByMinmax(playingAs);
		Scenario sc=tree.bestMoveByStrategicValue(plays,playingAs);
		if(sc==null) throw new IllegalStateException("null returned as sc");
		int[] moves=g.getFree();
		for(int move:moves){
			if(!sc.checkAvb(move)){
				tree.updateRoot(move);
				return move;
			}
		}
		return -1;
	}
    public void printValues(Grid g){//for debugging purposes
    	Scenario s;
    	for(int i=1; i<10;i++){
    		if(!g.checkAvb(i)){
    			System.out.print(g.getPosChar(i));
    		}else{
    			s= new Scenario(new Grid(g).setPos(i, g.getTurn()));
    			System.out.print(s.EvaluateStrategicValue(g.getTurnInt())+" ");
    		}
    		if(i%3==0){
    			System.out.println();
    		}
    		
    	}
    }
}	
