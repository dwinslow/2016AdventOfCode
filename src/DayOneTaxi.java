/**
 * Created by dwinslow on 12/21/2016.
 * Only 20 days behind!
 */
public class DayOneTaxi {
      public static void main(String[] args) {
          TaxiPosition position = new TaxiPosition();


    }

    public enum TaxiOrientation {
        NORTH, EAST, SOUTH, WEST;

        public TaxiOrientation TurnLeft() {
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

        public TaxiOrientation TurnRight() {
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

    public static class TaxiPosition{
        int x; //-east to +west
        int y; //-south to +north
        TaxiOrientation orientation;

        TaxiPosition() {
            x = 0;
            y =  0;
            orientation = TaxiOrientation.NORTH;
        }

        public int TaxiDistanceFromOrigin() {
            return x+y;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    }

