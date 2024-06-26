
package utils;
import java.io.Serializable;


public class Point implements Serializable{
    
    private int x;
    private int y;
    
    public Point(){
    this(0, 0);
    }
    
    public Point(int x, int y){
    this.x = x;
    this.y = y;
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
    
}
