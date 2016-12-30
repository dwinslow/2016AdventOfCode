import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dwinslow on 12/21/2016.
 */
class TaxiPositionTest {
    @Test
    void driveTaxi() {
        Map<DayOneTaxi.TaxiPosition, Integer> taxiTrail = new HashMap<>(500);
        //check setup
        DayOneTaxi.TaxiPosition pos = new DayOneTaxi.TaxiPosition(taxiTrail);
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
        pos = new DayOneTaxi.TaxiPosition(taxiTrail);
        pos.TurnRight();
        pos.DriveTaxi(2);
        pos.TurnRight();
        pos.DriveTaxi(2);
        pos.TurnRight();
        pos.DriveTaxi(2);
        assertEquals(2, pos.TaxiDistanceFromOrigin());


        pos = new DayOneTaxi.TaxiPosition(taxiTrail);
        pos.TurnRight();
        pos.DriveTaxi(5);
        pos.TurnLeft();
        pos.DriveTaxi(5);
        pos.TurnRight();
        pos.DriveTaxi(5);
        pos.TurnRight();
        pos.DriveTaxi(3);
        assertEquals(12, pos.TaxiDistanceFromOrigin());

        assertEquals("The corner of (10, 2) and 12 blocks from drop point\n", pos.toString());
    }

  }