package biz.pavonis.hexameter.api;

import static biz.pavonis.hexameter.api.Point.distance;
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
        Point p = new Point(x, y);
        assertEquals(x, p.x);
        assertEquals(y, p.y);
    }

    @Test
    public void testDistance() {
        int y2 = 5;
        int y1 = 4;
        int x2 = 9;
        int x1 = 6;
        double expectedDistance = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double actualDistance = distance(new Point(x1, y1), new Point(x2, y2));
        assertEquals(expectedDistance, actualDistance);
    }
}
