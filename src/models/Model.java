package models;

import java.util.ArrayList;

public class Model {
    private int bordSize = 10; //Vaikimisi mängulaua suurus
    private ArrayList<GridData> gridData;

    public Model() {
        gridData = new ArrayList<>();
    }

    //GETTERS

    public int getBoardSize() {
        return bordSize;
    }

    public ArrayList<GridData> getGridData() {
        return gridData;
    }

    //SETTERS

    public void setBoardSize(int bordSize) {
        this.bordSize = bordSize;
    }

    public void setGridData(ArrayList<GridData> gridData) {
        this.gridData = gridData;
    }
}
