package graph;

import com.itedamascusuniversity.generator.LargSizeArrayGenerator;
import java.io.Serializable;
import java.util.ArrayList;
import utils.Cell;
import utils.Point;

public class Graph implements Serializable {

    private ArrayList<Cell> CellsGraph;
    private LargSizeArrayGenerator generator;
    public static ArrayList<Cell> BoardSave;/////

    public Graph() {
        CellsGraph = new ArrayList<>();
        generator = new LargSizeArrayGenerator();

        generator.GenerateANewField();
        BoardSave = new ArrayList<>();

    }

    public Cell get(int x, int y) {

        for (Cell c : CellsGraph) {

            if (c.getX() == x && c.getY() == y) {
                return c;
            }

        }

        return null;

    }

    public void Build(int[][] Field, int FieldWidth, int FieldHieght) {

        int CurrentCell = 0;

        for (int i = 0; i < FieldHieght; i++) {
            for (int j = 0; j < FieldWidth; j++) {
                if (Field[i][j] == 1) {
                    CellsGraph.add(new Cell(j, i, true, false));
                } else if (Field[i][j] == 2) {
                    CellsGraph.add(new Cell(j, i, false, true));
                } else {
                    CellsGraph.add(new Cell(j, i, false, false));
                }

                if (j + 1 < FieldWidth) {
                    CellsGraph.get(CurrentCell).addNewPoint(j + 1, i);
                }
                if (j - 1 > -1) {
                    CellsGraph.get(CurrentCell).addNewPoint(j - 1, i);
                }
                if (i + 1 < FieldHieght) {
                    CellsGraph.get(CurrentCell).addNewPoint(j, i + 1);
                }
                if (i - 1 > -1) {
                    CellsGraph.get(CurrentCell).addNewPoint(j, i - 1);
                }
                if (i + 1 < FieldHieght && j + 1 < FieldWidth) {
                    CellsGraph.get(CurrentCell).addNewPoint(j + 1, i + 1);
                }
                if (i + 1 < FieldHieght && j - 1 > -1) {
                    CellsGraph.get(CurrentCell).addNewPoint(j - 1, i + 1);
                }
                if (i - 1 > -1 && j + 1 < FieldWidth) {
                    CellsGraph.get(CurrentCell).addNewPoint(j + 1, i - 1);
                }
                if (i - 1 > -1 && j - 1 > -1) {
                    CellsGraph.get(CurrentCell).addNewPoint(j - 1, i - 1);
                }

                CurrentCell++;

            }

        }
        BoardSave = this.CellsGraph;
    }

    public void TemporaryMeth() {

        for (Cell c : CellsGraph) {

            System.out.print("(" + c.getX() + "," + c.getY() + ")");
            System.out.print(c.getShield());
            System.out.print(c.IsPump());
            for (Point p : c.getPointsSurrounded()) {
                System.out.print("    " + p.getX() + "," + p.getY());
            }

            System.out.println("");

        }

    }

    public void AnotherTemporaryMeth() {

        //InitializeGraph();
        for (Cell c : CellsGraph) {
            System.out.print("(" + c.getX() + "," + c.getY() + ")");
            System.out.print(c.getNumberOfPumpSurrounded());
        }

    }

    public void InitializeGraph() {

        for (Cell c : CellsGraph) {
            if (c.IsPump()) {
                ArrayList<Point> Points = c.getPointsSurrounded();

                for (Point p : Points) {
                    for (Cell C : CellsGraph) {

                        if (C.getX() == p.getX() && C.getY() == p.getY()) {
                            C.IncreaseNumberOfPumpSurrounded();
                        }

                    }
                }

            }
        }

    }

    public void GenerateGraph() {

        Build(generator.getField(), generator.getFieldWidth(), generator.getFieldHieght());
        InitializeGraph();

    }

    public LargSizeArrayGenerator getTheGenertor() {
        return generator;
    }

    public ArrayList<Cell> getCallsList() {
        return CellsGraph;
    }

}
