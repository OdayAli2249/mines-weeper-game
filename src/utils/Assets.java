/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Image;
import java.util.ArrayList;
import static utils.Assets.Ch;
import static utils.Assets.EX;
import static utils.Assets.INV;

/**
 *
 * @author Emperor
 */
public class Assets {

    public static Image[] Ch;
    public static Image INV;
    public static Image BG;
    public static Image EX;
    public static Image Start0;
    public static Image Start1;

    public static Image Option0;
    public static Image Option1;

    public static Image Exit0;
    public static Image Exit1;

    public static Image Back0;
    public static Image Back1;

    public static Image MBG;

    public static Image Option;
    public static Image Option2;

    public static Image Plus;
    public static Image PlusFocus;

    public static Image Minus;
    public static Image MinusFocus;

    public static Image Save0;
    public static Image Save1;

    public static Image B0;
    public static Image B1;
    public static Image MoreOption;
    public static Image MoreOption2;
    public static Image Shield;

    public static Image SaveGame1;
    public static Image SaveGame2;
    public static Image Load;
    public static Image Load2;

    public static Image LoadGameStart;

    public static Image Game1;
    public static Image Game2;
    public static Image Game3;
    public static Image Game4;
    public static Image Game5;
    public static Image Game6;

    public static Image Game7;
    public static Image Game8;
    public static Image Game9;
    public static Image Game10;

    public static Image Game1f;
    public static Image Game2f;
    public static Image Game3f;
    public static Image Game4f;
    public static Image Game5f;
    public static Image Game6f;
    public static Image Game7f;
    public static Image Game8f;
    public static Image Game9f;
    public static Image Game10f;

    public static Image cyr;
    public static Image cyr1;
    public static Image Delet;

    public static Image GameDisplayer1;
    public static Image GameDisplayer2;
    public static Image Search;
    public static Image Search1;
    public static ArrayList<Image> Image;
    public static ArrayList<Image> Image2;
    public static Image Loadf;
    public static Image Loadd;
    public static Image ScoreBoard;
    public static Image SB1;
    public static Image SB2;
    public static Image SBC2;
    public static Image SBC1;
    public static Image SBC3;

    public static Image Sview;
    public static Image Mview;
    public static Image GameDisplay;

    public static Image STE;
    public static Image STS;
    public static Image SNP;

    public static Image STE2;
    public static Image STS2;
    public static Image SNP2;
    public static Image QuikSave;
    public static Image QuikSave2;
    public static Image Countenue;

