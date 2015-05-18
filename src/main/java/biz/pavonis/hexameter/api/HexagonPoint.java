package biz.pavonis.hexameter.api;

/**
 * Represents a point.
 */
public final class HexagonPoint {

    private final Double x;
    private final Double y;

    public HexagonPoint(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    
   /**
    * @return value of x of point
    */
   public Double getX() {
      return x;
   }

   
   /**
    * @return value of y of point
    */
   public Double getY() {
      return y;
   }

   /**
     * Calculates a distance between two points.
     *
     * @param p0
     * @param p1
     * @return distance
     */
    public static final Double distance(HexagonPoint p0, HexagonPoint p1) {
        return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
    }
}
