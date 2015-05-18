package biz.pavonis.hexameter.api;

/**
 * Enum representing the possible orientations of a {@link Hexagon}. The names
 * are self-explanatory.
 */
public enum HexagonOrientation {

    POINTY_TOP(0.5f), FLAT_TOP(0);

    private Double coordinateOffset;

    private HexagonOrientation(double coordinateOffset) {
        this.coordinateOffset = coordinateOffset;
    }

    /**
     * This is because the flat/pointy shape of a hexagon.
     * It needs to be offset for pointy when calculating
     * the coordinates of its points.
     *
     * @return offset
     */
    public final Double getCoordinateOffset() {
        return coordinateOffset;
    }

}
