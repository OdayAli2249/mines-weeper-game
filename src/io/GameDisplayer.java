/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damascusuniversity.io;

import SaveAndLoad.Save;
import static com.damascusuniversity.io.GUIio.IMAGEHIEGHT;
import static com.damascusuniversity.io.GUIio.IMAGEWIDTH;
import static com.damascusuniversity.io.IO.CM;
import static com.damascusuniversity.io.UserInterfaceIO.Display;
import com.itedemescusuniversity.cells.BCell;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import players.Player;
import utils.Assets;
import utils.Timer;

/**
 *
 * @author Emperor
 */
public class GameDisplayer extends UserInterfaceIO {
    
    private Save s;
    private BufferStrategy bs;
    private Graphics g;
    public static int Counter;//
    public static ArrayList<BCell> Press;//
    private static ArrayList<Character> Point;
    public static Timer timer;
    public static long TimerLimit;
//    private CellsManager CM;
    public static int FirstClick;
    public static String NameFile;
    
    public GameDisplayer() {
        FirstClick = 0;
        
        s = new Save();
        Counter = 0;
        Press = new ArrayList<BCell>();
        Point = new ArrayList<>();
        
    }
    
    @Override
    public void setMeToBasicFrame() {
        
    }
    
    @Override
    public void Render(Graphics g, CellsManager c) {  // سادسا هون نسخنا تابع الريندر مشان يعرض اللعبة عواجهة العارض
        if (FirstClick == 1) {
            s.ReleaseLoadDisplayThread(NameFile);
            try {
                s.thread.join();
            } catch (InterruptedException ex) {
            }
            
            GUIio.ApdateCellsDimension();
            timer = new Timer();
            timer.setTimeLimit(TimerLimit);
            Press = CellsManager.pressDisplayer;
            Point = CellsManager.PointDisplayer2;
            for (BCell b : Press) {
                System.out.println("(" + b.getXCoordinate() + "," + b.getYcoordinate() + ")");
                System.out.println("(" + b.getVisible() + "," + b.getExpected() + ")");
                System.out.println("     Player" + b.getPlayerWhoPressCell());
                System.out.println("     Time" + b.getTime());
            }
            FirstClick = 2;
            
        }
        
        g.drawImage(Assets.BG, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);
        
        for (int i = 0; i < IO.CM.getBoardHieght(); i++) {
            for (int j = 0; j < IO.CM.getBoardWidth(); j++) {
                switch (IO.CM.getCell(j, i).getType()) {
                    case 'N':
                        if (IO.CM.getCell(j, i).getIsSheild() == false) {
                            g.drawImage(Assets.ImageChoicer2(IO.CM.getCell(j, i).getNumberOfMineSurrounded(), IO.CM.getCell(j, i).getVisible(),
                                    IO.CM.getCell(j, i).getExpected(),'N'),
                                    IO.CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                    IO.CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                        } else {
                           
                            g.drawImage(Assets.ImageChoicer2((10 + IO.CM.getCell(j, i).getNumberOfMineSurrounded()), IO.CM.getCell(j, i).getVisible(),
                                    IO.CM.getCell(j, i).getExpected(),'S'),
                                    IO.CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                    IO.CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                        }
                        break;
                    case 'M':
                        g.drawImage(Assets.ImageChoicer2(9, IO.CM.getCell(j, i).getVisible(), IO.CM.getCell(j, i).getExpected(),'M'),
                                IO.CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                IO.CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                        break;
                    default:
                        if (IO.CM.getCell(j, i).getIsSheild() == false) {
                            g.drawImage(Assets.ImageChoicer2(0, IO.CM.getCell(j, i).getVisible(), IO.CM.getCell(j, i).getExpected(),'N'),
                                    CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                    CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                        } else {
                            g.drawImage(Assets.ImageChoicer2(10, IO.CM.getCell(j, i).getVisible(), IO.CM.getCell(j, i).getExpected(),'S'),
                                    IO.CM.getCell(j, i).getXCoordinate() * IMAGEWIDTH,
                                    IO.CM.getCell(j, i).getYcoordinate() * IMAGEHIEGHT, IMAGEWIDTH, IMAGEHIEGHT, null);
                        }
                        break;
                }
                
            }
        }
        
        if (Counter==Press.size()) {
            g.drawString("Game Over", 670, 300);
        }
        
        
           int x=0,y=0;
         for (Player P:CM.getPlayers()) {
             if(P.getPlayerState()==true){
                         g.drawString("Player"+(P.getPlayerID()+1)+"  Score"+P.getScore(), 670, 200+x);
                         x+=50;
             }

        }
         for(Player Pa: CM.getLosedPlayers())
         {
              g.drawString("Player"+(Pa.getPlayerID()+1)+"  Score"+Pa.getScore(), 670, 200+y);
                      y+=50;
         }
        
        g.drawString("Time Passed : " + ((timer.getTimeLimit() - timer.getTimePassed()) / 1000), 670, 400);
        g.drawString("Turn : \nPlayer " + (IO.CM.getCurrentPlayer().getPlayerID() + 1), 50, 620);
        //   g.drawString("Shield : " + (CM.getCurrentPlayer().getNumberofshield()), 60, 635);
        if (IO.CM.getCurrentPlayer().getNumberofshield() != 0) {
            for (int i = 0; i < IO.CM.getCurrentPlayer().getNumberofshield(); i++) {
                g.drawImage(Assets.Shield, 140 + (i * 25), 606, 20, 20, null);
            }
        }
        if (IO.CM.getCurrentPlayer().getScore() < 20) {
            g.drawString("Score : " + (IO.CM.getCurrentPlayer().getScore()), 60, 635);
            
        }
    }
    
    @Override
    public void HandlingInput() {
        
    }
    
    @Override
    public void DisplayOutput(CellsManager cellsmanager) {

        
    }
    
    @Override
    public void Apdate(CellsManager cellsmanager) {
        
        if (CurrentUserInterface == gamedisplayer) {
            timer.ApdateTimer();
            if (timer.Warning()) {
                IO.CM.NextPlayer();
                
                timer.Reset();
            }
        } else {
            timer.ApdateLastTime();
            
        }

        // خامسا هون بدنا ناخد من الملف ونعمل اجرائية الضغط او التوقع مع مراعاة بعض الشروط
        if (Counter < Press.size()) {

            // رابعا بنفس هاد الكلاس عند الريندر
            BCell c;
            c = Press.get(Counter);
            
            if (Point.get(Counter) == 'E') {// لان وقت عم نحفظ البورد كلها عم تنحفظ مفتوحه لما عم حول كل الخلايا ل مو مفتوحه عم تروح هي تببع توقعArraylistبتحوي اذا الخليه متوقعه او مرئيه

                if (c.getPlayerWhoPressCell() == IO.CM.getCurrentPlayer().getPlayerID() && (c.getTime() == (timer.getTimeLimit() - timer.getTimePassed()) / 1000)) {
                    
                    IO.CM.getCell(c.getXCoordinate(), c.getYcoordinate()).CellExpected(IO.CM, c.getXCoordinate(), c.getYcoordinate());
                    Counter++;
                    timer.Reset();
                    
                }
                
            } else {
                if (c.getPlayerWhoPressCell() == IO.CM.getCurrentPlayer().getPlayerID() && (c.getTime() == (timer.getTimeLimit() - timer.getTimePassed()) / 1000)) {
                    
                    IO.CM.getCell(c.getXCoordinate(), c.getYcoordinate()).CellPressed(IO.CM, c.getXCoordinate(), c.getYcoordinate());
                    Counter++;
                    
                    timer.Reset();
                    
                }
            }
        } 
        
    }
    
}
