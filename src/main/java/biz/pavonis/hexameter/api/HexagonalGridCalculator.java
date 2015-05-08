package biz.pavonis.hexameter.api;

import java.util.Set;

import biz.pavonis.hexameter.api.exception.HexagonNotReachableException;

/**
 * Supports common operations on a {@link HexagonalGrid}.
 * Operations supported:
 * <ul>
 * <li>Calculating distance between 2 {@link Hexagon}s.</li>
 * <li>Calsulating movement range from a {@link Hexagon} using an arbitrary distance.</li>
 * </ul>
 * <em>Not implemented yet, but are on the roadmap:</em>
 * <ul>
 * <li>Calculating movement range with obstacles</li>
 * <li>Calculating field of view</li>
 * <li>Pathfinding between two {@link Hexagon}s (using obstacles)</li>
 * </ul>
 */
public interface HexagonalGridCalculator {

    /**
     * Calculates the distance (in hexagons) between two {@link Hexagon} objects on the grid.
     *
     * @param hex0
     * @param hex1
     * @return distance
     */
    int calculateDistanceBetween(Hexagon hex0, Hexagon hex1);

    /**
     * Calculates the distance (in hexagons) between two {@link Hexagon} objects on the grid 
     * considering the presence of obstacles.
     *
     * @param hex0
     * @param hex1
     * @return distance
     */
    int calculateObstacleDistanceBetween(Hexagon hex0, Hexagon hex1)
       throws HexagonNotReachableException;

    /**
     * Returns all {@link Hexagon}s which are within distance (inclusive) from the {@link Hexagon}.
     *
     * @param hexagon {@link Hexagon}
     * @param distance
     * @return {@link Hexagon}s within distance (inclusive)
     */
    Set<Hexagon> calculateMovementRangeFrom(Hexagon hexagon, int distance);

    /**
     * Returns all {@link Hexagon}s which are within distance (inclusive) from the {@link Hexagon}
     * considering presence of obstacles.
     *
     * @param hexagon {@link Hexagon}
     * @param distance
     * @return {@link Hexagon}s within distance (inclusive)
     */
    Set<Hexagon> calculateObstacleMovementRangeFrom(Hexagon hexagon, int distance);

}
