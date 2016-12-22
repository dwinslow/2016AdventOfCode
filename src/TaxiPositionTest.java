import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dwinslow on 12/21/2016.
 */
class TaxiPositionTest {
    @Test
    void driveTaxi() {
        //check setup
        DayOneTaxi.TaxiPosition pos = new DayOneTaxi.TaxiPosition();
        assertEquals(0, pos.getX());
        assertEquals(0, pos.getY());
        assertEquals(DayOneTaxi.TaxiOrientation.NORTH, pos.getOrientation());

        //simple case
        pos.TurnRight();
        pos.DriveTaxi(2);
        pos.TurnLeft();
        pos.DriveTaxi(3);

        assertEquals(5, pos.TaxiDistanceFromOrigin());

        //Go in a Circle
        pos = new DayOneTaxi.TaxiPosition();
        pos.TurnRight();
        pos.DriveTaxi(2);
        pos.TurnRight();
        pos.DriveTaxi(2);
        pos.TurnRight();
        pos.DriveTaxi(2);
        assertEquals(2, pos.TaxiDistanceFromOrigin());


        pos = new DayOneTaxi.TaxiPosition();
        pos.TurnRight();
        pos.DriveTaxi(5);
        pos.TurnLeft();
        pos.DriveTaxi(5);
        pos.TurnRight();
        pos.DriveTaxi(5);
        pos.TurnRight();
        pos.DriveTaxi(3);
        assertEquals(12, pos.TaxiDistanceFromOrigin());

        assertEquals("Santa is at the corner of (10, 2) and is 12 blocks from town\n", pos.toString());
    }

  }