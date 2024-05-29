
package utils;

import java.io.Serializable;
import java.util.ArrayList;

 
public class Cell implements Serializable{
    
    private int x;
    private int y;
    
      private boolean IsShield;

    private boolean IsPump;
    private int NumberOfMineSurrounded;
    
    private ArrayList<Point> CellsSurrounded;
    
       public Cell() {
        IsPump = false;
     IsShield = false;
    NumberOfMineSurrounded = 0;
    CellsSurrounded = new ArrayList<>();
    }
    
    public Cell(int x, int y, boolean IsPump,boolean IsShield){
    this.x = x;
    this.y = y;
    this.IsPump = IsPump;
        this.IsShield = IsShield;

    NumberOfMineSurrounded = 0;
     CellsSurrounded = new ArrayList<>();
    }
    
    public void setX(int x){
    this.x = x;
    }
    
    
    public int getX(){
    return x;
    }
    
    public void setY(int y){
    this.y = y;
    }
    
    public int getY(){
    return y;
    } 
    
    public boolean IsPump(){
    return IsPump;
    }
    
    public void setPump(boolean IsPump){
    this.IsPump = IsPump;
    }
    
    public void setNumberOfPumpSurrounded(int NumberOfPumpSurrounded){
    this.NumberOfMineSurrounded = NumberOfPumpSurrounded;
    }
    
    public int getNumberOfPumpSurrounded(){
    return NumberOfMineSurrounded;
    }
    
    public void addNewPoint(int x, int y){
    CellsSurrounded.add(new Point(x, y));
    }
    
    public Point getPoint(int v){
    return CellsSurrounded.get(v);
    }
    
    public ArrayList<Point> getPointsSurrounded(){
    return CellsSurrounded;
    }
    
    public void IncreaseNumberOfPumpSurrounded(){
    NumberOfMineSurrounded++;
    }
    
    
    
    
     public boolean getShield() {
        return IsShield;
    }

    public void setShield(boolean IsShield) {
        this.IsShield = IsShield;
    }
    
}
