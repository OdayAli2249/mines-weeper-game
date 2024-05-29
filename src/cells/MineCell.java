package com.itedemescusuniversity.cells;

import com.damascusuniversity.io.GUIio;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import players.Player;
import utils.Point;

public class MineCell extends BCell {

    public static int DEFAULTWIDTH = 20;
    public static int DEFAULTHIEGHT = 20;
    public int punchmint;
    private static final char DEFAULTTYPE = 'M';

    public MineCell(int XCoordinate, int YCoordinate, boolean IsShield) {
        super(XCoordinate, YCoordinate, DEFAULTTYPE, false);
        this.IsMine = true;
        this.IsShield = false;
    }

    public char getDefaultType() {
        return Type;
    }

    @Override
    public void CellPressed(CellsManager Board, int x, int y) {

        if (!getExpected() && !getVisible()) {
            //  Board.getPlayers()[0].DecreaseScore(250);

            //   if (Board.getPlayers()[0].getScore() < 0) {
            // ArrayList<BCell> B = Board.getBoard();
            // for (BCell b : B) {
            //     if (b.IsMine) {
            //   b.setVisible(true);
            //     }
            // }
            setVisible(true);

            Board.getCell(x, y).setPlayerWhoPressCell(Board.getCurrentPlayer().getPlayerID());
            Board.getCell(x, y).setTime((GUIio.timer.getTimeLimit() - GUIio.timer.getTimePassed()) / 1000);
            CellsManager.pressCell.add(Board.getCell(x, y));
                        CellsManager.PointDisplayer.add('V');

            if (Board.getCurrentPlayer().getNumberofshield() == 0) {

                Board.getCurrentPlayer().IncreaseScore(Player.getMineicCellPresses());
                System.out.println(Board.getCurrentPlayer().getPlayerType() + " (" + x + "," + y + ").");
                System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
                System.out.println("Score " + Board.getCurrentPlayer().getScore());
                Board.getCurrentPlayer().checkScore();

            } else if (Player.getTheShieldAndThePunishmentOfTheMine() == -(Player.getMineicCellPresses()) || (Player.getTheShieldAndThePunishmentOfTheMine() > (Player.getMineicCellPresses()))) {
                Board.getCurrentPlayer().DecreaseShield(1);
                System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
                System.out.println("Score " + Board.getCurrentPlayer().getScore());
                //     System.out.println("Sheild:" + IO.CM.getCurrentPlayer().getNumberofshield());

            } else {
                punchmint = Player.getTheShieldAndThePunishmentOfTheMine() + Player.getMineicCellPresses();
                Board.getCurrentPlayer().DecreaseShield(1);
                Board.getCurrentPlayer().IncreaseScore(punchmint);
                Board.getCurrentPlayer().checkScore();
                System.out.println("Score " + Board.getCurrentPlayer().getScore());

            }

        }

        Board.NextPlayer();
        GUIio.getTimer().Reset();

    }

    @Override
    public void CellExpected(CellsManager Board, int x, int y
    ) {
        if (!getExpected() && !getVisible()) {

            setExpected(true);
            Board.getCell(x, y).setPlayerWhoPressCell(Board.getCurrentPlayer().getPlayerID());
            Board.getCell(x, y).setTime((GUIio.timer.getTimeLimit() - GUIio.timer.getTimePassed()) / 1000);
            CellsManager.pressCell.add(Board.getCell(x, y));
                        CellsManager.PointDisplayer.add('E');

            Point pointExpected = new Point(x, y);
            Board.getCurrentPlayer().getCellsExpected().add(pointExpected);
            Board.getCurrentPlayer().IncreaseScore(Player.getCorrectMineExpectState());
            System.out.println(Board.getCurrentPlayer().getPlayerType() + " (" + x + "," + y + ").");
            System.out.println("player id " + Board.getCurrentPlayer().getPlayerID());
            System.out.println("Score " + Board.getCurrentPlayer().getScore());
            Board.NextPlayer();
            GUIio.getTimer().Reset();

        }
    }
}
