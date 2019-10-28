public class TestSolver
{
    public static void legalTest() {
        Solver s1 = new Solver();
        s1.findAllSolutions(1);
        s1.findAllSolutions(2);
        s1.findAllSolutions(6);
        System.out.println("");
        s1.findNoOfSolutions(5, 12);
        System.out.println("");
        s1.testLegal(8, 4, 6, 3, 5);
        s1.testLegal(8, 0, 2, 4);
        s1.testLegal(8, 0, 2, 4, 6);
        s1.testLegal(8, 0);
        s1.testLegal(8, 0, 3);

    }

    public static void maxQueens() {
        Solver solver = new Solver();
        solver.setShowSolutions(false);
        long start = 0;
        long end = 0;
        int i = 1;
        while (end - start < 3*60*1000) {
            start = System.currentTimeMillis();
            solver.findAllSolutions(i);
            end = System.currentTimeMillis();
            System.out.println(i);
            i++;
            }
        System.out.println(i-1);
    }

}
