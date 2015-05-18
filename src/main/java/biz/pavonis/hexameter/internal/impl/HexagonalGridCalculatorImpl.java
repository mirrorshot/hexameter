package biz.pavonis.hexameter.internal.impl;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridCalculator;
import biz.pavonis.hexameter.api.exception.HexagonNotFoundException;
import biz.pavonis.hexameter.api.exception.HexagonNotReachableException;

public final class HexagonalGridCalculatorImpl implements HexagonalGridCalculator {

   protected final HexagonalGrid hexagonalGrid;
   private static final Logger LOGGER = Logger.getLogger("HexagonalGridCalculator");

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
   public int calculateObstacleDistanceBetween(Hexagon hex0, Hexagon hex1)
      throws HexagonNotReachableException{
      //TODO
      int distance;
      distance = calculateObstacleDistanceBetweenImpl(hex0, hex1, new ArrayList<Hexagon>()).size();
      distance = 0;
      return distance;
   }
   
   /**
    * Recursive implementation for calculateObstacleDistanceBetween
    * 
    * @param hex0 start hexagon
    * @param hex1 end hexagon
    * @return distance
    */
   public List<Hexagon> calculateObstacleDistanceBetweenImpl(Hexagon hex0, Hexagon hex1, List<Hexagon> path)
      throws HexagonNotReachableException{
      //TODO
      if(hex0.equals(hex1))
         return path;
      int distance = Integer.MAX_VALUE;
      hex0.visit(1);
      path.add(hex0);
      List<Hexagon> minPath = null;
      Set<Hexagon> near = calculateMovementRangeFrom(hex0,1);
      int notAvalible = 0;
      for(Hexagon hex : near){
         if(!hex.isObstacle() && !hex.isVisited()){
            try{
               List<Hexagon> locPath = calculateObstacleDistanceBetweenImpl(hex, hex1,(ArrayList<Hexagon>) ((ArrayList<Hexagon>)path).clone());
               if(locPath.size() < distance){
                  distance = locPath.size();
                  minPath = locPath;
               }
            }
            catch(HexagonNotReachableException hnre){
               notAvalible++;
               LOGGER.log(Level.INFO, "Blind path", hnre);
            }
         }
         else
            notAvalible++;
      }
      if(notAvalible == near.size())
         throw new HexagonNotReachableException("End of path");
      return minPath;
   }
   
   /**
    * @see biz.pavonis.hexameter.api.HexagonalGridCalculator#calculateMovementRangeFrom(biz.pavonis.hexameter.api.Hexagon, int)
    */
   public Set<Hexagon> calculateMovementRangeFrom(Hexagon hexagon, int distance) {
      Set<Hexagon> ret = new HashSet<Hexagon>();
      for (int x = -distance; x <= distance; x++) {
         for (int y = max(-distance, -x - distance); y <= min(distance, -x + distance); y++) {
            int z = -x - y;
            try{
               ret.add(hexagonalGrid.getByGridCoordinate(hexagon.getGridX() + x, hexagon.getGridZ() + z));
            }
            catch(HexagonNotFoundException hnfe){
               LOGGER.log(Level.INFO, "Cannot go outside of borders", hnfe);
            }
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
      //if(hexagon.isVisited())
      //   return reach;
      reach.add(hexagon);
      //hexagon.visit();
      if(distance == 0)
      	return reach;
      Set<Hexagon> local = calculateMovementRangeFrom(hexagon,1);
      for(Hexagon step : local){
         if(!step.isObstacle() /*&& !step.isVisited()*/){
            reach.add(step);
            reach.addAll(calculateObstacleMovementRangeFromImpl(step, distance - 1));
         }
      }
      return reach;
   }
   
}
