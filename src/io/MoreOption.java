/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damascusuniversity.io;

import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import static com.damascusuniversity.io.UserInterfaceIO.Display;
import static com.damascusuniversity.io.UserInterfaceIO.mainui;
import static com.damascusuniversity.io.UserInterfaceIO.optionui;
import com.itedamascusuniversity.generator.LargSizeArrayGenerator;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import players.AutomaticPlayer;
import players.HumanPlayer;
import players.Player;
import utils.Assets;

/**
 *
 * @author Ranoom
 */
public class MoreOption extends UserInterfaceIO implements ActionListener {
    
    private Rectangle[] IncreaseButtons;
    private Rectangle[] DecreaseButtons;
    private final int ButtonsNumber = 7;
    
    private Rectangle SaveButton;
    private Rectangle BackButton;
    //private Rectangle MoreOption2;

    private int[] Values;
    private int[] Distance;
    
    MoreOption() {
        
        IncreaseButtons = new Rectangle[ButtonsNumber];
        DecreaseButtons = new Rectangle[ButtonsNumber];
        
        SaveButton = new Rectangle(620, 590, 200, 40);
        BackButton = new Rectangle(5, 3, 150, 40);
        Values = new int[ButtonsNumber];
        Distance = new int[ButtonsNumber];
        
        InitializeIncreaseRect2();
        InitializeDereaseRect2();
        InitializeValues2();
        InitializeDistance2();
    }
    
    @Override
    public void setMeToBasicFrame() {
    }
    
    @Override
    public void HandlingInput() {
        
        Rectangle Temp = new Rectangle(x, y, 1, 1);
        if (Temp.intersects(BackButton)) {
            setCurrentUserInterface(optionui);
        }
        
        if (Temp.intersects(SaveButton)) {
            Player.setNumberofshieldtoplayer(Values[0]);
            LargSizeArrayGenerator.ShieldNumber = Values[1];
            CellsManager.setTypeOfPlayersWhoCannotHaveShield(Values[2]);
         
            AutomaticPlayer.setstateofShield(Values[3]);
            HumanPlayer.setstateofShield(Values[4]); 
            CellsManager.setShieldscore(Values[5]);
            Player.setTheShieldAndThePunishmentOfTheMine(Values[6]);
            GUIio.ApdateCellsDimension();
            GUIio.setNewCM(true);
            GUIio.setGameStarted(false);
            setCurrentUserInterface(mainui);
            
        }
        
        for (int i = 0; i < ButtonsNumber; i++) {
            
            if (Temp.intersects(IncreaseButtons[i])) {
                if (-1 < i && i == 0) {///عدد الدروع يلي بدي وزعها عاللاعبين

                    if (Values[i] < 2) {
                        Values[i]++;
                    }
                }
                if (i == 1) {//عدد دروع الرقعه
                    if (Values[i] < 15) {
                        Values[i]++;
                        
                    }
                }
                if (i == 2) {
                    if (Values[i] < 3) {
                        Values[i]++;
                        
                    }
                }
                if (i == 5) {
                    if (Values[i] < 75) {
                        Values[i]++;
                    }
                    
                }
                if (i == 3 || i == 4) {
                    if (Values[i] < 1) {
                        Values[i]++;
                    }
                    
                }
                if (i == 6) {
                    if (Values[i] < 250) {
                        Values[i]++;
                    }
                    
                }
                
            }
            if (Temp.intersects(DecreaseButtons[i])) {
                
                if (i == 0 || i == 1 || i == 2) {///عدد الدروع يلي بدي وزعها عاللاعبين
                    if (Values[i] > 0) {
                        Values[i]--;
                    }
                    
                }
                if (i == 5) {
                    if (Values[i] > 25) {
                        Values[i]--;
                    }
                    
                }
                if (i == 4 || i == 3) {
                    if (Values[i] > 0) {
                        Values[i]--;
                    }
                }
                if (i == 6) {
                    if (Values[i] > 50) {
                        Values[i]--;
                    }
                    
                }
                
            }
        }
    }
    
