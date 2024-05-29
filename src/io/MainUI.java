package com.damascusuniversity.io;

import SaveAndLoad.Save;
import static com.damascusuniversity.io.UserInterfaceIO.Display;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import misse.mine.game.MisseMineGame;
import utils.Assets;

/**
 *
 * @author Emperor
 */
public class MainUI extends UserInterfaceIO {

    private Rectangle S;
    private Rectangle O;
    private Rectangle E;
    private Rectangle B;
    private Rectangle Save;
    private Rectangle Load;
    private Rectangle GameDisplayer;
    private Rectangle Scoreboard;
    private Rectangle Countenue;

    private Save save;

    public static boolean CanLoad1;
    public static boolean State1;
    public static boolean Cansavedisplay;
    public static int FirstClick = 0;

    public MainUI() {
        save = new Save();
        CanLoad1 = true;
        State1 = false;
        Cansavedisplay = false;
        S = new Rectangle(340, 475, 200, 25);
        O = new Rectangle(340, 502, 200, 25);
        E = new Rectangle(340, 610, 200, 25);
        B = new Rectangle(5, 5, 40, 40);
        Save = new Rectangle(340, 530, 200, 25);
        Countenue = new Rectangle(-100, 60, 400, 60);
        //  Load = new Rectangle(340, 557, 200, 25);
        GameDisplayer = new Rectangle(340, 584, 200, 25);
        Scoreboard = new Rectangle(340, 557, 200, 25);

    }

    @Override
    public void setMeToBasicFrame() {
        // Build();
        // Display.getFrame().addMouseListener(null);
        // Display.getFrame().add(label);
    }

    @Override
    public void HandlingInput() {
        if (new Rectangle(x, y, 1, 1).intersects(S)) {
            CanLoad1 = false;
            State1 = true;
            GUIio.setNewCM(true);

            GUIio.setGameStarted(false);

            GUIio.getTimer().Reset();
            /*  if (CanstartnewGame == false) {
                CellsManager.setPlayersNumber(1, 1);
                GUIio.setNewCM(true);

                GUIio.setGameStarted(false);

                GUIio.getTimer().Reset();
            }*/
            CurrentUserInterface = null;

        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(O)) {
            setCurrentUserInterface(optionui);
        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(Scoreboard)) {
            FirstClick = 1;

            scoreboard.setMeToBasicFrame();
            setCurrentUserInterface(scoreboard);
        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(B)) {
            setCurrentUserInterface(null);
        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(E)) {
            MisseMineGame.g.setRunning(false);

            Display.getFrame().dispose();
        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(Save)) {
            GUIio.setGameStarted(false);

            loadgame.setMeToBasicFrame();

            setCurrentUserInterface(loadgame);

        } else {

        }
        if (new Rectangle(x, y, 1, 1).intersects(Countenue)) {
            if (GUIio.Co.exists()) {
                try {
                    save.ReleaseLoadThread("Co");
                } catch (Exception e) {

                }
                try {
                    save.thread.join();
                } catch (InterruptedException ex) {
                }
                GUIio.ApdateCellsDimension();
                IO.CM.setGameState(false);

                GUIio.getTimer().Reset();
                MainUI.State1 = true;
                MainUI.CanLoad1 = false;

                setCurrentUserInterface(null);
            } else {
                GUIio.setNewCM(true);

                GUIio.setGameStarted(false);

                GUIio.getTimer().Reset();
                setCurrentUserInterface(null);
            }

        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(GameDisplayer)) {
            //  GUIio.setNewCM(true);     
            //  State = false;
            GUIio.setGameStarted(false);

            loadgameanddisplay.setMeToBasicFrame();

            setCurrentUserInterface(loadgameanddisplay);

        } else {

        }

    }

    @Override
    public void DisplayOutput(CellsManager cellsmanager) {

    }

    @Override
    public void Apdate(CellsManager cellsmanager) {

    }

    @Override
    public void Render(Graphics g, CellsManager c) {

        Rectangle M = new Rectangle(x, y, 1, 1);

        g.drawImage(Assets.MBG, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);

        if (M.intersects(S)) {
            g.drawImage(Assets.Start1, S.x, S.y, S.width, S.height, null);
        } else {
            g.drawImage(Assets.Start0, S.x, S.y, S.width, S.height, null);
        }

        if (M.intersects(O)) {
            g.drawImage(Assets.Option1, O.x, O.y, O.width, O.height, null);
        } else {
            g.drawImage(Assets.Option0, O.x, O.y, O.width, O.height, null);
        }

        if (M.intersects(E)) {
            g.drawImage(Assets.Exit1, E.x, E.y, E.width, E.height, null);
        } else {
            g.drawImage(Assets.Exit0, E.x, E.y, E.width, E.height, null);
        }

        if (M.intersects(B)) {
            g.drawImage(Assets.Back1, B.x, B.y, B.width, B.height, null);
        } else {
            g.drawImage(Assets.Back0, B.x, B.y, B.width, B.height, null);
        }

        if (M.intersects(Save)) {
            g.drawImage(Assets.SaveGame1, Save.x, Save.y, Save.width, Save.height, null);
        } else {
            g.drawImage(Assets.SaveGame2, Save.x, Save.y, Save.width, Save.height, null);
        }

        if (M.intersects(Countenue)) {
            g.drawImage(Assets.Countenue, Countenue.x, Countenue.y, Countenue.width, Countenue.height, null);
        } else {
            g.drawImage(Assets.Countenue, Countenue.x, Countenue.y, Countenue.width, Countenue.height, null);
        }

        if (M.intersects(GameDisplayer)) {
            g.drawImage(Assets.GameDisplayer2, GameDisplayer.x, GameDisplayer.y, GameDisplayer.width, GameDisplayer.height, null);
        } else {
            g.drawImage(Assets.GameDisplayer1, GameDisplayer.x, GameDisplayer.y, GameDisplayer.width, GameDisplayer.height, null);
        }

        if (M.intersects(Scoreboard)) {
            g.drawImage(Assets.SB2, Scoreboard.x, Scoreboard.y, Scoreboard.width, Scoreboard.height, null);
        } else {
            g.drawImage(Assets.SB1, Scoreboard.x, Scoreboard.y, Scoreboard.width, Scoreboard.height, null);
        }

    }

}
