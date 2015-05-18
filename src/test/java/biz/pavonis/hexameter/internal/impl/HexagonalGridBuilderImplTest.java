package biz.pavonis.hexameter.internal.impl;

import static org.junit.Assert.*;
import biz.pavonis.hexameter.api.AxialCoordinate;
import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridCalculator;
import biz.pavonis.hexameter.api.HexagonalGridLayout;
import biz.pavonis.hexameter.api.exception.HexagonalGridCreationException;
import biz.pavonis.hexameter.internal.impl.HexagonalGridBuilderImpl;
import biz.pavonis.hexameter.internal.impl.layoutstrategy.GridLayoutStrategy;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class HexagonalGridBuilderImplTest {

    private static final int GRID_HEIGHT = 9;
    private static final HexagonalGridLayout GRID_LAYOUT = HexagonalGridLayout.RECTANGULAR;
    private static final GridLayoutStrategy GRID_LAYOUT_STRATEGY = HexagonalGridLayout.RECTANGULAR.getGridLayoutStrategy();
    private static final int GRID_WIDTH = 9;
    private static final HexagonOrientation ORIENTATION = HexagonOrientation.FLAT_TOP;
    private static final Double RADIUS = 30D;

    private HexagonalGridBuilderImpl target;

    @Before
    public void setUp() {
        target = new HexagonalGridBuilderImpl();
        target.setGridHeight(GRID_HEIGHT).setGridLayout(GRID_LAYOUT).setGridWidth(GRID_WIDTH).setOrientation(ORIENTATION).setRadius(RADIUS);
    }

    @Test
    public void testSetters() {
        assertEquals(GRID_HEIGHT, target.getGridHeight());
        assertEquals(GRID_WIDTH, target.getGridWidth());
        assertEquals(GRID_LAYOUT_STRATEGY, target.getGridLayoutStrategy());
        assertEquals(RADIUS, target.getRadius());
        Assert.assertNotNull(target.getSharedHexagonData());
    }

    @Test(expected = IllegalStateException.class)
    public void testFailedSharedHexagonDataWhenNoOrientation() {
        target.setOrientation(null);
        target.getSharedHexagonData();
    }

    @Test(expected = IllegalStateException.class)
    public void testFailedSharedHexagonDataWhenNoRadius() {
        target.setRadius(0);
        target.getSharedHexagonData();
    }

    @Test(expected = HexagonalGridCreationException.class)
    public void testFailedWhenNoOrientation() throws HexagonalGridCreationException{
        target.setOrientation(null);
        target.build();
    }

    @Test(expected = HexagonalGridCreationException.class)
    public void testFailedWhenNoRadius() throws HexagonalGridCreationException{
        target.setRadius(0);
        target.build();
    }

    @Test(expected = HexagonalGridCreationException.class)
    public void testFailedWhenNoLayout() throws HexagonalGridCreationException{
        target.setGridLayout(null);
        target.build();
    }

    @Test(expected = HexagonalGridCreationException.class)
    public void testFailedWhenBadLayout() throws HexagonalGridCreationException{
        target.setGridLayout(HexagonalGridLayout.TRIANGULAR);
        target.setGridHeight(4);
        target.build();
    }

    @Test
    public void testAddCustomCoordinate() {
        int gridX = 1;
        int gridZ = 2;
        int size = target.getCustomCoordinates().size();
        target.addCustomAxialCoordinate(new AxialCoordinate(gridX, gridZ));
        assertTrue(target.getCustomCoordinates().size() == size + 1);
    }

    @Test
    public void testSetCustomStorage() {
        Map<String, Hexagon> customStorage = new HashMap<String, Hexagon>();
        target.setStorage(customStorage);
        assertEquals(customStorage, target.getStorage());
    }

    @Test
    public void testBuildCalculatorFor() {
        HexagonalGridCalculator calc = target.buildCalculatorFor(null);
        Assert.assertNotNull(calc);
        assertTrue(calc instanceof HexagonalGridCalculator);
    }

    @Test
    public void testGetOrientation() {
        assertEquals(ORIENTATION, target.getOrientation());
    }

    @Test
    public void testBuild() {
        HexagonalGrid grid = null;
        try{
           grid = target.build();
        }
        catch(HexagonalGridCreationException hgce){
           hgce.printStackTrace();
        }
        assertNotNull(grid);
    }

}
