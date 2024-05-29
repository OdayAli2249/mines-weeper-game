/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damascusuniversity.io;

import SaveAndLoad.Save;
import static com.damascusuniversity.io.UserInterfaceIO.CurrentUserInterface;
import static com.damascusuniversity.io.UserInterfaceIO.Display;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import utils.Assets;

/**
 *
 * @author Ranoom
 */
public class LoadGame extends UserInterfaceIO {

    private Rectangle LoadGame1;
    private Rectangle LoadGame2;
    private Rectangle LoadGame3;
    private Rectangle LoadGame4;
    private Rectangle LoadGame5;
    private Rectangle LoadGame6;
    private Rectangle LoadGame7;
    private Rectangle LoadGame8;
    private Rectangle LoadGame9;
    private Rectangle LoadGame10;

    private Rectangle L1;
    private Rectangle L2;
    private Rectangle L3;
    private Rectangle L4;
    private Rectangle L5;
    private Rectangle L6;
    private Rectangle L7;
    private Rectangle L8;
    private Rectangle L9;
    private Rectangle L10;
    private Rectangle B;
    private Rectangle cyr[];
    private Rectangle Delet[];
    private ArrayList<Rectangle> LoadGame;
    private Rectangle Search;
    private Rectangle QuikSave;

    private Save s;
    private Save F;
    public static String[] Filename;
    public static String[] Filename2;

    private JFrame frame;
    private JPanel panel;
    private JTextField field;
    private JButton button;
    private Stage f;
    private String FN;
    private ArrayList<Rectangle> Loadthis;

    public LoadGame() {

        LoadGame = new ArrayList<>();
        Loadthis = new ArrayList<>();
        LoadGame1 = new Rectangle(50, 100, 250, 60);
        LoadGame2 = new Rectangle(50, 170, 250, 60);
        LoadGame3 = new Rectangle(50, 240, 250, 60);
        LoadGame4 = new Rectangle(50, 310, 250, 60);
        LoadGame5 = new Rectangle(50, 380, 250, 60);

        LoadGame6 = new Rectangle(450, 100, 250, 60);
        LoadGame7 = new Rectangle(450, 170, 250, 60);
        LoadGame8 = new Rectangle(450, 240, 250, 60);
        LoadGame9 = new Rectangle(450, 310, 250, 60);
        LoadGame10 = new Rectangle(450, 380, 250, 60);
        LoadGame.add(LoadGame1);
        LoadGame.add(LoadGame2);
        LoadGame.add(LoadGame3);
        LoadGame.add(LoadGame4);
        LoadGame.add(LoadGame5);
        LoadGame.add(LoadGame6);
        LoadGame.add(LoadGame7);
        LoadGame.add(LoadGame8);
        LoadGame.add(LoadGame9);
        LoadGame.add(LoadGame10);
        L1 = new Rectangle(380, 125, 70, 30);
        L2 = new Rectangle(380, 195, 70, 30);
        L3 = new Rectangle(380, 265, 70, 30);
        L4 = new Rectangle(380, 335, 70, 30);
        L5 = new Rectangle(380, 405, 70, 30);

        L6 = new Rectangle(775, 125, 70, 30);
        L7 = new Rectangle(775, 195, 70, 30);
        L8 = new Rectangle(775, 265, 70, 30);
        L9 = new Rectangle(775, 335, 70, 30);
        L10 = new Rectangle(775, 405, 70, 30);
        Loadthis.add(L1);
        Loadthis.add(L2);
        Loadthis.add(L3);
        Loadthis.add(L4);
        Loadthis.add(L5);
        Loadthis.add(L6);
        Loadthis.add(L7);
        Loadthis.add(L8);
        Loadthis.add(L9);
        Loadthis.add(L10);

        cyr = new Rectangle[10];
        cyr[0] = new Rectangle(270, 110, 70, 60);
        cyr[1] = new Rectangle(270, 180, 70, 60);
        cyr[2] = new Rectangle(270, 250, 70, 60);
        cyr[3] = new Rectangle(270, 320, 70, 60);
        cyr[4] = new Rectangle(270, 380, 70, 60);
        cyr[5] = new Rectangle(675, 110, 70, 60);
        cyr[6] = new Rectangle(675, 180, 70, 60);
        cyr[7] = new Rectangle(675, 250, 70, 60);
        cyr[8] = new Rectangle(675, 320, 70, 60);
        cyr[9] = new Rectangle(675, 380, 70, 60);

        Delet = new Rectangle[10];
        Delet[0] = new Rectangle(330, 110, 70, 60);
        Delet[1] = new Rectangle(330, 180, 70, 60);
        Delet[2] = new Rectangle(330, 250, 70, 60);
        Delet[3] = new Rectangle(330, 320, 70, 60);
        Delet[4] = new Rectangle(330, 380, 70, 60);
        Delet[5] = new Rectangle(730, 110, 70, 60);
        Delet[6] = new Rectangle(730, 180, 70, 60);
        Delet[7] = new Rectangle(730, 250, 70, 60);
        Delet[8] = new Rectangle(730, 320, 70, 60);
        Delet[9] = new Rectangle(730, 380, 70, 60);
        B = new Rectangle(5, 5, 40, 40);
        s = new Save();
        F = new Save();
        Filename = new String[10];
        FN = null;

        Search = new Rectangle(300, 500, 250, 60);
        QuikSave = new Rectangle(290, 570, 300, 60);

    }

