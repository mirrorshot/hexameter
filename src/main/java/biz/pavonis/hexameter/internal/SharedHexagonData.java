package biz.pavonis.hexameter.internal;

import static java.lang.Math.sqrt;

import java.io.Serializable;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGrid;

/**
 * Immutable class which holds the shared data between the {@link Hexagon}s of a {@link HexagonalGrid}.
 * This is just for optimization of memory usage.
 */
public final class SharedHexagonData implements Serializable{
   
   private static final long serialVersionUID = -5728758529278680222L;
   
   private final HexagonOrientation orientation;
   private final Double radius;
   private final Double height;
   private final Double width;
   
   public SharedHexagonData(HexagonOrientation orientation, double radius) {
      this.orientation = orientation;
      this.radius = radius;
      this.height = HexagonOrientation.FLAT_TOP.equals(orientation) ? calculateHeight(radius) : calculateWidth(radius);
      this.width = HexagonOrientation.FLAT_TOP.equals(orientation) ? calculateWidth(radius) : calculateHeight(radius);
   }
   
   private double calculateHeight(double radius) {
      return sqrt(3) * radius;
   }

   private double calculateWidth(double radius) {
      return radius * 3 / 2;
   }

   public HexagonOrientation getOrientation() {
      return orientation;
   }

   public Double getRadius() {
      return radius;
   }

   public Double getHeight() {
      return height;
   }

   public Double getWidth() {
      return width;
   }

}
