/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameInfo implements Serializable {

    public static final String FileName = "Source.txt";

    private static int GameID;
    private int[] GameScore;
    private String[] PlayersName;
    private String GameSource;
    public static ArrayList<String> FilesName;
    public String FileNAmeDisplayer;
    public int[] PlayerID;

    // متحولات اضافية للتاريخ
    public String StartGameDate;
    public String EndGameDate;

    // اضافة المتحولات ضمن بارامترات الباني بالقيم
    public GameInfo(int GameID, int[] GameScore, int[] PlayerID, String[] PlayersName, String FileDisplayer, String StartGameDate, String EndGameDate) {

        this.GameID = GameID;
        this.GameScore = GameScore;
        this.PlayersName = PlayersName;
        this.GameSource = "Source" + GameID;
        this.FilesName = new ArrayList<String>();
        this.FileNAmeDisplayer = FileDisplayer;
        this.PlayerID = PlayerID;

        /*  ضفت هدول المتحولين بعبرو عن تاريخ بدء وانتهاء لعبة معينة هي متحولين سترينغ */
 /* ينكتبو بالشكل */
 /* number/number/number */
 /* حصرا والا بصير خطأ */
        this.StartGameDate = StartGameDate;
        this.EndGameDate = EndGameDate;

    }

    public void addGame(GameInfo gameinfo) throws FileNotFoundException, IOException {
        ReadSourcesFile();
        File f = new File(GameSource);
        ObjectOutputStream printwriter = new ObjectOutputStream(new FileOutputStream((f)));

        printwriter.writeObject(gameinfo);

        printwriter.close();
        FilesName.add(GameSource);
        AddCurrentFileName();

    }

    public static GameInfo readGame(String FN) throws FileNotFoundException, IOException, ClassNotFoundException {

        File f = new File(FN);

        ObjectInputStream printwriter2 = null;

        printwriter2 = new ObjectInputStream(new FileInputStream(f));

        GameInfo GI = (GameInfo) printwriter2.readObject();
        for (int i = 0; i < GI.PlayerID.length; i++) {
            System.out.println("Id" + GI.PlayerID[i]);
            System.out.println("Name" + GI.PlayersName[i]);
            System.out.println("Score" + GI.GameScore[i]);

        }
        System.out.println("Sorce" + GI.GameSource);

        return GI;

    }

    public void setGameScore(int[] GameScore) {

        this.GameScore = GameScore;

    }

    public void setGameID(int GameID) {

        this.GameID = GameID;

    }

    public void setPlayersName(String PlayerName) {

        this.PlayersName = PlayersName;

    }

    public int getGameID() {

        return GameID;

    }

    public int[] getGameScore() {

        return GameScore;

    }

    public String[] getPlayerName() {

        return PlayersName;

    }

    public String getGameSource() {

        return GameSource;

    }

    public static void ReadSourcesFile() throws FileNotFoundException, IOException {

        File f = new File(FileName);
        BufferedReader Reader = new BufferedReader(new FileReader(f));
        String File;
        while ((File = Reader.readLine()) != null) {

            FilesName.add(File);

        }

        Reader.close();

    }

    public void AddCurrentFileName() throws FileNotFoundException, IOException {

        File f = new File(FileName);
        PrintWriter writer = new PrintWriter(new FileWriter(f));

        for (String FN : FilesName) {

            writer.println(FN);

        }

        writer.close();

    }

    public void Display(GameInfo GI) {

        for (int i = 0; i < GI.PlayerID.length; i++) {
            System.out.println("Id" + GI.PlayerID[i]);
            System.out.println("Name" + GI.PlayersName[i]);
            System.out.println("Score" + GI.GameScore[i]);

        }
        System.out.println("Sorce" + GI.GameSource);

    }

    /* ضفت تلت توابع كل واحد بياخد ليست وبرجعا مرتبة تبعا لمتحول معين */
    public static Comparator<GameInfo> PlayerNameComparator = new Comparator<GameInfo>() {

        public int compare(GameInfo g1, GameInfo g2) {
            String PlayerName1 = g1.getPlayerName()[0].toUpperCase();
            String PlayerName2 = g2.getPlayerName()[0].toUpperCase();

            return PlayerName1.compareTo(PlayerName2);

        }
    };

    /* بياخد ليست معلومات الالعاب وبرجعا مرتبة تبعا لمتحول اسماء اللاعبين */
 /* بيرتب الليست على حسب التسلسل الابجدي لاسم اللاعب الاول من كل لعبة */
    public static ArrayList<GameInfo> CreateNamesSorting(ArrayList<GameInfo> list) {
        Collections.sort(list, GameInfo.PlayerNameComparator);
        return list;
    }

    public static Comparator<GameInfo> GameDateComparator = new Comparator<GameInfo>() {

        @Override
        public int compare(GameInfo g1, GameInfo g2) {
            String StartGameDate1 = g1.getStartGameDate();
            String StartGameDate2 = g2.getStartGameDate();

            String[] DMY1 = StartGameDate1.split(":");
            String[] DMY2 = StartGameDate2.split(":");

            int[] Date1 = new int[6];
            int[] Date2 = new int[6];

            Date1[0] = DMY1[0].charAt(0);
            Date1[1] = DMY1[0].charAt(1);
            Date1[2] = DMY1[1].charAt(0);
            Date1[3] = DMY1[1].charAt(1);
            Date1[4] = DMY1[2].charAt(0);
            Date1[5] = DMY1[2].charAt(1);

            Date2[0] = DMY2[0].charAt(0);
            Date2[1] = DMY2[0].charAt(1);
            Date2[2] = DMY2[1].charAt(0);
            Date2[3] = DMY2[1].charAt(1);
            Date2[4] = DMY2[2].charAt(0);
            Date2[5] = DMY2[2].charAt(1);

            int LastDate1 = (Date1[0] * 10 + Date1[1]) * 3600 + (Date1[2] * 10 + Date1[3]) * 60 + (Date1[4] * 10 + Date1[5]);
            int LastDate2 = (Date2[0] * 10 + Date2[1]) * 3600 + (Date2[2] * 10 + Date2[3]) * 60 + (Date2[4] * 10 + Date2[5]);

            return LastDate1 - LastDate2;

        }
    };

    /* تابع بياخد ليست وبرجعا مرتبة تبعا لتاريخ بدء اللعبة  */
 /* لازم يكون متحول السترينغ اللي بعبر عن التاريخ مكتوب بالشكل حصرا */
 /* number / number / number */
    public static ArrayList<GameInfo> CreateDatesSorting(ArrayList<GameInfo> list) {
        Collections.sort(list, GameInfo.GameDateComparator);
        return list;
    }

    public static Comparator<GameInfo> EndGameDateComparator = new Comparator<GameInfo>() {

        @Override
        public int compare(GameInfo g1, GameInfo g2) {
            String StartGameDate1 = g1.getEndGameDate();
            String StartGameDate2 = g2.getEndGameDate();
            String[] DMY1 = StartGameDate1.split(":");
            String[] DMY2 = StartGameDate2.split(":");

            int[] Date1 = new int[6];
            int[] Date2 = new int[6];

            Date1[0] = DMY1[0].charAt(0);
            Date1[1] = DMY1[0].charAt(1);
            Date1[2] = DMY1[1].charAt(0);
            Date1[3] = DMY1[1].charAt(1);
            Date1[4] = DMY1[2].charAt(0);
            Date1[5] = DMY1[2].charAt(1);

            Date2[0] = DMY2[0].charAt(0);
            Date2[1] = DMY2[0].charAt(1);
            Date2[2] = DMY2[1].charAt(0);
            Date2[3] = DMY2[1].charAt(1);
            Date2[4] = DMY2[2].charAt(0);
            Date2[5] = DMY2[2].charAt(1);

            int LastDate1 = (Date1[0] * 10 + Date1[1]) * 3600 + (Date1[2] * 10 + Date1[3]) * 60 + (Date1[4] * 10 + Date1[5]);
            int LastDate2 = (Date2[0] * 10 + Date2[1]) * 3600 + (Date2[2] * 10 + Date2[3]) * 60 + (Date2[4] * 10 + Date2[5]);

            return LastDate1 - LastDate2;

        }
    };

    /* نفس التابع السابق بس انو برجع الليست اللي بنعطيها للتابع مرتبة تبعا لتاريخ نهاية اللعبة */
    public static ArrayList<GameInfo> CreateEndDatesSorting(ArrayList<GameInfo> list) {
        Collections.sort(list, GameInfo.EndGameDateComparator);
        return list;
    }

    /* gette .. */
    public String getStartGameDate() {
        return StartGameDate;
    }

    public String getEndGameDate() {
        return EndGameDate;
    }

}
