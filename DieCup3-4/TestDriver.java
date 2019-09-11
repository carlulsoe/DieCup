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
            eyes += getEyes();
        }
        System.out.println(eyes/noOfRolls);
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
