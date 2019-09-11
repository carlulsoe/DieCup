import java.util.ArrayList;

/**
 * Tester dieCup
 * 
 * @author Carl Uls�e Christensen og Freja �sterb�g
 * @version 2.0
 */
public class TestDriver   {    
    public static void test4638(int numberOfRolls){
        ArrayList<Integer> newDies = new ArrayList<>(); 
        newDies.add(4); 
        newDies.add(6); 
        newDies.add(3); 
        newDies.add(8); 
        DieCup cup = new DieCup(newDies);
        cup.roll();
        System.out.println(cup.getEyes());
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
