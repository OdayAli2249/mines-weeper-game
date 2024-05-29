/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.damascusuniversity.io;

import com.itedamascusuniversity.generator.LargSizeArrayGenerator;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import players.Player;
import utils.Assets;

/**
 *
 * @author Emperor
 */
public class OptionUI extends UserInterfaceIO implements ActionListener {

    private Rectangle[] IncreaseButtons;
    private Rectangle[] DecreaseButtons;
    private final int ButtonsNumber = 11;

    private Rectangle SaveButton;
    private Rectangle BackButton;
    private Rectangle MoreOption2;
    private Rectangle TimePlus;
    private Rectangle TimeMinus;

    private int[] Values;
    private int[] Distance;
    private int TimeNumber = 5;

    public OptionUI() {

        IncreaseButtons = new Rectangle[ButtonsNumber];
        DecreaseButtons = new Rectangle[ButtonsNumber];

        SaveButton = new Rectangle(620, 590, 200, 40);
        BackButton = new Rectangle(5, 3, 150, 40);
        MoreOption2 = new Rectangle(200, 600, 160, 40);
        TimePlus = new Rectangle(570, 163, 20, 20);
        TimeMinus = new Rectangle(470, 170, 20, 20);

        Values = new int[ButtonsNumber];
        Distance = new int[ButtonsNumber];

        InitializeIncreaseRect();
        InitializeDereaseRect();
        InitializeValues();
        InitializeDistance();

    }

    @Override
    public void setMeToBasicFrame() {

    }

    @Override
    public void HandlingInput() {

        Rectangle Temp = new Rectangle(x, y, 1, 1);

        if (Temp.intersects(BackButton)) {
            setCurrentUserInterface(mainui);
        }
        if (Temp.intersects(MoreOption2)) {
            setCurrentUserInterface(moreoption2);
        }

        if (Temp.intersects(TimePlus)) {
            if (TimeNumber < 15) {
                TimeNumber++;
            }
        }
        if (Temp.intersects(TimeMinus)) {
            if (TimeNumber > 5) {
                TimeNumber--;
            }
        }

        if (Temp.intersects(SaveButton)) {

            // Players
            CellsManager.setPlayersNumber(Values[0], Values[1]);

            // Board
            LargSizeArrayGenerator.FIELDWIDTH = Values[2];
            LargSizeArrayGenerator.FIELDHIEGHT = Values[3];
            LargSizeArrayGenerator.MINENUMBER = Values[4];

            // Score Logic
            Player.setMineicCellPresses(Values[5]);
            Player.setEmptyCellPresses(Values[6]);
            Player.setAutomaticCellOpened(Values[7]);
            Player.setNumericCellPresses(Values[8]);
            Player.setCorrectMineExpectState(Values[9]);
            Player.setWrongMineExpectState(Values[10]);

            GUIio.getTimer().setTimeLimit(TimeNumber*1000);
            GUIio.getTimer().Reset();
            GUIio.ApdateCellsDimension();
            GUIio.setNewCM(true);
            GUIio.setGameStarted(false);
            setCurrentUserInterface(mainui);
        }

        for (int i = 0; i < ButtonsNumber; i++) {

            if (Temp.intersects(IncreaseButtons[i])) {
                if (-1 < i && i < 2) {

                    if (i == 0) {
                        if (Values[1] > 0) {
                            if (Values[0] == 0) {
                                Values[0]++;
                            }

                        } else if (Values[0] < 2) {
                            Values[0]++;
                        }
                    }

                    if (i == 1) {

                        if (Values[0] == 1) {
                            if (Values[1] == 0) {
                                Values[1]++;
                            }
                        }

                    }
                    /*
                    if (Values[i] < 3) {
                        Values[i]++;
                    }
                     */
                } else if (i == 2 || i == 3) {

                    if (Values[i] < 31) {
                        Values[i]++;
                    }

                } else if (i == 4) {

                    if (Values[i] < Values[4] * Values[3]) {
                        Values[i]++;
                    }

                } else if (Values[i] < 200) {
                    Values[i] += 5;
                }
            }
            if (Temp.intersects(DecreaseButtons[i])) {
                if (-1 < i && i < 2) {

                    if (Values[i] > 0) {
                        Values[i]--;
                    }

                } else if (i == 2 || i == 3) {

                    if (Values[i] > 7) {
                        Values[i]--;
                    }

                } else if (i == 4) {

                    if (Values[i] > 10) {
                        Values[i]--;
                    }

                } else if (Values[i] > -200) {
                    Values[i] -= 5;
                }
            }

        }

    }

