package models;

import java.util.ArrayList;

public class Model {
    private int bordSize = 10; //Vaikimisi mängulaua suurus
    private ArrayList<GridData> gridData;

    public Model() {
        gridData = new ArrayList<>();
    }

    /**
     * Tagastab hiire kordinaatide järgi massiivi indeksi ehk lahti id
     *
     * @param mouseX
     * @param mouseY
     * @return lahtri id
     */

    public int checkGridIndex(int mouseX, int mouseY) {
        int result = -1; //viga
        int index = 0;
        for (GridData gd : gridData) {
            if(mouseX > gd.getX() && mouseX <= (gd.getX() + gd.getWidth())
                && mouseY > gd.getY() && mouseY <= (gd.getY() + gd.getHeight())) {
                result = index;
            }
            index++;
        }
        return result;
    }

    /**
     * Tagstab mängulaua reanumbri saadud id põhjal (chechGridIndex)
     * @param id mängulaua lahti id
     * @return mängulaua rea number
     */
    public int getRowById(int id){
        if(id != -1){
            return gridData.get(id).getRow();
        }
        return -1;//Viga
    }


    /**
     * Tagstab mängulaua veeru numbri saadu id põhjal (checkGridIndex)
     * @param id mängulaua id
     * @return mängulaua veeru number
     */
    public int getColById(int id){
        if(id != -1){
            return gridData.get(id).getCol();
        }
        return -1; //Viga
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
