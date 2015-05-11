package biz.pavonis.hexameter.internal.impl.layoutstrategy;

import static biz.pavonis.hexameter.api.CoordinateConverter.createKeyFromCoordinate;
import static biz.pavonis.hexameter.api.HexagonOrientation.FLAT_TOP;
import static biz.pavonis.hexameter.internal.impl.layoutstrategy.GridLayouStrategyTestUtil.fetchDefaultBuilder;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;

public class TriangularGridLayoutStrategyTest {

    private HexagonalGridBuilder builder;
    private TriangularGridLayoutStrategy target;

    @Before
    public void setUp() throws Exception {
        builder = fetchDefaultBuilder();
        target = new TriangularGridLayoutStrategy();
    }

    @Test
    public void testCreateHexagonsWithPointy() {
        Map<String, Hexagon> hexagons = target.createHexagons(builder);
        testHexagons(hexagons);
    }

    @Test
    public void testCreateHexagonsWithFlat() {
        builder.setOrientation(FLAT_TOP);
        Map<String, Hexagon> hexagons = target.createHexagons(builder);
        testHexagons(hexagons);
    }

    private void testHexagons(Map<String, Hexagon> hexagons) {
        assertNotNull(hexagons.get(createKeyFromCoordinate(0, 0)));
        assertNotNull(hexagons.get(createKeyFromCoordinate(1, 0)));
        assertNotNull(hexagons.get(createKeyFromCoordinate(2, 0)));
        assertNotNull(hexagons.get(createKeyFromCoordinate(0, 1)));
        assertNotNull(hexagons.get(createKeyFromCoordinate(1, 1)));
        assertNotNull(hexagons.get(createKeyFromCoordinate(0, 2)));

        assertNull(hexagons.get(createKeyFromCoordinate(-1, 0)));
        assertNull(hexagons.get(createKeyFromCoordinate(0, -1)));
        assertNull(hexagons.get(createKeyFromCoordinate(1, -1)));
        assertNull(hexagons.get(createKeyFromCoordinate(2, -1)));
        assertNull(hexagons.get(createKeyFromCoordinate(3, -1)));
        assertNull(hexagons.get(createKeyFromCoordinate(3, 0)));
        assertNull(hexagons.get(createKeyFromCoordinate(2, 1)));
        assertNull(hexagons.get(createKeyFromCoordinate(1, 2)));
        assertNull(hexagons.get(createKeyFromCoordinate(0, 3)));
        assertNull(hexagons.get(createKeyFromCoordinate(-1, 3)));
        assertNull(hexagons.get(createKeyFromCoordinate(-1, 2)));
        assertNull(hexagons.get(createKeyFromCoordinate(-1, 1)));
    }

    @Test
    public void testCheckParameters0() {
        boolean result = target.checkParameters(1, 1); // super: true, derived: true
        assertTrue(result);
    }

    @Test
    public void testCheckParameters1() {
        boolean result = target.checkParameters(1, 2); // super: true, derived: false
        assertFalse(result);
    }

    @Test
    public void testCheckParameters3() {
        boolean result = target.checkParameters(0, 0); // super: false, derived: false;
        assertFalse(result);
    }

    @Test
    public void testCheckParameters4() {
        boolean result = target.checkParameters(-1, -1); // super: false, derived: true;
        assertFalse(result);
    }
}
