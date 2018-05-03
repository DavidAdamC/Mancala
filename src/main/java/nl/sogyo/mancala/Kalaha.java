package nl.sogyo.mancala;

public class Kalaha extends Field{
    private Field neighbour;
    private int dist;
    private int qStones;
    private Player player1;
    private Player player2;

    private Player Owner;
    Bowl startBowl;
    void Initialize() {
        //player1 = new Player();
        //player2 = new Player();
        startBowl = new Bowl();

    }

    @Override
    public void passStones(int qstones) {
        System.out.println("From Kalaha: passStones: " + getOwner().getName() + " heeft " + getOwner().hasTurn() + " de beurt." + qstones);
        if(getOwner().hasTurn() == true) {
            addstone();
            if (qstones > 1) {
                this.getNeighbour().passStones(qstones - 1);
            } else {

            }
        } else {
            this.getNeighbour().passStones(qstones);
        }
    }

    public void addStonesFromTaken(int qTakenStones) {
        super.setqStones(super.getqStones() + qTakenStones);
    }
}
