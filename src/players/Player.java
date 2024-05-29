package players;

import com.damascusuniversity.io.IO;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.io.Serializable;
import java.util.ArrayList;
import utils.Point;

public abstract class Player implements Serializable {

    protected int Score;
    protected boolean playerstate;
    protected final char Type;
    protected static int NumberofShieldFirst = 2;
    protected int NumberofShield;

    protected ArrayList<Point> CellsExpected;
    protected int PlayerID;
    protected static int CurrentPlayerID = 0;

    private static int EMPTYCELLPRESSEDSTATE = 10;
    private static int NUMERICCELLPRESSEDSTATE = -1;
    private static int MINECELLPRESSEDSTATE = -250;
    private static int TheShieldAndThePunishmentOfTheMine = -MINECELLPRESSEDSTATE;
    private static int AUTOMATICCELLOPENEDSTATE = 1;
    private static int ENDINGGAMESTATE;
    private static int EXPECTCORRECTMINECELLSTATE = 5;
    private static int EXPECTWRONGMINECELLSTATE = -1;

    public transient Thread PlayerThread;
    protected boolean ThreadEnded = false;

    public Player(char Type) {
        CellsExpected = new ArrayList<>();
        this.Score = 0;
        this.playerstate = true;
        this.Type = Type;
        PlayerID = CurrentPlayerID++;
        (this.NumberofShield) = NumberofShieldFirst;

    }

    public abstract Point getInput(CellsManager cellsmanager);

    public abstract int getInputType();

    public int getScore() {
        return Score;
    }

    public boolean getplayerstate() {
        return playerstate;
    }

