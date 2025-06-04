package models;

import java.util.ArrayList;

public class Model {
    private int boardSize = 10; //Vaikimisi mängulaua suurus
    private ArrayList<GridData> gridData;
    private Game game; //Laevade info mängulaual

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
     * @param id mängulaua lahtri id
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

    public void setupNewGame() {
        game = new Game(boardSize);
    }

    //GETTERS

    public int getBoardSize() {
        return boardSize;
    }

    public ArrayList<GridData> getGridData() {
        return gridData;
    }

    public Game getGame() {
        return game;
    }



    //SETTERS

    public void setBoardSize(int bordSize) {
        this.boardSize = bordSize;
    }

    public void setGridData(ArrayList<GridData> gridData) {
        this.gridData = gridData;
    }
}
