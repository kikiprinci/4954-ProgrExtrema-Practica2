package es.urjccode.mastercloudapps.adcs.draughts.models;

public class Piece {

	private Color color;
	private static final int MAX_DISTANCE = 2;
	private static final int WHITE_LIMIT = 0;
	private static final int BLACK_LIMIT = 7;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!pieceProvider.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		return null;
	}

	boolean isLimit(Coordinate coordinate){
		return coordinate.getRow()== Piece.WHITE_LIMIT && this.getColor() == Color.WHITE ||
		coordinate.getRow()== Piece.BLACK_LIMIT && this.getColor() == Color.BLACK;
	}

	boolean isAdvanced(Coordinate origin, Coordinate target) {
		assert origin != null;
		assert target != null;
		int difference = origin.getRow() - target.getRow();
		if (color == Color.WHITE) {
			return difference > 0;
		}
		return difference < 0;
	}

	Color getColor() {
		return this.color;
	}

}