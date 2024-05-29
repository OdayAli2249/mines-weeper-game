package com.itedemescusuniversity.cells.cellsmangmentsystem;

import SaveAndLoad.Save;
import com.damascusuniversity.io.GUIio;
import com.damascusuniversity.io.GameDisplayer;

import com.damascusuniversity.io.IO;
import com.itedamascusuniversity.generator.LargSizeArrayGenerator;
import com.itedemescusuniversity.cells.BCell;
import com.itedemescusuniversity.cells.EmptyCell;
import com.itedemescusuniversity.cells.MineCell;
import com.itedemescusuniversity.cells.NumericCell;
import graph.Graph;
import java.io.Serializable;
import java.util.ArrayList;
import misse.mine.game.MisseMineGame;
import players.AutomaticPlayer;
import players.HumanPlayer;
import players.Player;
import utils.Cell;
import utils.Timer;

public class CellsManager implements Serializable {

    private static int BoardWidth;
    private static int BoardHieght;
    public ArrayList<Player> P;
    public int CurrentPlayerIndex;
    private static int Shieldscore = 50;////
    private static int HUMANPLAYERS = 1;
    private static int AUTOMATICPLAYERS = 1;
    private static char PlayersWhoCannotHaveShield[];///
    private static int TypeOfPlayersWhoCannotHaveShield = 1;////
    private static ArrayList<BCell> Board;
    private static Graph graph;
    private ArrayList<Integer> LosedPlayersID;
    private ArrayList<Player> LosedPlayers;
    private Player[] PlayersOrder;
    private int PlayersOrederSize;
    private static ArrayList<Player> SavePlayer;
    private static ArrayList<BCell> BoardSave;
    public static ArrayList<BCell> pressCell;
    public static ArrayList<BCell> pressDisplayer;
    public static ArrayList<Character> PointDisplayer;
    public static ArrayList<Character> PointDisplayer2;

    public static int GameID;

    private boolean IsEnd;

    public CellsManager() {
        PointDisplayer = new ArrayList<>();
        PointDisplayer2 = new ArrayList<>();

        SavePlayer = new ArrayList<>();
        BoardSave = new ArrayList<>();
        pressCell = new ArrayList<>();
        Board = new ArrayList<>();
        graph = new Graph();
        CurrentPlayerIndex = 0;
        this.IsEnd = false;
        graph.GenerateGraph();

        BoardWidth = graph.getTheGenertor().getFieldWidth();
        BoardHieght = graph.getTheGenertor().getFieldHieght();

        LosedPlayersID = new ArrayList<>();

        P = new ArrayList<>();
        LosedPlayers = new ArrayList<>();
        if (MisseMineGame.IOType.equals("GUI")) {
            Player.ApdatePlayerID();

            for (int i = 0; i < HUMANPLAYERS; i++) {

                P.add(new HumanPlayer());
            }

            for (int i = 0; i < AUTOMATICPLAYERS; i++) {
                P.add(new AutomaticPlayer());
            }
        }
        PlayersOrederSize = 0;
        PlayersOrder = new Player[6];
        PlayersWhoCannotHaveShield = new char[2];
        setPlayersWhoCannotHaveShield();

    }

    public synchronized void BuildNewBoard() {
        for (Cell c : graph.getCallsList()) {

            if (c.IsPump()) {
                Board.add(new MineCell(c.getX(), c.getY(), false));
            } else if (c.getNumberOfPumpSurrounded() == 0) {
                if (c.getShield() == true) {
                    Board.add(new EmptyCell(c.getX(), c.getY(), true));

                } else {
                    Board.add(new EmptyCell(c.getX(), c.getY(), false));
                }

            } else if (c.getShield() == true) {
                Board.add(new NumericCell(c.getX(), c.getY(), c.getNumberOfPumpSurrounded(), true));
            } else {
                Board.add(new NumericCell(c.getX(), c.getY(), c.getNumberOfPumpSurrounded(), false));
            }

        }

        SaveBoardOfGame();

    }

    public BCell getCell(int Order) {
        return Board.get(Order);
    }

    public BCell getCell(int x, int y) {

        for (BCell c : Board) {

            if (c.getXCoordinate() == x && c.getYcoordinate() == y) {

                return c;

            }
        }
        return null;
    }

    public void Test() {

        for (int i = 0; i < BoardHieght; i++) {
            System.out.println();
            for (int j = 0; j < BoardWidth; j++) {

                if (getCell(j, i).getType() == 'M') {
                    System.out.print("  *");
                } else if (getCell(j, i).getType() == 'N') {
                    System.out.print("  " + getCell(j, i).getNumberOfMineSurrounded());
                } else {
                    System.out.print("  -");
                }

            }

        }

        for (Player p : P) {
            System.out.println(p.getPlayerID());
        }

    }

