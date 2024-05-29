package com.itedemescusuniversity.cells;

import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.Image;
import java.io.Serializable;

public abstract class BCell implements Serializable{
  protected int PlayerwhoPressCell;  
    protected long Time;
    protected final char Type;
    protected boolean Expected;

    protected int CellWidth;
    protected int CellHieght;

    protected int XCoordinate;
    protected int YCoordinate;

    protected Image Appereance;
    protected static Image InvisibleStateAppereance;

    protected int NumberOfMineSurrounded;
    protected boolean IsMine;

    protected boolean IsShield;

    protected boolean IsVisible;

    public BCell(int XCoordinate, int YCoordinate, char Type, boolean IsShield) {
        this.Type = Type;
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
        this.IsMine = false;
        this.IsShield = IsShield;

        this.IsVisible = false;
        this.Expected = false;
     this.PlayerwhoPressCell = 0;
        this.Time = 0;
    }

    public abstract void CellPressed(CellsManager Board, int x, int y);

    public abstract void CellExpected(CellsManager Board, int x, int y);

    public char getType() {
        return Type;
    }

    /**
     * @return the CellWidth
     */
    public int getCellWidth() {
        return CellWidth;
    }

    /**
     * @return the CellHieght
     */
    public int getCellHieght() {
        return CellHieght;
    }

    /**
     * @return the XCoordinate
     */
    public int getXCoordinate() {
        return XCoordinate;
    }

    /**
     * @return the Ycoordinate
     */
    public int getYcoordinate() {
        return YCoordinate;
    }

    public boolean IsMine() {
        return IsMine;
    }

    public int getNumberOfMineSurrounded() {
        return NumberOfMineSurrounded;
    }

    public void setVisible(boolean v) {
        this.IsVisible = v;
    }

    public boolean getVisible() {
        return IsVisible;
    }

    public void setExpected(boolean Expected) {
        this.Expected = Expected;
    }

    public boolean getExpected() {
        return Expected;
    }

    public void setIsSheild(boolean isShield) {
        this.IsShield = isShield;
    }

    public boolean getIsSheild() {
        return IsShield;
    }
    
    
    
     public int getPlayerWhoPressCell() {
        return PlayerwhoPressCell;
    }

    public void setPlayerWhoPressCell(int id) {
        this.PlayerwhoPressCell = id;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long Time) {
        this.Time = Time;
    }

}
