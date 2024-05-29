package com.itedamascusuniversity.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class LargSizeArrayGenerator extends ArrayGenerator implements Serializable {

    public static int FIELDWIDTH = 12;
    public static int FIELDHIEGHT = 12;
    public static int MINENUMBER = 10;
    public static int ShieldNumber = 15;

    public LargSizeArrayGenerator() {
        FieldWidth = FIELDWIDTH;
        FieldHieght = FIELDHIEGHT;
        MineNumber = MINENUMBER;
        Shield = ShieldNumber;
        Field = new int[FieldHieght][FIELDWIDTH];
        Initialize();
    }

    public void Initialize() {
        for (int i = 0; i < FieldHieght; i++) {
            for (int j = 0; j < FieldWidth; j++) {
                Field[i][j] = 0;
            }
        }
    }

    @Override
    public void GenerateANewField() {
        

        int MineAdded = 0;
    
        while (MineAdded != MineNumber) {

            int x = RW.nextInt(FieldHieght);
            int y = RH.nextInt(FieldWidth);

            if (Field[x][y] != 1) {
                Field[x][y] = 1;
                MineAdded++;
            }

        }

        int ShieldAdded = 0;
        while (ShieldAdded != ShieldNumber) {
            int Sx = RW.nextInt(FieldHieght);
            int Sy = RH.nextInt(FieldWidth);

            if (Field[Sx][Sy] != 2 && Field[Sx][Sy] != 1) {
                Field[Sx][Sy] = 2;
                ShieldAdded++;
            }

        }
       

    }

    public void TemporaryMeth() {
        for (int i = 0; i < FieldHieght; i++) {
            for (int j = 0; j < FieldWidth; j++) {
                System.out.print(Field[i][j]);
            }
            System.out.println("");
        }
    }

    public int getElementOfEndex(int x, int y) {
        return Field[x][y];
    }

    public int getFieldWidth() {
        return FieldWidth;
    }

    public int getFieldHieght() {
        return FieldHieght;
    }
    
    public void setFieldWidth(int w) {
        FieldWidth=w;
    }

    public void setFieldHieght(int H) {
         FieldHieght=H;
    }


    public int getNumberofEmptyAndNumericalCell() {
        return ((FIELDWIDTH * FIELDHIEGHT) - MINENUMBER);
    }

    public void generateShield() {
        int ShieldAdded = 0;
        while (ShieldAdded != ShieldNumber) {
            int Sx = RW.nextInt(FieldHieght);
            int Sy = RH.nextInt(FieldWidth);

            if (Field[Sx][Sy] != 2) {
                Field[Sx][Sy] = 2;
                ShieldAdded++;
            }

        }
    }

public void GenerateANewField2() throws FileNotFoundException, IOException {

                BufferedReader reader = null;
                    File f = new File("text.txt");
                    reader = new BufferedReader(new FileReader(f));
                    String currentLine;
                    int nunmberofline = 1;
                    int ff;
                        while ((currentLine = reader.readLine()) != null) {

                            if (nunmberofline == 1) {
                                FIELDWIDTH = (currentLine.charAt(0) * 10) + currentLine.charAt(1);

                                nunmberofline++;

                            } else if (nunmberofline == 2) {
                                FieldHieght = (currentLine.charAt(0) * 10) + currentLine.charAt(1);

                                nunmberofline++;

                            } else {
                                int i = 0;
                                int x, y, c, NumberOfpump;
                                while (i < currentLine.length()) {

                                    if (currentLine.charAt(i) == 'M') {
                                        i += 2;
                                        x = currentLine.charAt(i);
                                        i += 1;
                                        if (currentLine.charAt(i) == ',') {
                                            i++;
                                            y = currentLine.charAt(i);
                                            i++;
                                            if (currentLine.charAt(i) == ')') {
                                                Field[x][y] = 1;

                                            } else {
                                                y = (y * 10) + currentLine.charAt(i);
                                                Field[x][y] = 1;
                                            }

                                        } else {
                                            x = (x * 10) + currentLine.charAt(i);

                                            i++;
                                            y = currentLine.charAt(i);
                                            i++;
                                            if (currentLine.charAt(i) == ')') {
                                                Field[x][y] = 1;

                                            } else {
                                                y = (y * 10) + currentLine.charAt(i);
                                                Field[x][y] = 1;
                                            }

                                        }
                                        i = currentLine.length();

                                    } else if (currentLine.charAt(i) == 't') {

                                        i += 2;
                                        x = currentLine.charAt(i);
                                        i += 1;
                                        if (currentLine.charAt(i) == ',') {
                                            i++;
                                            y = currentLine.charAt(i);
                                            i++;
                                            if (currentLine.charAt(i) == ')') {
                                                Field[x][y] = 2;

                                            } else {
                                                y = (y * 10) + currentLine.charAt(i);
                                                Field[x][y] = 2;
                                            }

                                        } else {
                                            x = (x * 10) + currentLine.charAt(i);

                                            i++;
                                            y = currentLine.charAt(i);
                                            i++;
                                            if (currentLine.charAt(i) == ')') {
                                                Field[x][y] = 2;

                                            } else {
                                                y = (y * 10) + currentLine.charAt(i);
                                                Field[x][y] = 2;
                                            }

                                        }
                                        i = currentLine.length();

                                    }
                                }
                            }
                        }
                        reader.close();
                   
            }


        }
    


