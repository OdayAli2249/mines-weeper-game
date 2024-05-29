package com.itedemescusuniversity.cells;

import com.damascusuniversity.io.GUIio;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import players.Player;
import utils.Point;

public class NumericCell extends BCell {

    public static int DEFAULTWIDTH = 20;
    public static int DEFAULTHIEGHT = 20;

    private static final char DEFAULTTYPE = 'N';

    public NumericCell(int XCoordinate, int YCoordinate, int NumberOfMineSurrounded, boolean IsShield) {
        super(XCoordinate, YCoordinate, DEFAULTTYPE, IsShield);
        this.NumberOfMineSurrounded = NumberOfMineSurrounded;
    }

    @Override
    public void CellPressed(CellsManager Board, int x, int y) {
        if (!getExpected() && !getVisible()) {
            if (Board.getCell(x, y).getIsSheild() == true && Board.searchTypeOfPlayersWhoCannotHaveShield(Board.getCurrentPlayer().getPlayerType()) == false) {
                Board.getCurrentPlayer().IncreaseShield(1);
            }

            int ScoreAdded = 0;

            if (Player.getNumericCellPresses() == -1) {
                ScoreAdded = Board.getCell(x, y).getNumberOfMineSurrounded();
            } else {
                ScoreAdded = Player.getNumericCellPresses();
            }

            setVisible(true);
            Board.getCell(x, y).setPlayerWhoPressCell(Board.getCurrentPlayer().getPlayerID());
            Board.getCell(x, y).setTime((GUIio.timer.getTimeLimit() - GUIio.timer.getTimePassed()) / 1000);
            CellsManager.pressCell.add(Board.getCell(x, y));
            CellsManager.PointDisplayer.add('V');
            Board.getCurrentPlayer().IncreaseScore(ScoreAdded);

            System.out.println(Board.getCurrentPlayer().getPlayerType() + " (" + x + "," + y + ").");
            System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
            System.out.println("Score " + Board.getCurrentPlayer().getScore());
            Board.IsGameEnded();
            Board.NextPlayer();
            GUIio.getTimer().Reset();

            /*
            boolean Ok = false;

            for (BCell c : Board.getBoard()) {
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
                for (BCell c : Board.getBoard()) {
                    if (!c.getExpected() && c.getType() == 'M') {
                        Board.getCurrentPlayer().IncreaseScore(100);
                    }
                }

                Board.setGameState(true);
            }
             */
        }

    }

    public char getDefaultType() {
        return Type;
    }

    @Override
    public void CellExpected(CellsManager Board, int x, int y) {
        if (!getExpected() && !getVisible()) {

            setExpected(true);
            Point pointExpected = new Point(x, y);
            Board.getCurrentPlayer().getCellsExpected().add(pointExpected);
            Board.getCurrentPlayer().IncreaseScore(Player.getWrongMineExpectState());
            Board.getCell(x, y).setPlayerWhoPressCell(Board.getCurrentPlayer().getPlayerID());
            Board.getCell(x, y).setTime((GUIio.timer.getTimeLimit() - GUIio.timer.getTimePassed()) / 1000);
            CellsManager.pressCell.add(Board.getCell(x, y));
            CellsManager.PointDisplayer.add('E');

            System.out.println(Board.getCurrentPlayer().getPlayerType() + " (" + x + "," + y + ").");
            System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
            System.out.println("Score " + Board.getCurrentPlayer().getScore());
            Board.getCurrentPlayer().checkScore();
            Board.IsGameEnded();
            Board.NextPlayer();
            GUIio.getTimer().Reset();

        }
    }

}
