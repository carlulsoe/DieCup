import java.util.ArrayList;

/**
 * Tester dieCup
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 2.0
 */

public class TestDriver {    
    public static void test4638(int noOfRolls){
        ArrayList<Integer> newDies = new ArrayList<>(); 
        newDies.add(4); 
        newDies.add(6); 
        newDies.add(3); 
        newDies.add(8); 
        DieCup cup = new DieCup(newDies);
        double eyes = 0;
        for (int i = 0; i < noOfRolls; i++) {
            cup.roll();
            eyes += cup.getEyes();
        }
        System.out.println(eyes/noOfRolls);
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
