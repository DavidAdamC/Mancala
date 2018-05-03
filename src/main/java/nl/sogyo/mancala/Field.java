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
        //player1 = new Player();
        //player2 = new Player();
        startBowl = new Bowl();
        //player1.setName("Fred");
        //player2.setName("Barney");
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

    public void passStones(int qstones) {

    }



    public void makeNeighbour(Bowl root) {

        Field res;
        if(this.getDist() % 7 == 5) {
            res = new Kalaha();
            res.setDist(this.getDist() + 1);
            res.setOwner(this.getOwner());
            res.Initialize();
            this.setNeighbour(res);

            if(this.getDist() == 6) {
            //    makeNeighbour(root);
            }

        } else {

            res = new Bowl();
            res.setDist(this.getDist() + 1);
            if(res.getDist() == 7) {
                Player player2 = new Player();
                player2.setName("Barney");
                player2.setResult(-1);
                player2.setOpponent(this.getOwner());
                res.setOwner(player2);
            } else {
                res.setOwner(this.getOwner());
            }
            res.Initialize();
            ((Bowl) res).InitializeBowl();
            this.setNeighbour(res);
            //makeNeighbour(root);
            //res.makeBowl();
        }
    }


}
