package models;

import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class Game {
    private int boardSize; //Mägulaua suurus vaikimisi 10x10
    private int[][] boardMatrix; //Mängu laual asuvad laevad
    private Random random = new Random(); //Juhuslikkuse jaoks
    //private int[] ships = {4, 3, 3, 2, 2, 2, 1}; //Laeva pikkused (US)'
    private int[] ships = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; //Laevade pikkused Eesti versioon
    //private int[] ships = {5, 4, 4, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1, 1}; //Õpilaste verisoon suudab teha vaid siis kui on 15x15 mängulaud
    private int shipsCounter = 0; //Laevu kokku
    private int clickCounter = 0; //Mitu korda klikiti mängus

    public Game(int boardSize) {
        this.boardSize = boardSize;
        this.boardMatrix = new int[boardSize][boardSize]; //Uue mängulaua loomine
    }

    /** * Näita konsoolis mängulaua sisu*/
    public void showGameBoard() {
        System.out.println(); //Tühi rida
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                System.out.print(boardMatrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void setupGameBoard() {
        boardMatrix = new int[boardSize][boardSize]; // Uus laua suurus(algseis)
        int shipsTotal = ships.length; // Kui palju ona laevu kokku
        int shipsPlaced = 0; //Kui palju on laevu paigutatud
        //TODO laevade järjekorra segamine

        while (shipsPlaced < shipsTotal) {
            int length = ships[shipsPlaced]; // Millist laeva paigutada (laeva pikkus)
            boolean placed = false; //Laeva pole paigutatud

            //Valime juhusliku alguspunkti
            int startRow = random.nextInt(boardSize); //Juhuslik rida alustamiseks
            int startCol = random.nextInt(boardSize); //Juhuslik veerg alustamiseks

            //Käime kogu laua läbi alates sellest punktist
            outerLoop: //Lihtsalt silt (label) ehk nimi for-loopile
            for(int rOffset = 0; rOffset < boardSize; rOffset++) { //Rida
                int r = (startRow + rOffset) % boardSize;
                for(int cOffset = 0; cOffset < boardSize; cOffset++) { //Veerg
                    int c = (startCol + cOffset) % boardSize;

                    boolean vertical = random.nextBoolean(); //Määrame juhusliku suuna true = vertikaaln, false = horisontaalne
                    if(tryPlaceShip(r, c, length, vertical || tryPlaceShip(r, c, length, !vertical))) {
                        placed = true; //Laev paigutatud
                        break outerLoop; //Katkesta mõelamd for-loop kordused
                    }
                }

            }

            if(placed) {
                shipsPlaced++; //Järgmine laev
            }else {
                //Kui ei leitud sobivat kohta, katkestame ja laustame uuesti
                setupGameBoard();//Ise edna välja kutsumine
                return;
            }
        }
        //Eemaldame ajutised kaitsetsoonid(9-d), jättes alles ainult laevad (1-4) ja tühjad veekohad (0)
        replaceNineToZero();

    }

    private void replaceNineToZero() {
        for(int row = 0; row < boardSize; row++) {
            for(int col = 0; col < boardSize; col++) {
                if(boardMatrix[row][col] == 9) {
                    boardMatrix[row][col] = 0;
                }
            }
        }
    }

    private boolean tryPlaceShip(int row, int col, int length, boolean vertical) {
        //Kontrolli kas laev üldse mahub mängulauale
        if(vertical && row + length > boardSize) return false;
        if(!vertical && col + length > boardSize) return false;

        //Kontrolli kas sihtpiirkond on vaba (s.h. kaitsetsoon)
        if(!canPlaceShip(row, col, length, vertical)) return false;
        
        //Kirjutame laeva mängulauale - paigutame iagsse lahtrisse laeva pikkuse
        for(int i = 0; i < length; i++) {
            int r = vertical ? row + i : row; // Kasutame rida või mitte, olenevalt suunast
            int c = vertical ? col : col + i; //Sama veeru kohta
            boardMatrix[r][c] = length; //Määrame laeva lahtrisse selle pikkuse
        }
        
        //Määrame ümber laeva kaitsetsooni(vältimaks kontaktset paigutust)
        makeSurrounding(row, col, length, vertical);
        return true;
        
        

    }

    private void makeSurrounding(int row, int col, int length, boolean vertical) {
        Area area = getShipsSurroundingArea(row, col, length, vertical);
        //Käime ala igas lahtris ja kui seal on vei (0), siis märgime selle kaitseks(9)
        for(int r = area.startRow; r <= area.endRow; r++) {
            for(int c = area.startCol; c <= area.endCol; c++) {
                if(boardMatrix[r][c] == 0) { //Kas on vesi
                    boardMatrix[r][c] = 9; //Pane kaitse
                }
            }
        }
    }

    private boolean canPlaceShip(int row, int col, int length, boolean vertical) {
        Area area = getShipsSurroundingArea(row, col, length, vertical); //Saame laeva ümbritseva ala
        //Kontrollime igat lahtrit alal - kui kuskil pole tühjust (0), katkestame
        for (int r = area.startRow; r <= area.endRow; r++) {
            for (int c = area.startCol; c <= area.endCol; c++) {
                if(boardMatrix[r][c] != 0) return false; //Midagi ees, ei sobi
            }
        }
        return true; // Kõik kohad olid vabad 
    }

    private Area getShipsSurroundingArea(int row, int col, int length, boolean vertical) {
        //Arvutame ümbritseva ala piiri, hoides neid mängulaua piires
        int startRow = Math.max(0, row - 1);
        int endRow = Math.min(boardSize - 1, vertical ? row + length : row + 1);
        int startCol = Math.max(0, col - 1);
        int endCol = Math.min(boardSize - 1, vertical ? col + 1 : col + length);
        return new Area(startRow, endRow, startCol, endCol);
    }

    //GETTERID


    public int[][] getBoardMatrix() {
        return boardMatrix;
    }

    public int getShipsCounter() {
        return shipsCounter;
    }

    public int getClickCounter() {
        return clickCounter;
    }

    /**
     * {4,3,3,jne} laevade summa näide 10 (4,3,3)
     * @returnlaeva pikkuste summa
     */
    public  int getShipsParts(){
        return IntStream.of(ships).sum();
    }
}//LÕPP
