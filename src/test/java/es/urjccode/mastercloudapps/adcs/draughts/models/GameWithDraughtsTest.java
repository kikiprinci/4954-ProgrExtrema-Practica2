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

    Game gameBuilder;

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

    
    @Test
    public void testGivenGameWhenWhiteDraughtMoveThenError() {
        Coordinate origin = new Coordinate(0, 7);
        Coordinate target = new Coordinate(0, 6);
        this.gameBuilder = new GameBuilder()
            .row("       B")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .build();
        assertEquals(Error.OPPOSITE_PIECE, this.game.isCorrect(origin, target));
    }

    @Test
    public void testGivenGameWhenBlackDraughtMoveThenError() {
        Coordinate origin = new Coordinate(7, 0);
        Coordinate target = new Coordinate(7, 1);
        this.gameBuilder = new GameBuilder()
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("N        ")
            .build();
        assertEquals(Error.OPPOSITE_PIECE, this.game.isCorrect(origin, target));
    }

    @Test
    public void testGivenGameWhenWhiteDraughtMoveSevenPositionsThenCorrect() {
        Coordinate origin = new Coordinate(0, 7);
        Coordinate target = new Coordinate(7, 0);
        this.gameBuilder = new GameBuilder()
            .row("       B")
            .row("        ")
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
    }

    @Test
    public void testGivenGameWhenBlackDraughtMoveSevenPositionsThenCorrect() {
        Coordinate origin = new Coordinate(7, 0);
        Coordinate target = new Coordinate(0, 7);
        
        this.gameBuilder = new GameBuilder()
            .row("        ")
            .row("        ")
            .row("        ")
            .row("b       ")
            .row("        ")
            .row("        ")
            .row("        ")
            .row("N       ")
            .build();
        this.gameBuilder.move(new Coordinate(3, 0), new Coordinate(2, 1));
        this.gameBuilder.move(origin, target);
        assertNull(this.gameBuilder.getPiece(origin));
        assertNotNull(this.gameBuilder.getPiece(target));
        assertEquals(Color.BLACK, this.gameBuilder.getColor(target));
    }

}