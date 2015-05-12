package biz.pavonis.hexameter.example;

import java.text.NumberFormat;

import biz.pavonis.hexameter.api.HexagonOrientation;
import biz.pavonis.hexameter.api.HexagonalGrid;
import biz.pavonis.hexameter.api.HexagonalGridBuilder;
import biz.pavonis.hexameter.api.HexagonalGridLayout;
import biz.pavonis.hexameter.api.exception.HexagonalGridCreationException;
import biz.pavonis.hexameter.internal.impl.HexagonalGridBuilderImpl;

public class Benchmark {

	private static final NumberFormat nf = NumberFormat.getInstance();
	static {
		nf.setMinimumFractionDigits(1);
		nf.setMaximumFractionDigits(1);
		nf.setGroupingUsed(false);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testBuilding();

	}

	@SuppressWarnings("unused")
	private static void testBuilding() {
		int size = 2500;
		HexagonalGridBuilder builder = new HexagonalGridBuilderImpl();
		builder.setGridHeight(size).setGridWidth(size).setRadius(10).setOrientation(HexagonOrientation.POINTY_TOP).setGridLayout(HexagonalGridLayout.RECTANGULAR);
		try{
		   HexagonalGrid grid = builder.build();
		}
		catch(HexagonalGridCreationException hgce){
		   hgce.printStackTrace();
		}
		System.out.println("Grid (" + size + "x" + size + ") built.");
	}
}
