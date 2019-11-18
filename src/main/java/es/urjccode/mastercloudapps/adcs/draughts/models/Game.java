package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Game {

	private Board board;
	private Turn turn;

	public Game() {
		this.turn = new Turn();
		this.board = new Board();
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.board.getDimension(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				Pawn pawn = this.getInitialPiece(coordinate);
				if (pawn != null) {
					this.board.put(coordinate, pawn);
				}
			}
		}
	}

	public Game (Board board) {
		this.board = board;
		this.turn = new Turn();
	}

	private Pawn getInitialPiece(Coordinate coordinate) {
		assert coordinate != null;
		if (coordinate.isBlack()) {
			final int row = coordinate.getRow();
			Color color = null;
			if (row <= 2) {
				color = Color.BLACK;
			} else if (row >= 5) {
				color = Color.WHITE;
			}
			if (color != null) {
				return new Pawn(color);
			}
		}
		return null;
	}

	public void move(Coordinate origin, Coordinate target) {
		assert this.isCorrect(origin, target) == null;
		Color originalColor = this.getColor(origin);
		if (origin.diagonalDistance(target) == 2) {
			this.board.remove(origin.betweenDiagonal(target));
		}
		this.board.move(origin, target);
		if (this.board.getPiece(target).isLimit(target)){
			this.board.remove(target);
			this.board.put(target, new Draught(originalColor));
		}
		this.turn.change();
	}

	public Error isCorrect(Coordinate origin, Coordinate target){
		assert origin != null;
		assert target != null;
		if (board.isEmpty(origin)) {
			return Error.EMPTY_ORIGIN;
		}
		if (this.turn.getColor() != this.board.getColor(origin)) {
			return Error.OPPOSITE_PIECE;
		}
		return this.board.getPiece(origin).isCorrect(origin, target, board);
	}

	public Color getColor(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getColor(coordinate);
	}

	public Color getColor() {
		return this.turn.getColor();
	}

	public boolean isBlocked() {
		//return checkMovementsPiece();
		return (movementAllow() && !checkMovementsPiece());
	}



	public int getDimension() {
		return this.board.getDimension();
	}

	public Piece getPiece(Coordinate coordinate) {
		assert coordinate != null;
		return this.board.getPiece(coordinate);
	}

	@Override
	public String toString() {
		return this.board + "\n" + this.turn;
	}

	private boolean checkMovementsPiece(){
		return this.board.getPieces(this.turn.getColor()).isEmpty();
	}

	private boolean isAllow(Coordinate origin){
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.getDimension(); j++) {
				if (this.isCorrect(origin,new Coordinate(i,j))==null)
					return true;
			}
		}
		return false;
	}

	private boolean movementAllow(){
		for (int i = 0; i < this.board.getDimension(); i++) {
			for (int j = 0; j < this.getDimension(); j++) {
				if (this.getPiece(new Coordinate(i, j)) != null) {
					return this.isAllow(new Coordinate(i, j));
				}
			}
		}
        return false;
	}

	

}