    @Override
    public void setMeToBasicFrame() {
        try {
            Filename = new String[10];

            //  String[] NameOfFile = new String[5];
            File f = new File("text.txt");
            BufferedReader Reader = new BufferedReader(new FileReader(f));
            Filename[0] = Reader.readLine();
            Filename[1] = Reader.readLine();
            Filename[2] = Reader.readLine();
            Filename[3] = Reader.readLine();
            Filename[4] = Reader.readLine();
            Filename[5] = Reader.readLine();
            Filename[6] = Reader.readLine();
            Filename[7] = Reader.readLine();
            Filename[8] = Reader.readLine();
            Filename[9] = Reader.readLine();
            Reader.close();
        } catch (IOException ex) {
        }

        Filename2 = new String[1];
        File s = new File("QuickSave.txt");
        BufferedReader Reader2 = null;
        try {
            Reader2 = new BufferedReader(new FileReader(s));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Filename2[0] = Reader2.readLine();
        } catch (IOException ex) {
            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Reader2.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void HandlingInput() {
        Scanner Sc;

        if (new Rectangle(x, y, 1, 1).intersects(QuikSave)) {
            if (MainUI.State1 != false) {
                                Filename2[0]="QuikSave";

                s.setFileName("QuikSave");
                s.ReleaseSaveThread();

            } else if (MainUI.CanLoad1 == true&&!Filename2[0].equals("Rano.ser")) {
                s.ReleaseLoadThread("QuikSave");

                try {
                    s.thread.join();
                } catch (InterruptedException ex) {
                }
                GUIio.ApdateCellsDimension();
                IO.CM.setGameState(false);

                GUIio.getTimer().Reset();
                MainUI.State1 = true;
                MainUI.CanLoad1 = false;
                // MainUI.CanstartnewGame=false;

                CurrentUserInterface = null;

            }
            
        } else {

        }
        if (MainUI.State1 != false) {

            for (int i = 0; i < LoadGame.size(); i++) {

                if (new Rectangle(x, y, 1, 1).intersects(LoadGame.get(i))) {

                    CreateFrame();
                    while (FN == null) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            break;

                        }
                    }
                    s.setFileName(FN);
                    Filename[i] = s.getFileName();

                    s.ReleaseSaveThread();
                    FN = null;
                }
            }
        }

        for (int Lo = 0; Lo < Loadthis.size(); Lo++) {
            if (new Rectangle(x, y, 1, 1).intersects(Loadthis.get(Lo))) {

                if (MainUI.CanLoad1 == true) {
                    if (Filename[Lo].equals("Rano.ser") != true) {

                        s.ReleaseLoadThread(Filename[Lo]);
                        try {
                            s.thread.join();
                        } catch (InterruptedException ex) {
                        }
                        GUIio.ApdateCellsDimension();
                        IO.CM.setGameState(false);

                        GUIio.getTimer().Reset();
                        MainUI.State1 = true;
                        MainUI.CanLoad1 = false;
                        // MainUI.CanstartnewGame=false;

                        CurrentUserInterface = null;

                    }
                }
            }
        }
        //  setCurrentUserInterface(o);
        if (new Rectangle(x, y, 1, 1).intersects(B)) {
            for (int i = 0; i < Filename.length; i++) {
                if (Filename[i] == null) {
                    Filename[i] = "Rano.ser";
                }
            }
            try {
                F.SaveNameFile("text.txt", Filename);
            } catch (IOException ex) {
                Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            try {
                F.SaveNameFile("QuickSave.txt", Filename2);
            } catch (IOException ex) {
                Logger.getLogger(LoadGame.class.getName()).log(Level.SEVERE, null, ex);
            }

            setCurrentUserInterface(mainui);

        } else {

        }
        for (int j = 0; j < Delet.length; j++) {

            if (new Rectangle(x, y, 1, 1).intersects(Delet[j])) {

                if (MainUI.State1 = true) {
                    if (!Filename[j].equals("Rano.ser")) {
                        File s = new File(Filename[j]);
                        s.delete();
                        Filename[j] = "Rano.ser";
                    }

                } else {

                }
            }
        }
        if (new Rectangle(x, y, 1, 1).intersects(Search)) {

            if (MainUI.CanLoad1 == true) {
                CreateFrame();
                while (FN == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        break;

                    }
                }
                for (int i = 0; i < Filename.length; i++) {
                    if (Filename[i].equals(FN)) {
                        s.setFileName(FN);
                        Filename[i] = s.getFileName();

                        s.ReleaseLoadThread(s.getFileName());

                        try {
                            s.thread.join();
                        } catch (InterruptedException ex) {
                        }
                        FN = null;

                        GUIio.ApdateCellsDimension();

                        GUIio.setGameStarted(false);
                        GUIio.getTimer().Reset();
                        MainUI.CanLoad1 = false;
                        CurrentUserInterface = null;
                    }
                }
            }
        } else {

        }

    }

