package biz.pavonis.hexameter.internal.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonPoint;
import biz.pavonis.hexameter.internal.SharedHexagonData;

public class HexagonImplTest {

    private static final Double TEST_RADIUS = 10D;
    private static final SharedHexagonData TEST_POINTY_DATA = new SharedHexagonData(HexagonOrientation.POINTY_TOP, TEST_RADIUS);
    private static final SharedHexagonData TEST_FLAT_DATA = new SharedHexagonData(HexagonOrientation.FLAT_TOP, TEST_RADIUS);
    private static final int TEST_GRID_X = 2;
    private static final int TEST_GRID_Z = 3;
    private static final int TEST_GRID_Y = -5;
    private static final Object TEST_SATELLITE_DATA = new Object();
    private static final Double EXPECTED_POINTY_CENTER_X = 69D;
    private static final Double EXPECTED_FLAT_CENTER_X = 40D;
    private static final Double EXPECTED_POINTY_CENTER_Y = 55D;
    private static final Double EXPECTED_FLAT_CENTER_Y = 78D;
    private static final HexagonPoint[] EXPECTED_FLAT_POINTS = new HexagonPoint[]{new HexagonPoint(50, 78), new HexagonPoint(45, 87), new HexagonPoint(35, 87), new HexagonPoint(30, 78), new HexagonPoint(35, 69),
        new HexagonPoint(45, 69)};
    private static final HexagonPoint[] EXPECTED_POINTY_POINTS = new HexagonPoint[]{new HexagonPoint(78, 60), new HexagonPoint(69, 65), new HexagonPoint(61, 60), new HexagonPoint(61, 50), new HexagonPoint(69, 45),
        new HexagonPoint(78, 50)};

    private Hexagon target;

    @Before
    public void setUp() {
        target = new HexagonImpl(TEST_POINTY_DATA, TEST_GRID_X, TEST_GRID_Z);
    }

    @Test
    public void testGetPointsPointy() {
        for (int i = 0; i < 6; i++) {
            assertEquals(EXPECTED_POINTY_POINTS[i].getX(), target.getHexagonPoints()[i].getX());
            assertEquals(EXPECTED_POINTY_POINTS[i].getY(), target.getHexagonPoints()[i].getY());
        }
    }

    @Test
    public void testGetPointsFlat() {
        target = new HexagonImpl(TEST_FLAT_DATA, TEST_GRID_X, TEST_GRID_Z);
        for (int i = 0; i < 6; i++) {
            assertEquals(EXPECTED_FLAT_POINTS[i].getX(), target.getHexagonPoints()[i].getX());
            assertEquals(EXPECTED_FLAT_POINTS[i].getY(), target.getHexagonPoints()[i].getY());
        }
    }

    @Test
    public void testSetSatelliteData() {
        target.setSatelliteData(TEST_SATELLITE_DATA);
        assertEquals(TEST_SATELLITE_DATA, target.getSatelliteData());
    }

    @Test
    public void testGetGridX() {
        assertEquals(TEST_GRID_X, target.getGridX());
    }

    @Test
    public void testGetGridY() {
        assertEquals(TEST_GRID_Y, target.getGridY());
    }

    @Test
    public void testGetGridZ() {
        assertEquals(TEST_GRID_Z, target.getGridZ());
    }

    @Test
    public void testGetCenterXPointy() {
        assertEquals(EXPECTED_POINTY_CENTER_X, target.getCenterX());
    }

    @Test
    public void testGetCenterXFlat() {
        target = new HexagonImpl(TEST_FLAT_DATA, TEST_GRID_X, TEST_GRID_Z);
        assertEquals(EXPECTED_FLAT_CENTER_X, target.getCenterX());
    }

    @Test
    public void testGetCenterYPointy() {
        assertEquals(EXPECTED_POINTY_CENTER_Y, target.getCenterY());
    }

    @Test
    public void testGetCenterYFlat() {
        target = new HexagonImpl(TEST_FLAT_DATA, TEST_GRID_X, TEST_GRID_Z);
        assertEquals(EXPECTED_FLAT_CENTER_Y, target.getCenterY());
    }

}
