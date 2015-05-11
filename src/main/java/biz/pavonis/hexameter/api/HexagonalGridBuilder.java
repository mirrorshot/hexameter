package biz.pavonis.hexameter.api;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import biz.pavonis.hexameter.api.exception.HexagonalGridCreationException;
import biz.pavonis.hexameter.internal.SharedHexagonData;
import biz.pavonis.hexameter.internal.impl.layoutstrategy.GridLayoutStrategy;

/**
 * <p>Builder for a {@link HexagonalGrid}.
 * Can be used to build a {@link HexagonalGrid}.
 * Mandatory parameters are:</p>
 * <ul>
 * <li>width of the grid</li>
 * <li>height of the grid</li>
 * <li>radius of a {@link Hexagon}</li>
 * </ul>
 */

public interface HexagonalGridBuilder {

    /**
     * Mandatory parameter. Sets the number of {@link Hexagon}s in the horizontal direction.
     * 
     * @param gridWidth
     * @return this {@link HexagonalGridBuilder}
     */
    public HexagonalGridBuilder setGridWidth(int gridWidth);
    
    /**
     * Mandatory parameter. Sets the number of {@link Hexagon}s in the vertical direction.
     * 
     * @param gridHeight
     * @return this {@link HexagonalGridBuilder}
     */
    public HexagonalGridBuilder setGridHeight(int gridHeight);

    /**
     * Sets the {@link HexagonOrientation} used in the resulting {@link HexagonalGrid}.
     * If it is not set HexagonOrientation.POINTY will be used.
     * 
     * @param orientation
     * @return this {@link HexagonalGridBuilder}
     */
    public HexagonalGridBuilder setOrientation(HexagonOrientation orientation);

    /**
     * Sets the radius of the {@link Hexagon}s contained in the resulting {@link HexagonalGrid}.
     * 
     * @param radius in pixels
     * @return this {@link HexagonalGridBuilder}
     */
    public HexagonalGridBuilder setRadius(double radius);

    /**
     * Sets the {@link HexagonalGridLayout} which will be used when creating the {@link HexagonalGrid}.
     * If it is not set <pre>RECTANGULAR</pre> will be assumed.
     * 
     * @param gridLayout
     * @return this {@link HexagonalGridBuilder}.
     */
    public HexagonalGridBuilder setGridLayout(HexagonalGridLayout gridLayout);
    
    /**
     * Adds a custom coordinate to the {@link HexagonalGrid} which will be produced.
     * 
     * @param axialCoordinate
     * @return this {@link HexagonalGridBuilder}.
     */
    public HexagonalGridBuilder addCustomAxialCoordinate(AxialCoordinate axialCoordinate);

    /**
     * Sets a custom storage object to the {@link HexagonalGrid}. It will be used
     * instead of the internal storage. You can pass any custom storage object
     * as long as it implements the {@link Map} interface. Refer to the swt example
     * project for examples. <strong>Please note</strong> that if you supply a Map
     * which is not empty the {@link HexagonalGrid} will overwrite its contents.
     * Methods you must implement:
     * <ul>
     * <li> {@link Map#containsKey(Object)}</li>
     * <li> {@link Map#get(Object)}</li>
     * <li> {@link Map#putAll(Map)}</li>
     * <li> {@link Map#put(Object, Object)}</li>
     * <li> {@link Map#remove(Object)}</li>
     * <li> {@link Map#keySet()}</li>
     * </ul>
     * Others are not necessary but highly recommended. Refer to the javadoc of {@link AbstractMap} if you need help.
     * 
     * @param customStorage
     * @return this {@link HexagonalGridBuilder}.
     */
    public HexagonalGridBuilder setStorage(Map<String, Hexagon> customStorage);

    /**
     * Builds a {@link HexagonalGrid} using the parameters supplied.
     * Throws {@link HexagonalGridCreationException} if not all mandatory parameters
     * are filled and/or they are not valid. In both cases you will be supplied with
     * a {@link HexagonalGridCreationException} detailing the cause of failure.
     * 
     * @return {@link HexagonalGrid} from this builder parameters
     * @throws HexagonalGridCreationException
    */
   public HexagonalGrid build() 
          throws HexagonalGridCreationException;

    /**
     * Creates a {@link HexagonalGridCalculator} for your {@link HexagonalGrid}.
     * 
     * @param hexagonalGrid
     * @return calculator
     */
    public HexagonalGridCalculator buildCalculatorFor(HexagonalGrid hexagonalGrid);

    /**
    * @return {@link GridLayoutStrategy} for the builder
    */
   public GridLayoutStrategy getGridLayoutStrategy();
   

   /**
    * @return {@link List} of custom {@link AxialCoordinate} from this builder 
    */
   public List<AxialCoordinate> getCustomCoordinates();

    /**
    * @return the storage given in the setup of the builder
    */
   public Map<String, Hexagon> getStorage();

    /**
    * @return {@link SharedHexagonData} for the builder
    */
   public SharedHexagonData getSharedHexagonData();
   
   /**
    * @return
    */
   public double getRadius();

   /**
    * @return
    */
   public int getGridWidth();

   /**
    * @return
    */
   public int getGridHeight();

   /**
    * @return
    */
   public HexagonOrientation getOrientation();
   
}
