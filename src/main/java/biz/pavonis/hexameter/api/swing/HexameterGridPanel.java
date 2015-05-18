package biz.pavonis.hexameter.api.swing;

import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.api.HexagonalGridCalculator;
import biz.pavonis.hexameter.api.HexagonalGridLayout;
import biz.pavonis.hexameter.api.exception.HexagonalGridCreationException;
import biz.pavonis.hexameter.internal.impl.HexagonalGridBuilderImpl;
import biz.pavonis.hexameter.internal.impl.HexagonalGridCalculatorImpl;


/**
 * @author Daniele Brambilla
 *
 */
public class HexameterGridPanel {
   
   private static final int DEFAULT_GRID_WIDTH = 15;
   private static final int DEFAULT_GRID_HEIGHT = 15;
   private static final int DEFAULT_RADIUS = 20;
   private static final HexagonOrientation DEFAULT_ORIENTATION = HexagonOrientation.FLAT_TOP;
   private static final HexagonalGridLayout DEFAULT_GRID_LAYOUT = HexagonalGridLayout.RECTANGULAR;

   protected HexagonalGridBuilder builder;
   protected HexagonalGrid grid;
   protected HexagonalGridCalculator calculator;
   protected Integer gridHeight;
   protected Integer gridWidth;
   protected Double radius;
   
   public static final long serialVersionUID = 1L;
   
   public HexameterGridPanel(){
      builder = new HexagonalGridBuilderImpl();
   }
   
   public HexameterGridPanel(HexagonalGridBuilder builder){
      this.builder = builder;
   }
   
   public HexameterGridPanel(HexagonalGrid grid){
      this.grid = grid;
      calculator = new HexagonalGridCalculatorImpl(grid);
   }
   
   public HexagonalGrid build() throws HexagonalGridCreationException{
      grid = builder.build();
      return grid;
   }

   

}
