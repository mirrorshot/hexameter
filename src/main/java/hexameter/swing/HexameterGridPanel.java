package hexameter.swing;

import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.api.HexagonalGridCalculator;
import biz.pavonis.hexameter.api.exception.HexagonalGridCreationException;
import biz.pavonis.hexameter.internal.impl.HexagonalGridCalculatorImpl;


public class HexameterGridPanel {
   
   protected HexagonalGridBuilder builder;
   protected HexagonalGrid grid;
   protected HexagonalGridCalculator calculator;
   protected Integer gridHeight;
   protected Integer gridWidth;
   protected Double radius;
   
   public static final long serialVersionUID = 1L;
   
   public HexameterGridPanel(){
      builder = new HexagonalGridBuilder();
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
