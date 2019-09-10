import java.util.ArrayList;

/**
 * Tester dieCup
 * 
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 1.0
 */
public class TestDriver   {    
    public static void test4638(int numberOfRolls){
        ArrayList<Integer> newDies = new ArrayList<>(); 
        newDies.add(4); 
        newDies.add(6); 
        newDies.add(3); 
        newDies.add(8); 
        DieCup cup = new DieCup(newDies);
        
        double sum = 0.0;
        for (int i = 0; i < numberOfRolls; i++) {
            cup.roll();
            sum += cup.getEyes();
            System.out.println("Throw no " + (i+1) + ": "+ cup.getEyes());
        }
        System.out.println(sum/numberOfRolls);
        
    }
}
