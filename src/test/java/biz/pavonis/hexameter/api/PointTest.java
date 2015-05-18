package biz.pavonis.hexameter.api;

import static biz.pavonis.hexameter.api.HexagonPoint.distance;
import static java.lang.Math.sqrt;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PointTest {

    @Test
    public void testPoint() {
        Double x = 0D;
        Double y = 1D;
        HexagonPoint p = new HexagonPoint(x, y);
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test
    public void testDistance() {
        int y2 = 5;
        int y1 = 4;
        int x2 = 9;
        int x1 = 6;
        Double expectedDistance = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        Double actualDistance = distance(new HexagonPoint(x1, y1), new HexagonPoint(x2, y2));
        assertEquals(expectedDistance, actualDistance);
    }
}
