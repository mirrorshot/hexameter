package biz.pavonis.hexameter.api;

/**
 * Represents a point.
 */
public final class HexagonPoint {

    private final double x;
    private final double y;

    public HexagonPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    
   /**
    * @return value of x of point
    */
   public double getX() {
      return x;
   }

   
   /**
    * @return value of y of point
    */
   public double getY() {
      return y;
   }

   /**
     * Calculates a distance between two points.
     *
     * @param p0
     * @param p1
     * @return distance
     */
    public static final double distance(HexagonPoint p0, HexagonPoint p1) {
        return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
    }
}
