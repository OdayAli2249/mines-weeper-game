package com.damascusuniversity.io;

import com.damascusuniversity.io.outputdisplay.ScreenDisplayer;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Graphics;

/**
 *
 * @author Emperor
 */
public abstract class UserInterfaceIO extends IO {

    public static ScreenDisplayer Display;
    public static MainUI mainui = new MainUI(); 
    public static GameDisplayer gamedisplayer;             // أولا عملنا المتحول هون
public static LoadGameAndDisplay loadgameanddisplay;
    public static OptionUI optionui = new OptionUI();
    public static MoreOption moreoption2 = new MoreOption();
    public static LoadGame loadgame;
    public static ScoreBoard scoreboard;

    public static GUIio guiio;
    public static UserInterfaceIO CurrentUserInterface = null;
    protected int x, y;

    public static void RunInterface() {
        Display = new ScreenDisplayer();
        mainui = new MainUI();
        loadgame = new LoadGame();
        gamedisplayer = new GameDisplayer();  
loadgameanddisplay=new  LoadGameAndDisplay();
// تانيا عملنا الاوبجكت هون وتالتا بالجي يو آي
        //mainui.setMeToBasicFrame();
        
        scoreboard=new ScoreBoard();
       
    }

    public abstract void setMeToBasicFrame();

    public abstract void Render(Graphics g, CellsManager c);

    public void setCurrentUserInterface(UserInterfaceIO CurrentUserInterface) {
        this.CurrentUserInterface = CurrentUserInterface;
    }

    public UserInterfaceIO getCurrentUserInterface() {
        return CurrentUserInterface;
    }

    public void setCurrentUserInterface() {
        CurrentUserInterface = null;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX(int x) {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY(int y) {
        return y;
    }

}