    public Assets() {
        Image2 = new ArrayList<>();
        Image = new ArrayList<>();
        Ch = new Image[19];
        Ch[0] = ImageLoader.LoadImage("Res/0.jpg");
        Ch[1] = ImageLoader.LoadImage("Res/1.jpg");
        Ch[2] = ImageLoader.LoadImage("Res/2.jpg");
        Ch[3] = ImageLoader.LoadImage("Res/3.jpg");
        Ch[4] = ImageLoader.LoadImage("Res/4.jpg");
        Ch[5] = ImageLoader.LoadImage("Res/5.jpg");
        Ch[6] = ImageLoader.LoadImage("Res/6.jpg");
        Ch[7] = ImageLoader.LoadImage("Res/7.jpg");
        Ch[8] = ImageLoader.LoadImage("Res/8.jpg");
        Ch[9] = ImageLoader.LoadImage("Res/9.jpg");

        Ch[10] = ImageLoader.LoadImage("Res/0S.jpg");
        Ch[11] = ImageLoader.LoadImage("Res/1S.jpg");
        Ch[12] = ImageLoader.LoadImage("Res/2S.jpg");
        Ch[13] = ImageLoader.LoadImage("Res/3S.jpg");
        Ch[14] = ImageLoader.LoadImage("Res/4S.jpg");
        Ch[15] = ImageLoader.LoadImage("Res/5S.jpg");
        Ch[16] = ImageLoader.LoadImage("Res/6S.jpg");
        Ch[17] = ImageLoader.LoadImage("Res/7S.jpg");
        Ch[18] = ImageLoader.LoadImage("Res/8S.jpg");
        INV = ImageLoader.LoadImage("Res/INV.png");

        BG = ImageLoader.LoadImage("Res/BG.png");
        EX = ImageLoader.LoadImage("Res/EX.png");
        MBG = ImageLoader.LoadImage("Res/MBG.png");
        Start0 = ImageLoader.LoadImage("Res/Start0.png");
        Start1 = ImageLoader.LoadImage("Res/Start1.png");
        Back0 = ImageLoader.LoadImage("Res/Back0.png");
        Back1 = ImageLoader.LoadImage("Res/Back1.png");
        Option0 = ImageLoader.LoadImage("Res/Option0.png");
        Option1 = ImageLoader.LoadImage("Res/Option1.png");
        Exit0 = ImageLoader.LoadImage("Res/Exit0.png");
        Exit1 = ImageLoader.LoadImage("Res/Exit1.png");
        Option = ImageLoader.LoadImage("Res/Option.png");
        Option2 = ImageLoader.LoadImage("Res/Option2.jpg");
        Plus = ImageLoader.LoadImage("Res/+.png");
        PlusFocus = ImageLoader.LoadImage("Res/+f.png");
        Minus = ImageLoader.LoadImage("Res/-.png");
        MinusFocus = ImageLoader.LoadImage("Res/-f.png");
        Save0 = ImageLoader.LoadImage("Res/Save0.png");
        Save1 = ImageLoader.LoadImage("Res/Save1.png");
        B0 = ImageLoader.LoadImage("Res/B0.png");

        B1 = ImageLoader.LoadImage("Res/B1.png");
        MoreOption = ImageLoader.LoadImage("Res/MoreOption.jpg");
        MoreOption2 = ImageLoader.LoadImage("Res/MoreOption3.jpg");
        Shield = ImageLoader.LoadImage("Res/Sh.jpg");

        SaveGame1 = ImageLoader.LoadImage("Res/SaveGame1.png");
        SaveGame2 = ImageLoader.LoadImage("Res/SaveGame2.png");
        Load = ImageLoader.LoadImage("Res/Load.png");
        Load2 = ImageLoader.LoadImage("Res/Load2.png");

        Game1 = ImageLoader.LoadImage("Res/Game1.png");
        Game2 = ImageLoader.LoadImage("Res/Game2.png");
        Game3 = ImageLoader.LoadImage("Res/Game3.png");
        Game4 = ImageLoader.LoadImage("Res/Game4.png");
        Game5 = ImageLoader.LoadImage("Res/Game5.png");

        Game6 = ImageLoader.LoadImage("Res/Game6.png");
        Game7 = ImageLoader.LoadImage("Res/Game7.png");
        Game8 = ImageLoader.LoadImage("Res/Game8.png");
        Game9 = ImageLoader.LoadImage("Res/Game9.png");
        Game10 = ImageLoader.LoadImage("Res/Game10.png");

        Game1f = ImageLoader.LoadImage("Res/Game1f.png");
        Game2f = ImageLoader.LoadImage("Res/Game2f.png");
        Game3f = ImageLoader.LoadImage("Res/Game3f.png");
        Game4f = ImageLoader.LoadImage("Res/Game4f.png");
        Game5f = ImageLoader.LoadImage("Res/Game5f.png");

        Game6f = ImageLoader.LoadImage("Res/Game6f.png");
        Game7f = ImageLoader.LoadImage("Res/Game7f.png");
        Game8f = ImageLoader.LoadImage("Res/Game8f.png");
        Game9f = ImageLoader.LoadImage("Res/Game9f.png");
        Game10f = ImageLoader.LoadImage("Res/Game10f.png");
        LoadGameStart = ImageLoader.LoadImage("Res/LoadGameStart.png");
        cyr = ImageLoader.LoadImage("Res/cyr.png");
        cyr1 = ImageLoader.LoadImage("Res/cyr1.png");
        Delet = ImageLoader.LoadImage("Res/Delet.png");
        GameDisplayer1 = ImageLoader.LoadImage("Res/GameDisplayer1.png");
        GameDisplayer2 = ImageLoader.LoadImage("Res/GameDisplayer2.png");
        Search = ImageLoader.LoadImage("Res/Search.png");
        Search1 = ImageLoader.LoadImage("Res/Search1.png");
        Loadf = ImageLoader.LoadImage("Res/Loadf.png");
        Loadd = ImageLoader.LoadImage("Res/Loadd.png");
        ScoreBoard = ImageLoader.LoadImage("Res/ScoreBoard.png");
        SB1 = ImageLoader.LoadImage("Res/SB1.png");
        SB2 = ImageLoader.LoadImage("Res/SB2.png");
        SBC1 = ImageLoader.LoadImage("Res/ScoreBoardCy1.png");
        SBC2 = ImageLoader.LoadImage("Res/ScoreBoardCy2.png");
        Mview = ImageLoader.LoadImage("Res/Mview.png");
        Sview = ImageLoader.LoadImage("Res/Sview.png");
        SBC3 = ImageLoader.LoadImage("Res/ScoreBoardCy3.png");
        GameDisplay = ImageLoader.LoadImage("Res/GameDisplay.png");
        STE = ImageLoader.LoadImage("Res/STE.png");
        STS = ImageLoader.LoadImage("Res/STS.png");
        SNP = ImageLoader.LoadImage("Res/SPN.png");
        STE2 = ImageLoader.LoadImage("Res/STE2.png");
        STS2 = ImageLoader.LoadImage("Res/STS2.png");
        SNP2 = ImageLoader.LoadImage("Res/SPN2.png");
        QuikSave = ImageLoader.LoadImage("Res/QuikSave.png");
        QuikSave2 = ImageLoader.LoadImage("Res/QuikSave2.png");
        Countenue = ImageLoader.LoadImage("Res/Coutenue.png");
        Image.add(Game1);
        Image.add(Game2);
        Image.add(Game3);
        Image.add(Game4);
        Image.add(Game5);
        Image.add(Game6);
        Image.add(Game7);
        Image.add(Game8);
        Image.add(Game9);
        Image.add(Game10);
        Image2.add(Game1f);
        Image2.add(Game2f);
        Image2.add(Game3f);
        Image2.add(Game4f);
        Image2.add(Game5f);
        Image2.add(Game6f);
        Image2.add(Game7f);
        Image2.add(Game8f);
        Image2.add(Game9f);
        Image2.add(Game10f);

    }

    public static Image ImageChoicer(int Order, boolean Visible, boolean Expected) {

        if (Visible) {
            return Ch[Order];
        } else if (Visible == false && Expected == true) {
            return EX;
        } else {
            return INV;
        }
    }

    public static Image ImageChoicer2(int Order, boolean Visible, boolean Expected, char s) {

        if (Visible) {
            return Ch[Order];
        } else if (Visible == false && Expected == true) {
            return EX;
        } else {
            if (s == 'S') {
                return Sview;
            } else if (s == 'M') {
                return Mview;

            } else {
                return INV;
            }
        }
    }

}
