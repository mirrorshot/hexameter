package biz.pavonis.hexameter.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HexagonOrientationTest {

    @Test
    public void testFlatCoordinateOffset() {
       Double zero = 0D;
       assertEquals(zero, HexagonOrientation.FLAT_TOP.getCoordinateOffset());
    }

    @Test
    public void testPointyCoordinateOffset() {
       Double half = 0.5D;
       assertEquals(half, HexagonOrientation.POINTY_TOP.getCoordinateOffset());
    }
}
