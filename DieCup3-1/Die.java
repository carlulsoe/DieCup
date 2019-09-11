// import class for generation of random numbers
import java.util.Random;

/**
 * This class models a Die
 * 
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 3.0
 */
public class Die {
    private Random random;   //gen. of random numbers
    private int eyes;        //number of eyes shown
    private int sides;
    //The constructor for the die class
    public Die(int noOfSides) {
        random = new Random();
        eyes = 0;
        // check if number of sides is greater than 1 otherwise it print an error message
        if (noOfSides >= 2) {
            sides = noOfSides;
        }
        else {
            System.out.println("Sides must be greater than 1");
        }
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
