import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dwinslow on 12/21/2016.
 */
class TaxiOrientationTest {
    @org.junit.jupiter.api.Test
    void turnLeft() {
        assertEquals(DayOneTaxi.TaxiOrientation.SOUTH, DayOneTaxi.TaxiOrientation.WEST.TurnLeft());
        assertEquals(DayOneTaxi.TaxiOrientation.WEST, DayOneTaxi.TaxiOrientation.NORTH.TurnLeft());
        assertEquals(DayOneTaxi.TaxiOrientation.EAST, DayOneTaxi.TaxiOrientation.SOUTH.TurnLeft());
        assertEquals(DayOneTaxi.TaxiOrientation.NORTH, DayOneTaxi.TaxiOrientation.EAST.TurnLeft());
    }

    @org.junit.jupiter.api.Test
    void turnRight() {
        assertEquals(DayOneTaxi.TaxiOrientation.NORTH, DayOneTaxi.TaxiOrientation.WEST.TurnRight());
        assertEquals(DayOneTaxi.TaxiOrientation.EAST, DayOneTaxi.TaxiOrientation.NORTH.TurnRight());
        assertEquals(DayOneTaxi.TaxiOrientation.WEST, DayOneTaxi.TaxiOrientation.SOUTH.TurnRight());
        assertEquals(DayOneTaxi.TaxiOrientation.SOUTH, DayOneTaxi.TaxiOrientation.EAST.TurnRight());
    }

}