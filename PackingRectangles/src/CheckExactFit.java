import java.util.ArrayList;

public class CheckExactFit {
	
	private Rectangle[] rectangles;
	private ArrayList<Factor> factors;
	
	public CheckExactFit(Rectangle[] rectangles, ArrayList<Factor> factors) {
		this.rectangles = rectangles;
		this.factors = factors;
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
	
	private int[][] copy2DArray(int[][] src) {
		
		int[][] dest = new int[src.length][src[0].length];
		
		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < src[0].length; j++) {
				dest[i][j] = src[i][j];
			}
		}
		return dest;
	}
	    
    private void insertRectangles (int row, int col, int rectIndex, int[][] currentBoard) {
        
        if (rectIndex == rectangles.length) {
        	System.out.println("EXACT (BRUTE FORCE) SOLUTION:\n");
        	printBoard(currentBoard);
        	System.out.println("Min Area: " + currentBoard.length + " x " + currentBoard[0].length);
        	System.exit(0);
        }
        
        if (row < currentBoard.length && col < currentBoard[0].length) {
            Rectangle currentRect = rectangles[rectIndex];
            int countArea = 0;
            boolean flag = true;
            
            int[][] unchangedBoard = copy2DArray(currentBoard);
                        
            for (int i = col; i < (currentRect.getHoriz() + col) && i < currentBoard[0].length && flag; i++) {
                for (int j = row; j < (currentRect.getVert() + row) && j < currentBoard.length; j++) {
                    if (currentBoard[j][i] == 0) {
                        currentBoard[j][i] = 1+rectIndex;
                        countArea++;
                        if (countArea == currentRect.getArea()) {
                            insertRectangles(0, 0, rectIndex+1, currentBoard);
                        }
                    } else {
                    	flag = false;
                    	break;
                    }
                }
            }
            if (col < currentBoard[0].length-1) {
                insertRectangles(row, col + 1, rectIndex, unchangedBoard);
            } else {
                insertRectangles(row+1, 0, rectIndex, unchangedBoard);
            }
        }
    }
}