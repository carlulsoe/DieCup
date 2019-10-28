import java.util.*;
import java.util.stream.Stream;

public class Solver  {
    private int noOfQueens;
    private int[] queens;
    private int noOfSolutions;
    private boolean showSolutions = true;


    public void findAllSolutions(int noOfQueens) {
        if (showSolutions) {
            System.out.println("");
            Stream.generate(() -> "*").limit(50).forEach(System.out::print);
            System.out.printf("\nSolutions for %d queens", noOfQueens);
            System.out.println("");
            System.out.println("");
        }

        this.noOfQueens = noOfQueens;
        noOfSolutions = 0;
        queens = new int[noOfQueens];
        positionQueens(0);

        if (showSolutions)  {
            System.out.println("");
            System.out.printf("A total of %d solutions were found", noOfSolutions);
        }
    }

    private void positionQueens(int row)  {
        for (int col = 0; col < noOfQueens; col++) {
            if (legal(row, col)) {
                queens[row] = col;
                if (row == noOfQueens-1) {
                    noOfSolutions++;
                    if (showSolutions) {
                        printSolution();
                    }
                }
                positionQueens(row+1);
            }
        }
    }

    public void findNoOfSolutions(int min, int max)    {
        setShowSolutions(false);

        Stream.generate(() -> "*").limit(50).forEach(System.out::print);
        System.out.println("\n  Queens      Solutions      Time(ms)      Solutions/ms");
        for (int i = min; i <= max; i++) {
            long start = System.currentTimeMillis();
            findAllSolutions(i);
            long end = System.currentTimeMillis();
            int time = Math.max(1, (int)(end-start));
            System.out.printf("%8d %14d %13d %17d \n", noOfQueens, noOfSolutions, time, noOfSolutions/time);
        }
        Stream.generate(() -> "*").limit(50).forEach(System.out::print);
        setShowSolutions(true);
    }

    public void setShowSolutions(boolean showSolutions) {
        this.showSolutions = showSolutions;
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

    private void printSolution() {
        for (int queen = 0; queen < noOfQueens; queen++) {
            System.out.printf("%3s ", convert(queen, queens[queen]));
        }
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

