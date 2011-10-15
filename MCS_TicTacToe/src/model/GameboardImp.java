package model;

public class GameboardImp implements Gameboard, Cloneable {
	
	private PlaceValue[][] currentBoard;
	private boolean xsTurn;
	private boolean osTurn;
	private GameResult result;
	
	public GameboardImp() {
		currentBoard = new PlaceValue[3][3];
		for(PlaceValue[] c:currentBoard){
			for(PlaceValue p:c){
				p = PlaceValue.BLANK;
			}
		}
		xsTurn = true;
		osTurn = false;
		result = GameResult.PENDING;
	}
	
	public Gameboard clone() {
		GameboardImp cloneBoard = new GameboardImp();
		for(int i = 0; i < 3; i++){
			for(int j = 0; i < 3; j++){
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
			for(int j = 0; i < 3; j++){
				cloneBoard[i][j] = currentBoard[i][j];
			}
		}
		return cloneBoard;
	}

	@Override
	public boolean xsTurn() {
		return xsTurn;
	}

	@Override
	public boolean osTurn() {
		return osTurn;
	}

	@Override
	public boolean requestMove(int xPosition, int yPosition,
			PlaceValue pieceToPlace) {
		if((currentBoard[xPosition][yPosition] == PlaceValue.BLANK)&&
				((pieceToPlace == PlaceValue.X && this.xsTurn())||
				 (pieceToPlace == PlaceValue.O && this.osTurn()))) {
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
		if((currentBoard[0][0] != PlaceValue.BLANK)&&
				((currentBoard[0][0] == currentBoard[1][0])&&(currentBoard[0][0] == currentBoard[2][0]))||
				((currentBoard[0][0] == currentBoard[0][1])&&(currentBoard[0][0] == currentBoard[0][2]))||
				((currentBoard[0][0] == currentBoard[1][1])&&(currentBoard[0][0] == currentBoard[2][2])))
		{
			if(currentBoard[0][0] == PlaceValue.X){
				this.result = GameResult.XWIN;
			}
			else{
				this.result = GameResult.OWIN;
			}
		}
		else if((currentBoard[1][1] != PlaceValue.BLANK)&&
				((currentBoard[1][1] == currentBoard[1][0])&&(currentBoard[1][1] == currentBoard[1][2]))||
				((currentBoard[1][1] == currentBoard[0][1])&&(currentBoard[1][1] == currentBoard[2][1]))||
				((currentBoard[1][1] == currentBoard[2][0])&&(currentBoard[1][1] == currentBoard[0][2])))
		{
			if(currentBoard[1][1] == PlaceValue.X){
				this.result = GameResult.XWIN;
			}
			else{
				this.result = GameResult.OWIN;
			}
		}
		else if((currentBoard[2][2] != PlaceValue.BLANK)&&
				((currentBoard[2][2] == currentBoard[1][2])&&(currentBoard[2][2] == currentBoard[0][2]))||
				((currentBoard[2][2] == currentBoard[2][1])&&(currentBoard[2][2] == currentBoard[2][0])))
		{
			if(currentBoard[2][2] == PlaceValue.X){
				this.result = GameResult.XWIN;
			}
			else{
				this.result = GameResult.OWIN;
			}
		}
		else{
			for(PlaceValue[] c:currentBoard){
				for(PlaceValue p:c){
					if(p == PlaceValue.BLANK){
						return GameResult.PENDING;
					}
				}
			}
			this.result = GameResult.CAT;
		}
		return this.result;
	}

}
