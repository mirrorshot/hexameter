package biz.pavonis.hexameter.api;

import static biz.pavonis.hexameter.api.HexagonPoint.distance;
import static java.lang.Math.sqrt;
import static junit.framework.Assert.assertEquals;

import biz.pavonis.hexameter.categories.UnitTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTests.class)
public class PointTest {

    @Test
    public void testPoint() {
        double x = 0;
        double y = 1;
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
        double expectedDistance = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double actualDistance = distance(new HexagonPoint(x1, y1), new HexagonPoint(x2, y2));
        assertEquals(expectedDistance, actualDistance);
    }
}
