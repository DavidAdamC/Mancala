package nl.sogyo.mancala;

public class Player {
    private String name;
    private boolean myTurn;
    private Player Opponent;
    private int result; // -1; spel niet klaar. 0 <= result <= 2: spel klaar. result = 0, 1, 2: resp. verloren, gelijk, gewonnen.

    public Player(String givenName, boolean givenMyTurn) {
        myTurn = givenMyTurn;
        name = givenName;
        result = -1;
    }

    public String getName() {
        return name;
    }

    public void setOpponent(Player Opp) {
        Opponent = Opp;
    }

    public Player getOpponent() {
        return Opponent;
    }

    public void setName(String gname) {
        name = gname;
    }

    public boolean hasTurn() {
        return(myTurn);
    }

    public void takeTurn() {
        myTurn = true;
    }

    public void giveTurn() {
        myTurn = false;
        getOpponent().takeTurn();
    }

    public void setResult(int res) {
        result = res;
    }

    public int getResult() {
        return result;
    }


    public void swapTurn() {
        myTurn = !myTurn;
        getOpponent().myTurn = !getOpponent().myTurn;
    }
}
