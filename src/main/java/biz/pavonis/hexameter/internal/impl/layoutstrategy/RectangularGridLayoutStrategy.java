package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import java.util.HashMap;
import java.util.Map;

import biz.pavonis.hexameter.api.CoordinateConverter;
import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.internal.impl.HexagonImpl;

/**
 * This strategy is responsible for generating a {@link HexagonalGrid} which has a rectangular
 * shape.
 */
public final class RectangularGridLayoutStrategy extends AbstractGridLayoutStrategy {

   private static final long serialVersionUID = -8634703801052395250L;

   @Override
   public Map<String, Hexagon> createHexagons(HexagonalGridBuilder builder) {
      Map<String, Hexagon> hexagons = new HashMap<String, Hexagon>();
      for (int y = 0; y < builder.getGridHeight(); y++) {
         for (int x = 0; x < builder.getGridWidth(); x++) {
            int gridX = CoordinateConverter.convertOffsetCoordinatesToAxialX(x, y, builder.getOrientation());
            int gridZ = CoordinateConverter.convertOffsetCoordinatesToAxialZ(x, y, builder.getOrientation());
            Hexagon hexagon = new HexagonImpl(builder.getSharedHexagonData(), gridX, gridZ);
            hexagons.put(CoordinateConverter.createKeyFromCoordinate(gridX, gridZ), hexagon);
         }
      }
      addCustomHexagons(builder, hexagons);
      return hexagons;
   }

}
