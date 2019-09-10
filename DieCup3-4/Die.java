// import class for generation of random numbers
import java.util.Random;

/**
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 */

public class Die {
    private Random random;   //gen. of random numbers
    private int eyes;        //number of eyes shown
    private int sides;
    //The constructor for the die class
    public Die(int sides) {
        random = new Random();
        this.sides = sides;
    }
    
    //Rolling the die
    public void roll() {
        eyes = random.nextInt(sides) + 1;
    }

    //Get result from last roll
    public int getEyes() {
        return eyes;
    }
}
