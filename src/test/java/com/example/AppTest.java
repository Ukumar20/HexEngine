package com.example;

import com.example.base.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class to execute all the test cases
 */
public class AppTest extends BaseTest {

    @BeforeAll
    public static void setup(){
        BaseTest.setup();
    }

    @Test
    public void testRedPlaysFirstAndWins() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 10,10},
                {"OK", "RED", "move", 1,0},
                {"OK", "BLUE", "move", 10,9},
                {"OK", "RED", "move", 2,0},
                {"OK", "BLUE", "move", 10,8},
                {"OK", "RED", "move", 3,0},
                {"OK", "BLUE", "move", 10,7},
                {"OK", "RED", "move", 4,0},
                {"OK", "BLUE", "move", 10,6},
                {"OK", "RED", "move", 5,0},
                {"OK", "BLUE", "move", 10,5},
                {"OK", "RED", "move", 6,0},
                {"OK", "BLUE", "move", 10,4},
                {"OK", "RED", "move", 7,0},
                {"OK", "BLUE", "move", 10,3},
                {"OK", "RED", "move", 8,0},
                {"OK", "BLUE", "move", 10,2},
                {"OK", "RED", "move", 9,0},
                {"OK", "BLUE", "move", 10,1},
                {"WON", "RED", "move", 10,0}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testBluePlaysFirstAndWins() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 10,10},
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 10,9},
                {"OK", "RED", "move", 1,0},
                {"OK", "BLUE", "move", 10,8},
                {"OK", "RED", "move", 2,0},
                {"OK", "BLUE", "move", 10,7},
                {"OK", "RED", "move", 3,0},
                {"OK", "BLUE", "move", 10,6},
                {"OK", "RED", "move", 4,0},
                {"OK", "BLUE", "move", 10,5},
                {"OK", "RED", "move", 5,0},
                {"OK", "BLUE", "move", 10,4},
                {"OK", "RED", "move", 6,0},
                {"OK", "BLUE", "move", 10,3},
                {"OK", "RED", "move", 7,0},
                {"OK", "BLUE", "move", 10,2},
                {"OK", "RED", "move", 8,0},
                {"OK", "BLUE", "move", 10,1},
                {"OK", "RED", "move", 9,0},
                {"WON", "BLUE", "move", 10,0},
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testRedPlaysSecondAndWins() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 10,10},
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 10,9},
                {"OK", "RED", "move", 1,0},
                {"OK", "BLUE", "move", 10,8},
                {"OK", "RED", "move", 2,0},
                {"OK", "BLUE", "move", 10,7},
                {"OK", "RED", "move", 3,0},
                {"OK", "BLUE", "move", 10,6},
                {"OK", "RED", "move", 4,0},
                {"OK", "BLUE", "move", 10,5},
                {"OK", "RED", "move", 5,0},
                {"OK", "BLUE", "move", 10,4},
                {"OK", "RED", "move", 6,0},
                {"OK", "BLUE", "move", 10,3},
                {"OK", "RED", "move", 7,0},
                {"OK", "BLUE", "move", 10,2},
                {"OK", "RED", "move", 8,0},
                {"OK", "BLUE", "move", 10,1},
                {"OK", "RED", "move", 9,0},
                {"OK", "BLUE", "move", 9,1},
                {"WON", "RED", "move", 10,0},
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testBluePlaysSecondAndWins() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 10,10},
                {"OK", "RED", "move", 1,0},
                {"OK", "BLUE", "move", 10,9},
                {"OK", "RED", "move", 2,0},
                {"OK", "BLUE", "move", 10,8},
                {"OK", "RED", "move", 3,0},
                {"OK", "BLUE", "move", 10,7},
                {"OK", "RED", "move", 4,0},
                {"OK", "BLUE", "move", 10,6},
                {"OK", "RED", "move", 5,0},
                {"OK", "BLUE", "move", 10,5},
                {"OK", "RED", "move", 6,0},
                {"OK", "BLUE", "move", 10,4},
                {"OK", "RED", "move", 7,0},
                {"OK", "BLUE", "move", 10,3},
                {"OK", "RED", "move", 8,0},
                {"OK", "BLUE", "move", 10,2},
                {"OK", "RED", "move", 9,0},
                {"OK", "BLUE", "move", 10,1},
                {"OK", "RED", "move", 9,1},
                {"WON", "BLUE", "move", 10,0}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testInvalidMoveFromRed() throws Exception {
        Object[][] tuples = {
                {"INVALID", "RED", "move", -1,0}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testInvalidMoveFromBlue() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 1,1},
                {"OK", "RED", "move", 2,2},
                {"INVALID", "BLUE", "move", 99,99}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotEmptyMoveFromBlue() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 0,4},
                {"OK", "RED", "move", 0,1},
                {"OK", "BLUE", "move", 0,3},
                {"OK", "RED", "move", 0,2},
                {"NOT_EMPTY", "BLUE", "move", 0,2}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotEmptyMoveFromRed() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"OK", "RED", "move", 0,4},
                {"OK", "BLUE", "move", 0,1},
                {"OK", "RED", "move", 0,3},
                {"OK", "BLUE", "move", 0,2},
                {"NOT_EMPTY", "RED", "move", 0,2}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotYourTurnInFirstMoveForRed() throws Exception {
        Object[][] tuples = {
                {"NOT_YOUR_TURN", "RED", "move", 0,0}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotYourTurnInFirstMoveForBlue() throws Exception {
        Object[][] tuples = {
                {"NOT_YOUR_TURN", "BLUE", "move", 0,0}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotYourTurnAfterFewMovesForRedPlayingFirst() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 0,10},
                {"OK", "RED", "move", 0,1},
                {"OK", "BLUE", "move", 0,9},
                {"OK", "RED", "move", 0,2},
                {"NOT_YOUR_TURN", "RED", "move", 0,8}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotYourTurnAfterFewMovesForBluePlayingFirst() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"OK", "RED", "move", 0,10},
                {"OK", "BLUE", "move", 0,1},
                {"OK", "RED", "move", 0,9},
                {"OK", "BLUE", "move", 0,2},
                {"NOT_YOUR_TURN", "BLUE", "move", 0,8}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotYourTurnAfterFewMovesForBluePlayingSecond() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 0,10},
                {"OK", "RED", "move", 0,1},
                {"OK", "BLUE", "move", 0,9},
                {"NOT_YOUR_TURN", "BLUE", "move", 0,2}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotYourTurnAfterFewMovesForRedPlayingSecond() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"OK", "RED", "move", 0,10},
                {"OK", "BLUE", "move", 0,1},
                {"OK", "RED", "move", 0,9},
                {"NOT_YOUR_TURN", "RED", "move", 0,2}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotApplicableSwapForRedPlayingFirst() throws Exception {
        Object[][] tuples = {
                {"NOT_APPLICABLE", "RED", "swap"}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotApplicableSwapForBluePlayingFirst() throws Exception {
        Object[][] tuples = {
                {"NOT_APPLICABLE", "BLUE", "swap"}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotApplicableSwapForRedPlayingSecond() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"NOT_APPLICABLE", "RED", "swap"}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotApplicableSwapForBluePlayingSecond() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"NOT_APPLICABLE", "BLUE", "swap"}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotApplicableSwapForRedPlayingFirstWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"NOT_APPLICABLE", "RED", "swap"}
        };
        triggerAutoPlay("RED", true, tuples);
    }

    @Test
    public void testNotApplicableSwapForBluePlayingFirstWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"NOT_APPLICABLE", "BLUE", "swap"}
        };
        triggerAutoPlay("BLUE", true, tuples);
    }

    @Test
    public void testNotApplicableSwapForRedWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"OK", "BLUE", "move", 0,10},
                {"NOT_APPLICABLE", "RED", "swap"}
        };
        triggerAutoPlay("RED", true, tuples);
    }

    @Test
    public void testNotApplicableSwapForBlueWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"OK", "RED", "move", 0,10},
                {"NOT_APPLICABLE", "BLUE", "swap"}
        };
        triggerAutoPlay("BLUE", true, tuples);
    }


    @Test
    public void testSwapForRedWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,2},
                {"OK", "RED", "swap"},
                {"OK", "BLUE", "move", 0,2},
                {"OK", "RED", "move", 5,0}
        };
        triggerAutoPlay("BLUE", true, tuples);
    }

    @Test
    public void testSwapForBlueWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,2},
                {"OK", "BLUE", "swap"},
                {"OK", "RED", "move", 0,2},
                {"OK", "BLUE", "move", 5,0}
        };
        triggerAutoPlay("RED", true, tuples);
    }

    @Test
    public void testNotApplicableSecondSwapForRedWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,2},
                {"OK", "RED", "swap"},
                {"OK", "BLUE", "move", 0,2},
                {"NOT_APPLICABLE", "RED", "swap"}
        };
        triggerAutoPlay("BLUE", true, tuples);
    }

    @Test
    public void testNotApplicableSecondSwapForBlueWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,2},
                {"OK", "BLUE", "swap"},
                {"OK", "RED", "move", 0,2},
                {"NOT_APPLICABLE", "BLUE", "swap"}
        };
        triggerAutoPlay("RED", true, tuples);
    }

    @Test
    public void testNotYourTurnToSwapForRedWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"NOT_YOUR_TURN", "RED", "swap"}
        };
        triggerAutoPlay("RED", true, tuples);
    }

    @Test
    public void testNotYourTurnToSwapForBlueWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"NOT_YOUR_TURN", "BLUE", "swap"}
        };
        triggerAutoPlay("BLUE", true, tuples);
    }

    @Test
    public void testNotAuthenticMoveFromRed() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"NOT_AUTHENTIC", "RED", "move", 5,5,1234}
        };
        triggerAutoPlay("BLUE", false, tuples);
    }

    @Test
    public void testNotAuthenticMoveFromBlue() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"NOT_AUTHENTIC", "BLUE", "move", 5,5,1234}
        };
        triggerAutoPlay("RED", false, tuples);
    }

    @Test
    public void testNotAuthenticMoveFromRedWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "BLUE", "move", 0,0},
                {"NOT_AUTHENTIC", "RED", "swap", 1234}
        };
        triggerAutoPlay("BLUE", true, tuples);
    }

    @Test
    public void testNotAuthenticMoveFromBlueWithSwapEnabled() throws Exception {
        Object[][] tuples = {
                {"OK", "RED", "move", 0,0},
                {"NOT_AUTHENTIC", "BLUE", "swap", 1234}
        };
        triggerAutoPlay("RED", true, tuples);
    }
}
