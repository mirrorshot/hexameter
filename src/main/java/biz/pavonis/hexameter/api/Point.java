package biz.pavonis.hexameter.api;

/**
 * Represents a point.
 */
public final class Point {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    
   public double getX() {
      return x;
   }

   
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
    public final static double distance(Point p0, Point p1) {
        return Math.sqrt((p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y));
    }
}
