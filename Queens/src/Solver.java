
import java.util.*;
public class Solver  {
    private int noOfQueens;
    private int[] queens;
    private int noOfSolutions;


    public void findAllSolutions(int noOfQueens) {
        this.noOfQueens = noOfQueens;
        printStuff(true);

        queens = new int[noOfQueens];
        positionQueens(0);
        printStuff(false);
    }

    private void positionQueens(int row)  {
        if (row != noOfQueens) {
            for (int col = 0; col < noOfQueens; col++) {
                if (legal(row, col)) {
                    queens[row] = col;
                    if (row == noOfQueens-1)  {
                        noOfSolutions++;
                        printSolution();
                    }
                    positionQueens(row+1);
                }
            }
        }
    }

    private boolean legal(int row, int col) {
        // Down left
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == col - i) {
                return false;
            }
        }
        // Down right
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == col + i) {
                return false;
            }
        }
        // Down
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == col) {
                return false;
            }
        }

        return true;
    }

    private void printStuff(boolean first)   {
        if (first)  {
            System.out.println("*****************************");
            System.out.printf("Solutions for %d queens", noOfQueens);
            System.out.println("");
            System.out.println("");
        }
        else    {
            System.out.println("");
            System.out.println("");
            System.out.printf("A total of %d solutions were found", noOfSolutions);
            System.out.println("\n*****************************");
        }
    }

    private void printSolution() {
        for (int queen = 0; queen < noOfQueens; queen++) {
            System.out.print(convert(queen, queens[queen]) + " ");
        }
        System.out.println("");
    }

    public String convert(int row, int col) {
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

