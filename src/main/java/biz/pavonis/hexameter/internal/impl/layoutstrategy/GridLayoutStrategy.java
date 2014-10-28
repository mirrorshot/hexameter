package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import java.util.Map;

/**
 * Represents the method of creating a {@link HexagonalGrid} corresponding to a given shape.
 */
public interface GridLayoutStrategy {

    /**
     * Creates the {@link Hexagon} objects which fit in the shape
     * of the requested layout.
     *
     * @param builder contains the data needed
     * @return created hexagons.
     */
    Map<String, Hexagon> createHexagons(HexagonalGridBuilder builder);

    /**
     * Checks whether the supplied parameters are valid for the given strategy.
     * <i>For example a hexagonal grid layout only works if the width equals to the height</i>
     *
     * @param gridHeight
     * @param gridWidth
     * @return valid?
     */
    boolean checkParameters(int gridHeight, int gridWidth);

}
