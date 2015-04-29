package biz.pavonis.hexameter.api;

import java.io.Serializable;

/**
 * <p>
 * Represents a Hexagon.
 * </p>
 * <em>Please note</em> that all coordinates are relative to the {@link HexagonalGrid} containing this {@link Hexagon}.
 */
public interface Hexagon extends Serializable {

    /**
     * Returns an array containing the {@link Point}s of this {@link Hexagon}.
     *
     * @return points array
     */
    Point[] getPoints();

    /**
     * Returns this {@link Hexagon}'s <b>x</b> coordinate on the {@link HexagonalGrid}.
     *
     * @return x coordinate on the grid
     */
    int getGridX();

    /**
     * Returns this {@link Hexagon}'s <b>y</b> coordinate on the {@link HexagonalGrid}.
     * The Y coordinate is not present in the axial model but it is in the cube model.
     * This method is just for convenience.
     *
     * @return y coordinate on the grid
     */
    int getGridY();

    /**
     * Returns this {@link Hexagon}'s <b>z</b> coordinate on the {@link HexagonalGrid}.
     *
     * @return z coordinate on the grid
     */
    int getGridZ();

    /**
     * Returns the center <b>x</b> coordinate of this {@link Hexagon}.
     *
     * @return center x
     */
    double getCenterX();

    /**
     * Returns the center <b>y</b> coordinate of this {@link Hexagon}.
     *
     * @return center y
     */
    double getCenterY();

    /**
     * Can be used to add arbitrary satellite data to a {@link Hexagon}.
     *
     * @param data
     */
    <T> void setSatelliteData(T data);

    /**
     * Returns the previously set satellite data from this {@link Hexagon}.
     *
     * @return
     */
    <T> T getSatelliteData();
    
   /**
    * Sets the {@link Hexagon} to be or not an obstacle.
    * 
    * @param obstacle
    */
   void becomeObstacle(boolean obstacle);
    
    /**
    * @return true if the hexagon is set to be a obstacle in the map
    */
   boolean isObstacle();

}
