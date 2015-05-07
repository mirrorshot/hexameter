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
     * Returns an array containing the {@link HexagonPoint}s of this {@link Hexagon}.
     *
     * @return points array
     */
    HexagonPoint[] getHexagonPoints();

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
    * Sets the {@link Hexagon} as visited for recursive explorations.
    */
   void visit();
   
   /**
    * Sets the {@link Hexagon} as not visited, after a recursive exploration.
    */
   void clearVisit();
   
   /**
    * Looks if the {@link Hexagon} has been visited in the exploration.
    * 
    * @return true if the hexagon has been visited
    */
   boolean isVisited();
    
   /**
    * Sets the {@link Hexagon} to be an obstacle.
    */
   void fromNowObstacle();
   
   /**
    * Sets the {@link Hexagon} to not be an obstacle.
    */
   void noMoreObstacle();
    
    /**
     * Looks if the {@link Hexagon} is an obstacle.
     * 
    * @return true if the hexagon is set to be a obstacle in the map
    */
   boolean isObstacle();

}
