package GameAI;

import java.util.ArrayList;
import java.util.List;

import grid.Grid;

public class TreeOfScenarios {
	
	private Scenario originRoot;
	private Scenario currentRoot;
	TreeOfScenarios(){
		originRoot=new Scenario(new Grid());
		//System.out.println(originRoot.scs.size());
		currentRoot=originRoot;
	}
	
	private void setCurrentRoot(Scenario s){
		if(s==null) throw new IllegalStateException("null set as current root");
		currentRoot=s;
	}
	/**@param turn to be evaluated wrt
	 * @return returns best move 
	 */
	public List<Scenario> getListOfBestMovesByMinmax(int turn){
		if(currentRoot==null){
			throw new IllegalStateException("null set as current root");
		}
		ArrayList<Scenario> list1= new ArrayList<Scenario>();
		ArrayList<Scenario> list0= new ArrayList<Scenario>();
		ArrayList<Scenario> listminus1= new ArrayList<Scenario>();
		int eval;
		for(int i=0; i<currentRoot.getFree().length;i++){
			eval=currentRoot.getChildren()[i].MinMax(turn);
			if(eval==1){
				list1.add(currentRoot.getChildren()[i]);
			}else if(eval==0){
				list0.add(currentRoot.getChildren()[i]);
			}else{
				listminus1.add(currentRoot.getChildren()[i]);
			}
		}
		if(list1.size()!=0){
			return list1;
		}
		if(list0.size()!=0){
			return list0;
		}
		return listminus1;
	}
	/**
	 * Don't forget !!!!!
	 * @param moves
	 * @return
	 */
	public Scenario bestMoveByStrategicValue(List<Scenario> moves, int turn) {
		int max=Integer.MIN_VALUE;
		Scenario node=null;
		for(int i=0;i<moves.size();i++){
			Scenario s=moves.get(i);
			int temp=s.EvaluateStrategicValue(turn*-1);
			if(temp>max){
				max=temp;
				node=s;
			}
		}
		return node;
	}
	/**
	 * updates the current root after a player has played to the 
	 * scenario representing current state of board
	 * note: this can only update if the new root is child of the old root
	 * @param play
	 */
	public void updateRoot(int play) {
		for(Scenario child:currentRoot.getChildren()){
			if(!child.checkAvb(play)){
				setCurrentRoot(child);
				return;
			}
		}
		throw new IllegalStateException("tree couldn't be updated for move: "+play);
	}
	/**
	 * 	resets root to original
	 */
	public void reset(){
		currentRoot=originRoot;
	}
}