    public void setplayerstate(boolean Turn) {
        this.playerstate = Turn;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void IncreaseScore(int number) {

        Score += number;
    }

    public void DecreaseScore(int number) {
        Score -= number;
    }

    public char getPlayerType() {
        return Type;
    }

    public ArrayList<Point> getCellsExpected() {
        return CellsExpected;
    }

    public Integer getPlayerID() {
        return PlayerID;
    }

    public boolean getPlayerState() {
        return playerstate;
    }

    public void setPlayerState(boolean playerstate) {
        this.playerstate = playerstate;
    }

    public void checkScore() {
        if (IO.CM.getCurrentPlayer().getScore() < 0) {

            IO.CM.getLosedPlayerID().add(IO.CM.getCurrentPlayer().getPlayerID());
            IO.CM.getLosedPlayers().add(IO.CM.getCurrentPlayer());
            IO.CM.getLosedPlayers().get(IO.CM.getLosedPlayers().size() - 1).setPlayerState(false);

            for (Point p : IO.CM.getCurrentPlayer().getCellsExpected()) {
                IO.CM.getCell(p.getX(), p.getY()).setExpected(false);

            }

            // Board.getPlayers().remove(Board.getCurrentPlayerIndex());
            if (IO.CM.getPlayers().size() > 1) {
                System.out.println(IO.CM.getCurrentPlayer().getPlayerID() + " Removed");

                IO.CM.getPlayers().remove(IO.CM.getCurrentPlayerIndex());
                System.out.println(IO.CM);
                IO.CM.DecreaseCurrentPlayerIndex();
            } else {

                IO.CM.setGameState(true);
                //Board.getPlayers().clear();

            }

            System.out.println(IO.CM.getPlayers().size());

            for (Integer a : IO.CM.getLosedPlayerID()) {
                System.out.println("Losde " + a);
            }

        }
    }

    public static void setEmptyCellPresses(int EMPTYCELL) {
        EMPTYCELLPRESSEDSTATE = EMPTYCELL;
    }

    public static int getEmptyCellPresses() {
        return EMPTYCELLPRESSEDSTATE;
    }

    public static void setNumericCellPresses(int NUMERICCELL) {
        NUMERICCELLPRESSEDSTATE = NUMERICCELL;
    }

    public static int getNumericCellPresses() {
        return NUMERICCELLPRESSEDSTATE;
    }

    public static void setMineicCellPresses(int MINECELL) {
        MINECELLPRESSEDSTATE = MINECELL;
    }

    public static int getMineicCellPresses() {
        return MINECELLPRESSEDSTATE;
    }

    public static void setAutomaticCellOpened(int CELL) {
        AUTOMATICCELLOPENEDSTATE = CELL;
    }

    public static int getAutomaticCellOpened() {
        return AUTOMATICCELLOPENEDSTATE;
    }

    public static void setCorrectMineExpectState(int CELL) {
        EXPECTCORRECTMINECELLSTATE = CELL;
    }

    public static int getCorrectMineExpectState() {
        return EXPECTCORRECTMINECELLSTATE;
    }

    public static void setWrongMineExpectState(int CELL) {
        EXPECTWRONGMINECELLSTATE = CELL;
    }

    public static int getWrongMineExpectState() {
        return EXPECTWRONGMINECELLSTATE;
    }

    public static void ApdatePlayerID() {
        CurrentPlayerID = 0;
    }

    public void setNumberofshield(int numberofshield) {
        this.NumberofShield = numberofshield;

    }

    public static void setNumberofshieldtoplayer(int numberofshield) {
        NumberofShieldFirst = numberofshield;

    }
     public static int getNumberofshieldtoplayer() {
       return NumberofShieldFirst;

    }

    public int getNumberofshield() {
        return NumberofShield;

    }

    public void IncreaseShield(int number) {

        NumberofShield += number;
        System.out.println("Sheild:" + IO.CM.getCurrentPlayer().getNumberofshield());

    }

    public void DecreaseShield(int number) {

        NumberofShield -= number;
        System.out.println("Sheild:" + IO.CM.getCurrentPlayer().getNumberofshield());

    }

    public static void setTheShieldAndThePunishmentOfTheMine(int PunishmentOfTheMine) {
        TheShieldAndThePunishmentOfTheMine = PunishmentOfTheMine;
    }

    public static int getTheShieldAndThePunishmentOfTheMine() {
        return TheShieldAndThePunishmentOfTheMine;
    }

    public abstract void ReleaseThread();

 /*   public void SavePlayer() throws IOException, ClassNotFoundException {
        File f = new File("t.txt");
        ObjectOutputStream printwriter = new ObjectOutputStream(new FileOutputStream((f)));
        for (Player P : IO.CM.getPlayers()) {
            printwriter.writeInt(P.getPlayerID());
            printwriter.writeInt(P.getScore());
        }
        printwriter.writeChars("Ramy");


        //   File2 file = new File2();
        /*   printwriter.print(BoardWidth);
        printwriter.println();
        printwriter.print(BoardHieght);
        printwriter.println();

        for (BCell d : Board) {
            if (d.getType() == 'M') {
                char c = d.getType();
                printwriter.write(c);
                printwriter.write("(");
                printwriter.print(d.getXCoordinate());
                printwriter.write(",");
                printwriter.print(d.getYcoordinate());
                printwriter.write(")");
                printwriter.write(" ");

                if (d.getVisible() == true) {
                    printwriter.write("V");

                }
                printwriter.write(" ");

                if (d.getExpected() == true) {
                    printwriter.write("E");

                }

                printwriter.println();

            } //  printwriter.write("\n");
            else if (d.getIsSheild() == true) {
                printwriter.print('t');
                printwriter.write("(");
                printwriter.print(d.getXCoordinate());
                printwriter.write(",");
                printwriter.print(d.getYcoordinate());
                printwriter.write(")");

                if (d.getVisible() == true) {
                    printwriter.write("V");

                }
                printwriter.write(" ");

                if (d.getExpected() == true) {
                    printwriter.write("E");

                }
                printwriter.println();

            } else if (d.getIsSheild() == false && d.getType() != 'M') {

                if (d.getVisible() == true) {

                    printwriter.write("(");
                    printwriter.print(d.getXCoordinate());
                    printwriter.write(",");
                    printwriter.print(d.getYcoordinate());
                    printwriter.write(")");
                    printwriter.println();

                    printwriter.write("V");

                }

                if (d.getExpected() == true) {

                    printwriter.write("(");
                    printwriter.print(d.getXCoordinate());
                    printwriter.write(",");
                    printwriter.print(d.getYcoordinate());
                    printwriter.write(")");
                    printwriter.write("E");
                    printwriter.println();

                }

            }

        }
       
        //  File2 s = new File2();
        //   s.readFromFile();

      
          printwriter.close();

           ArrayList<Player> CM2;
        File Reader2 = new File("t.txt");
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream((f)));
        CM2 = (ArrayList<Player>) inputStream.readObject();
        for (Player a : CM2) {
            System.out.print("Id" + a.getPlayerID());
            System.out.println("Score" + a.getScore());

         
        File r = new File("t.txt");
        ObjectInputStream printwriter2 = null;
        
        printwriter2 = new ObjectInputStream(new FileInputStream(f));
        int Id;
        int Score;

        {
            Id = printwriter2.readInt();
            System.out.print("Id=" + Id);
            Score = printwriter2.readInt();
            System.out.println(" Score " + Score);
            Id = printwriter2.readInt();
            System.out.print("Id2=" + Id);
            Score = printwriter2.readInt();
            System.out.println(" Score2 " + Score);
                System.out.print(printwriter2.readChar());//

            }

        }*/
    }

