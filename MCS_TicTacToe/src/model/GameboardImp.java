package model;
import java.util.LinkedList;

public class GameboardImp implements Gameboard, Cloneable {
	
	private PlaceValue[][] currentBoard;
	private Integer[] rowStates;
	private LinkedList<Integer>[][] members;
	private boolean xsTurn;
	private boolean osTurn;
	private GameResult result;
	
	private LinkedList<Integer> coordToRows(int x, int y){
		int switchTrick = (x*10)+y;
		LinkedList<Integer> returnRows = new LinkedList<Integer>();
		switch(switchTrick){
			case 0:
				returnRows.add(0);
				returnRows.add(3);
				returnRows.add(6);
				break;
			case 1:
				returnRows.add(0);
				returnRows.add(4);
				break;
			case 2:
				returnRows.add(0);
				returnRows.add(5);
				returnRows.add(7);
				break;
			case 10:
				returnRows.add(1);
				returnRows.add(3);
				break;
			case 11:
				returnRows.add(1);
				returnRows.add(4);
				returnRows.add(6);
				returnRows.add(7);
				break;
			case 12:
				returnRows.add(1);
				returnRows.add(5);
				break;
			case 20:
				returnRows.add(2);
				returnRows.add(3);
				returnRows.add(7);
				break;
			case 21:
				returnRows.add(2);
				returnRows.add(4);
				break;
			case 22:
				returnRows.add(2);
				returnRows.add(3);
				returnRows.add(6);
				break;
		}
		return returnRows;
	}
	
	public GameboardImp() {
		currentBoard = new PlaceValue[3][3];
		members = (LinkedList<Integer>[][]) new LinkedList[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				currentBoard[i][j] = PlaceValue.BLANK;
				members[i][j] = new LinkedList<Integer>();
			}
		}
		rowStates = new Integer[8];
		for(int i = 0; i<8; i++){
			rowStates[i] = 0;
		}
		members[0][0].add(rowStates[0]);
		members[0][0].add(rowStates[3]);
		members[0][0].add(rowStates[6]);
		
		members[1][0].add(rowStates[0]);
		members[1][0].add(rowStates[4]);
		
		members[2][0].add(rowStates[0]);
		members[2][0].add(rowStates[5]);
		members[2][0].add(rowStates[7]);
		
		members[0][1].add(rowStates[1]);
		members[0][1].add(rowStates[3]);
		
		members[1][1].add(rowStates[1]);
		members[1][1].add(rowStates[4]);
		members[1][1].add(rowStates[6]);
		members[1][1].add(rowStates[7]);
		
		members[2][1].add(rowStates[1]);
		members[2][1].add(rowStates[5]);
		
		members[0][2].add(rowStates[2]);
		members[0][2].add(rowStates[3]);
		members[0][2].add(rowStates[7]);
		
		members[1][2].add(rowStates[2]);
		members[1][2].add(rowStates[4]);
		
		members[2][2].add(rowStates[2]);
		members[2][2].add(rowStates[3]);
		members[2][2].add(rowStates[6]);
		xsTurn = true;
		osTurn = false;
		result = GameResult.PENDING;
	}
	
	public Gameboard clone() {
		GameboardImp cloneBoard = new GameboardImp();
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				cloneBoard.forceMove(i, j, currentBoard[i][j]);
			}
		}
		cloneBoard.xsTurn = this.xsTurn;
		cloneBoard.osTurn = this.osTurn;
		return cloneBoard;
	}
	
	private void forceMove(int xPosition, int yPosition, PlaceValue piece){
		currentBoard[xPosition][yPosition] = piece;
	}

	@Override
	public Gameboard getState() {
		return this.clone();
	}
	
	public PlaceValue[][] getBoard(){
		PlaceValue[][] cloneBoard = new PlaceValue[3][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				cloneBoard[i][j] = currentBoard[i][j];
			}
		}
		return currentBoard;
	}

	@Override
	public boolean xsTurn() {
		return xsTurn;
	}

	@Override
	public boolean osTurn() {
		return osTurn;
	}
	
	public GameResult getResult(){
		return this.result;
	}

	@Override
	public boolean requestMove(int xPosition, int yPosition,
			PlaceValue pieceToPlace) {
		if((currentBoard[xPosition][yPosition] == PlaceValue.BLANK)&&
				((pieceToPlace == PlaceValue.X && this.xsTurn())||
				 (pieceToPlace == PlaceValue.O && this.osTurn()))) {
			for(Integer rowNum:coordToRows(xPosition, yPosition)){
				if(pieceToPlace == PlaceValue.X){
					rowStates[rowNum]++;
				}else{
					rowStates[rowNum]--;
				}
			}
			this.forceMove(xPosition, yPosition, pieceToPlace);
			if(this.checkResult() == GameResult.PENDING){
				xsTurn = !xsTurn;
				osTurn = !osTurn;
			}else {
				xsTurn = false;
				osTurn = false;
			}
			return true;
		}
		return false;
	}
	
	private GameResult checkResult(){
		for(Integer sum:rowStates){
			if(sum.equals(3)){
				this.result = GameResult.XWIN;
				return this.result;
			}else if(sum.equals(-3)){
				this.result = GameResult.OWIN;
				return this.result;
			}
		}
		for(PlaceValue[] c:currentBoard){
			for(PlaceValue p:c){
				if(p == PlaceValue.BLANK){
					return this.result;
				}
			}
		}
		this.result = GameResult.CAT;
		return this.result;
	}

}
