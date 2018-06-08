package nl.sogyo.mancala;

public abstract class Field {
    private int dist;
    private Player player1;
    private Player player2;

    private Player Owner;

    Bowl startBowl;
    Field neighbour;

    private int qStones;
    public int getDist() {
        return(dist);
    }

    void Field() {
        Initialize();
    }

    void Initialize() {
        startBowl = new Bowl();
        dist = 0;

    }

    public int getqStones() {
        return qStones;
    }

    public void setqStones(int num) {
        qStones = num;
    }
    public void setDist(int gdist) {
        dist = gdist;
    }

    public Player getOwner() {
        return(Owner);
    }

    public void setOwner(Player player) {
        Owner = player;
    }

    public void setNeighbour(Field buur) {
        neighbour = buur;
    }

    public Field getNeighbour() {
        return neighbour;
    }

    public void addstone() {
        setqStones(qStones + 1);
    }

    public void passStones(int qstones) { }

    public void makeNeighbour(Bowl root) {

        Field res;
        Player eigenaar;
        if(this.getDist() == 6) {
            eigenaar = makePlayer();
        } else {
            eigenaar = this.getOwner();
        }
        if(this.getDist() % 7 == 5) {
            res = new Kalaha();
        } else {
            res = new Bowl();
            ((Bowl) res).InitializeBowl();
        }
        res.Initialize();
        res.setDist(this.getDist() + 1);
        res.setOwner(eigenaar);
        this.setNeighbour(res);
    }

    Player makePlayer() {
        Player player2 = new Player("Barney", false);
        player2.setName("Barney");
        player2.setResult(-1);
        player2.setOpponent(this.getOwner());
        return(player2);
    }
}
