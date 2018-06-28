package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    @Test
    public void testApp() {
        Assert.assertTrue(true);
    }

    @Test
    public void TestFindsBuurman() {
        Bowl myBowl = new Bowl();
        myBowl.makeNeighbour(myBowl);
        Assert.assertTrue(myBowl.getNeighbour().getDist() == myBowl.getDist() + 1);
    }

    @Test
    public void TestFindsOpposite() {
        Bowl myBowl = new Bowl(true);
        for (int i = 0; i < 3; i++) {
            myBowl = (Bowl) myBowl.getNeighbour();
        }

        Assert.assertTrue(myBowl.getDist() + myBowl.getOpposite().getDist() == 12);
    }

    @Test
    public void TestQStones() {
        Bowl myBowl = new nl.sogyo.mancala.Bowl();
        myBowl.setqStones(4);

        Assert.assertTrue(myBowl.getqStones() == 4);
    }

    @Test
    public void TestPlayerOwner() {
        Bowl myBowl = new Bowl(true);
        Player player1 = new Player("Fred", false);
        myBowl.setOwner(player1);
        Assert.assertTrue("Fred".equals(myBowl.getOwner().getName()));
    }

    @Test
    public void TestMakeField() {
        Bowl myBowl = new Bowl(true);

        Field me = myBowl;
        for (int i = 0; i < 14; i++) {
            me = me.getNeighbour();
        }
        Assert.assertTrue(myBowl == me);

    }

    @Test
    public void TestGetMyKalaha() {
        Bowl myBowl = new Bowl(true);
        Kalaha myKalaha = myBowl.getMyKalaha();
        Assert.assertTrue(myBowl.getOwner() == myKalaha.getOwner());
    }

    @Test
    public void ChosenBowlClearsItself() {
        Bowl myBowl = new Bowl(true);
        myBowl.choose(0);
        Assert.assertEquals(0, myBowl.getqStones());
    }

    @Test
    public void ChosenBowlGivesToNeighbour() {
        Bowl myBowl = new Bowl(true);
        myBowl.choose(0);
        Assert.assertEquals(5, myBowl.getNeighbour().getqStones());
    }

    @Test
    public void ChosenBowlGivesToOwnKalaha() {
        Bowl myBowl = new Bowl(true);
        for (int i = 0; i < 2; i++) {
            myBowl = (Bowl) myBowl.getNeighbour();
        }
        myBowl.choose(0);
        Assert.assertEquals(1, myBowl.getMyKalaha().getqStones());
    }

    @Test
    public void BowlAfterKalahaDoesntGetStoneWhenNotNeededTo() {
        Bowl myBowl = new Bowl(true);
        myBowl.choose(0);
        Assert.assertEquals(4, myBowl.getMyKalaha().getNeighbour().getqStones());
    }


    @Test
    public void TestPassStonesEndInOwnNonEmptyBowl() {
        Bowl myBowl = new Bowl(true);
        Bowl myEnd = myBowl;
        for (int i = 0; i < myBowl.getqStones(); i++) {
            myEnd = (Bowl) myEnd.getNeighbour();
        }
        myBowl.choose(0);
        Assert.assertEquals(5, myEnd.getqStones());
    }

    @Test
    public void TestTurnHoldsWhenEndingInOwnKalaha() {
        Bowl myBowl = new Bowl(true);
        for (int i = 0; i < 2; i++) {
            myBowl = (Bowl) myBowl.getNeighbour();
        }
        myBowl.choose(0);
        Assert.assertTrue(myBowl.getOwner().hasTurn());

    }

    @Test
    public void TestOpponentGetsTurn() {
        Bowl myBowl = new Bowl(true);
        Field myOppBowl = myBowl;

        myBowl.makeField();
        for (int i = 0; i < 7; i++) {
            myOppBowl = myOppBowl.getNeighbour();
        }

        myBowl.getOwner().setOpponent(myOppBowl.getOwner());
        myOppBowl.getOwner().setOpponent(myBowl.getOwner());
        Assert.assertTrue("Barney".equals(myBowl.getOwner().getOpponent().getName()));
        myBowl.getOwner().giveTurn();
        Assert.assertTrue(myBowl.getOwner().getOpponent().hasTurn());
    }

    @Test
    public void TestOpponentKalahaDoesntTakeStonesOnMyTurn() {
        Bowl myBowl = new Bowl(true);
        myBowl.setqStones(15);
        myBowl.choose(0);
        Field myOppBowl = (Bowl) myBowl.getMyKalaha().getNeighbour();
        Assert.assertEquals(0, ((Bowl) myOppBowl).getMyKalaha().getqStones());
        myBowl.printBowlgetqStones();
    }

    @Test
    public void TestRejectsChosenKalaha() {
        Bowl myBowl = new Bowl(true);
        myBowl.choose(6);
    }

    @Test
    public void TestEenAantalZetten() {
        int[] myIntScores = new int[]{0, 5, 5, 5, 0, 5, 8, 0, 0, 5, 5, 5, 5, 0};
        Bowl myBowl = new Bowl(true);
        myBowl.choose(4);
        myBowl.printBowlgetqStones();
        myBowl.choose(7);
        myBowl.printBowlgetqStones();
        myBowl.choose(0);
        myBowl.printBowlgetqStones();
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestGameHasEnded() {
        int[] myIntScores = new int[]{0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 24};
        int[] myMoves = new int[]{2, 4, 8, 7, 0, 11, 2, 10, 2, 12, 4, 11, 2, 9, 0, 11, 1, 9, 3, 11, 10, 4, 2, 8, 1, 7, 4, 8, 3, 9, 11, 10, 0, 12, 3};
        Bowl myBowl = new Bowl(true);
        myBowl.playMoves(myMoves);
        myBowl.printBowlgetqStones();
        Assert.assertTrue(myBowl.gameHasEnded());
    }

    @Test
    public void TestAfterGameEndedStonesAreStolen() {
        int[] myIntScores = new int[]{0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 24};
        int[] myMoves = new int[]{2, 4, 8, 7, 0, 11, 2, 10, 2, 12, 4, 11, 2, 9, 0, 11, 1, 9, 3, 11, 10, 4, 2, 8, 1, 7, 4, 8, 3, 9, 11, 10, 0, 12, 3};
        Bowl myBowl = new Bowl(true);
        myBowl.playMoves(myMoves);
        myBowl.printBowlgetqStones();

        if (!myBowl.getOwner().hasTurn()) {
            myBowl = (Bowl) myBowl.getMyKalaha().getNeighbour();
        }
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestEenPaarZetten() {
        int[] myIntScores = new int[]{4, 4, 0, 0, 6, 6, 2, 5, 5, 4, 4, 4, 4, 0};
        int[] myMoves = new int[]{2, 3, 4}; // speler kiest niet een eigen bowl.
        Bowl myBowl = new Bowl(true);
        myBowl.playMoves(myMoves);
        myBowl.printBowlgetqStones();
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestEenPartij() {
        int[] myIntScores = new int[]{0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 24};
        int[] myMoves = new int[]{2, 4, 8, 7, 0, 11, 2, 10, 2, 12, 4, 11, 2, 9, 0, 11, 1, 9, 3, 11, 10, 4, 2, 8, 1, 7, 4, 8, 3, 9, 11, 10, 0, 12, 3};
        Bowl myBowl = new Bowl(true);
        //myBowl.playMoves(myMoves);
        myBowl.choose(2);
        myBowl.choose(4);
        myBowl.choose(8);
        myBowl.choose(7);
        myBowl.choose(0);
        myBowl.choose(11);
        myBowl.choose(2);
        myBowl.choose(10);
        myBowl.choose(2);
        myBowl.choose(12);
        myBowl.choose(4);
        myBowl.choose(11);
        myBowl.choose(2);
        myBowl.choose(9);
        myBowl.choose(0);
        myBowl.choose(11);
        myBowl.choose(1);
        myBowl.choose(9);
        myBowl.choose(3);
        myBowl.choose(11);
        myBowl.choose(10);
        myBowl.choose(4);
        myBowl.choose(2);
        myBowl.choose(8);
        myBowl.choose(1);
        myBowl.choose(7);
        myBowl.choose(4);
        myBowl.choose(8);
        myBowl.choose(3);
        myBowl.choose(9);
        myBowl.choose(11);
        myBowl.choose(10);
        myBowl.choose(0);
        myBowl.choose(12);
        myBowl.choose(3);



        myBowl.printBowlgetqStones();
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestEenPartijMetOngeldigeZet() {
        int[] myIntScores = new int[]{4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
        int[] myMoves = new int[]{2, 4, 8, 7, 0, 11, 2, 10, 2, 12, 4, 11, 2, 9, 0, 11, 1, 9, 3, 11, 10, 4, 2, 8, 1, 7, 4, 8, 3, 9, 11, 10, 0, 12, 3};
        Bowl myBowl = new Bowl(true);
        //myBowl.playMoves(myMoves);
        myBowl.choose(7);


        myBowl.printBowlgetqStones();
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestEenPartijMetVetVeelOngeldigeZetten() {
        int[] myIntScores = new int[]{0, 5, 5, 5, 5, 4, 0, 0, 5, 5, 5, 5, 4, 0};
        int[] myMoves = new int[]{0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12};
        Bowl myBowl = new Bowl(true);
        myBowl.playMoves(myMoves);



        myBowl.printBowlgetqStones();
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestDeclareWinner() {
        int[] myIntScores = new int[]{0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 24};
        int[] myMoves = new int[]{2, 4, 8, 7, 0, 11, 2, 10, 2, 12, 4, 11, 2, 9, 0, 11, 1, 9, 3, 11, 10, 4, 2, 8, 1, 7, 4, 8, 3, 9, 11, 10, 0, 12, 3};
        Bowl myBowl = new Bowl(true);
        myBowl.playMoves(myMoves);
        Assert.assertEquals(1, myBowl.getOwner().getResult());
    }

    @Test
    public void TestEenAnderePartij() {
        int[] myIntScores = new int[]{0, 0, 0, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 15};
        int[] myMoves = new int[]{2, 5, 7, 1, 8, 0, 9, 1, 12, 5, 2, 7, 1, 11, 5, 4, 12};
        Bowl myBowl = new Bowl(true);
        myBowl.playMoves(myMoves);
        myBowl.printBowlgetqStones();
        Field myRunner = myBowl;
        for (int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], myRunner.getqStones());
            myRunner = myRunner.getNeighbour();
        }
    }

    @Test
    public void TestMancalaFacadeWinnerState() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        Assert.assertEquals("-1;-1", mancalaFacade.getWinnerState());
    }

    @Test
    public void TestMancalaFacadeInitialBoardState() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        int[] myIntScores = new int[]{4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
        for(int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], mancalaFacade.getBoardState()[i]);
        }
    }

    @Test
    public void TestMancalaFacadeAfterOneSpecificMove() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        mancalaFacade.playMove(2);
        int[] myIntScores = new int[]{4, 4, 0, 5, 5, 5, 1, 4, 4, 4, 4, 4, 4, 0};
        for(int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], mancalaFacade.getBoardState()[i]);
        }
    }

    @Test
    public void TestMancalaFacadeAfterOneSpecificWrongMove() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        mancalaFacade.playMove(7);
        int[] myIntScores = new int[]{4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
        for(int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], mancalaFacade.getBoardState()[i]);
        }
    }

    @Test
    public void TestFacadeReturnsPlayerTurn() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        mancalaFacade.playMove(1);
        Assert.assertEquals(1, mancalaFacade.getBoardState()[14]);
    }

     /*
    @Test
    public void TestMancalaFacadeAfterOneSpecificMove() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        mancalaFacade.playMove(2);
        int[] myIntScores = new int[]{4, 4, 0, 5, 5, 5, 1, 4, 4, 4, 4, 4, 4, 0};
        for(int i = 0; i < 14; i++) {
            Assert.assertEquals(myIntScores[i], mancalaFacade.getBoardState()[i]);
        }
    }




    @Test
    public void TestMancalaFacadeOpponentGetsTurn() {
        MancalaFacade mancalaFacade = new MancalaFacade();
        mancalaFacade.playMove(2);
        Assert.assertEquals(mancalaFacade.playerWithTurn(), "Barney");
    }
    */
}
