package nl.sogyo.mancala;

public class Player {
    private String name;
    private boolean myTurn;
    private Player Opponent;
    private int result; // -1; spel niet klaar. 0 <= result <= 2: spel klaar. result = 0, 1, 2: resp. verloren, gelijk, gewonnen.


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
        System.out.println("From Player: swapTurn: Swapping turns...");
        myTurn = !myTurn;
        getOpponent().myTurn = !getOpponent().myTurn;
        if(this.hasTurn()) {
            System.out.println("From Player: swapTurn: Swapping turns... " + this.getName() + " has Turn.");
        } else {
            System.out.println("From Player: swapTurn: Swapping turns... " + this.getOpponent().getName() + " has Turn.");
        }
    }
}