    @Override
    public void DisplayOutput(CellsManager cellsmanager
    ) {

    }

    @Override
    public void Apdate(CellsManager cellsmanager
    ) {

    }

    @Override
    public void Render(Graphics g, CellsManager c
    ) {

        Rectangle M = new Rectangle(x, y, 1, 1);

        g.drawImage(Assets.LoadGameStart, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);

        for (int R = 0; R < LoadGame.size(); R++) {
            if (M.intersects(LoadGame.get(R))) {
                g.drawImage(Assets.Image2.get(R), LoadGame.get(R).x, LoadGame.get(R).y, LoadGame.get(R).width, LoadGame.get(R).height, null);
            } else {
                g.drawImage(Assets.Image.get(R), LoadGame.get(R).x, LoadGame.get(R).y, LoadGame.get(R).width, LoadGame.get(R).height, null);
            }
        }

        if (Filename[0].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[0].x, cyr[0].y, cyr[0].width, cyr[0].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[0].x, cyr[0].y, cyr[0].width, cyr[0].height, null);
        }
        if (Filename[1].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[1].x, cyr[1].y, cyr[1].width, cyr[1].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[1].x, cyr[1].y, cyr[1].width, cyr[1].height, null);
        }
        if (Filename[2].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[2].x, cyr[2].y, cyr[2].width, cyr[2].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[2].x, cyr[2].y, cyr[2].width, cyr[2].height, null);
        }
        if (Filename[3].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[3].x, cyr[3].y, cyr[3].width, cyr[3].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[3].x, cyr[3].y, cyr[3].width, cyr[3].height, null);
        }
        if (Filename[4].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[4].x, cyr[4].y, cyr[4].width, cyr[4].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[4].x, cyr[4].y, cyr[4].width, cyr[4].height, null);
        }

        if (Filename[5].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[5].x, cyr[5].y, cyr[5].width, cyr[5].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[5].x, cyr[5].y, cyr[5].width, cyr[5].height, null);
        }
        if (Filename[6].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[6].x, cyr[6].y, cyr[6].width, cyr[6].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[6].x, cyr[6].y, cyr[6].width, cyr[6].height, null);
        }
        if (Filename[7].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[7].x, cyr[7].y, cyr[7].width, cyr[7].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[7].x, cyr[7].y, cyr[7].width, cyr[7].height, null);
        }
        if (Filename[8].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[8].x, cyr[8].y, cyr[8].width, cyr[8].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[8].x, cyr[8].y, cyr[8].width, cyr[8].height, null);
        }
        if (Filename[9].equals("Rano.ser")) {
            g.drawImage(Assets.cyr1, cyr[9].x, cyr[9].y, cyr[9].width, cyr[9].height, null);
        } else {
            g.drawImage(Assets.cyr, cyr[9].x, cyr[9].y, cyr[9].width, cyr[9].height, null);
        }

        g.drawImage(Assets.Delet, Delet[0].x, Delet[0].y, Delet[0].width, Delet[0].height, null);
        g.drawImage(Assets.Delet, Delet[1].x, Delet[1].y, Delet[1].width, Delet[1].height, null);
        g.drawImage(Assets.Delet, Delet[2].x, Delet[2].y, Delet[2].width, Delet[2].height, null);
        g.drawImage(Assets.Delet, Delet[3].x, Delet[3].y, Delet[3].width, Delet[3].height, null);
        g.drawImage(Assets.Delet, Delet[4].x, Delet[4].y, Delet[4].width, Delet[4].height, null);
        g.drawImage(Assets.Delet, Delet[5].x, Delet[5].y, Delet[5].width, Delet[5].height, null);
        g.drawImage(Assets.Delet, Delet[6].x, Delet[6].y, Delet[6].width, Delet[6].height, null);
        g.drawImage(Assets.Delet, Delet[7].x, Delet[7].y, Delet[7].width, Delet[7].height, null);
        g.drawImage(Assets.Delet, Delet[8].x, Delet[8].y, Delet[8].width, Delet[8].height, null);
        g.drawImage(Assets.Delet, Delet[9].x, Delet[9].y, Delet[9].width, Delet[9].height, null);

        if (M.intersects(Search)) {
            g.drawImage(Assets.Search1, Search.x, Search.y, Search.width, Search.height, null);
        } else {
            g.drawImage(Assets.Search, Search.x, Search.y, Search.width, Search.height, null);
        }
        for (int e = 0; e < Loadthis.size(); e++) {
            if (M.intersects(Loadthis.get(e))) {
                g.drawImage(Assets.Loadf, Loadthis.get(e).x, Loadthis.get(e).y, Loadthis.get(e).width, Loadthis.get(e).height, null);
            } else {
                g.drawImage(Assets.Loadd, Loadthis.get(e).x, Loadthis.get(e).y, Loadthis.get(e).width, Loadthis.get(e).height, null);
            }
        }
        if (M.intersects(B)) {
            g.drawImage(Assets.Back1, B.x, B.y, B.width, B.height, null);
        } else {
            g.drawImage(Assets.Back0, B.x, B.y, B.width, B.height, null);
        }
        if (M.intersects(QuikSave)) {
            g.drawImage(Assets.QuikSave2, QuikSave.x, QuikSave.y, QuikSave.width, QuikSave.height, null);
        } else {
            g.drawImage(Assets.QuikSave, QuikSave.x, QuikSave.y, QuikSave.width, QuikSave.height, null);
        }
    }

    public void CreateFrame() {

        frame = new JFrame();

        frame.setSize(new Dimension(200, 200));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setSize(new Dimension(500, 200));

        panel = new JPanel();
        field = new JTextField(50);
        //field.setVisible(true);
        button = new JButton();
        button.setText("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FN = field.getText().toString();
                frame.dispose();
                try {
                    s.thread.interrupt();
                } catch (Exception ss) {

                }
            }

        });

        panel.add(field);
        panel.add(button);
        //frame.add(button);
        frame.add(panel);

    }

}
