package grid;

public class Judge {
	private final int[][] pos_win = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 },{ 1, 5, 9 }, { 3, 5, 7 } };// every 3 ints represent one of the 8 possible scenarios for a win
	
	
	Judge(){
		
	}
	/**
	 * Checks for wins
	 * @return who wins
	 */
	public int checkWin(Grid grid) {
		for (int[] arr:pos_win) {
			if (grid.getPosInt(arr[0]) + grid.getPosInt(arr[1])+ grid.getPosInt(arr[2]) == 3) {
				return 1;
			} else if (grid.getPosInt(arr[0]) + grid.getPosInt(arr[1])+ grid.getPosInt(arr[2]) == -3) {
				return -1;
			}
		}
		return 0;
	}
	/**checks for possible wins aka x*x xx* *xx or o equivalent
	 * 
	 */
	public int checkPosWin(Grid grid) {
		int a=0;
		for (int[] arr:pos_win) {
			if (grid.getPosInt(arr[0]) + grid.getPosInt(arr[1])+ grid.getPosInt(arr[2]) == 2) {
				a+=1;
			} else if (grid.getPosInt(arr[0]) + grid.getPosInt(arr[1])+ grid.getPosInt(arr[2]) == -2) {
				a+=-1;
			}
		}
		return a;
	}
}
