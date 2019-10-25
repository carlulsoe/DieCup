import java.util.*;
public class Solver
{
    private int noOfQueens;
    private int[] queens;
    private int noOfSolutions;

    public void findAllSolutions(int noOfQueens) {
        queens = new int[noOfQueens];
        Arrays.fill(queens, -1);
        positionQueens(0);

    }

    private void positionQueens(int row)  {
        if (row != noOfQueens) {
            for (int col = 0; col <= noOfQueens; col++) {
                if (legal(row, col)) {
                    queens[row] = col; 
                    positionQueens(row+1);
                }
            }
        }
    }

    private boolean legal(int row, int col) {  

        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == col - i) {
                return false;
            }
        }
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == col + i) {
                return false;
            }
        }
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == col) {
                return false;
            } 
        }
        return true;
    }

    private void printSolution() {
        System.out.println("*****************************");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    private String convert(int row, int col) {
        col += 1;
        char r = (char) (97 + row);
        return r + Integer.toString(col);
    }

    public void testLegal(int n, int... pos) {
        noOfQueens = n;
        queens = Arrays.copyOf(pos, n);

        System.out.print(n + "x" + n + " with queens in: " +
            Arrays.toString(pos) + " => Legal positions: ");

        for (int i = 0; i < n; i++) {
            if (legal(pos.length, i)) { 
                System.out.print(i + " "); 
            }
        }
        System.out.println();
    }


}

