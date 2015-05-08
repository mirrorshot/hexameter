package biz.pavonis.hexameter.api.exception;


/**
 * @author daniele
 * 
 * Exception to be thrown while calculating distance between two {@link Hexagon}s in
 * whene obstacles are present or if target {@link Hexagon} is an obstacle.
 *
 */
public class HexagonNotReachableException extends Exception {

   public static final long serialVersionUID = 1L;
   
   public HexagonNotReachableException(String msg){
      super(msg);
   }
   
}