    @Override
    public void DisplayOutput(CellsManager cellsmanager) {
    }

    @Override
    public void Apdate(CellsManager cellsmanager) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void Render(Graphics g, CellsManager c) {

        Rectangle Temp = new Rectangle(x, y, 1, 1);

        g.drawImage(Assets.Option, 0, 0, Display.getFrameWidth(), Display.getFrameHieght(), null);

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

        if (Temp.intersects(TimePlus)) {
            g.drawImage(Assets.PlusFocus, TimePlus.x, TimePlus.y, TimePlus.width, TimePlus.height, null);
        } else {
            g.drawImage(Assets.Plus, TimePlus.x, TimePlus.y, TimePlus.width, TimePlus.height, null);
        }

        if (Temp.intersects(TimeMinus)) {
            g.drawImage(Assets.MinusFocus, TimeMinus.x, TimeMinus.y, TimeMinus.width, TimeMinus.height, null);
        } else {
            g.drawImage(Assets.Minus, TimeMinus.x, TimeMinus.y, TimeMinus.width, TimeMinus.height, null);
        }

        g.setColor(Color.WHITE);
        g.drawString("" + TimeNumber, 523, 178);

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
        if (Temp.intersects(MoreOption2)) {
            g.drawImage(Assets.MoreOption2, MoreOption2.x, MoreOption2.y, MoreOption2.width, MoreOption2.height, null);
        } else {
            g.drawImage(Assets.MoreOption, MoreOption2.x, MoreOption2.y, MoreOption2.width, MoreOption2.height, null);
        }

    }

    private void InitializeIncreaseRect() {
        IncreaseButtons[0] = new Rectangle(570, 70, 20, 20);
        IncreaseButtons[1] = new Rectangle(570, 115, 20, 20);
        // IncreaseButtons[2] = new Rectangle(570, 170, 20, 20);
        IncreaseButtons[2] = new Rectangle(570, 220, 20, 20);
        IncreaseButtons[3] = new Rectangle(570, 260, 20, 20);
        IncreaseButtons[4] = new Rectangle(570, 303, 20, 20);
        IncreaseButtons[5] = new Rectangle(570, 360, 20, 20);
        IncreaseButtons[6] = new Rectangle(570, 395, 20, 20);
        IncreaseButtons[7] = new Rectangle(570, 440, 20, 20);
        IncreaseButtons[8] = new Rectangle(570, 480, 20, 20);
        IncreaseButtons[9] = new Rectangle(570, 525, 20, 20);
        IncreaseButtons[10] = new Rectangle(570, 567, 20, 20);

    }

    private void InitializeDereaseRect() {
        DecreaseButtons[0] = new Rectangle(470, 70, 20, 20);
        DecreaseButtons[1] = new Rectangle(470, 115, 20, 20);
        //DecreaseButtons[2] = new Rectangle(470, 170, 20, 20);
        DecreaseButtons[2] = new Rectangle(470, 220, 20, 20);
        DecreaseButtons[3] = new Rectangle(470, 260, 20, 20);
        DecreaseButtons[4] = new Rectangle(470, 303, 20, 20);
        DecreaseButtons[5] = new Rectangle(470, 360, 20, 20);
        DecreaseButtons[6] = new Rectangle(470, 395, 20, 20);
        DecreaseButtons[7] = new Rectangle(470, 440, 20, 20);
        DecreaseButtons[8] = new Rectangle(470, 480, 20, 20);
        DecreaseButtons[9] = new Rectangle(470, 525, 20, 20);
        DecreaseButtons[10] = new Rectangle(470, 567, 20, 20);
    }

    private void InitializeValues() {
        Values[0] = 1;
        Values[1] = 1;
        //   Values[2] = 0;
        Values[2] = 10;
        Values[3] = 10;
        Values[4] = 30;
        Values[5] = -250;
        Values[6] = 10;
        Values[7] = 1;
        Values[8] = -1;
        Values[9] = 5;
        Values[10] = -1;
    }

    private void InitializeDistance() {
        Distance[0] = 78;
        Distance[1] = 123;
        //     Distance[2] = 178;
        Distance[2] = 228;
        Distance[3] = 268;
        Distance[4] = 310;
        Distance[5] = 373;
        Distance[6] = 403;
        Distance[7] = 448;
        Distance[8] = 488;
        Distance[9] = 533;
        Distance[10] = 575;

    }
}
