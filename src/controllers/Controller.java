package controllers;

import controllers.listener.MyComboBoxListener;
import controllers.listener.MyNewGameListener;
import models.Model;
import views.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        //Listenerid
        view.registerComboBox(new MyComboBoxListener(model, view) );
        view.registerNewGameButton(new MyNewGameListener(model, view)); //TODO GameTimer
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("Mouse Moved"); //TEST, et näha terminalis, kas hiire liikumine toimib
        String mouse = String.format("x = %03d && y = %03d", e.getX(), e.getY());
        view.getLblMouseXY().setText(mouse);

        // Loe id, row ja col infot
        int id = model.checkGridIndex(e.getX(), e.getY());
        int row = model.getRowById(id);
        int col = model.getColById(id);

        //id näitamie labelil
        if(id != -1){
        view.getLblID().setText(String.valueOf(id + 1));
        }

        //ROW & COL NÄITAMINE
        String rowcol = String.format("%d, %d", row + 1, col + 1);
        if(row == -1 || col == -1){
            rowcol = "Pole mängulaual!";
        }
        view.getLblRowCol().setText(rowcol);
    }
}
