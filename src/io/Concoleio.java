package com.damascusuniversity.io;

import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import misse.mine.game.MisseMineGame;
import static misse.mine.game.MisseMineGame.g;
import players.AutomaticPlayer;
import players.HumanPlayer;
import players.Player;
import utils.Assets;
import utils.Point;

public class Concoleio extends IO {

    public Scanner SC;
    public Random RN;

    @Override
    public void HandlingInput() {

    }

    @Override
    public void DisplayOutput(CellsManager cellsmanager) {
        System.out.print("\n");
        for (int i = 0; i < cellsmanager.getBoardHieght(); i++) {
            for (int j = 0; j < cellsmanager.getBoardWidth(); j++) {
                if (cellsmanager.getCell(j, i).getVisible() == false && cellsmanager.getCell(j, i).getExpected() == false) {
                    System.out.print("*");
                } else if (cellsmanager.getCell(j, i).getVisible() == true) {
                    if (cellsmanager.getCell(j, i).getType() == 'M') {
                        System.out.print("O");

                    }
                    if (cellsmanager.getCell(j, i).getType() == 'N') {
                        System.out.print(cellsmanager.getCell(j, i).getNumberOfMineSurrounded());

                    }
                    if (cellsmanager.getCell(j, i).getType() == 'E') {
                        System.out.print("-");
                    }
                } else if (cellsmanager.getCell(j, i).getExpected() == true) {
                    System.out.print("|");
                }
                System.out.print("\t");
            }
            System.out.println();

        }
    }

