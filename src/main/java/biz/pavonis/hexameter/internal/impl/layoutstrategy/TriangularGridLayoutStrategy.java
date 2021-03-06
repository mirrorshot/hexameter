package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import static biz.pavonis.hexameter.api.CoordinateConverter.createKeyFromCoordinate;

import java.util.HashMap;
import java.util.Map;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.internal.impl.HexagonImpl;

/**
 * This strategy is responsible for generating a {@link HexagonalGrid} which has a triangular
 * shape.
 */
public final class TriangularGridLayoutStrategy extends AbstractGridLayoutStrategy {

   private static final long serialVersionUID = 7486123618040276203L;

   @Override
   public Map<String, Hexagon> createHexagons(HexagonalGridBuilder builder) {
      int gridSize = builder.getGridHeight();
      Map<String, Hexagon> hexagons = new HashMap<String, Hexagon>();
      for (int y = 0; y < gridSize; y++) {
         int endX = gridSize - y;
         for (int x = 0; x < endX; x++) {
            Hexagon hexagon = new HexagonImpl(builder.getSharedHexagonData(), x, y);
            hexagons.put(createKeyFromCoordinate(x, y), hexagon);
         }
      }
      addCustomHexagons(builder, hexagons);
      return hexagons;
   }

   @Override
   public boolean checkParameters(int gridHeight, int gridWidth) {
      boolean superResult = super.checkParameters(gridHeight, gridWidth);
      boolean result = gridHeight == gridWidth;
      return superResult && result;
   }
}
