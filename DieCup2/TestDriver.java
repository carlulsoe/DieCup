
/**
 * Tester dieCup
 * 
 * @author Carl Ulsøe Christensen
 * @version 1.0
 */
public class TestDriver
{    
    public static void test(int sides1, int sides2){
        DieCup cup = new DieCup(sides1, sides2);
        cup.roll();
        System.out.println(cup.getEyes());
    }
    
    public static void testMulitple(int noOfRolls, int sides1, int sides2) {
        DieCup cup = new DieCup(sides1, sides2);
        if (noOfRolls == 0) {
            return;
        }
        // We make a new variable which contains the sum of all the rolls
        double rollSum = 0.0;
        // Now we make a for loop to run roll exactly noOfRolls times. 
        for (int i = 1; i <= noOfRolls; i++) {
            // We roll and saves the number of eyes rolled in a temporary variable
            cup.roll();
            int tempEyes = cup.getEyes();
            // We then add the number of eyes to our sum and print out what we rolled.
            rollSum += tempEyes;
            System.out.println("Throw no " + i + ": " + tempEyes);
        }
        // then we output the average.
        double average = rollSum/noOfRolls;
        System.out.println("Average no of eyes: " + average);
    }
    
    public static void compareDieCups(int s1, int s2, int s3, int s4, int noOfRolls){
        // vi laver alle DieCups
        DieCup d1 = new DieCup(s1, s2);
        DieCup d2 = new DieCup(s3, s4);
        // tracker scoren
        int wins1 = 0;
        int wins2 = 0;
        int sameScore = 0;
        // roller noOfRolls gange
        for (int i = 1; i <= noOfRolls; i++) {
            // roller
            d1.roll();
            d2.roll();
            // tjekker eyes
            int eyes1 = d1.getEyes();
            int eyes2 = d2.getEyes();
            // scorer rollet
            if (eyes1 > eyes2){
                wins1++;
            }
            else if (eyes2 > eyes1){
                wins2++;
            }
            else {
                sameScore++;
            }
        }
        // printer slut scoren.
        System.out.println("DieCup 1 with " + s1 + " and " + s2 + " sides is highest: " + wins1 + " times");
        System.out.println("DieCup 2 with " + s3 + " and " + s4 + " sides is highest: " + wins2 + " times");
        System.out.println("Same score in both: " + sameScore + " times");
    }
}