    @Override
    public void Apdate(CellsManager cellsmanager) {

        while (cellsmanager.getGameState() == false) {
            DisplayOutput(cellsmanager);
            int x, y, expect;
            Scanner SC = new Scanner(System.in);
            Scanner SC1 = new Scanner(System.in);

            if (cellsmanager.getCurrentPlayer().getPlayerType() == 'H' && cellsmanager.getCurrentPlayer().getPlayerState() == true) {
                do {
                    System.out.println("Please enter the Cell you want To press x , y ,expect");
                    // DisplayOutput(cellsmanager);
                    //Scanner SC = new Scanner(System.in);

                    x = SC.nextInt();
                    y = SC.nextInt();
                    expect = SC.nextInt();
                    //       Point choice = new Point(x, y);

                } while ((x < 0 || y < 0) || (x > cellsmanager.getBoardWidth()) || (y > cellsmanager.getBoardHieght()) || (expect != 0 && expect != 1));

                Point choice = new Point(x, y);
                if (expect == 0) {
                    cellsmanager.getCell(choice.getX(), choice.getY()).CellPressed(cellsmanager, choice.getX(), choice.getY());

                } else if (expect == 1) {
                    cellsmanager.getCell(choice.getX(), choice.getY()).CellExpected(cellsmanager, choice.getX(), choice.getY());
                }

            } else if (cellsmanager.getCurrentPlayer().getPlayerState() == true) {

                try {
                    Thread.sleep(1000);

                    //System.out.println(CM.getCurrentPlayer().getPlayerType());
                    RN = new Random();
                    Point p = cellsmanager.getCurrentPlayer().getInput(cellsmanager);
                    //  int EX = CM.getCurrentPlayer().g
                    int R = RN.nextInt(3);
                    if (R == 0 || R == 1) {
                        if (!cellsmanager.getCell(p.getX(), p.getY()).getVisible()) {

                            cellsmanager.getCell(p.getX(), p.getY()).CellPressed(cellsmanager, p.getX(), p.getY());
                            if (cellsmanager.getCell(p.getX(), p.getY()).getType() == 'M') {

                                DisplayOutput(cellsmanager);
                                System.out.println("M" + '(' + p.getX() + ',' + p.getY() + ')');

                            }

                        }
                    } else if (R == 2) {
                        cellsmanager.getCell(p.getX(), p.getY()).CellExpected(cellsmanager, p.getX(), p.getY());
                    }

                    // CM.Test();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GUIio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        if (cellsmanager.getnumberofCellVisible() == cellsmanager.getGraph().getTheGenertor().getNumberofEmptyAndNumericalCell()) {
            DisplayOutput(cellsmanager);
            System.out.println("......You Won......");

            for (Player p : cellsmanager.getPlayers()) {
                //   p.IncreaseScore(100 * cellsmanager.getnumberofMineNonExpected());
                System.out.println(" Id: " + p.getPlayerID() + "  Score   " + p.getScore());

            }
            System.out.println("......Losed Player......");

            for (Player L : cellsmanager.getLosedPlayers()) {

                System.out.println(" Id: " + L.getPlayerID() + "  Score   " + L.getScore());

            }

        } else {
            System.out.println("......Game over......");
            System.out.println("......Losed Player......");

            for (Player L : cellsmanager.getLosedPlayers()) {

                System.out.println(" Id: " + L.getPlayerID() + "  Score   " + L.getScore());

            }
        }

    }

    public void GameConcole(CellsManager cellsmanager) {

        try {
            int x, Playernumber, Type, GameType;
            Scanner SC = new Scanner(System.in);
            do {
                System.out.print("1:           Play In Concole           \n" + "2:           Play In GUI           \n");
                GameType = SC.nextInt();
            } while (GameType != 1 && GameType != 2);
            switch (GameType) {
                
                case 1:
                    MisseMineGame.IOType = "concole";
                    IO.CM.BuildNewBoard();
                    IO.CM.newPlayers();
                    
                    
                    do {
                        
                        System.out.print("1:           Play           \n" + "2:           Exite           \n");
                        x = SC.nextInt();
                    } while (x != 1 && x != 2);
                    do {
                        System.out.print("1: Play  with one Automatic or one Human Player Player         \n" + "2: Play with TWo Human or Two Auotomatic player          \n" + "3: Play  with Two Player  Aoutomatic And Human Player         \n");
                        Playernumber = SC.nextInt();
                    } while (Playernumber != 1 && Playernumber != 2 && Playernumber != 3);
                    switch (x) {
                        case 1:
                            switch (Playernumber) {
                                case 1:
                                    do {
                                        System.out.println("1: One Automatic Player    \n" + "2: One Human Player    ");
                                        Type = SC.nextInt();
                                    } while (Type != 1 && Type != 2);
                                    switch (Type) {
                                        case 1:
                                            cellsmanager.P.add(new AutomaticPlayer());
                                            Apdate(cellsmanager);
                                            
                                            break;
                                            
                                        case 2:
                                            cellsmanager.P.add(new HumanPlayer());
                                            
                                            Apdate(cellsmanager);
                                            break;
                                    }
                                    break;
                                case 2:
                                    do {
                                        System.out.println("1: Two Automatic Player    \n" + "2: TWo Human Player    ");
                                        Type = SC.nextInt();
                                    } while (Type != 1 && Type != 2);
                                    switch (Type) {
                                        case 1:
                                            cellsmanager.P.add(new AutomaticPlayer());
                                            cellsmanager.P.add(new AutomaticPlayer());
                                            
                                            Apdate(cellsmanager);
                                            
                                            break;
                                            
                                        case 2:
                                            cellsmanager.P.add(new HumanPlayer());
                                            cellsmanager.P.add(new HumanPlayer());
                                            
                                            Apdate(cellsmanager);
                                            break;
                                    }
                                    break;
                                case 3:
                                    System.out.println("1: One Automatic Player And one Human Player    ");
                                    
                                    cellsmanager.P.add(new HumanPlayer());
                                    cellsmanager.P.add(new AutomaticPlayer());
                                    Apdate(cellsmanager);
                                    
                                    break;
                                    
                            }
                            break;
                        case 2:
                            return;
                            
                            //default:
                    }
                    break;
                case 2:
                    MisseMineGame.IOType = "GUI";
                    
                    UserInterfaceIO.RunInterface();
                    
                    g = new GUIio();
                    
                    new Assets();
                    
                    g.RunScreen(IO.CM);
                    break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Concoleio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
