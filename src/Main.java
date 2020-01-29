import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		HousesCellsService housesCellsService = new HousesCellsService();
		
		List<List<Integer>> test1 = new ArrayList<List<Integer>>();
		test1.add(Arrays.asList(0, 1, 1, 0, 1));
		test1.add(Arrays.asList(0, 1, 0, 1, 0));
		test1.add(Arrays.asList(0, 0, 0, 0, 1));
		test1.add(Arrays.asList(0, 1, 0, 0, 0));
		housesCellsService.showGrid(4, 5, test1);

		List<List<Integer>> test2 = new ArrayList<List<Integer>>();
		test2.add(Arrays.asList(1, 0, 0, 0, 0));
		test2.add(Arrays.asList(0, 1, 0, 0, 0));
		test2.add(Arrays.asList(0, 0, 1, 0, 0));
		test2.add(Arrays.asList(0, 0, 0, 1, 0));
		test2.add(Arrays.asList(0, 0, 0, 0, 1));
		housesCellsService.showGrid(5, 5, test2);

		List<List<Integer>> test3 = new ArrayList<List<Integer>>();
		test3.add(Arrays.asList(0, 0, 1, 0, 0, 0));
		test3.add(Arrays.asList(0, 0, 0, 0, 0, 0));
		test3.add(Arrays.asList(0, 0, 0, 0, 0, 1));
		test3.add(Arrays.asList(0, 0, 0, 0, 0, 0));
		test3.add(Arrays.asList(0, 1, 0, 0, 0, 0));
		housesCellsService.showGrid(5, 6, test3);
		
		List<List<Integer>> test5 = new ArrayList<List<Integer>>();
		test5.add(Arrays.asList(1, 1, 1, 1));
		test5.add(Arrays.asList(1, 1, 1, 1));
		test5.add(Arrays.asList(1, 1, 1, 1));
		test5.add(Arrays.asList(1, 1, 1, 1));
		housesCellsService.showGrid(4, 4, test5);
	}
}

class HousesCellsService {

	public void showGrid(int rows, int columns, List<List<Integer>> grid) {
		System.out.println("\nRows: " + rows + ", Columns: " + columns);

		for (List<Integer> r : grid) {
			System.out.println(r);
		}

		System.out.println("Minimun days: " + minimunDays(rows, columns, grid));
	}

	public int minimunDays(int rows, int columns, List<List<Integer>> grid) {
		int days = 0;
		boolean iterationFinish = false;
		int countOnes = 0;
		
		while (!iterationFinish) {
			countOnes = 0;
			
			// copy the current servers that will be updated
			List<List<Integer>> updateGrid = new ArrayList<List<Integer>>();

			for (List<Integer> row : grid) {
				List<Integer> rowList = new ArrayList<Integer>();
				for (Integer block : row) {
					rowList.add(block);
				}
				updateGrid.add(rowList);
			}

			for (int i=0; i<rows; i++) { // iterate rows
				for (int j=0; j<columns; j++) { // iterate columns
				
					// if current block is out of date server
					if (updateGrid.get(i).get(j) == 1) {						
						countOnes++;
						
						// if the left, right, bottom and below can update
						if (j>0 && i>0 && j<(columns-1) && i<(rows-1)) {

							// update right server
							if (grid.get(i).get(j+1) == 0)
								grid.get(i).set(j+1, 1);

							// update left server
							if (grid.get(i).get(j-1) == 0)
								grid.get(i).set(j-1, 1);

							// update bottom server
							if (grid.get(i+1).get(j) == 0)
								grid.get(i+1).set(j, 1);

							// update below server
							if (grid.get(i-1).get(j) == 0)
								grid.get(i-1).set(j, 1);
						} else {
							// left servers
							if (j==0 && i>0 && i<(rows-1)) {
								// update right server
								if (grid.get(i).get(j+1) == 0)
									grid.get(i).set(j+1, 1);

								// update bottom server
								if (grid.get(i+1).get(j) == 0)
									grid.get(i+1).set(j, 1);

								// update below server
								if (grid.get(i-1).get(j) == 0)
									grid.get(i-1).set(j, 1);
							}

							// right servers
							if (j==(columns-1) && i>0 && i<(rows-1)) {
								// update left server
								if (grid.get(i).get(j-1) == 0)
									grid.get(i).set(j-1, 1);

								// update bottom server
								if (grid.get(i+1).get(j) == 0)
									grid.get(i+1).set(j, 1);

								// update below server
								if (grid.get(i-1).get(j) == 0)
									grid.get(i-1).set(j, 1);

							}

							// above servers
							if (i==0 && j>0 && j<(columns-1)) {
								// update right server
								if (grid.get(i).get(j+1) == 0)
									grid.get(i).set(j+1, 1);

								// update left server
								if (grid.get(i).get(j-1) == 0)
									grid.get(i).set(j-1, 1);

								// update bottom server
								if (grid.get(i+1).get(j) == 0)
									grid.get(i+1).set(j, 1);
							}

							// below servers
							if (i==(rows-1) && j>0 && j<(columns-1)) {
								// update right server
								if (grid.get(i).get(j+1) == 0)
									grid.get(i).set(j+1, 1);

								// update left server
								if (grid.get(i).get(j-1) == 0)
									grid.get(i).set(j-1, 1);

								// update below server
								if (grid.get(i-1).get(j) == 0)
									grid.get(i-1).set(j, 1);
							}

							// left above corner
							if (i==0 && j==0) {
								// update right server
								if (grid.get(i).get(j+1) == 0)
									grid.get(i).set(j+1, 1);

								// update bottom server
								if (grid.get(i+1).get(j) == 0)
									grid.get(i+1).set(j, 1);
							}

							// right above corner
							if (i == 0 && j == columns - 1) {
								// update left server
								if (grid.get(i).get(j-1) == 0)
									grid.get(i).set(j-1, 1);

								// update bottom server
								if (grid.get(i+1).get(j) == 0)
									grid.get(i+1).set(j, 1);
							}

							// left below corner
							if (i==(rows-1) && j==0) {
								// update right server
								if (grid.get(i).get(j+1) == 0)
									grid.get(i).set(j+1, 1);

								// update below server
								if (grid.get(i-1).get(j) == 0)
									grid.get(i-1).set(j, 1);
							}

							// right below corner
							if (i==(rows-1) && j==(columns-1)) {
								// update left server
								if (grid.get(i).get(j-1) == 0)
									grid.get(i).set(j-1, 1);

								// update below server
								if (grid.get(i-1).get(j) == 0)
									grid.get(i-1).set(j, 1);
							}
						}
												
						if (countOnes == (rows * columns)) {
							if (days == 0 ) {
								return -1;
							} else {
								return days;
							}
						}
					}
				} // iterate rows end
			} // iterate columns end
			
			days++;
		}
		
		return days;
	}
}
