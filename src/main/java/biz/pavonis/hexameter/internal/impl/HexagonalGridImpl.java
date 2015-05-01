package biz.pavonis.hexameter.internal.impl;

import static biz.pavonis.hexameter.api.CoordinateConverter.convertOffsetCoordinatesToAxialX;
import static biz.pavonis.hexameter.api.CoordinateConverter.convertOffsetCoordinatesToAxialZ;
import static biz.pavonis.hexameter.api.CoordinateConverter.createKeyFromCoordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.api.HexagonPoint;
import biz.pavonis.hexameter.api.exception.HexagonNotFoundException;
import biz.pavonis.hexameter.internal.SharedHexagonData;
import biz.pavonis.hexameter.internal.impl.layoutstrategy.GridLayoutStrategy;

public final class HexagonalGridImpl implements HexagonalGrid {

    private static final int[][] NEIGHBORS = {{+1, 0}, {+1, -1}, {0, -1}, {-1, 0}, {-1, +1}, {0, +1}};
    private static final int NEIGHBOR_X_INDEX = 0;
    private static final int NEIGHBOR_Z_INDEX = 1;

    private final GridLayoutStrategy gridLayoutStrategy;
    private final SharedHexagonData sharedHexagonData;
    private final Map<String, Hexagon> hexagonStorage;

