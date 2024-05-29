/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SaveAndLoad;

/**
 *
 * @author Ranoom
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.damascusuniversity.io.GUIio;
import com.damascusuniversity.io.IO;
import com.itedamascusuniversity.generator.LargSizeArrayGenerator;
import com.itedemescusuniversity.cells.BCell;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import players.AutomaticPlayer;
import players.HumanPlayer;
import players.Player;
import utils.Timer;

/**
 *
 * @author Ranoom
 */
public class Save {

    public Thread thread;
    private String FileName;

    public void ReleaseSaveThread() {

        thread = new Thread(new Runnable() {

            public synchronized void run() {

                try {
                    SaveGame();
                } catch (IOException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        thread.start();

    }

    public void ReleaseLoadThread(String FileName) {

        thread = new Thread(new Runnable() {

            public synchronized void run() {

                try {
                    LoadGame(FileName);
                } catch (IOException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        thread.start();

    }

    public void ReleaseLoadDisplayThread(String FileName) {

        thread = new Thread(new Runnable() {

            public synchronized void run() {

                try {
                    ReadAsaveGameTowatch(FileName);
                } catch (IOException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        thread.start();

    }

    public void ReleaseSaveDisplayThread() {

        thread = new Thread(new Runnable() {

            @Override
            public synchronized void run() {

                try {
                    SaveGameTowatch();
                } catch (IOException ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        thread.start();

    }

    public void SaveGame() throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File(FileName);
        ObjectOutputStream printwriter = new ObjectOutputStream(new FileOutputStream((f)));
        printwriter.writeObject(IO.CM.getBoard());

        printwriter.writeInt(IO.CM.getBoardHieght());
        printwriter.writeInt(IO.CM.getBoardWidth());
        printwriter.writeInt(LargSizeArrayGenerator.FIELDHIEGHT);
        printwriter.writeInt(LargSizeArrayGenerator.FIELDWIDTH);
        printwriter.writeObject(IO.CM.getPlayers());
        printwriter.writeObject(IO.CM.getLosedPlayers());
        printwriter.writeObject(IO.CM.getLosedPlayerID());
        printwriter.writeObject(IO.CM.getCurrentPlayer());
        printwriter.writeInt(CellsManager.getAutomaticPlayersNumber());
        printwriter.writeInt(CellsManager.getHumanPlayersNumber());
        printwriter.writeInt(CellsManager.getTypeOfPlayersWhoCannotHaveShield());
        printwriter.writeInt(CellsManager.getShieldscore());
        printwriter.writeInt(Player.getEmptyCellPresses());
        printwriter.writeInt(Player.getNumericCellPresses());
        printwriter.writeInt(Player.getMineicCellPresses());
        printwriter.writeInt(Player.getAutomaticCellOpened());
        printwriter.writeInt(Player.getWrongMineExpectState());

        printwriter.writeInt(Player.getCorrectMineExpectState());
        printwriter.writeInt(Player.getTheShieldAndThePunishmentOfTheMine());
        printwriter.writeObject(GUIio.timer);
        printwriter.writeObject(IO.CM.getBoardSave());
        printwriter.writeObject(CellsManager.pressCell);
        printwriter.writeObject(CellsManager.PointDisplayer);
        printwriter.writeInt(HumanPlayer.getstateofShield());
        printwriter.writeInt(AutomaticPlayer.getstateofShield());
        printwriter.writeInt(Player.getNumberofshieldtoplayer());
        printwriter.close();

    }

    public void LoadGame(String FileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<BCell> Board;
        ArrayList<Player> Player;
        ArrayList<Player> LosedPlayer;
        ArrayList<Integer> LosedPlayerId;
        ArrayList<BCell> BoardSave = new ArrayList<BCell>();
        ArrayList<Character> PointType = new ArrayList<Character>();

        ArrayList<BCell> pressCell = new ArrayList<BCell>();
        int numberofAutomaticPlayer, numberofHumanPlayer;
        Player currentPlayer;
        Timer t;
        int BoardH, BoardW, FieldH, FieldW, ShieldScore, PlayerWhoCannotHaveAshield;
        int EmptyCellPresses, NumericCellPresses, WrongMineExpectState, MineicCellPresses, CorrectMineExpectState, AutomaticCellOpened, TheShieldAndThePunishmentOfTheMine;
        int stateOfShieldH, stateOfShieldA, NumberofFirstShield;
        File r = new File(FileName);
        ObjectInputStream printwriter2 = null;

        printwriter2 = new ObjectInputStream(new FileInputStream(r));

        Board = (ArrayList<BCell>) printwriter2.readObject();

        BoardH = (int) printwriter2.readInt();
        BoardW = (int) printwriter2.readInt();
        FieldH = (int) printwriter2.readInt();

        FieldW = (int) printwriter2.readInt();
        Player = (ArrayList<Player>) printwriter2.readObject();
        LosedPlayer = (ArrayList<Player>) printwriter2.readObject();
        LosedPlayerId = (ArrayList<Integer>) printwriter2.readObject();
        currentPlayer = (Player) printwriter2.readObject();
        numberofAutomaticPlayer = (int) printwriter2.readInt();
        numberofHumanPlayer = (int) printwriter2.readInt();
        PlayerWhoCannotHaveAshield = printwriter2.readInt();
        ShieldScore = printwriter2.readInt();
        EmptyCellPresses = printwriter2.readInt();
        NumericCellPresses = printwriter2.readInt();
        MineicCellPresses = printwriter2.readInt();
        AutomaticCellOpened = printwriter2.readInt();

        WrongMineExpectState = printwriter2.readInt();
        CorrectMineExpectState = printwriter2.readInt();
        TheShieldAndThePunishmentOfTheMine = printwriter2.readInt();
        t = (Timer) printwriter2.readObject();
        BoardSave = (ArrayList<BCell>) printwriter2.readObject();
        pressCell = (ArrayList<BCell>) printwriter2.readObject();
        PointType = (ArrayList<Character>) printwriter2.readObject();
        stateOfShieldH = printwriter2.readInt();
        stateOfShieldA = printwriter2.readInt();
        NumberofFirstShield = printwriter2.readInt();
        printwriter2.close();

        for (BCell C : Board) {
            System.out.println("x=" + C.getXCoordinate() + "y=" + C.getYcoordinate());
            System.out.println("    Type" + C.getType() + "  V  " + C.getVisible() + "  E  " + C.getExpected());

        }
        for (Player p : Player) {
            System.out.println("Id" + p.getPlayerID() + "Score" + p.getScore());

        }
        for (Player Losed : LosedPlayer) {
            System.out.println("IdLosed" + Losed.getPlayerID() + "Score" + Losed.getScore());

        }

        System.out.println("numberofAutomaticPlayer" + numberofAutomaticPlayer + "numberofHumanPlayer" + numberofHumanPlayer);
        System.out.println("BoardH" + BoardH + "BoardW" + BoardW);
        System.out.println(" FieldH;" + FieldH + " FieldW;" + FieldW);
        System.out.println(" ShieldScore;" + ShieldScore + " PlayerWhoCannotHaveAshield;" + PlayerWhoCannotHaveAshield);

        IO.CM.setCellsmanager(Board, Player, LosedPlayer, LosedPlayerId, pressCell, PointType, BoardSave, currentPlayer, numberofAutomaticPlayer, numberofHumanPlayer, BoardH, BoardW, FieldH, FieldW, ShieldScore, PlayerWhoCannotHaveAshield,
                EmptyCellPresses, NumericCellPresses, WrongMineExpectState, MineicCellPresses, CorrectMineExpectState, AutomaticCellOpened, TheShieldAndThePunishmentOfTheMine, t, stateOfShieldH, stateOfShieldA, NumberofFirstShield);
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFileName() {
        return FileName;
    }

    public void SaveNameFile(String FileSource, String FileName[]) throws IOException {

        File f = new File(FileSource);
        PrintWriter writer = new PrintWriter(new FileWriter(f));
        for (int i = 0; i < FileName.length; i++) {
            writer.println(FileName[i]);
        }

        writer.close();

    }

    public String[] ReadNameFile(String FileSource) throws IOException {
        String[] NameOfFile = new String[5];
        File f = new File(FileSource);
        BufferedReader Reader = new BufferedReader(new FileReader(f));
        NameOfFile[0] = Reader.readLine();
        NameOfFile[1] = Reader.readLine();
        NameOfFile[2] = Reader.readLine();
        NameOfFile[3] = Reader.readLine();
        NameOfFile[4] = Reader.readLine();
        Reader.close();
        return NameOfFile;

    }

    public void SaveGameTowatch() throws FileNotFoundException, IOException {
        ObjectOutputStream printwriter;
        File SaveGame = new File(FileName);
        printwriter = new ObjectOutputStream(new FileOutputStream(SaveGame));
        printwriter.writeObject(IO.CM.getBoardSave());
        printwriter.writeObject(CellsManager.pressCell);
        printwriter.writeObject(CellsManager.getSavePlayer());

        printwriter.writeInt(IO.CM.getBoardHieght());
        printwriter.writeInt(IO.CM.getBoardWidth());
        printwriter.writeInt(LargSizeArrayGenerator.FIELDHIEGHT
        );
        printwriter.writeInt(LargSizeArrayGenerator.FIELDWIDTH
        );
        printwriter.writeInt(CellsManager.getAutomaticPlayersNumber());
        printwriter.writeInt(CellsManager.getHumanPlayersNumber());
        printwriter.writeInt(CellsManager.getTypeOfPlayersWhoCannotHaveShield());
        printwriter.writeInt(CellsManager.getShieldscore());
        printwriter.writeInt(Player.getEmptyCellPresses());
        printwriter.writeInt(Player.getNumericCellPresses());
        printwriter.writeInt(Player.getMineicCellPresses());
        printwriter.writeInt(Player.getAutomaticCellOpened());
        printwriter.writeInt(Player.getWrongMineExpectState());
        printwriter.writeInt(Player.getCorrectMineExpectState());
        printwriter.writeInt(Player.getTheShieldAndThePunishmentOfTheMine());
        printwriter.writeObject(CellsManager.PointDisplayer);
        printwriter.writeObject(GUIio.timer);
        printwriter.writeInt(HumanPlayer.getstateofShield());
        printwriter.writeInt(AutomaticPlayer.getstateofShield());
        printwriter.writeInt(Player.getNumberofshieldtoplayer());

        printwriter.close();

    }

    public void ReadAsaveGameTowatch(String SaveGame) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream printwriter2;
        printwriter2 = new ObjectInputStream(new FileInputStream(SaveGame));
        ArrayList<BCell> BoardSave = new ArrayList<BCell>();
        ArrayList<Player> SavePlayer = new ArrayList<Player>();
        ArrayList<Character> PointType = new ArrayList<Character>();

        ArrayList<BCell> pressCell = new ArrayList<BCell>();
        Timer t;
        int stateOfShieldH, stateOfShieldA;
        int numberofAutomaticPlayer, numberofHumanPlayer;
        int BoardH, NumberofFirstShield, BoardW, FieldH, FieldW, ShieldScore, PlayerWhoCannotHaveAshield;
        int EmptyCellPresses, NumericCellPresses, WrongMineExpectState, MineicCellPresses, CorrectMineExpectState, AutomaticCellOpened, TheShieldAndThePunishmentOfTheMine;
        BoardSave = (ArrayList<BCell>) printwriter2.readObject();

        pressCell = (ArrayList<BCell>) printwriter2.readObject();
        SavePlayer = (ArrayList<Player>) printwriter2.readObject();

        BoardH = (int) printwriter2.readInt();
        BoardW = (int) printwriter2.readInt();
        FieldH = (int) printwriter2.readInt();

        FieldW = (int) printwriter2.readInt();
        numberofAutomaticPlayer = (int) printwriter2.readInt();
        numberofHumanPlayer = (int) printwriter2.readInt();
        PlayerWhoCannotHaveAshield = printwriter2.readInt();
        ShieldScore = printwriter2.readInt();
        EmptyCellPresses = printwriter2.readInt();
        NumericCellPresses = printwriter2.readInt();
        MineicCellPresses = printwriter2.readInt();
        AutomaticCellOpened = printwriter2.readInt();

        WrongMineExpectState = printwriter2.readInt();
        CorrectMineExpectState = printwriter2.readInt();
        TheShieldAndThePunishmentOfTheMine = printwriter2.readInt();
        PointType = (ArrayList<Character>) printwriter2.readObject();
        t = (Timer) printwriter2.readObject();
        stateOfShieldH = printwriter2.readInt();
        stateOfShieldA = printwriter2.readInt();
        NumberofFirstShield = printwriter2.readInt();

        printwriter2.close();
        for (BCell b : pressCell) {
            System.out.println("(" + b.getXCoordinate() + "," + b.getYcoordinate() + ")");
            System.out.println("(" + b.getVisible() + "," + b.getExpected() + ")");
            System.out.println("     Player" + b.getPlayerWhoPressCell());
            System.out.println("     Time" + b.getTime());

        }

        for (BCell A : BoardSave) {
            System.out.println("(" + A.getXCoordinate() + "," + A.getYcoordinate() + ")");
            System.out.print("(" + A.getVisible() + "," + A.getExpected() + ")");
            System.out.print("     Player" + A.getPlayerWhoPressCell());
            System.out.print("     Time" + A.getTime());

        }

        System.out.println("numberofAutomaticPlayer" + numberofAutomaticPlayer + "numberofHumanPlayer" + numberofHumanPlayer);

        IO.CM.setdisplayCellsmanager(BoardSave, SavePlayer, pressCell, numberofAutomaticPlayer, numberofHumanPlayer, BoardH, BoardW, FieldH, FieldW, ShieldScore, PlayerWhoCannotHaveAshield,
                EmptyCellPresses, NumericCellPresses, WrongMineExpectState, MineicCellPresses, CorrectMineExpectState, AutomaticCellOpened, TheShieldAndThePunishmentOfTheMine, PointType, t, stateOfShieldH, stateOfShieldA, NumberofFirstShield);
    }

    public void WriteLastGameID() throws FileNotFoundException, IOException {
        FileOutputStream fi = new FileOutputStream("abd");
        ObjectOutputStream is = new ObjectOutputStream(fi);
        is.writeInt(CellsManager.GameID);

        is.close();
        fi.close();
    }

    public int ReadLastGameID() throws FileNotFoundException, IOException {
        int Id;
        DataInputStream Reader = new DataInputStream(new BufferedInputStream(new FileInputStream("LastGemeId.txt")));
        FileInputStream fi = new FileInputStream("abd");
        ObjectInputStream is = new ObjectInputStream(fi);
        Id = is.readInt();
        System.out.println(Id);
        Reader.close();
        return Id;
    }
}
