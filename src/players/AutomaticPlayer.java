package players;

import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.io.Serializable;
import java.util.Random;
import utils.Point;

public class AutomaticPlayer extends Player implements Serializable {

    private Random RN;
    private int x, y;
 private static int stateofshield;

    public AutomaticPlayer() {
        super('M');
        RN = new Random();
        this.NumberofShield=stateofshield*NumberofShieldFirst;

    }

    @Override
    public Point getInput(CellsManager cellsmanager) {

        do {
            x = RN.nextInt(cellsmanager.getBoardWidth());

            y = RN.nextInt(cellsmanager.getBoardHieght());
        } while (cellsmanager.getCell(x, y).getExpected() == true
                || cellsmanager.getCell(x, y).getVisible() == true);

        return new Point(x, y);
    }

    @Override
    public int getInputType() {
        return RN.nextInt(3);
    }
    
    
    
     public static void setstateofShield(int State)
    {
        stateofshield=State;
    }
    public static int getstateofShield()
    {
        return stateofshield;
    }

    @Override
    public void ReleaseThread() {
        
    }

}
