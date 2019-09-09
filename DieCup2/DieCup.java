/**
 * This class models a DieCup (raflebaeger)
 * 
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 2.0
 **/
public class DieCup {
    private Die d1;   //first die
    private Die d2;   //second die
    private int maxEyes;   //Max count of eyes
    
    public DieCup(int sides1, int sides2) {
        d1 = new Die(sides1);
        d2 = new Die(sides2);
        maxEyes = 0;
    }
    
    public void roll() {
        d1.roll();
        d2.roll();
        
        // updates maxEyes if the new roll is bigger
        int newEyes = getEyes();
        if (maxEyes < newEyes) {
            maxEyes = newEyes;
        }
    }
    
    public int getEyes() {
        return d1.getEyes() + d2.getEyes();
    }
    
    public int getMaxEyes() {
        return maxEyes;
    }
    
    public void resetMaxEyes() {
        maxEyes = 0;
    }
    
    
}