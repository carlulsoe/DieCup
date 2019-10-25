
/**
 * Lav en beskrivelse af klassen TestSolver her.
 * 
 * @author (dit navn her)
 * @version (versions nummer eller dato her)
 */
public class TestSolver
{
    public static void legalTest() {
        Solver s1 = new Solver();
        s1.testLegal(8, 4, 6, 3, 5);
        s1.testLegal(8, 0, 2, 4);
        s1.testLegal(8, 0, 2, 4, 6);
        
    }
}
