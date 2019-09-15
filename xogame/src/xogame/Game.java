package xogame;

import java.util.Scanner;

import GameAI.GeniusAI;
import grid.Grid;

public class Game {

	public static void main(String[] args) {
		Grid board= new Grid();
        Scanner s= new Scanner(System.in);
        GeniusAI ai= new GeniusAI();
        int pos;
        ai.setPlayAs(1);
        System.out.println("Choose a position between 1-9 to play:");
        while(board.checkWin()==0 && board.getSpaces()!=9){
        	
        	
        	board.setPos(ai.play(board), board.getTurn());
        	board.printGrid();
        	if(board.checkWin()!=0 || board.getSpaces()==9)break;
        	//ai.printValues(board);
        	
        	pos=s.nextInt();
        	board.setPos(/*ai.play(board)*/pos, 'o');
        	board.printGrid();
        	System.out.println(" ");
        	
        }
        board.printGrid();
        if(board.checkWin()==0){
        	System.out.println("no one wins");
        }else{
        	System.out.println((board.checkWin()==1?"X wins":"O wins"));
        }
        s.close();
	}

}
