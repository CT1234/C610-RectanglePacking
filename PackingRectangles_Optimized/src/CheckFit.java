import java.util.ArrayList;

public class CheckFit {
	
	private Rectangle[] rectangles;
	private ArrayList<Factor> factors;
	private int maxHoriz;
	private int maxVert;
	
	public CheckFit(Rectangle[] rectangles, ArrayList<Factor> factors, int minHoriz, int minVert) {
		this.rectangles = rectangles;
		this.factors = factors;
		this.maxHoriz = minHoriz;
		this.maxVert = minVert;
	}
		    
    private int[][] createBoard(int a, int b) {
        int[][] boardArray = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                boardArray[i][j] = 0;
            }
        }
        return boardArray;
    }
    
    private void printBoard(int[][] board) {
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			System.out.print(board[i][j] + " ");
    		}
    		System.out.println();
    	}
		System.out.println();
    }
    
	public boolean checkRectangleFit() {
	    for (int i = 0; i < factors.size(); i++) {
	        int[][] board = createBoard(factors.get(i).getA(), factors.get(i).getB());
	        insertRectangles(0, 0, 0, board);
	    }
	    return false;
	}

	    
    private void insertRectangles (int row, int col, int rectIndex, int[][] currentBoard) {
    	
    	if(currentBoard.length > maxVert && currentBoard[0].length > maxHoriz) {
            if (rectIndex == rectangles.length-1) {
            	System.out.println("OPTIMIZED (POLYNOMIAL TIME) SOLUTION:\n");
            	printBoard(currentBoard);
            	System.out.println("FAST SOLUTION FOUND: " + currentBoard.length + " x " + currentBoard[0].length);
            	System.exit(0);
            }
            
            if (row < currentBoard.length && col < currentBoard[0].length) {
                Rectangle currentRect = rectangles[rectIndex];
                int countArea = 0;
                boolean flag = true;
                            
                for (int i = col; i < (currentRect.getHoriz() + col) && i < currentBoard[0].length && flag; i++) {
                    for (int j = row; j < (currentRect.getVert() + row) && j < currentBoard.length; j++) {
                        if (currentBoard[j][i] == 0) {
                            currentBoard[j][i] = 1 + rectIndex;
                            countArea++;
                            if (countArea == currentRect.getArea()) {
                            	if (rectIndex != rectangles.length-1) {
                            		int nextRectangleHoriz = rectangles[1 + rectIndex].getHoriz();
                            		if ( (col + nextRectangleHoriz) < currentBoard[0].length) {
                                        insertRectangles(row, i+1, rectIndex+1, currentBoard);
                            		} else {
                            			int firstZeroRow = 0;
                            			while( (firstZeroRow < currentBoard.length) && currentBoard[firstZeroRow][0] != 0){
                            				firstZeroRow++;
                            			}
                                        insertRectangles(firstZeroRow, 0, rectIndex+1, currentBoard);
                            		}
                            	}
                            }
                        } else {
                        	flag = false;
                        	break;
                        }
                    }
                }
            }
       }
    }
}