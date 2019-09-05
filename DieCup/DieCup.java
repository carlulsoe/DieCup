//@author Freja Østerb1øg & Carl Ulsøe

public class DieCup {
    private Die d1;   //first die
    private Die d2;   //second die
    private int maxEyes;   //Max count of eyes

    public DieCup() {
        d1 = new Die();
        d2 = new Die();
        resetMaxEyes();
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

    public void multipleRolls(int noOfRolls) {
        if (noOfRolls == 0) {
            return;
        }
        // We make a new variable
        // which contains the sum of all the rolls
        double rollSum = 0.0;
        // Now we make a for loop to run roll noOfRolls times. 
        for (int i = 1; i <= noOfRolls; i++) {
            // We roll and saves the number of eyes rolled
            // in a temporary variable
            roll();
            int tempEyes = getEyes();
            // We then add the number of eyes to our sum
            // and print out what we rolled.
            rollSum += tempEyes;
            System.out.println("Throw no " + i + ": " + tempEyes);
        }
        // Then we output the average.
        double average = rollSum/noOfRolls;
        System.out.println("Average no of eyes: " + average);
    }
}