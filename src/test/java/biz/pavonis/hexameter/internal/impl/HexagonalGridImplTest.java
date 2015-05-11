package biz.pavonis.hexameter.internal.impl;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import biz.pavonis.hexameter.api.CoordinateConverter;
import biz.pavonis.hexameter.api.Hexagon;
import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.exception.HexagonNotFoundException;
import biz.pavonis.hexameter.api.exception.HexagonalGridCreationException;

public class HexagonalGridImplTest {

	private static final int RADIUS = 30;
	private static final int GRID_WIDTH = 10;
	private static final int GRID_HEIGHT = 10;
	private static final HexagonOrientation ORIENTATION = HexagonOrientation.POINTY_TOP;
	private static final int GRID_X_FROM = 2;
	private static final int GRID_X_TO = 4;
	private static final int GRID_Z_FROM = 3;
	private static final int GRID_Z_TO = 5;
	private HexagonalGrid target;
	private HexagonalGridBuilderImpl builder;

	@Before
	public void setUp() throws Exception {
		builder = new HexagonalGridBuilderImpl();
		builder.setGridHeight(GRID_HEIGHT).setGridWidth(GRID_WIDTH).setRadius(RADIUS).setOrientation(ORIENTATION);
		target = builder.build();
	}

	@Test
	public void testHexagonalGridImplWithCustomStorage() {
		Map<String, Hexagon> expected = new HashMap<String, Hexagon>();
		builder.setStorage(expected);
		try{
		   target = builder.build();
		}
		catch(HexagonalGridCreationException hgce){
		   hgce.printStackTrace();
		}
		assertEquals(expected, target.getHexagons());
	}

	@Test
	public void testGetHexagons() {
		Map<String, Hexagon> hexagons = target.getHexagons();
		assertEquals(100, hexagons.size());
		for (int x = 0; x < GRID_WIDTH; x++) {
			for (int y = 0; y < GRID_HEIGHT; y++) {
				int gridX = CoordinateConverter.convertOffsetCoordinatesToAxialX(x, y, ORIENTATION);
				int gridY = CoordinateConverter.convertOffsetCoordinatesToAxialZ(x, y, ORIENTATION);
				String key = CoordinateConverter.createKeyFromCoordinate(gridX, gridY);
				assertTrue(hexagons.containsKey(key));
				Hexagon hex = hexagons.get(key);
				assertTrue(hex.getGridX() == gridX);
				assertTrue(hex.getGridZ() == gridY);
			}
		}
	}

	@Test
	public void testGetHexagonsByAxialRange() {
	   try{
   		Set<Hexagon> expected = new HashSet<Hexagon>();
   
   		expected.add(target.getByGridCoordinate(2, 3));
   		expected.add(target.getByGridCoordinate(3, 3));
   		expected.add(target.getByGridCoordinate(4, 3));
   
   		expected.add(target.getByGridCoordinate(2, 4));
   		expected.add(target.getByGridCoordinate(3, 4));
   		expected.add(target.getByGridCoordinate(4, 4));
   
   		expected.add(target.getByGridCoordinate(2, 5));
   		expected.add(target.getByGridCoordinate(3, 5));
   		expected.add(target.getByGridCoordinate(4, 5));
   
   		Map<String, Hexagon> actual = target.getHexagonsByAxialRange(GRID_X_FROM, GRID_X_TO, GRID_Z_FROM, GRID_Z_TO);
   		assertEquals(expected.size(), actual.size());
   		for (Hexagon hex : expected) {
   			String key = CoordinateConverter.createKeyFromCoordinate(hex.getGridX(), hex.getGridZ());
   			assertTrue(actual.containsKey(key));
   			assertEquals(hex, actual.get(key));
   		}
	   }
	   catch(HexagonNotFoundException hnfe){
	      
	   }
	}

	@Test
	public void testGetHexagonsByOffsetRange() {
	   try{
   	   Set<Hexagon> expected = new HashSet<Hexagon>();
   
   		expected.add(target.getByGridCoordinate(1, 3));
   		expected.add(target.getByGridCoordinate(2, 3));
   		expected.add(target.getByGridCoordinate(3, 3));
   
   		expected.add(target.getByGridCoordinate(0, 4));
   		expected.add(target.getByGridCoordinate(1, 4));
   		expected.add(target.getByGridCoordinate(2, 4));
   
   		expected.add(target.getByGridCoordinate(0, 5));
   		expected.add(target.getByGridCoordinate(1, 5));
   		expected.add(target.getByGridCoordinate(2, 5));
   
   		Map<String, Hexagon> actual = target.getHexagonsByOffsetRange(GRID_X_FROM, GRID_X_TO, GRID_Z_FROM, GRID_Z_TO);
   		assertEquals(expected.size(), actual.size());
   		for (Hexagon hex : expected) {
   			String key = CoordinateConverter.createKeyFromCoordinate(hex.getGridX(), hex.getGridZ());
   			assertTrue(actual.containsKey(key));
   			assertEquals(hex, actual.get(key));
   		}
	   }
	   catch(HexagonNotFoundException hnfe){
	      
	   }
	}

