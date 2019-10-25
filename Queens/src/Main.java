import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        // solver.testLegal(8, 4, 6, 3, 5);

        /*
        for (int i = 1; i <= 8; i++) {
            long start = System.currentTimeMillis();
            solver.findAllSolutions(i);
            long end = System.currentTimeMillis();
            System.out.printf(" in %d ms \n", end - start);
            Stream.generate(() -> "*").limit(50).forEach(System.out::print);
            System.out.println("");
        }
        */

        solver.findNoOfSolutions(1,12);
    }
}
