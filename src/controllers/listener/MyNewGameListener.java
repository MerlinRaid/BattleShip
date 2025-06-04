package controllers.listener;

import models.GameTimer;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyNewGameListener implements ActionListener {
    private Model model;
    private View view;
    private GameTimer gameTimer;

    public MyNewGameListener(Model model, View view, GameTimer gameTimer) {
        this.model = model;
        this.view = view;
        this.gameTimer = gameTimer;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("Ei kliki");
        if(!gameTimer.isRunning()) {//Mäng ei käi
            //See on uus lahendus
            new Thread(() -> {
               model.setupNewGame();
               model.getGame().setupGameBoard();
               model.getGame().showGameBoard();
               view.getLblShip().setText(model.getGame().getShipsCounter() + " / " + model.getGame().getShipsParts());
               //GUI uuendused Swing lõimes (mahutab toimetamine ja kasutajaliides ei hangu)
               SwingUtilities.invokeLater(() -> {
                   view.getBtnNewGame().setText("Katkesta mäng");
                   gameTimer.start();//Käivita aeg
               });
            }).start();

            //Vanalehndus koos kommentaaridega
//            model.setupNewGame(); //Teeme uue mängu
//            model.getGame().setupGameBoard(); //Seadistame mängulaua
//            model.getGame().showGameBoard();
//            view.getBtnNewGame().setText("Katkesta mäng"); //Muuda uus mäng => Katsekasta mäng
//            gameTimer.start(); //Käivita aeg

        }else {//Meil on mäng
            gameTimer.stop(); //Peata aeg
            view.getBtnNewGame().setText("Uus mäng"); //Muuda Katkesta mäng => Uus mäng

        }
    }
}
