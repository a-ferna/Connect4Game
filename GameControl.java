
public class GameControl {
	
	private int grid[][];
	
	GameControl() {
		grid = new int [6][7];
		
		printGrid();
	}
	
	
	public void addChecker(int x, int y, int player) {
		grid[x][y] = player;
		
		printGrid();
	}
	
//	public void clearMove(int x, int y) {
//		grid[x][y] = 0;
//		System.out.print(grid[x][y]+"\n");
//
//	}
	
	private void printGrid() {
		for(int i = 0; i<6; i++) {
			for(int j =0 ; j<7; j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	
	public boolean checkforTie() {
		for (int i = 0 ; i <=5 ; ++i) {
			for (int j = 0 ; j <= 6 ; ++j) {
				if (grid[i][j] == 0)
					return false;
			}
		}
		return true;
	}
	
	
	public boolean checkforWin(int p) {

		if (checkRows(p))
			return true;
		if (checkCols(p))
			return true;
		if (checkDiagDown(p))
			return true;
		if (checkDiagUp(p))
			return true;
		else 
			return false;
	 
	}
	
	
	private boolean checkRows(int p) {
	
		for (int x = 5 ; x >= 0 ; --x) {
			int y = 0;
			int numChecks = 0;
			while (y <= 6) {
//				System.out.print(x+" "+y+"\n");
				
				int c = grid[x][y];
				
				if (c == 0) {
					numChecks = 0;
					++y;
					continue;
				}
				
				if (c == p) {
					++numChecks;
				} else {
					numChecks = 0;
				}
				++y;
				
				if (numChecks == 4) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	private boolean checkCols(int p) {

		for (int y = 0 ; y <= 6 ; ++y) {
			int x = 0;
			int numChecks = 0;
			while (x <= 5) {
//				System.out.print(x+" "+y+"\n");
				
				int c = grid[x][y];
				
				if (c == 0) {
					numChecks = 0;
					++x;
					continue;
				}
				
				if (c == p) {
					++numChecks;
				} else {
					numChecks = 0;
				}
				++x;
				
				if (numChecks == 4) {
					return true;
				}
			}
		}
		
		return false;		
	}
	
	
	private boolean checkDiagDown(int p) {
		
		for (int i = 2 ; i >= 0 ; --i) {
			int x = i;
			int y = 0;
			int numChecks = 0;
			while (x <= 5) {
				
//				System.out.print(x+" "+y+"\n");
				
				int c = grid[x][y];
				
				if (c == 0) {
					numChecks = 0;
					++x;
					++y;
					continue;
				}
				
				if (c == p) {
					++numChecks;
				} else {
					numChecks = 0;
				}
				++x;
				++y;
				
				if (numChecks == 4) {
					return true;
				}
			
			}
		}
		
		
		for (int i = 1 ; i <= 3 ; ++i) {
			int x = 0;
			int y = i;
			int numChecks = 0;
			while (y <= 6) {
				
//				System.out.print(x+" "+y+"\n");
				
				int c = grid[x][y];
				
				if (c == 0) {
					numChecks = 0;
					++x;
					++y;
					continue;
				}
				
				if (c == p) {
					++numChecks;
				} else {
					numChecks = 0;
				}
				++x;
				++y;
				
				if (numChecks == 4) {
					return true;
				}
			
			}
		}
		
		
		return false;
	}
	
	
private boolean checkDiagUp(int p) {
		
		for (int i = 3 ; i <= 5 ; ++i) {
			int x = i;
			int y = 0;
			int numChecks = 0;
			while (x >= 0) {
				
//				System.out.print(x+" "+y+"\n");
				
				int c = grid[x][y];
				
				if (c == 0) {
					numChecks = 0;
					--x;
					++y;
					continue;
				}
				
				if (c == p) {
					++numChecks;
				} else {
					numChecks = 0;
				}
				--x;
				++y;
				
				if (numChecks == 4) {
					return true;
				}
			
			}
		}
		
		
		for (int i = 1 ; i <= 3 ; ++i) {
			int x = 5;
			int y = i;
			int numChecks = 0;
			while (y <= 6) {
				
//				System.out.print(x+" "+y+"\n");
				
				int c = grid[x][y];
				
				if (c == 0) {
					numChecks = 0;
					--x;
					++y;
					continue;
				}
				
				if (c == p) {
					++numChecks;
				} else {
					numChecks = 0;
				}
				--x;
				++y;
				
				if (numChecks == 4) {
					return true;
				}
			
			}
		}
		
		
		return false;
	}
	
	
		
}
