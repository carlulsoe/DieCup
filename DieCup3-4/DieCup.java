import java.util.ArrayList;

/**
 * This class models a DieCup (raflebaeger)
 * 
 * @author Carl Ulsøe Christensen og Freja Østerbøg
 * @version 2019-09-08
 **/
public class DieCup {
    private int maxEyes;   //Max count of eyes
    ArrayList<Die> dies;

    public DieCup(int numberOfDies) {
        if (numberOfDies >= 1)  {
            dies = new ArrayList<>();
            for (int i = 0; i < numberOfDies; i++)  {
                dies.add(new Die());
            }
        }/*
        else if (numberOfDies == 0) {
            System.out.println("This is pointless");
        }
        else  {
            System.out.println("Really? Why are you even trying?");
        }*/
        maxEyes = 0;
    }

    public void roll() {
        for (int i = 0; i < dies.size(); i++) {
            dies.get(i).roll();
        }
        // updates maxEyes if the new roll is bigger
        int newEyes = getEyes();
        if (maxEyes < newEyes) {
            maxEyes = newEyes;
        }
    }

    public int getEyes() {
        int sum = 0;
        for (int i = 0; i < dies.size(); i++)   {
            sum += dies.get(i).getEyes();
        }
        return sum;
    }

    public int getMaxEyes() {
        return maxEyes;
    }

    public void resetMaxEyes() {
        maxEyes = 0;
    }

}