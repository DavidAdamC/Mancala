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