    public int getBoardWidth() {
        return BoardWidth;
    }

    public int getBoardHieght() {
        return BoardHieght;
    }

    public static void setBoardWidthAndHieght(int H, int W) {
        BoardWidth = W;
        BoardHieght = H;

    }

    public Graph getGraph() {
        return graph;
    }

    public ArrayList<BCell> getBoard() {
        return Board;
    }

    public ArrayList<Player> getPlayers() {
        return P;
    }

    public void NextPlayer() {
        if (CurrentPlayerIndex < P.size() - 1) {
            CurrentPlayerIndex++;
        } else {
            CurrentPlayerIndex = 0;
        }
    }

    public Player getCurrentPlayer() {
        try {
            return P.get(CurrentPlayerIndex);
        } catch (Exception e) {

            IO.CM.setGameState(true);
            return new HumanPlayer();
        }
    }

    public int getCurrentPlayerIndex() {
        return CurrentPlayerIndex;
    }

    public ArrayList<Integer> getLosedPlayerID() {
        return LosedPlayersID;
    }

    public void decreaseCurrentPlayerIndex() {
        CurrentPlayerIndex--;
    }

    public void setGameState(boolean IsEnd) {
        this.IsEnd = IsEnd;
    }

    public boolean getGameState() {
        return IsEnd;
    }

    public ArrayList<Player> getLosedPlayers() {
        return LosedPlayers;
    }

    public void setLosedPlayers(ArrayList<Player> losed) {
        this.LosedPlayers = losed;
    }

    public static void setPlayersNumber(int HUMAN, int AUTOMATIC) {
        HUMANPLAYERS = HUMAN;
        AUTOMATICPLAYERS = AUTOMATIC;
    }

    public static int getHumanPlayersNumber() {
        return HUMANPLAYERS;

    }

    public static int getAutomaticPlayersNumber() {
        return AUTOMATICPLAYERS;
    }

    public void PrepareResultArray() {

        PlayersOrederSize = 0;
        PlayersOrder = new Player[6];
        try {

            for (Player p : LosedPlayers) {
                PlayersOrder[PlayersOrederSize++] = p;
            }

            for (Player p : P) {
                if (!Found(p.getPlayerID())) {
                    PlayersOrder[PlayersOrederSize++] = p;
                }
            }

            for (int i = 0; i < PlayersOrederSize; i++) {
                if (PlayersOrder[i].getScore() >= 0) {
                    PlayersOrder[i].setplayerstate(true);
                } else {
                    PlayersOrder[i].setplayerstate(false);
                }
            }

            // Sort
            Player Temp;

            for (int i = PlayersOrederSize - 1; i > -1; i--) {
                for (int j = 0; j < i; j++) {
                    if (PlayersOrder[j].getScore() > PlayersOrder[1 + j].getScore()) {

                        // Swap
                        Temp = PlayersOrder[i];
                        PlayersOrder[i] = PlayersOrder[i + 1];
                        PlayersOrder[i + 1] = Temp;

                    }
                }
            }

        } catch (Exception a) {

        }

    }

    public boolean Found(int ID) {
        for (int i = 0; i < PlayersOrederSize; i++) {
            if (PlayersOrder[i].getPlayerID() == ID) {
                return true;
            }
        }
        return false;
    }

    public String ResultMassege() {

        StringBuilder ResultMassege = new StringBuilder();
        int j = 0;
        String Result = " ";

        try {

            Result += "       Game Finished \n ";

            for (int i = PlayersOrederSize - 1; i > -1; i--) {
                Result += (++j) + ". " + "Player : " + PlayersOrder[i].getPlayerID() + "      Score :"
                        + " " + PlayersOrder[i].getScore() + "\n";

            }
        } catch (Exception a) {
            System.out.println("Error");

        }
        return Result;

    }

    public int getnumberofCellVisible() {
        int numberofCellVisible = 0;

        for (int i = 0; i < BoardHieght; i++) {
            for (int j = 0; j < BoardWidth; j++) {
                if ((getCell(j, i).getVisible() == true && getCell(j, i).getType() != 'M') || (getCell(j, i).getExpected() == true && getCell(j, i).getType() != 'M')) {
                    numberofCellVisible++;
                }
            }
        }

        return numberofCellVisible;
    }

    public int getnumberofMineNonExpected() {
        int numberofMineNonExpected = 0;

        for (int i = 0; i < BoardHieght; i++) {
            for (int j = 0; j < BoardWidth; j++) {
                if (getCell(j, i).getVisible() != true && getCell(j, i).getType() == 'M' && getCell(j, i).getExpected() != true) {
                    numberofMineNonExpected++;
                }
            }
        }
        return numberofMineNonExpected;
    }

