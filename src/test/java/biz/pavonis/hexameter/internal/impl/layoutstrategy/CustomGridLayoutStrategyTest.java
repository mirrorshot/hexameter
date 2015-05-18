package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.internal.impl.HexagonalGridBuilderImpl;

public class CustomGridLayoutStrategyTest {

    private static final Double RADIUS = 30D;
    private static final int GRID_WIDTH = 0;
    private static final int GRID_HEIGHT = 0;
    private static final HexagonOrientation ORIENTATION = HexagonOrientation.POINTY_TOP;

    private CustomGridLayoutStrategy target;
    private HexagonalGridBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new HexagonalGridBuilderImpl();
        builder.setGridHeight(GRID_HEIGHT).setGridWidth(GRID_WIDTH).setRadius(RADIUS).setOrientation(ORIENTATION);
        target = new CustomGridLayoutStrategy();
    }

    @Test
    public void testCreateHexagons() {
        Map<String, Hexagon> hexagons = target.createHexagons(builder);
        assertTrue(hexagons.isEmpty());
    }

    @Test
    public void testCheckParameters() {
        target.checkParameters(0, 0);
    }

}
