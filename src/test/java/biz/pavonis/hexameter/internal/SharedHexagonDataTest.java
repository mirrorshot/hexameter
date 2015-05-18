package biz.pavonis.hexameter.internal;

import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

import biz.pavonis.hexameter.api.HexagonOrientation;
import org.junit.After;
import org.junit.Test;

public class SharedHexagonDataTest {

    private static final HexagonOrientation ORIENTATION = HexagonOrientation.FLAT_TOP;
    private static final Double RADIUS = 30D;
    SharedHexagonData target;

    @After
    public void tearDown() {
        target = null;
    }

    @Test
    public void testRadiusSet() {
        target = new SharedHexagonData(ORIENTATION, RADIUS);
        assertEquals(RADIUS, target.getRadius());
    }

    @Test
    public void testCalculateWidthWithPointy() {
        target = createWithPointy();
        Double expectedWidth = sqrt(3) * RADIUS;
        Double actualWidth = target.getWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    public void testCalculateWidthWithFlat() {
        target = createWithFlat();
        Double expectedWidth = RADIUS * 3 / 2;
        Double actualWidth = target.getWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    public void testCalculateHeightWithPointy() {
        target = createWithPointy();
        Double expectedHeight = RADIUS * 3 / 2;
        Double actualHeight = target.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void testCalculateHeightWithFlat() {
        target = createWithFlat();
        Double expectedHeight = sqrt(3) * RADIUS;
        Double actualHeight = target.getHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void testGetOrientation() {
        target = new SharedHexagonData(ORIENTATION, RADIUS);
        assertEquals(ORIENTATION, target.getOrientation());
    }

    @Test
    public void testGetCoordinateOffset() {
        target = new SharedHexagonData(ORIENTATION, RADIUS);
        assertEquals(ORIENTATION.getCoordinateOffset(), target.getOrientation().getCoordinateOffset());
    }

    private SharedHexagonData createWithFlat() {
        return new SharedHexagonData(HexagonOrientation.FLAT_TOP, RADIUS);
    }

    private SharedHexagonData createWithPointy() {
        return new SharedHexagonData(HexagonOrientation.POINTY_TOP, RADIUS);
    }
}