    public boolean IsGameEnded() {

        boolean Ok = false;

        for (BCell c : getBoard()) {
            if (c.getType() == 'N' && c.getVisible() == false) {
                if (!c.getExpected()) {
                    Ok = true;
                }
            }

            if (c.getType() == 'E' && c.getVisible() == false) {
                if (!c.getExpected()) {
                    Ok = true;
                }
            }
        }

        if (Ok == false) {
            for (BCell c : getBoard()) {
                if (!c.getExpected() && c.getType() == 'M' && !c.getVisible()) {
                    getCurrentPlayer().IncreaseScore(100);
                }
            }
            ScoreOfShield();
            setGameState(true);

        }

        return Ok;

    }

    public void ScoreOfShield() {

        for (Player P : IO.CM.getPlayers()) {
            if (P.getplayerstate() == true && P.getNumberofshield() != 0) {
                IO.CM.getCurrentPlayer().IncreaseScore(Shieldscore * P.getNumberofshield());
            }
            System.out.print("Score" + P.getScore());
        }
    }

    public static char[] getPlayersWhoCannotHaveShield() {

        return PlayersWhoCannotHaveShield;
    }

    public static void setPlayersWhoCannotHaveShield() {
        if (TypeOfPlayersWhoCannotHaveShield == 0) {

            PlayersWhoCannotHaveShield[0] = ' ';
            PlayersWhoCannotHaveShield[1] = ' ';

        } else if (TypeOfPlayersWhoCannotHaveShield == 1) {
            PlayersWhoCannotHaveShield[0] = 'M';
            PlayersWhoCannotHaveShield[1] = ' ';

        } else if (TypeOfPlayersWhoCannotHaveShield == 3) {
            PlayersWhoCannotHaveShield[0] = 'M';
            PlayersWhoCannotHaveShield[1] = 'H';

        } else if (TypeOfPlayersWhoCannotHaveShield == 2) {
            PlayersWhoCannotHaveShield[0] = ' ';
            PlayersWhoCannotHaveShield[1] = 'H';
        }
    }

    public boolean searchTypeOfPlayersWhoCannotHaveShield(char Type) {
        boolean result = false;
        for (int i = 0; i < 2; i++) {
            if (PlayersWhoCannotHaveShield[i] == Type) {
                result = true;
            }
        }

        return result;
    }

    public static void setTypeOfPlayersWhoCannotHaveShield(int Type) {
        TypeOfPlayersWhoCannotHaveShield = Type;
    }

    public static int getTypeOfPlayersWhoCannotHaveShield() {
        return TypeOfPlayersWhoCannotHaveShield;
    }

    public static void setShieldscore(int Shieldscore2) {
        Shieldscore = Shieldscore2;
    }

    public static int getShieldscore() {
        return Shieldscore;
    }

    public void newPlayers() {
        P = new ArrayList<Player>();
    }

    public synchronized void SaveBoardOfGame() {
        for (BCell c : Board) {
            if (c.getVisible() == true) {
                c.setVisible(false);
            }
            BoardSave.add(c);
        }

    }

    public void SavePlayers() {
        for (Player pa : P) {
            SavePlayer.add(pa);
        }
    }

