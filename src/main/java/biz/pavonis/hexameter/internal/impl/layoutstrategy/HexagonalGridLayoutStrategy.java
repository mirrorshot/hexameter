package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import static biz.pavonis.hexameter.api.CoordinateConverter.createKeyFromCoordinate;
import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.max;
import static java.lang.Math.round;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.internal.impl.HexagonImpl;
import java.util.HashMap;
import java.util.Map;

/**
 * This strategy is responsible for generating a {@link HexagonalGrid} which has a hexagonal
 * shape.
 */
public final class HexagonalGridLayoutStrategy extends AbstractGridLayoutStrategy {
   
   public static final long serialVersionUID = 1L;

    public Map<String, Hexagon> createHexagons(HexagonalGridBuilder builder) {
        double gridSize = builder.getGridHeight();
        Map<String, Hexagon> hexagons = new HashMap<String, Hexagon>();
        int startX = (int) (HexagonOrientation.FLAT_TOP.equals(builder.getOrientation()) ? floor(gridSize / 2d) : round(gridSize / 4d));
        int hexRadius = (int) floor(gridSize / 2d);
        int minX = startX - hexRadius;
        for (int y = 0; y < gridSize; y++) {
            int distanceFromMid = Math.abs(hexRadius - y);
            for (int x = max(startX, minX); x <= max(startX, minX) + hexRadius + hexRadius - distanceFromMid; x++) {
                int gridX = x;
                int gridZ = HexagonOrientation.FLAT_TOP.equals(builder.getOrientation()) ? y - (int) floor(gridSize / 4d) : y;
                Hexagon hexagon = new HexagonImpl(builder.getSharedHexagonData(), gridX, gridZ);
                hexagons.put(createKeyFromCoordinate(gridX, gridZ), hexagon);
            }
            startX--;
        }
        addCustomHexagons(builder, hexagons);
        return hexagons;
    }

    public boolean checkParameters(int gridHeight, int gridWidth) {
        boolean superResult = super.checkParameters(gridHeight, gridWidth);
        boolean result = gridHeight == gridWidth && abs(gridHeight % 2) == 1;
        return result && superResult;
    }

}
