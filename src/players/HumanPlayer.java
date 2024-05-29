package players;

import com.damascusuniversity.io.GUIio;
import static com.damascusuniversity.io.IO.CM;
import com.damascusuniversity.io.UserInterfaceIO;
import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Scanner;
import utils.Point;

public class HumanPlayer extends Player  implements Serializable {

    private transient Scanner SC;
    private int x, y;
    private static int stateofshield = 1;

    public HumanPlayer() {
        super('H');
        SC = new Scanner(System.in);
        this.NumberofShield = stateofshield * NumberofShieldFirst;

    }

    @Override
    public Point getInput(CellsManager cellsmanager) {

        do {
            x = SC.nextInt();
            y = SC.nextInt();
        } while (-1 < x && x < cellsmanager.getBoardWidth() && -1 < y && y < cellsmanager.getBoardHieght());
        return new Point(x, y);

    }

    @Override
    public int getInputType() {
        return 0;
    }

    public static void setstateofShield(int State) {
        stateofshield = State;
    }

    public static int getstateofShield() {
        return stateofshield;
    }
    
       @Override
    public void ReleaseThread() {
        
        System.out.println();
        System.out.println("Human Thread Started ");
        ThreadEnded = false;

        PlayerThread = new Thread(new Runnable() {

            @Override
            public synchronized void run() {
                
                MouseEvent e = GUIio.e;

                if (CM.getCurrentPlayer().getPlayerType() == 'H') {

                    if (e.getButton() == MouseEvent.BUTTON3) {

                        int x = e.getX() / GUIio.IMAGEWIDTH;
                        int y = e.getY() / GUIio.IMAGEHIEGHT;

                        try {

                            CM.getCell(x, y).CellExpected(CM, x, y);
                            //Save S=new Save();
                             //S.ReleaseThread();
                        } catch (Exception a) {
                        }

                    } else if (e.getButton() == MouseEvent.BUTTON1) {
                        int x = e.getX() / GUIio.IMAGEWIDTH;
                        int y = e.getY() / GUIio.IMAGEHIEGHT;
                        try {
                            if (!CM.getCell(x, y).getVisible()) {
                                System.out.println("Current Player Id before Pressing " + CM.getCurrentPlayer().getPlayerID());

                               
                                CM.getCell(x, y).CellPressed(CM, x, y);

                            }
                        } catch (Exception a) {
                            UserInterfaceIO.CurrentUserInterface = UserInterfaceIO.mainui;         ///////////////////////////////////////
                        }
                       // CM.Test2();
                       // System.out.println();
                       // CM.Test();
                    }
                }
                
                System.out.println("Human Thread Ended ");
                ThreadEnded = true;
               
            }
            
           
            
        });
        
        PlayerThread.start();

    }
    
}



