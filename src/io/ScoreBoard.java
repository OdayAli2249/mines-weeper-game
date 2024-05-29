/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damascusuniversity.io;

import static com.damascusuniversity.io.UserInterfaceIO.Display;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Assets;
import utils.GameInfo;

/**
 *
 * @author Ranoom
 */
public class ScoreBoard extends UserInterfaceIO {

    private static ArrayList<String> FileNameOfSaveBoard;
    public static ArrayList<GameInfo> GIF;
    private GameInfo gameInfo;
    private Graphics g;
    private BufferStrategy bs;
    private ArrayList<Rectangle> cyr;
    public static int Firstclick;
    private Rectangle B;
    private Rectangle STE;
    private Rectangle STS;
    private Rectangle SNP;

    public ScoreBoard() {
        // gameInfo = new GameInfo();
        GIF = new ArrayList<>();
        cyr = new ArrayList<>();
        Firstclick = 0;
        B = new Rectangle(5, 5, 40, 40);
        STE = new Rectangle(760, 5, 120, 40);
        STS = new Rectangle(760, 45, 120, 40);
        SNP = new Rectangle(760, 85, 120, 40);

    }

    @Override
    public void setMeToBasicFrame() {

        FileNameOfSaveBoard = new ArrayList<>();

        String File;

        File f = new File("Source.txt");
        BufferedReader Reader = null;
        try {
            Reader = new BufferedReader(new FileReader(f));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while ((File = (String) Reader.readLine()) != null) {

                FileNameOfSaveBoard.add(File);

            }
        } catch (IOException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Reader.close();
        } catch (IOException ex) {
            Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String k : FileNameOfSaveBoard) {
            System.out.println(k);
        }

        for (int i = 0; i < FileNameOfSaveBoard.size(); i++) {
            try {
                GIF.add(GameInfo.readGame(FileNameOfSaveBoard.get(i)));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ScoreBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /// GIF=CreateNamesSorting(GIF);

    }

    @Override
    public void Render(Graphics g, CellsManager c) {

        Rectangle M = new Rectangle(x, y, 1, 1);

        g.drawImage(Assets.ScoreBoard, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);
        int y = 20;
        int x = 20;
        g.setColor(Color.BLACK);
        for (int information = 0; information < GIF.size(); information++) {
            for (int Name = 0; Name < GIF.get(information).getPlayerName().length; Name++) {
                g.drawString(GIF.get(information).getPlayerName()[Name], 75 + Name * 70, 140 + information * y);
            }
            for (int ID = 0; ID < GIF.get(information).PlayerID.length; ID++) {
                g.drawString("" + GIF.get(information).PlayerID[ID], 250 + ID * 40, 140 + information * y);
            }

            for (int Score = 0; Score < GIF.get(information).getGameScore().length; Score++) {
                g.drawString("" + GIF.get(information).getGameScore()[Score], 333 + Score * 40, 140 + information * y);
            }
            g.drawString(GIF.get(information).StartGameDate, 460, 140 + information * y);
            g.drawString(GIF.get(information).EndGameDate, 620, 140 + information * y);

            g.drawLine(70, 140 + information * y, 700, 140 + information * y);

            cyr.add(new Rectangle(700, 130 + information * y, 20, 20));

            if (M.intersects(cyr.get(information))) {
                g.drawImage(Assets.SBC2, cyr.get(information).x, cyr.get(information).y, cyr.get(information).width, cyr.get(information).height, null);
            } else if (!GIF.get(information).FileNAmeDisplayer.equals("Rano.ser")) {
                g.drawImage(Assets.SBC3, cyr.get(information).x, cyr.get(information).y, cyr.get(information).width, cyr.get(information).height, null);
            } else {
                g.drawImage(Assets.SBC1, cyr.get(information).x, cyr.get(information).y, cyr.get(information).width, cyr.get(information).height, null);

            }

        }
        if (M.intersects(B)) {
            g.drawImage(Assets.Back1, B.x, B.y, B.width, B.height, null);
        } else {
            g.drawImage(Assets.Back0, B.x, B.y, B.width, B.height, null);
        }

        if (M.intersects(STE)) {
            g.drawImage(Assets.STE2, STE.x, STE.y, STE.width, STE.height, null);
        } else {
            g.drawImage(Assets.STE, STE.x, STE.y, STE.width, STE.height, null);
        }

        if (M.intersects(STS)) {
            g.drawImage(Assets.STS2, STS.x, STS.y, STS.width, STS.height, null);
        } else {
            g.drawImage(Assets.STS, STS.x, STS.y, STS.width, STS.height, null);
        }
        if (M.intersects(SNP)) {
            g.drawImage(Assets.SNP2, SNP.x, SNP.y, SNP.width, SNP.height, null);
        } else {
            g.drawImage(Assets.SNP, SNP.x, SNP.y, SNP.width, SNP.height, null);
        }

    }

    @Override
    public void HandlingInput() {

        for (int cy = 0; cy < GIF.size(); cy++) {
            if (new Rectangle(x, y, 1, 1).intersects(cyr.get(cy))) {
                if (!GIF.get(cy).FileNAmeDisplayer.equals("Rano.ser")) {
                    GameDisplayer.NameFile = GIF.get(cy).FileNAmeDisplayer;
                    GameDisplayer.FirstClick = 1;
                    MainUI.CanLoad1 = false;

                    setCurrentUserInterface(gamedisplayer);
                    GIF = new ArrayList<>();
                    FileNameOfSaveBoard = new ArrayList<>();
                    cyr = new ArrayList<>();
                }
            }
        }

        if (new Rectangle(x, y, 1, 1).intersects(B)) {
            GIF = new ArrayList<>();
            FileNameOfSaveBoard = new ArrayList<>();
            cyr = new ArrayList<>();

            setCurrentUserInterface(mainui);
        } else {

        }

        if (new Rectangle(x, y, 1, 1).intersects(STE)) {
            GIF = GameInfo.CreateEndDatesSorting(GIF);
        } else {

        }
        if (new Rectangle(x, y, 1, 1).intersects(STS)) {
            GIF =GameInfo.CreateDatesSorting(GIF);

        } else {

        }
        if (new Rectangle(x, y, 1, 1).intersects(SNP)) {

            GIF = GameInfo.CreateNamesSorting(GIF);

        } else {
        }

        DisplayOutput(CM);

    }

    @Override
    public void DisplayOutput(CellsManager cellsmanager) {

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
    }

}
