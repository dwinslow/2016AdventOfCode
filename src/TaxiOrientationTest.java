import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dwinslow on 12/21/2016.
 */
class TaxiOrientationTest {
    @org.junit.jupiter.api.Test
    void turnLeft() {
        assertEquals(DayOneTaxi.TaxiOrientation.SOUTH, DayOneTaxi.TaxiOrientation.WEST.AfterLeftTurn());
        assertEquals(DayOneTaxi.TaxiOrientation.WEST, DayOneTaxi.TaxiOrientation.NORTH.AfterLeftTurn());
        assertEquals(DayOneTaxi.TaxiOrientation.EAST, DayOneTaxi.TaxiOrientation.SOUTH.AfterLeftTurn());
        assertEquals(DayOneTaxi.TaxiOrientation.NORTH, DayOneTaxi.TaxiOrientation.EAST.AfterLeftTurn());
    }

    @org.junit.jupiter.api.Test
    void turnRight() {
        assertEquals(DayOneTaxi.TaxiOrientation.NORTH, DayOneTaxi.TaxiOrientation.WEST.AfterRightTurn());
        assertEquals(DayOneTaxi.TaxiOrientation.EAST, DayOneTaxi.TaxiOrientation.NORTH.AfterRightTurn());
        assertEquals(DayOneTaxi.TaxiOrientation.WEST, DayOneTaxi.TaxiOrientation.SOUTH.AfterRightTurn());
        assertEquals(DayOneTaxi.TaxiOrientation.SOUTH, DayOneTaxi.TaxiOrientation.EAST.AfterRightTurn());
    }

}