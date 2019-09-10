import java.util.ArrayList;

/**
 * Tester dieCup
 * 
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 1.0
 */
public class TestDriver {    
    public static void test(){
        ArrayList<Integer> newDies = new ArrayList<>(); 
        newDies.add(4); 
        newDies.add(6); 
        newDies.add(3); 
        newDies.add(8); 
        DieCup cup = new DieCup(newDies);
        cup.roll();
        System.out.println(cup.getEyes());
    }

    public static void testMulitple(ArrayList<Integer> newDies, int noOfRolls) {
        DieCup cup = new DieCup(newDies);
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

    public static void compareDieCups(int dc1, int dc2, int noOfRolls){
        // vi laver alle DieCups
        ArrayList dc1List = createArrayList(dc1);
        ArrayList dc2List = createArrayList(dc2);
        DieCup d1 = new DieCup(dc1List);
        DieCup d2 = new DieCup(dc2List);
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
        System.out.println("DieCup 1 with " + dc1List + " is highest: " + wins1 + " times");
        System.out.println("DieCup 2 with " + dc2List + " is highest: " + wins2 + " times");
        System.out.println("Same score in both: " + sameScore + " times");
    }

    public static void spiltDigits(int digits) {
        ArrayList list = new ArrayList<Integer>();
        while (digits > 0) {
            int dig = digits % 10;
            System.out.println(dig);
            digits = digits / 10;
        }        
    }

    public static ArrayList createArrayList(int digits) {
        System.out.println(digits);
        ArrayList list = new ArrayList<Integer>();
        while (digits > 0) {
            int dig = digits % 10;
            list.add(dig);
            digits = digits / 10;
        }
        return list;
    }
}
