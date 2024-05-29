package com.damascusuniversity.io;

import SaveAndLoad.Save;
import static com.damascusuniversity.io.IO.CM;
import static com.damascusuniversity.io.MainUI.CanLoad1;
import static com.damascusuniversity.io.UserInterfaceIO.CurrentUserInterface;
import static com.damascusuniversity.io.UserInterfaceIO.Display;
import static com.damascusuniversity.io.UserInterfaceIO.gamedisplayer;
import static com.damascusuniversity.io.UserInterfaceIO.mainui;
import com.itedamascusuniversity.generator.LargSizeArrayGenerator;
import com.itedemescusuniversity.cells.BCell;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import players.Player;
import utils.Assets;
import utils.FieldWindow;
import utils.GameInfo;
import utils.Point;
import utils.Timer;

public class GUIio extends UserInterfaceIO implements ActionListener, MouseMotionListener, Runnable {
    
    public static final int BoardDimension = 600;
    
    public static int IMAGEHIEGHT = (int) BoardDimension / LargSizeArrayGenerator.FIELDHIEGHT;
    public static int IMAGEWIDTH = (int) BoardDimension / LargSizeArrayGenerator.FIELDWIDTH;
    public int x = 0, y = 0;
    public Scanner SC;
    public Random RN;
    
    private BufferStrategy bs;
    private Graphics g;
    private boolean Turn;
    private boolean Running = true;
    private boolean PressAction = false;
    
    public static MouseListener mouselistener;
    private static boolean newCM = false;
    public static MouseEvent e;
    
