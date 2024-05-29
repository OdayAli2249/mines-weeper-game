package com.damascusuniversity.io;

import com.itedemescusuniversity.cells.cellsmangmentsystem.CellsManager;

public abstract class IO {
    
    
    public static CellsManager CM  = new CellsManager();
    public abstract void HandlingInput();

    public abstract void DisplayOutput(CellsManager cellsmanager);

    public abstract void Apdate(CellsManager cellsmanager);

}
