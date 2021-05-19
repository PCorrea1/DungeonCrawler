package src;
import java.util.Random;

public class RandIntGen {


    /* Random Int Generator */
    public RandIntGen() {
    }

    public int getRandInt(int lowerBound, int range) {
        Random random = new Random();
        int randInt = random.nextInt(range) + lowerBound;
        return randInt;
    }

    /* Rand Int Gen Test */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            RandIntGen rig = new RandIntGen();
            int num = rig.getRandInt(4, 10);
            System.out.println(num);
        }
    }
}
