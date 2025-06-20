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
        // Test  System.out.println("Uus mäng");
        if(!gameTimer.isRunning()) { // Mäng ei käi
            // See on uus lahendus
            new Thread(() -> {
                model.setupNewGame(); //Teeme uue mängu
                model.getGame().setupGameBoard(); //seadistame mängulaua
                //model.getGame().showGameBoard(); //Testiks näitas konsooli mängulauda numbritega
                view.getLblShip().setText(model.getGame().getShipsCounter() + " / " + model.getGame().getShipsParts()); // Sellega kirjutame mitu laeva on kätte saadud mitmest
                SwingUtilities.invokeLater(() -> {
                    view.getBtnNewGame().setText("Katkesta");
                    view.getBtnScoreBoard().setEnabled(false);
                    view.getCmbSize().setEnabled(false);
                    view.getGameBoard().repaint(); // Panin selle rea juurde, et ta puhastaks mängulaua vanast mängutulemusest
                    gameTimer.start();
                });
            }).start();


        }else { // Meil on mäüng pooleli
            gameTimer.stop();
            view.getBtnNewGame().setText("Uus mäng");
            view.getCmbSize().setEnabled(true);
            view.getBtnScoreBoard().setEnabled(true);
        }
    }
}