	@Test
	public void testAddHexagon() {
		int gridX = 20;
		int gridZ = 30;
		assertFalse(target.containsCoordinate(gridX, gridZ));
		target.addHexagon(gridX, gridZ);
		assertTrue(target.containsCoordinate(gridX, gridZ));
	}

	@Test
	public void testRemoveHexagon() {
		int gridX = 2;
		int gridZ = 3;
		assertTrue(target.containsCoordinate(gridX, gridZ));
		try{
		   target.removeHexagon(gridX, gridZ);
		   assertFalse(target.containsCoordinate(gridX, gridZ));
		}
		catch(HexagonNotFoundException hnfe){
		   
		}
	}

	@Test
	public void testContainsCoordinate() {
		int gridX = 2;
		int gridZ = 3;
		assertTrue(target.containsCoordinate(gridX, gridZ));
	}

	@Test
	public void testGetByGridCoordinateWhichIsValid() {
		int gridX = 2;
		int gridZ = 3;
		try{
   		Hexagon hex = target.getByGridCoordinate(gridX, gridZ);
   		assertNotNull(hex);
		}
		catch(HexagonNotFoundException hnfe){
		   
		}
	}

	@Test(expected = HexagonNotFoundException.class)
	public void testGetByGridCoordinateWhichIsInvalid() throws HexagonNotFoundException {
		int gridX = 20;
		int gridZ = 30;
	   target.getByGridCoordinate(gridX, gridZ);
	}

	@Test
	public void testGetByPixelCoordinateWithGoodEstimation() {
		double x = 310;
		double y = 255;
		try{
   		Hexagon hex = target.getByPixelCoordinate(x, y);
   		assertTrue(hex.getGridX() == 3);
   		assertTrue(hex.getGridZ() == 5);
		}
		catch(HexagonNotFoundException hnfe){
		   
		}
	}

	@Test
	public void testGetByPixelCoordinateWithBadEstimation1() {
		double x = 300;
		double y = 275;
		try{
   		Hexagon hex = target.getByPixelCoordinate(x, y);
   		assertTrue(hex.getGridX() == 3);
   		assertTrue(hex.getGridZ() == 5);
		}
      catch(HexagonNotFoundException hnfe){
         
      }
	}

	@Test
	public void testGetByPixelCoordinateWithBadEstimation2() {
		double x = 325;
		double y = 275;
		try{
   		Hexagon hex = target.getByPixelCoordinate(x, y);
   		assertTrue(hex.getGridX() == 3);
   		assertTrue(hex.getGridZ() == 5);
		}
      catch(HexagonNotFoundException hnfe){
         
      }
	}

	@Test
	public void testGetNeighborsOfFromMiddle() {
	   try{
   		Hexagon hex = target.getByGridCoordinate(3, 7);
   		Set<Hexagon> expected = new HashSet<Hexagon>();
   		expected.add(target.getByGridCoordinate(3, 6));
   		expected.add(target.getByGridCoordinate(4, 6));
   		expected.add(target.getByGridCoordinate(4, 7));
   		expected.add(target.getByGridCoordinate(3, 8));
   		expected.add(target.getByGridCoordinate(2, 8));
   		expected.add(target.getByGridCoordinate(2, 7));
   		Set<Hexagon> actual = target.getNeighborsOf(hex);
   		assertEquals(expected, actual);
	   }
      catch(HexagonNotFoundException hnfe){
         
      }
	}

	@Test
	public void testGetNeighborsOfFromEdge() {
	   try{
   		Hexagon hex = target.getByGridCoordinate(5, 9);
   		Set<Hexagon> expected = new HashSet<Hexagon>();
   		expected.add(target.getByGridCoordinate(5, 8));
   		expected.add(target.getByGridCoordinate(4, 9));
   		Set<Hexagon> actual = target.getNeighborsOf(hex);
   		assertEquals(expected, actual);
	   }
      catch(HexagonNotFoundException hnfe){
         
      }
	}

	@Test
	public void testClearSatelliteData() {
	   try{
   		Hexagon testHex = target.getByGridCoordinate(2, 3);
   		Object data = new Object();
   		testHex.setSatelliteData(data);
   		target.clearSatelliteData();
   		assertTrue(testHex.getSatelliteData() == null);
	   }
      catch(HexagonNotFoundException hnfe){
         
      }
	}
}