    /**
     * Instantiate a HexagonalGrid from a {@link HexagonalGridBuilder}
     * 
    * @param builder for the {@link HexagonalGrid}
    */
   public HexagonalGridImpl(HexagonalGridBuilder builder) {
        sharedHexagonData = builder.getSharedHexagonData();
        gridLayoutStrategy = builder.getGridLayoutStrategy();
        hexagonStorage = builder.getStorage();
        hexagonStorage.putAll(gridLayoutStrategy.createHexagons(builder));
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#getHexagons()
    */
   public Map<String, Hexagon> getHexagons() {
        return hexagonStorage;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#getHexagonsByAxialRange(int, int, int, int)
    */
   public Map<String, Hexagon> getHexagonsByAxialRange(int gridXFrom,
        int gridXTo, int gridZFrom, int gridZTo) {
        Map<String, Hexagon> range = new HashMap<String, Hexagon>();
        for (int gridZ = gridZFrom; gridZ <= gridZTo; gridZ++) {
            for (int gridX = gridXFrom; gridX <= gridXTo; gridX++) {
                String key = createKeyFromCoordinate(gridX, gridZ);
                range.put(key, getByGridCoordinate(gridX, gridZ));
            }
        }
        return range;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#getHexagonsByOffsetRange(int, int, int, int)
    */
   public Map<String, Hexagon> getHexagonsByOffsetRange(int gridXFrom,
        int gridXTo, int gridYFrom, int gridYTo) {
        Map<String, Hexagon> range = new HashMap<String, Hexagon>();
        for (int gridY = gridYFrom; gridY <= gridYTo; gridY++) {
            for (int gridX = gridXFrom; gridX <= gridXTo; gridX++) {
                int axialX = convertOffsetCoordinatesToAxialX(gridX, gridY,
                    sharedHexagonData.getOrientation());
                int axialZ = convertOffsetCoordinatesToAxialZ(gridX, gridY,
                    sharedHexagonData.getOrientation());
                String key = createKeyFromCoordinate(axialX, axialZ);
                range.put(key, getByGridCoordinate(axialX, axialZ));
            }
        }
        return range;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#addHexagon(int, int)
    */
   public Hexagon addHexagon(int gridX, int gridZ) {
        Hexagon newHex = new HexagonImpl(sharedHexagonData, gridX, gridZ);
        hexagonStorage.put(createKeyFromCoordinate(gridX, gridZ), newHex);
        return newHex;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#removeHexagon(int, int)
    */
   public Hexagon removeHexagon(int gridX, int gridZ) {
        checkCoordinate(gridX, gridZ);
        return hexagonStorage.remove(createKeyFromCoordinate(gridX, gridZ));
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#containsCoordinate(int, int)
    */
   public boolean containsCoordinate(int gridX, int gridZ) {
        return hexagonStorage
            .containsKey(createKeyFromCoordinate(gridX, gridZ));
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#getByGridCoordinate(int, int)
    */
   public Hexagon getByGridCoordinate(int gridX, int gridZ) {
        checkCoordinate(gridX, gridZ);
        return hexagonStorage.get(createKeyFromCoordinate(gridX, gridZ));
    }

    /**
     * If given coordinate is invalid throws a {@link HexagonNotFoundException}
     * 
    * @param gridX
    * @param gridZ
    */
   private void checkCoordinate(int gridX, int gridZ) {
        if (!containsCoordinate(gridX, gridZ)) {
            throw new HexagonNotFoundException("Coordinates are off the grid: (x=" + gridX + ",z=" + gridZ + ")");
        }
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#getByPixelCoordinate(double, double)
    */
   public Hexagon getByPixelCoordinate(double x, double y) {
        int estimatedGridX = (int) (x / sharedHexagonData.getWidth());
        int estimatedGridZ = (int) (y / sharedHexagonData.getHeight());
        estimatedGridX = convertOffsetCoordinatesToAxialX(estimatedGridX,
            estimatedGridZ, sharedHexagonData.getOrientation());
        estimatedGridZ = convertOffsetCoordinatesToAxialZ(estimatedGridX,
            estimatedGridZ, sharedHexagonData.getOrientation());
        // it is possible that the estimated coordinates are off the grid so we
        // create a virtual hexagon
        Hexagon tempHex = new HexagonImpl(sharedHexagonData, estimatedGridX,
            estimatedGridZ);
        Hexagon trueHex = refineHexagonByPixel(tempHex, x, y);
        if (hexagonsAreAtTheSamePosition(tempHex, trueHex)) {
            return getByGridCoordinate(estimatedGridX, estimatedGridZ);
        } else {
            return trueHex;
        }
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#getNeighborsOf(biz.pavonis.hexameter.api.Hexagon)
    */
   public Set<Hexagon> getNeighborsOf(Hexagon hexagon) {
        Set<Hexagon> neighbors = new HashSet<Hexagon>();
        for (int[] neighbor : NEIGHBORS) {
            Hexagon retHex = null;
            int neighborGridX = hexagon.getGridX() + neighbor[NEIGHBOR_X_INDEX];
            int neighborGridZ = hexagon.getGridZ() + neighbor[NEIGHBOR_Z_INDEX];
            if (containsCoordinate(neighborGridX, neighborGridZ)) {
                retHex = getByGridCoordinate(neighborGridX, neighborGridZ);
                neighbors.add(retHex);
            }
        }
        return neighbors;
    }

    /**
     * Checks if two hexagons are in the same position of the grid
     * 
    * @param hex0 first hexagon to test
    * @param hex1 second hexagon to test
    * @return true if coordinate of the two hexagons are the same
    */
   private boolean hexagonsAreAtTheSamePosition(Hexagon hex0, Hexagon hex1) {
        return hex0.getGridX() == hex1.getGridX()
            && hex0.getGridZ() == hex1.getGridZ();
    }

    /**
     * 
     * 
    * @param hexagon
    * @param x
    * @param y
    * @return
    */
   private Hexagon refineHexagonByPixel(Hexagon hexagon, double x, double y) {
        Hexagon refined = hexagon;
        HexagonPoint clickedPoint = new HexagonPoint(x, y);
        double smallestDistance = HexagonPoint.distance(clickedPoint, new HexagonPoint(refined.getCenterX(), refined.getCenterY()));
        for (Hexagon neighbor : getNeighborsOf(hexagon)) {
            double currentDistance = HexagonPoint.distance(clickedPoint, new HexagonPoint(
                neighbor.getCenterX(), neighbor.getCenterY()));
            if (currentDistance < smallestDistance) {
                refined = neighbor;
                smallestDistance = currentDistance;
            }
        }
        return refined;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGrid#clearSatelliteData()
    */
   public void clearSatelliteData() {
        for (String key : hexagonStorage.keySet()) {
            hexagonStorage.get(key).setSatelliteData(null);
        }
    }

}
