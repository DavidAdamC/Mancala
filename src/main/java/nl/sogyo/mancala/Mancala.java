package nl.sogyo.mancala;

public class Mancala
{
    Player owner;
    Kalaha ownKalaha;
    int qStones;
    public static void main( String[] args ) {
        Mancala myMancala = new Mancala();

    }

    void Initialize() {
        //Player player1 = new Player();
        //Player player2 = new Player();

        Bowl startBowl = new Bowl();
    }
    public int getqStones() {
        return qStones;
    }

    public void setqStones(int num) {
        qStones = num;
    }

    public void addstone() {
        setqStones(qStones + 1);
    }



}
