package biz.pavonis.hexameter.internal.impl;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.HashSet;
import java.util.Set;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridCalculator;

public final class HexagonalGridCalculatorImpl implements HexagonalGridCalculator {

    private final HexagonalGrid hexagonalGrid;

    public HexagonalGridCalculatorImpl(HexagonalGrid hexagonalGrid) {
        this.hexagonalGrid = hexagonalGrid;
    }

    /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateDistanceBetween(biz.pavonis.hexameter.api.Hexagon, biz.pavonis.hexameter.api.Hexagon)
    */
   public int calculateDistanceBetween(Hexagon hex0, Hexagon hex1) {
        int absX = abs(hex0.getGridX() - hex1.getGridX());
        int absY = abs(hex0.getGridY() - hex1.getGridY());
        int absZ = abs(hex0.getGridZ() - hex1.getGridZ());
        return max(max(absX, absY), absZ);
    }

   /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateObstacleDistanceBetween(biz.pavonis.hexameter.api.Hexagon, biz.pavonis.hexameter.api.Hexagon)
    */
   public int calculateObstacleDistanceBetween(Hexagon hex0, Hexagon hex1){
      int distance = 0;
      //TODO
      return distance;
   }
   
    /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateMovementRangeFrom(biz.pavonis.hexameter.api.Hexagon, int)
    */
   public Set<Hexagon> calculateMovementRangeFrom(Hexagon hexagon, int distance) {
        Set<Hexagon> ret = new HashSet<Hexagon>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x + distance); y++) {
                int z = -x - y;
                ret.add(hexagonalGrid.getByGridCoordinate(hexagon.getGridX() + x, hexagon.getGridZ() + z));
            }
        }
        return ret;
    }

   /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateObstacleMovementRangeFrom(biz.pavonis.hexameter.api.Hexagon, int)
    */
   public Set<Hexagon> calculateObstacleMovementRangeFrom(Hexagon hexagon, int distance){
      Set<Hexagon> reach;
      reach = calculateObstacleMovementRangeFromImpl(hexagon,distance);
      for(Hexagon h : reach)
         h.clearVisit();
      return reach;
   }
   
   /**
    * Recursive implementation for calculateObstacleMovementRangeFrom
    * 
    * @param hexagon to start from
    * @param distance to reach
    * @return set of hexagons in the distance range
    */
   public Set<Hexagon> calculateObstacleMovementRangeFromImpl(Hexagon hexagon, int distance){
      Set<Hexagon> reach = new HashSet<Hexagon>();
      if(hexagon.isVisited())
         return reach;
      reach.add(hexagon);
      hexagon.visit();
      if(distance == 0)
      	return reach;
      Set<Hexagon> local = calculateMovementRangeFrom(hexagon,1);
      for(Hexagon step : local){
         if(!step.isObstacle() && !step.isVisited()){
            reach.add(step);
            reach.addAll(calculateObstacleMovementRangeFromImpl(step, distance - 1));
         }
      }
      return reach;
   }
   
}
