package biz.pavonis.hexameter.internal.impl;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridCalculator;
import java.util.HashSet;
import java.util.Set;

public final class HexagonalGridCalculatorImpl implements HexagonalGridCalculator {

    private final HexagonalGrid hexagonalGrid;

    public HexagonalGridCalculatorImpl(HexagonalGrid hexagonalGrid) {
        this.hexagonalGrid = hexagonalGrid;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateDistanceBetween(biz.pavonis.hexameter.api.Hexagon, biz.pavonis.hexameter.api.Hexagon)
    */
   public int calculateDistanceBetween(Hexagon hex0, Hexagon hex1) {
        int absX = abs(hex0.getGridX() - hex1.getGridX());
        int absY = abs(hex0.getGridY() - hex1.getGridY());
        int absZ = abs(hex0.getGridZ() - hex1.getGridZ());
        return max(max(absX, absY), absZ);
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateMovementRangeFrom(biz.pavonis.hexameter.api.Hexagon, int)
    */
   public Set<Hexagon> calculateMovementRangeFrom(Hexagon hexagon, int distance) {
        Set<Hexagon> ret = new HashSet<Hexagon>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x
                + distance); y++) {
                int z = -x - y;
                ret.add(hexagonalGrid.getByGridCoordinate(hexagon.getGridX()
                    + x, hexagon.getGridZ() + z));
            }
        }
        return ret;
    }
}
