package nl.sogyo.mancala;

public class MancalaFacade {
    private Bowl myBowl;
    private boolean gameEnded;
    private String winnerState;
    private int[] boardState;
    private String playerTurn;

    public MancalaFacade() {
        myBowl = new Bowl(true);
        gameEnded = false;
        winnerState = "-1;-1";
        boardState = new int[15];
        updateBoard();
    }

    public int[] getBoardState() {
        return boardState;
    }

    public void playMove(int bowlnr) {
        //System.out.println(bowlnr);
        myBowl.choose(bowlnr);
        updateBoard();
    }

    private void updateBoard() {
        Field myRunner = myBowl;
        for(int i = 0; i < 14; i++) {
            boardState[i] = myRunner.getqStones();
            myRunner = myRunner.getNeighbour();
        }
        UpdateResult();
        boardState[14] = getplayerTurn();
    }

    private void UpdateResult() {
        if (myBowl.getOwner().getResult() >= 0) {
            gameEnded = true;
            winnerState = myBowl.getOwner().getResult() + ";" + myBowl.getOwner().getOpponent().getResult();
        }
    }

    public int getplayerTurn() {
        
        if(myBowl.getOwner().hasTurn() == true) {
            //return(myBowl.getOwner().getName());
        	return(0);
        } else if (myBowl.getOwner().hasTurn() == false) {
            //return(myBowl.getOwner().getOpponent().getName());
        	return(1);
        }
        return(0);
    }

    public String getWinnerState() {
        return(winnerState);
    }


}
