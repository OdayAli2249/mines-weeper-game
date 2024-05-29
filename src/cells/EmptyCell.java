package com.itedemescusuniversity.cells;
import com.damascusuniversity.io.GUIio;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.util.ArrayList;
import players.Player;
import utils.Point;

public class EmptyCell extends BCell {

    private boolean First = true;

    public static int DEFAULTWIDTH = 20;
    public static int DEFAULTHIEGHT = 20;

    private static final char DEFAULTTYPE = 'E';

    public EmptyCell(int XCoordinate, int YCoordinate, boolean IsShield) {
        super(XCoordinate, YCoordinate, DEFAULTTYPE, IsShield);
    }

    public char getDefaultType() {
        return Type;
    }

    public void Recursion(CellsManager Board, int x, int y) {

        ArrayList<Point> P = Board.getGraph().
                get(x, y).getPointsSurrounded();

        setVisible(true);

        for (Point p : P) {

            BCell C = Board.getCell(p.getX(), p.getY());

            if (C.getVisible() == true) {
                continue;
            }

            if (C.getType() != 'M') {

                if (C.getType() == 'N') {

                    if (!C.getExpected()) {
                        C.setVisible(true);

                        if (Board.getCell(p.getX(), p.getY()).getIsSheild() == true && Board.searchTypeOfPlayersWhoCannotHaveShield(Board.getCurrentPlayer().getPlayerType()) == false) {
                            Board.getCurrentPlayer().IncreaseShield(1);
                        }
                        Board.getCurrentPlayer().IncreaseScore(Player.getAutomaticCellOpened());
                    }
                    continue;

                } else {
                    if (!C.getExpected()) {
                        C.setVisible(true);

                        if (Board.getCell(p.getX(), p.getY()).getIsSheild() == true && Board.searchTypeOfPlayersWhoCannotHaveShield(Board.getCurrentPlayer().getPlayerType()) == false) {
                            Board.getCurrentPlayer().IncreaseShield(1);
                        }
                        Board.getCurrentPlayer().IncreaseScore(Player.getAutomaticCellOpened());
                    }
                    Recursion(Board, p.getX(), p.getY());
                }

            }

        }
    }

    @Override
    public void CellPressed(CellsManager Board, int x, int y) {

        if (!getExpected() && !getVisible()) {
            Board.getCurrentPlayer().IncreaseScore(Player.getEmptyCellPresses());
            Board.getCell(x, y).setPlayerWhoPressCell(Board.getCurrentPlayer().getPlayerID());
            Board.getCell(x, y).setTime((GUIio.timer.getTimeLimit() - GUIio.timer.getTimePassed()) / 1000);
            CellsManager.pressCell.add(Board.getCell(x, y));
                        CellsManager.PointDisplayer.add('V');

            if (Board.getCell(x, y).getIsSheild() == true && Board.searchTypeOfPlayersWhoCannotHaveShield(Board.getCurrentPlayer().getPlayerType()) == false) {
                Board.getCurrentPlayer().IncreaseShield(1);
            }
            Recursion(Board, x, y);
            System.out.println(Board.getCurrentPlayer().getPlayerType() + " (" + x + "," + y + ").");
            System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
            System.out.println("Score " + Board.getCurrentPlayer().getScore());
            Board.IsGameEnded();
            Board.NextPlayer();
            GUIio.getTimer().Reset();
        }

    }

    @Override
    public void CellExpected(CellsManager Board, int x, int y) {

        if (!getExpected() && !getVisible()) {


            Board.getCell(x, y).setPlayerWhoPressCell(Board.getCurrentPlayer().getPlayerID());
            Board.getCell(x, y).setTime((GUIio.timer.getTimeLimit() - GUIio.timer.getTimePassed()) / 1000);
            CellsManager.pressCell.add(Board.getCell(x, y));
            CellsManager.PointDisplayer.add('E');
            Point pointExpected = new Point(x, y);
            Board.getCurrentPlayer().getCellsExpected().add(pointExpected);
            Board.getCurrentPlayer().IncreaseScore(Player.getWrongMineExpectState());
            System.out.println(Board.getCurrentPlayer().getPlayerType() + " (" + x + "," + y + ").");
            System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
            System.out.println("Score " + Board.getCurrentPlayer().getScore());
            System.out.println(Board.getCurrentPlayer().getCellsExpected());
            Board.getCurrentPlayer().checkScore();
            Board.IsGameEnded();
            Board.NextPlayer();
            GUIio.getTimer().Reset();

        }

    }

}
