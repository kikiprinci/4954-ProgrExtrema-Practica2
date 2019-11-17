package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GameBuilder {

    private List<String> lstRows;

    public GameBuilder() {
        this.lstRows = new ArrayList<>();
    }

    GameBuilder row(String row) {
        this.lstRows.add(row);
        return this;
    }

    Game build() {
        Board board = new Board();
        Map<Character, Piece> typeOfPieces = new HashMap<Character, Piece>();
        typeOfPieces.put('b', new Pawn(Color.WHITE));
        typeOfPieces.put('B', new Draught(Color.WHITE));
        typeOfPieces.put('n', new Pawn(Color.BLACK));
        typeOfPieces.put('N', new Draught(Color.BLACK));

        for (int i = 0; i < this.lstRows.size(); i++) {
            for (int j = 0; j < this.lstRows.size(); j++) {
                char character = this.lstRows.get(i).charAt(j);
                if (character != ' ') {
                    Piece piece = typeOfPieces.get(character);
                    board.put(new Coordinate(i, j), piece);
                }
            }
        }
        System.out.println(board);
        return new Game(board);
    }
}