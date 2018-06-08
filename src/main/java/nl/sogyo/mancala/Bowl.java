package nl.sogyo.mancala;

public class Bowl extends Field {
    Player player1;
    Player player2;
    //Player
    int qStones;
    private int dist;
    Bowl Opposite;

    public void setDist(int gdist) {
        dist = gdist;
    }

    public int getDist() {
        return dist;
    }

    public void addstone() {
        setqStones(getqStones() + 1);
    }

    public Bowl() {
        setqStones(4);
    }

    public Bowl (boolean mustMakeField) {
        setqStones(4);
        if(mustMakeField) {
            makeField();
        }
    }

    void InitializeBowl() {
        setqStones(4);
    }


    public void makeField() {
        Field myField = this;
        Player player1 = new Player("Fred", true);
        player1.takeTurn();
        player1.setName("Fred");
        player1.setResult(-1);
        myField.setOwner(player1);
       ((Bowl) myField).InitializeBowl();
        Bowl root = (Bowl)this;
        for(int i = 1; i <= 13; i++) {
            myField.makeNeighbour(root);
            myField = myField.getNeighbour();
            //System.out.println("From Bowl: MakeField: " + myField.getDist() + "  " + myField.getqStones() + " " + myField.getOwner().getName());
        }
        myField.setNeighbour(root);
        player1.setOpponent(myField.getOwner());
        myField = myField.getNeighbour();

    }

    public Bowl getOpposite() {
        Field myField = this;
        int steps = 0;
        for (int i = 0; i < 6; i++) {
            myField = myField.getNeighbour();
            if(myField.getClass().getName().equals("nl.sogyo.mancala.Kalaha")) {
                steps = i; break;
            }
        }

        for(int i = 0; i < steps + 1; i++) {
            myField = myField.getNeighbour();
        }
        Bowl Opposite = (Bowl)myField;
        return Opposite;
    }

    public Kalaha getMyKalaha() {
        Field myField = this;
        Kalaha myKalaha;
        for (int i = 0; i < 6; i++) {
            myField = myField.getNeighbour();
            if(myField.getClass().getName().equals("nl.sogyo.mancala.Kalaha")) {
                myKalaha = (Kalaha) myField;
                return(myKalaha);
            }
        }
        return(null);
    }

    public void printBowlgetqStones() {
        Field myCopy = this;
        for(int i = 0; i < 14; i++) {
            System.out.println("From Bowl: printBowlgetqStones: " + myCopy.getOwner().getName() + " " + myCopy.getDist() + " " + myCopy.getqStones());
            myCopy = myCopy.getNeighbour();
        }
    }

    public void passStones(int qpassStones) {
        //System.out.println("Stones to pass: " + qpassStones);
        if(qpassStones == 1) {
            this.addstone();

            if (this.getOwner().hasTurn()) {
                if(this.getqStones() == 1) {
                    this.setqStones(0);
                    this.getMyKalaha().addstone();
                    // sla steentjes aan de andere kant en doe ze in eigen kalaha
                    this.slaSteentjesvanOverliggendeVeld();

                }
            }
            this.getOwner().swapTurn();
        } else if(qpassStones > 0) {
            this.addstone();
            this.getNeighbour().passStones(qpassStones - 1);
        }
    }

    public void choose(int bowlnr) {
        Bowl chosenBowl = this;


        if(chooseKalaha(bowlnr)) {
            System.out.println("Je hebt een Kalaha gekozen. Kies a.j.b. een bowl."); return;
        }
        if(bowlnr > 6) {
            chosenBowl = (Bowl)chosenBowl.getMyKalaha().getNeighbour();
            bowlnr -= 7;
        }

        for(int i = 0; i < bowlnr; i++) {
            chosenBowl = (Bowl)chosenBowl.getNeighbour();
        }

        if(!eigenaarVanBowlHeeftBeurt(chosenBowl)) {
            System.out.println("Dit is de bowl van je tegenstander. Kies a.j.b. een van je eigen bowls.");
        }

        if(chosenBowl.getqStones() > 0) {
            chosenBowl.getNeighbour().passStones(chosenBowl.getqStones());
            chosenBowl.setqStones(0);
            if(gameHasEnded()) {
                System.out.println("Spel is klaar!");
                ((Bowl)this.getMyKalaha().getNeighbour()).slaSteentjesVanTegenstander();
                this.declareWinner();
            }
        } else {
            System.out.println("Je mag me niet kiezen; ik heb geen stenen.");
        }
    }

    private boolean chooseKalaha(int bowlNr) {
        return((Boolean) (bowlNr % 7 == 6));
    }

    private boolean eigenaarVanBowlHeeftBeurt(Bowl bowl) {
        return(bowl.getOwner().hasTurn());
    }

    private void slaSteentjesvanOverliggendeVeld() {
        this.getMyKalaha().addStonesFromTaken(this.getOpposite().getqStones());
        this.getOpposite().setqStones(0);
    }

    public void playMoves(int[] myMoves) {
        for(int i = 0; i < myMoves.length; i++) {
            this.choose(myMoves[i]);
        }
    }

    private void slaSteentjesVanTegenstander() {
        /* als deze methode wordt aangeroepen, moet deze speler wel aan de beurt zijn,
        maar laten we het 'double dutch' dubbelchecken */

        if(this.getOwner().hasTurn()) {
            Bowl myCopy = (Bowl)this.getMyKalaha().getNeighbour();
            System.out.println("Mijn afstand tot origin is: " + myCopy.getDist());
            for (int i = 0; i < 5; i++) {
                myCopy.getMyKalaha().addStonesFromTaken(myCopy.getqStones());
                myCopy.setqStones(0);
                myCopy = (Bowl)myCopy.getNeighbour();
            }
            myCopy.getMyKalaha().addStonesFromTaken(myCopy.getqStones());
            myCopy.setqStones(0);
        } else {
            System.out.println("Ik doe niets!");
        }
    }

    public boolean gameHasEnded() {
        Bowl myCopy = this;
        if(!this.getOwner().hasTurn()) {
            myCopy = (Bowl) myCopy.getMyKalaha().getNeighbour();
        }
        for(int i = 0; i < 5; i++) {
            if(myCopy.getqStones() > 0) {
                return(false);
            }
            myCopy = ((Bowl) myCopy.getNeighbour());
        }
        if(myCopy.getqStones() > 0) {
            return(false);
        }
        return(true);
    }

    private void declareWinner() {
        Player player1 = this.getOwner();
        Player player2 = this.getOwner().getOpponent();
        int player1Stones = this.getMyKalaha().getqStones();
        int player2Stones = ((Bowl)this.getMyKalaha().getNeighbour()).getMyKalaha().getqStones();
        if(player1Stones < player2Stones) {
            System.out.println(player2.getName() + " heeft gewonnen!");
            player2.setResult(2);
            player1.setResult(0);
        } else if(player1Stones > player2Stones) {
            System.out.println(player1.getName() + " heeft gewonnen!");
            player1.setResult(2);
            player2.setResult(0);
        } else {
            System.out.println("Het spel is in een gelijkspel ge\u00EBindigd!");
            player1.setResult(1);
            player2.setResult(1);
        }

    }

}
/*



 */