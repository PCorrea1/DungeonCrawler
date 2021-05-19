package src;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
//import src.InitialGameScreen;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class RandomizationTests {
    private InitialGameScreen igs;
    private int[][] nums = new int[24][4];

    @Before
    public void setup() {
        //my_stack = new MyStack();
        nums[0] = new int[]{1, 2, 3, 4};
        nums[1] = new int[]{1, 2, 4, 3};
        nums[2] = new int[]{1, 3, 2, 4};
        nums[3] = new int[]{1, 3, 4, 2};
        nums[4] = new int[]{1, 4, 3, 2};
        nums[5] = new int[]{1, 4, 2, 3};
        nums[6] = new int[]{2, 1, 3, 4};
        nums[7] = new int[]{2, 1, 4, 3};
        nums[8] = new int[]{2, 3, 1, 4};
        nums[9] = new int[]{2, 3, 4, 1};
        nums[10] = new int[]{2, 4, 3, 1};
        nums[11] = new int[]{2, 4, 1, 3};
        nums[12] = new int[]{3, 4, 2, 1};
        nums[13] = new int[]{3, 4, 1, 2};
        nums[14] = new int[]{3, 2, 1, 4};
        nums[15] = new int[]{3, 2, 4, 1};
        nums[16] = new int[]{3, 1, 2, 4};
        nums[17] = new int[]{3, 1, 4, 2};
        nums[18] = new int[]{4, 2, 3, 1};
        nums[19] = new int[]{4, 2, 1, 3};
        nums[20] = new int[]{4, 1, 3, 2};
        nums[21] = new int[]{4, 1, 2, 3};
        nums[22] = new int[]{4, 3, 2, 1};
        nums[23] = new int[]{4, 3, 1, 2};

        igs = new InitialGameScreen();
    }

    @Test
    public void generateRandomPathSequenceIndexBounds() {
        int randNum = igs.generateRandIndexPathSequence();
        // upper bound 23
        assertTrue(randNum <= 23);
        // lower bound 0
        assertTrue(randNum >= 0);
    }

    @Test
    public void selectPathSequenceCorrespondingToRandNum() {
        int randNum = igs.generateRandIndexPathSequence();
        int[] path = igs.randomizePaths(randNum);
        assertArrayEquals(path, nums[randNum]);
    }
    @Test
    public void testPreparePaths() {
        int randNum = igs.generateRandIndexPathSequence();
        int[] path = igs.randomizePaths(randNum);
        Path[] paths = igs.preparePaths(path);
        Path[] pathsCorrect = new Path[4];
        for (int i = 0; i < path.length; i++) {
            pathsCorrect[i] = new Path(path[i]);
        }
        for (int i = 0; i < path.length; i++) {
            assertEquals(paths[i].getNumber(), pathsCorrect[i].getNumber());
        }
    }

}
