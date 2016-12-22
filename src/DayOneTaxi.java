import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dwinslow on 12/21/2016.
 * Only 20 days behind!
 */
public class DayOneTaxi {
    public static final String PATH = "input_day_1.txt";

    public static void main(String[] args) {
        List<String> directions = ParseCsvTaxiDirections(PATH);
        TaxiPosition santa = FollowDrivingDirections(directions);
        System.out.println("Last place we visited is \n" + santa);
    }

    public static TaxiPosition FollowDrivingDirections(List<String> directions) {
        TaxiPosition santa = new TaxiPosition();

        for (String direction : directions) {
            char directionCode = direction.charAt(0);
            int distance = Integer.parseInt(direction.substring(1));

            if (directionCode == 'R')
                santa.TurnRight();
            else if (directionCode == 'L')
                santa.TurnLeft();
            else
                throw new IllegalStateException("We can't read the directions");
            santa.DriveTaxi(distance);

        }
        return santa;
    }

    public static List<String> ParseCsvTaxiDirections(String filePath) {
        List<String> result;
        Path path = Paths.get(filePath);

        try {
            result = Files.lines(path)
                          .flatMap((String str) -> Arrays.stream(str.split(", ")))
                          .collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return result;
    }


    /**
     * Taxi coordinate orientation
     */
    public enum TaxiOrientation {
        NORTH, EAST, SOUTH, WEST;

        public TaxiOrientation AfterLeftTurn() {
            switch (this) {
                case NORTH:
                    return WEST;
                case EAST:
                    return NORTH;
                case SOUTH:
                    return EAST;
                case WEST:
                    return SOUTH;
                default:
                    return this;
            }
        }

        public TaxiOrientation AfterRightTurn() {
            switch (this) {
                case NORTH:
                    return EAST;
                case EAST:
                    return SOUTH;
                case SOUTH:
                    return WEST;
                case WEST:
                    return NORTH;
                default:
                    return this;
            }
        }
    }


    /**
     * Taxi Coordinate Position
     */
    public static class TaxiPosition {
        private int x; //-west to +east
        private int y; //-south to +north
        private TaxiOrientation orientation;

        TaxiPosition() {
            x = 0;
            y = 0;
            orientation = TaxiOrientation.NORTH;
        }

        void DriveTaxi(int distance) {
            switch (orientation) {
                case NORTH:
                    y += distance;
                    break;
                case EAST:
                    x += distance;
                    break;
                case SOUTH:
                    y -= distance;
                    break;
                case WEST:
                    x -= distance;
                    break;
            }
        }

        int TaxiDistanceFromOrigin() {
            return Math.abs(x) + Math.abs(y);
        }

        void TurnLeft() {
            orientation = orientation.AfterLeftTurn();
        }

        void TurnRight() {
            orientation = orientation.AfterRightTurn();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if( !(obj instanceof TaxiPosition) )
                return false;
            final TaxiPosition pos = (TaxiPosition)obj;
            if(this.x != pos.x) return false;
            if(this.y != pos.y) return false;
            //if(this.orientation != pos.orientation) return false;

            return true;
        }

        @Override
        public String toString() {
            return "Santa is at the corner of (" + x + ", " + y + ") and is " +
                    TaxiDistanceFromOrigin() + " blocks from headquarters\n";
        }

        public TaxiOrientation getOrientation() {
            return orientation;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}


