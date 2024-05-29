package com.itedamascusuniversity.generator;

import java.util.Random;

public abstract class ArrayGenerator {

    protected int[][] Field;
    protected int FieldWidth;
    protected int FieldHieght;
    protected int MineNumber;
    protected int Shield;
    protected Random RW;
    protected Random RWs;

    protected Random RH;
    protected Random RHs;

    public ArrayGenerator() {

        RW = new Random();
        RH = new Random();

        RWs = new Random();
        RHs = new Random();
    }

    public abstract void GenerateANewField();
//public abstract void  generateShield();

    public int[][] getField() {
        return Field;
    }

    public void setField(int[][] field) {
        Field = field;
    }

    public int getFieldWidth() {
        return FieldWidth;
    }

    public void setFieldWidth(int fieldWidth) {
        FieldWidth = fieldWidth;
    }

    public int getFieldHieght() {
        return FieldHieght;
    }

    public void setFieldHieght(int fieldHieght) {
        FieldHieght = fieldHieght;
    }

    public int getMineNumber() {
        return MineNumber;
    }

    public void setMineNumber(int MineNumber) {
        this.MineNumber = MineNumber;
    }

    public int getShieldNumber() {
        return Shield;
    }

    public void  setShieldNumber(int ShieldNumber) {
        this.Shield = ShieldNumber;
    }

}
