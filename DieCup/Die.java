// import class for generation of random numbers
import java.util.Random;

/**
 * This class models a Die (terning)
 * 
 * @author Kurt Jensen
 * @version 2017-01-05
 */
import java.util.Random;
public class Die {
    private Random random;   //gen. of random numbers
    private int eyes;        //number of eyes shown

    //The constructor for the die class
    public Die() {
        random = new Random();
        eyes = 0;
    }
    
    //Rolling the die
    public void roll() {
        eyes = random.nextInt(6) + 1;
    }

    //Get result from last roll
    public int getEyes() {
        return eyes;
    }
}