    @Override
    public void Render(Graphics g, CellsManager c) {
        Rectangle Temp = new Rectangle(x, y, 1, 1);
        g.drawImage(Assets.Option2, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);
        for (int i = 0; i < ButtonsNumber; i++) {
            
            if (Temp.intersects(IncreaseButtons[i])) {
                g.drawImage(Assets.PlusFocus, IncreaseButtons[i].x, IncreaseButtons[i].y, IncreaseButtons[i].width, IncreaseButtons[i].height, null);
            } else {
                g.drawImage(Assets.Plus, IncreaseButtons[i].x, IncreaseButtons[i].y, IncreaseButtons[i].width, IncreaseButtons[i].height, null);
            }
            
            if (Temp.intersects(DecreaseButtons[i])) {
                g.drawImage(Assets.MinusFocus, DecreaseButtons[i].x, DecreaseButtons[i].y, DecreaseButtons[i].width, DecreaseButtons[i].height, null);
            } else {
                g.drawImage(Assets.Minus, DecreaseButtons[i].x, DecreaseButtons[i].y, DecreaseButtons[i].width, DecreaseButtons[i].height, null);
            }
            g.setColor(Color.WHITE);
            g.drawString("" + Values[i], 523, Distance[i]);
            
        }
        
        if (Temp.intersects(SaveButton)) {
            g.drawImage(Assets.Save1, SaveButton.x, SaveButton.y, SaveButton.width, SaveButton.height, null);
        } else {
            g.drawImage(Assets.Save0, SaveButton.x, SaveButton.y, SaveButton.width, SaveButton.height, null);
        }
        
        if (Temp.intersects(BackButton)) {
            g.drawImage(Assets.B1, BackButton.x, BackButton.y, BackButton.width, BackButton.height, null);
        } else {
            g.drawImage(Assets.B0, BackButton.x, BackButton.y, BackButton.width, BackButton.height, null);
        }
        
        
        
        
    }
    
    private void InitializeIncreaseRect2() {
        IncreaseButtons[0] = new Rectangle(570, 70, 20, 20);
        IncreaseButtons[1] = new Rectangle(570, 115, 20, 20);
        //IncreaseButtons[2] = new Rectangle(570, 170, 20, 20);
        IncreaseButtons[2] = new Rectangle(570, 260, 20, 20);
        IncreaseButtons[3] = new Rectangle(570, 370, 20, 20);
        IncreaseButtons[4] = new Rectangle(570, 415, 20, 20);
        IncreaseButtons[5] = new Rectangle(570, 490, 20, 20);
        IncreaseButtons[6] = new Rectangle(570, 550, 20, 20);
        
    }
    
    private void InitializeDereaseRect2() {
        DecreaseButtons[0] = new Rectangle(470, 70, 20, 20);
        DecreaseButtons[1] = new Rectangle(470, 115, 20, 20);
        // DecreaseButtons[2] = new Rectangle(470, 170, 20, 20);
        DecreaseButtons[2] = new Rectangle(470, 260, 20, 20);
        DecreaseButtons[3] = new Rectangle(470, 370, 20, 20);
        DecreaseButtons[4] = new Rectangle(470, 415, 20, 20);
        DecreaseButtons[5] = new Rectangle(470, 490, 20, 20);
        DecreaseButtons[6] = new Rectangle(470, 550, 20, 20);
        
    }
    
    private void InitializeValues2() {
        Values[0] = 1;
        Values[1] = 5;
        Values[2] = 0;
        Values[5] = 50;
        Values[3] = 0;
        Values[4] = 1;
        Values[6] = 250;
        
    }
    
    private void InitializeDistance2() {
        Distance[0] = 78;
        Distance[1] = 123;
        Distance[2] = 268;
        Distance[3] = 383;
        Distance[4] = 423;
        Distance[5] = 500;
        Distance[6] = 560;
        
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
    public void actionPerformed(ActionEvent e) {
    }
    
}
