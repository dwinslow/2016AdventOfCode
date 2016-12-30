import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
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
        System.out.println("Last place we drove to is \n" + santa);
        System.out.println("Santa's workshop should be at \n" + santa.getFirstVisitedTwice());

    }

    public static TaxiPosition FollowDrivingDirections(List<String> directions) {
        Map<TaxiPosition, Integer> taxiTrail = new HashMap<>(500);
        TaxiPosition santa = new TaxiPosition(taxiTrail);

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
        private TaxiOrientation orientation; //enumeration
        private Map<TaxiPosition, Integer> taxiTrail; //Number of times each position has been visited
        private TaxiPosition firstVisitedTwice = null; //meaningless until
        private boolean dejaVu = false;

        TaxiPosition(Map<TaxiPosition, Integer> taxiTrail) {
            this.taxiTrail = taxiTrail;
            this.x = 0;
            this.y = 0;
            orientation = TaxiOrientation.NORTH;
        }

        TaxiPosition(int x, int y, Map<TaxiPosition, Integer> taxiTrail) {
            this.taxiTrail = taxiTrail;
            this.x = x;
            this.y = y;
            orientation = TaxiOrientation.NORTH;
        }

        private void BeenHereBeforeCheck() {
            if(dejaVu)
                return;
            Integer visited = (Integer)taxiTrail.get(this);
            if(visited > 1) {
                dejaVu = true;
                firstVisitedTwice = new TaxiPosition(x, y, taxiTrail);
            }
        }

        void DriveTaxi(int distance) {
            switch (orientation) {
                case NORTH:
                    for(int i=1; i<=distance; i++) {
                        y++;
                        MarkPositionOnTrail(x, y);
                        BeenHereBeforeCheck();
                    }
                    break;
                case EAST:
                    for(int i=1; i<=distance; i++) {
                        x++;
                        MarkPositionOnTrail(x, y);
                        BeenHereBeforeCheck();
                    }
                    break;
                case SOUTH:
                    for(int i=1; i<=distance; i++) {
                        y--;
                        MarkPositionOnTrail(x, y);
                        BeenHereBeforeCheck();
                    }
                    break;
                case WEST:
                    for(int i=1; i<=distance; i++) {
                        x--;
                        MarkPositionOnTrail(x, y);
                        BeenHereBeforeCheck();
                    }
                    break;
            }
        }

        private void MarkPositionOnTrail(int x, int y) {
            TaxiPosition key = new TaxiPosition(x, y, taxiTrail);
            Integer defaultTimesVisited = 1;
            taxiTrail.merge(key, defaultTimesVisited, (u, v) -> u + v );
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


        /***
         * Equality is based only on position, not orientation
         * @param  obj A potential matching TaxiPosition object.
         * @return true if x and y positions match
         */
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

            return true;
        }


        @Override
        public int hashCode() {
            return x+y;
        }

        @Override
        public String toString() {
            return "The corner of (" + x + ", " + y + ") and " +
                    TaxiDistanceFromOrigin() + " blocks from drop point\n";
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

        public TaxiPosition getFirstVisitedTwice() {
            return firstVisitedTwice;
        }
    }

}


