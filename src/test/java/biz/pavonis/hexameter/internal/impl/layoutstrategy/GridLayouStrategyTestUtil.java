package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.internal.impl.HexagonalGridBuilderImpl;

public class GridLayouStrategyTestUtil {

    private static final int RADIUS = 30;
    private static final int GRID_WIDTH = 3;
    private static final int GRID_HEIGHT = 3;
    private static final HexagonOrientation ORIENTATION = HexagonOrientation.POINTY_TOP;

    public static HexagonalGridBuilder fetchDefaultBuilder() {
        return new HexagonalGridBuilderImpl().setGridHeight(GRID_HEIGHT).setGridWidth(GRID_WIDTH).setRadius(RADIUS).setOrientation(ORIENTATION);
    }
}
