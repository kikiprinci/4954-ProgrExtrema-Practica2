package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithDraughtsTest {

    @Mock
    Turn turn;

    @Mock
    Piece piece;
    
    @Mock
    Board board;

    @InjectMocks
    Game game;

    Game gameBuilder;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Piece(Color.WHITE));
        
        when(board.getPiece(target)).thenReturn(new Piece(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugtsWithBuilder(){
        Coordinate origin = new Coordinate(1,0);
        Coordinate target = new Coordinate(0,1);
        
        this.gameBuilder = new GameBuilder()
            .row("        ")
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .build();
        
        this.gameBuilder.move(origin, target);
        assertNull(this.gameBuilder.getPiece(origin));
        assertNotNull(this.gameBuilder.getPiece(target));
        assertEquals(Color.WHITE, this.gameBuilder.getColor(target));
        assertEquals(this.gameBuilder.getPiece(target).getClass(), Draught.class);
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugtsWithBuilder(){
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);

        this.gameBuilder = new GameBuilder()
        .row("        ")
        .row("  n     ")
        .row(" b      ")
        .row("        ")
        .row("        ")
        .row("        ")
        .row("        ")
        .row("        ")
        .build();

        this.gameBuilder.move(origin, target);
        assertNull(this.gameBuilder.getPiece(origin));
        assertNull(this.gameBuilder.getPiece(new Coordinate(1, 2)));
        assertNotNull(this.gameBuilder.getPiece(target));
        assertEquals(Color.WHITE, this.gameBuilder.getColor(target));
        assertEquals(this.gameBuilder.getPiece(target).getClass(), Draught.class);
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugts(){
        Coordinate origin = new Coordinate(2,1);
        Coordinate target = new Coordinate(0,3);
        when (turn.getColor()).thenReturn(Color.WHITE);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.WHITE);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Piece(Color.WHITE));
        when(board.getPiece(target)).thenReturn(new Piece(Color.WHITE));
        game.move(origin, target);
        verify(board).remove(origin.betweenDiagonal(target));
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts(){
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        when(turn.getColor()).thenReturn(Color.BLACK);
        when(board.isEmpty(origin)).thenReturn(false);
        when(board.getColor(origin)).thenReturn(Color.BLACK);
        when(board.getPiece(origin)).thenReturn(piece);
        when(piece.isCorrect(origin, target, board)).thenReturn(null);
        when(board.remove(origin)).thenReturn(new Piece(Color.BLACK));
        when(board.getPiece(target)).thenReturn(new Piece(Color.BLACK));
        game.move(new Coordinate(3, 0), new Coordinate(2, 1));
        game.move(origin, target);
        verify(board).remove(target);
        verify(board).put(any(Coordinate.class), any(Draught.class));
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugtsWithBuilder(){
        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        
        this.gameBuilder = new GameBuilder()
            .row("        ")
            .row("        ")
            .row("        ")
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("   n    ")
            .row("        ")
            .build();
        this.gameBuilder.move(new Coordinate(3, 0), new Coordinate(2, 1));
        this.gameBuilder.move(origin, target);
        assertNull(this.gameBuilder.getPiece(origin));
        assertNotNull(this.gameBuilder.getPiece(target));
        assertEquals(Color.BLACK, this.gameBuilder.getColor(target));
        assertEquals(this.gameBuilder.getPiece(target).getClass(), Draught.class);
    }
}