package views;

import models.Model;
import views.panels.GameBoard;
import views.panels.InfoBoard;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Model model;
    private GameBoard gameBoard; //Mängulaud
    private InfoBoard infoBoard; //Infopaneel

    public View(Model model)  {
        super("Laevade pommitamine");
        this.model = model;

        gameBoard = new GameBoard(model); //Mängulaua loomine
        infoBoard = new InfoBoard(); //Loome infopaneeli

        JPanel container = new JPanel( new BorderLayout());

        container.add(gameBoard, BorderLayout.CENTER); //Mängulaua ujuvale osale
        container.add(infoBoard, BorderLayout.EAST); //Infotahvel vasakule serva

        add(container);
    }
}