    public void setCellsmanager(ArrayList<BCell> Board, ArrayList<Player> Players, ArrayList<Player> LosedPlayer, ArrayList<Integer> IdPlayer, ArrayList<BCell> press, ArrayList<Character> PointType, ArrayList<BCell> SaveBoard, Player CurrentPlayer, int numberAut, int numberHum, int BoardH, int BoardW, int FieldH, int FieldW, int ShieldScore, int PlayerWhoCannotHaveAshield,
            int EmptyCellPresses, int NumericCellPresses, int WrongMineExpectState, int MineicCellPresses, int CorrectMineExpectState, int AutomaticCellOpened, int TheShieldAndThePunishmentOfTheMine, Timer t, int StateSH, int StateSA, int NumberofFirstShield) {
        CellsManager.TypeOfPlayersWhoCannotHaveShield = PlayerWhoCannotHaveAshield;
        LargSizeArrayGenerator.FIELDWIDTH = FieldW;
        LargSizeArrayGenerator.FIELDHIEGHT = FieldH;
        CellsManager.AUTOMATICPLAYERS = numberAut;
        CellsManager.HUMANPLAYERS = numberHum;
        GUIio.timer.setTimeLimit(t.getTimeLimit());
        Player.setNumberofshieldtoplayer(NumberofFirstShield);
        HumanPlayer.setstateofShield(StateSH);
        AutomaticPlayer.setstateofShield(StateSA);
        IO.CM = new CellsManager();
        CellsManager CM = new CellsManager();
        if (Players.size() == 2) {
            IO.CM.P = new ArrayList<>();
            IO.CM.CurrentPlayerIndex = CurrentPlayer.getPlayerID();
            IO.CM.P = Players;
            IO.CM.LosedPlayers = new ArrayList<>();
            IO.CM.LosedPlayers = LosedPlayer;
            this.LosedPlayersID = IdPlayer;
        } else {
            IO.CM.P = new ArrayList<>();
            IO.CM.P = Players;
            Player.ApdatePlayerID();

            IO.CM.LosedPlayers = new ArrayList<>();
            IO.CM.LosedPlayers = LosedPlayer;
            this.LosedPlayersID = IdPlayer;
        }

        CellsManager.Board = Board;
        CellsManager.BoardSave = Board;
        CellsManager.pressCell = press;
        CellsManager.PointDisplayer = PointType;

        CellsManager.setBoardWidthAndHieght(BoardH, BoardW);
        CellsManager.setShieldscore(ShieldScore);

        Player.setEmptyCellPresses(EmptyCellPresses);
        Player.setNumericCellPresses(NumericCellPresses);
        Player.setWrongMineExpectState(WrongMineExpectState);
        Player.setMineicCellPresses(MineicCellPresses);
        Player.setCorrectMineExpectState(CorrectMineExpectState);
        Player.setAutomaticCellOpened(AutomaticCellOpened);
        Player.setTheShieldAndThePunishmentOfTheMine(TheShieldAndThePunishmentOfTheMine);
        this.IsEnd = false;

    }

    public ArrayList<BCell> getBoardSave() {

        return BoardSave;
    }

    public static ArrayList<Player> getSavePlayer() {
        return SavePlayer;
    }

    public void setdisplayCellsmanager(ArrayList<BCell> Board, ArrayList<Player> Players, ArrayList<BCell> press, int numberAut, int numberHum, int BoardH, int BoardW, int FieldH, int FieldW, int ShieldScore, int PlayerWhoCannotHaveAshield,
            int EmptyCellPresses, int NumericCellPresses, int WrongMineExpectState, int MineicCellPresses, int CorrectMineExpectState, int AutomaticCellOpened, int TheShieldAndThePunishmentOfTheMine, ArrayList<Character> PointType, Timer t, int StateSH, int StateSA, int NumberofFirstShield) {
        CellsManager.TypeOfPlayersWhoCannotHaveShield = PlayerWhoCannotHaveAshield;
        int k = 0;

        for (Player P : Players) {

        }
        for (int i = 0; i < Board.size(); i++) {
            if (Board.get(i).getExpected() == true) {
                Board.get(i).setExpected(false);
            } else if (Board.get(i).getVisible() == true) {
                Board.get(i).setVisible(false);
            }

        }
        GameDisplayer.TimerLimit = t.getTimeLimit();
        LargSizeArrayGenerator.FIELDWIDTH = FieldW;
        LargSizeArrayGenerator.FIELDHIEGHT = FieldH;
        CellsManager.AUTOMATICPLAYERS = numberAut;
        CellsManager.HUMANPLAYERS = numberHum;
        Player.setNumberofshieldtoplayer(NumberofFirstShield);
        HumanPlayer.setstateofShield(StateSH);
        AutomaticPlayer.setstateofShield(StateSA);
        CellsManager CM = new CellsManager();
        CellsManager.pressDisplayer = press;
        this.LosedPlayers = new ArrayList<>();

        this.P = new ArrayList<>();

        Player.ApdatePlayerID();

        for (int i = 0; i < numberHum; i++) {

            P.add(new HumanPlayer());
        }

        for (int i = 0; i < numberAut; i++) {
            P.add(new AutomaticPlayer());

        }
        CellsManager.Board = Board;
        CellsManager.setBoardWidthAndHieght(BoardH, BoardW);
        CellsManager.setShieldscore(ShieldScore);
        Player.setEmptyCellPresses(EmptyCellPresses);
        Player.setNumericCellPresses(NumericCellPresses);
        Player.setWrongMineExpectState(WrongMineExpectState);
        Player.setMineicCellPresses(MineicCellPresses);
        Player.setCorrectMineExpectState(CorrectMineExpectState);
        Player.setAutomaticCellOpened(AutomaticCellOpened);
        Player.setTheShieldAndThePunishmentOfTheMine(TheShieldAndThePunishmentOfTheMine);
        CellsManager.PointDisplayer2 = PointType;
        this.CurrentPlayerIndex = 0;

    }

}