    private static boolean GameStarted = false;
    public static Timer timer;
    private Thread T;
    private Save s, contenue;
    private GameInfo GI;
    private String TimeStart;
    private String TimeEnd;
    private int Firstclick = 1;
    public static File Co;
    public GUIio() {
        Co=new File("Co");
        TimeEnd = new String();
        TimeStart = new String();
        
        s = new Save();
        contenue = new Save();
        try {
            CellsManager.GameID = s.ReadLastGameID();
        } catch (IOException ex) {
            Logger.getLogger(GUIio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mouselistener = new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
                GUIio.e = e;
                PressAction = true;
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };
        
        SC = new Scanner(System.in);
        timer = new Timer();
        setMeToBasicFrame();
        CM.Test();
        
    }

    //   THREAD 1
    public void RunScreen(CellsManager cellsmanager) throws InterruptedException {
        
        CM = cellsmanager;
        
        T = new Thread(this);
        T.start();
        
        while (Running) {
            
            if (CurrentUserInterface == null) {
               
                         
                timer.ApdateTimer();
                if (timer.Warning()) {
                   
                    if (CM.getCurrentPlayer().getPlayerType() == 'H') {
                        CM.NextPlayer();
                    } else {
                        T.interrupt();
                        CM.NextPlayer();
                    }
                    timer.Reset();
                }
            } else {
                timer.ApdateLastTime();
                
                if (CurrentUserInterface == gamedisplayer) {
                    // timer.Reset();// تالتا هون قلنالو ازا ما كنت عواجهة اللعبة وكنت عواجهة العرض عميل الابديت تبع واجة العرض
                    CurrentUserInterface.Apdate(CM);
                }                                               // رابعا بنفس هاد الكلاس عند الريندر

            }
            
            Apdate(CM);
            DisplayOutput(CM);
            System.out.print(CM.getCurrentPlayer());
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
            
        }
        
    }
    
    @Override
    public void Render(Graphics g, CellsManager C) {
        if (Firstclick == 1) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            TimeStart = dtf.format(now);
            Firstclick = 0;
        }
        if (CurrentUserInterface == null) {
            
            if (newCM == true) {
                
                this.CM = new CellsManager();
                this.CM.BuildNewBoard();
                
                newCM = false;
                return;
            }
            Rectangle M = new Rectangle(x, y, 1, 1);
            
            g.drawImage(Assets.BG, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);
            
            for (int i = 0; i < CM.getBoardHieght(); i++) {
                for (int j = 0; j < CM.getBoardWidth(); j++) {
                    switch (CM.getCell(j, i).getType()) {
                        case 'N':
                            if (CM.getCell(j, i).getIsSheild() == false) {
                                g.drawImage(Assets.ImageChoicer(CM.getCell(j, i).getNumberOfMineSurrounded(), CM.getCell(j, i).getVisible(),
                                        CM.getCell(j, i).getExpected()),
                                        CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                        CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                            } else {
                                g.drawImage(Assets.ImageChoicer((10 + CM.getCell(j, i).getNumberOfMineSurrounded()), CM.getCell(j, i).getVisible(),
                                        CM.getCell(j, i).getExpected()),
                                        CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                        CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                            }
                            break;
                        case 'M':
                            g.drawImage(Assets.ImageChoicer(9, CM.getCell(j, i).getVisible(), CM.getCell(j, i).getExpected()),
                                    CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                    CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                            break;
                        default:
                            if (CM.getCell(j, i).getIsSheild() == false) {
                                g.drawImage(Assets.ImageChoicer(0, CM.getCell(j, i).getVisible(), CM.getCell(j, i).getExpected()),
                                        CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                        CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                            } else {
                                g.drawImage(Assets.ImageChoicer(10, CM.getCell(j, i).getVisible(), CM.getCell(j, i).getExpected()),
                                        CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                        CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                            }
                            break;
                    }
                    
                }
            }
            
            if (CM.getGameState() == true) {
                g.drawString("Game Over", 670, 300);
            }
            
            g.drawString("Time Passed : " + ((timer.getTimeLimit() - timer.getTimePassed()) / 1000), 670, 400);
            
            g.drawString("Turn : \nPlayer " + (CM.getCurrentPlayer().getPlayerID() + 1), 50, 620);
            //   g.drawString("Shield : " + (CM.getCurrentPlayer().getNumberofshield()), 60, 635);
            if (CM.getCurrentPlayer().getNumberofshield() != 0) {
                for (int i = 0; i < CM.getCurrentPlayer().getNumberofshield(); i++) {
                    g.drawImage(Assets.Shield, 140 + (i * 25), 606, 20, 20, null);
                }
            }
            if (CM.getCurrentPlayer().getScore() < 20) {
                g.drawString("Score : " + (CM.getCurrentPlayer().getScore()), 60, 635);
                
            }
            
            int x = 0, y = 0;
            for (Player P : CM.getPlayers()) {
                if (P.getPlayerState() == true) {
                    g.drawString("Player" + (P.getPlayerID() + 1) + "  Score" + P.getScore(), 670, 200 + x);
                    x += 50;
                }
                
            }
            for (Player Pa : CM.getLosedPlayers()) {
                g.drawString("Player" + (Pa.getPlayerID() + 1) + "  Score" + Pa.getScore(), 670, 200 + y);
                y += 50;
            }
            
        } else {
            
            CurrentUserInterface.Render(g, CM);                  // هون نحنا من الأساس عم نستدعي تابع الريندر تبع الواجهة الحالية ازا ما كانت الواجهة الحالية هي واجهة اللعبة
        }                                                        // خامسا بكلاس عارض اللعبة
    }
    
    @Override
    public void HandlingInput() {
        
        try {
            
            Random RN = new Random();
            
            int Per = RN.nextInt(3);
            
            if (Per == 0 || Per == 1) {
                Thread.sleep(1000);
            } else {
                Thread.sleep(8000);
            }
            
            if (CM.getCurrentPlayer().getPlayerType() == 'M' && CM.getGameState() == false) {
                
                RN = new Random();
                Point p = CM.getCurrentPlayer().getInput(CM);
                
                int R = RN.nextInt(3);
                if (R == 0 || R == 1) {
                    if (!CM.getCell(p.getX(), p.getY()).getVisible()) {
                        CM.getCell(p.getX(), p.getY()).CellPressed(CM, p.getX(), p.getY());
                    }
                } else if (R == 2) {
                    CM.getCell(p.getX(), p.getY()).CellExpected(CM, p.getX(), p.getY());
                }

                // CM.Test();
            }
        } catch (InterruptedException ex) {
            System.out.println();
            System.out.println("Interrupted");
            return;
        }
        
    }
    
    @Override
    public void DisplayOutput(CellsManager cellsmanager) {
        //CM = cellsmanager;                  التعليمة  اللي فرشت البرنامج                   

        bs = Display.getCanvas().getBufferStrategy();
        if (bs == null) {
            Display.getCanvas().createBufferStrategy(3);
        }
        
        if (bs != null) {
            g = bs.getDrawGraphics();
        }
        
        if (g != null) {
            
            Render(g, CM);
            
            g.dispose();
            bs.show();
        }
    }
    
    @Override
    public void Apdate(CellsManager cellsmanager) {
        
        if (PressAction == true) {
            
            if (CurrentUserInterface == null) {
                
                if (CM.getGameState() == true) {
                    DateTimeFormatter End = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now2 = LocalDateTime.now();
                    TimeEnd = End.format(now2);
                    CM.PrepareResultArray();
                    ShowResult();
                    File f=new File("Co");
                    f.delete();
                    int G = 0;
                    CellsManager.GameID += 1;
                    int[] PlayersID = new int[CellsManager.getHumanPlayersNumber() + CellsManager.getAutomaticPlayersNumber()];
                    int[] PlayersScore = new int[CellsManager.getHumanPlayersNumber() + CellsManager.getAutomaticPlayersNumber()];
                    
                    if (CM.getLosedPlayers().size() == PlayersScore.length) {
                        for (int i = 0; i < PlayersScore.length; i++) {
                            PlayersScore[i] = CM.getLosedPlayers().get(i).getScore();
                            PlayersID[i] = CM.getLosedPlayers().get(i).getPlayerID();
                        }
                    } else if (CM.getLosedPlayers().size() != 0) {
                        for (int i = 0; i < PlayersScore.length; i++) {
                            PlayersScore[i] = CM.getLosedPlayers().get(i).getScore();
                            PlayersID[i] = CM.getLosedPlayers().get(i).getPlayerID();
                            G = i;
                        }
                        PlayersScore[G] = CM.getPlayers().get(G).getScore();
                        
                    } else {
                        for (int i = 0; i < PlayersScore.length; i++) {
                            PlayersScore[i] = CM.getPlayers().get(i).getScore();
                            PlayersID[i] = CM.getPlayers().get(i).getPlayerID();
                        }
                    }
                    
                    FieldWindow.CreateWindow();
                    while (FieldWindow.WindowAlive == true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            break;
                            
                        }
                    }
                    
                    FieldWindow.WindowAlive = true;
                    FieldWindow.PrintData();
                    String[] S = new String[CellsManager.getHumanPlayersNumber() + CellsManager.getAutomaticPlayersNumber()];
                    if (S.length == 1) {
                        S[0] = FieldWindow.FirstPlayerName;
                    } else {
                        S[0] = FieldWindow.FirstPlayerName;
                        if( CellsManager.getAutomaticPlayersNumber()!=1)
                        S[1] = FieldWindow.SecondPlayerNamel;
                        else{
                              S[1]="M";
                        }
                        
                    }
                    if (FieldWindow.SaveOrNotForDisplay == true) {
                        
                        loadgameanddisplay.setMeToBasicFrame();
                        LoadGameAndDisplay.Filename[FieldWindow.SaveOrder] = FieldWindow.SaveFile;
                        s.setFileName(FieldWindow.SaveFile);
                        LoadGameAndDisplay.Filename[FieldWindow.SaveOrder] = s.getFileName();
                        
                        s.ReleaseSaveDisplayThread();
                        try {
                            
                            s.SaveNameFile("Displayer.txt", LoadGameAndDisplay.Filename);
                        } catch (IOException ex) {
                            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    } else {
                        FieldWindow.SaveFile = "Rano.ser";
                    }
                    GI = new GameInfo(CellsManager.GameID, PlayersScore, PlayersID, S, FieldWindow.SaveFile, TimeStart, TimeEnd);
                    try {
                        GI.addGame(GI);
                    } catch (IOException ex) {
                    }
                    
                    try {
                        GI.readGame(GI.getGameSource());
                    } catch (IOException ex) {
                        Logger.getLogger(GUIio.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(GUIio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    try {
                        s.WriteLastGameID();
                    } catch (IOException ex) {
                        Logger.getLogger(GUIio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    MainUI.State1 = false;
                    MainUI.CanLoad1 = true;
                    MainUI.Cansavedisplay = true;
                    GUIio.setGameStarted(false);
                    Firstclick = 1;
                    setCurrentUserInterface(mainui);
                    
                } else if (CM.getCurrentPlayer().getPlayerType() == 'H') {
                    CM.getCurrentPlayer().ReleaseThread();
                      contenue.setFileName("Co");
                 try{
                    contenue.ReleaseSaveThread();
                 }catch(Exception e)
                 {
                     
                 }
                }
                try {
                    // CM.getCurrentPlayer().PlayerThread.join(500);
                    /*
                    if (CM.getCurrentPlayer().getPlayerType() == 'H') {
                    
                    if (e.getButton() == MouseEvent.BUTTON3) {
                    
                    int x = e.getX() / IMAGEWIDTH;
                    int y = e.getY() / IMAGEHIEGHT;
                    
                    try {
                    
                    CM.getCell(x, y).CellExpected(CM, x, y);
                    } catch (Exception a) {
                    }
                    
                    } else if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX() / IMAGEWIDTH;
                    int y = e.getY() / IMAGEHIEGHT;
                    try {
                    if (!CM.getCell(x, y).getVisible()) {
                    System.out.println("Current Player Id before Pressing " + CM.getCurrentPlayer().getPlayerID());
                    
                    if (GameStarted != true) {
                    System.out.println("I am inside GameStart Condition ");
                    if (CM.getCell(x, y).getType() == 'M' && AllCellsInvisibleAndNotExpected()) {
                    StrategyA(x, y);
                    }
                    }
                    CM.getCell(x, y).CellPressed(CM, x, y);
                    
                    }
                    } catch (Exception a) {
                    setCurrentUserInterface(mainui);         ///////////////////////////////////////
                    }
                    CM.Test2();
                    System.out.println();
                    CM.Test();
                    }
                    
                    }*/
                } catch (Exception ex) {
                }
            } else if (CurrentUserInterface == gamedisplayer) {
                if (GameDisplayer.Counter == GameDisplayer.Press.size()) {
                    // CM.PrepareResultArray();
                    //  ShowResult();
                    GameDisplayer.Counter = 0;
                    //   MainUI.State1 = false;    
                    //   GUIio.setGameStarted(false);
                    //  IO.CM.setGameState(true);
                    setCurrentUserInterface(loadgameanddisplay);
                    
                    setCurrentUserInterface(mainui);
                    CanLoad1 = true;

                    // newCM = true;
                }
            } else {
                CurrentUserInterface.HandlingInput();
            }
            
            PressAction = false;
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println("Clicked");
        
    }

    //   THREAD 2
    @Override
    public void run() {
        
        while (Running) {
            if (CurrentUserInterface == null) {
                HandlingInput();
            }
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (CurrentUserInterface != null) {
            CurrentUserInterface.setX(e.getX());
            CurrentUserInterface.setY(e.getY());
            
        }
    }
    
    @Override
    public void setMeToBasicFrame() {
        Display.getFrame().add(Display.getCanvas());
        Display.getFrame().addMouseListener(mouselistener);
        Display.getFrame().addMouseMotionListener(this);
        Display.getCanvas().addMouseMotionListener(this);
        Display.getCanvas().addMouseListener(mouselistener);
        
        CM.BuildNewBoard();
        System.out.println("");
        //  CM.Test2();
        setCurrentUserInterface(mainui);
        
    }
    
    public static void setNewCM(boolean newC) {
        newCM = newC;
    }
    
    public static void ApdateCellsDimension() {
        IMAGEHIEGHT = (int) BoardDimension / LargSizeArrayGenerator.FIELDHIEGHT;
        IMAGEWIDTH = (int) BoardDimension / LargSizeArrayGenerator.FIELDWIDTH;
    }
    
    public void setRunning(boolean Running) {
        this.Running = Running;
    }
    
    private boolean AllCellsInvisibleAndNotExpected() {
        
        boolean OK = true;
        
        System.out.println("First Enter");
        
        for (BCell c : CM.getBoard()) {
            if (c.getExpected() || c.getVisible()) {
                OK = false;
                break;
            }
        }
        
        GameStarted = true;
        
        return OK;
    }
    
    public static void setGameStarted(boolean Choice) {
        GameStarted = Choice;
    }
    
    private void StrategyA(int u, int v) {
        
        while (CM.getCell(u, v).getType() == 'M') {
            this.CM = new CellsManager();
            this.CM.BuildNewBoard();
            
        }
        
    }
    
    private void ShowResult() {
        JOptionPane.showMessageDialog(null, CM.ResultMassege());
    }
    
    public static Timer getTimer() {
        return timer;
    }
    
}
