package controllers.listener;

import models.Model;
import views.View;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyComboBoxListener implements ItemListener {
    private Model model;
    private View view;
    public MyComboBoxListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }



    @Override
    public void itemStateChanged(ItemEvent e) {
        //System.out.println(e.getItem()); //TEST! Kuvab hetkel valitud kui ka uue mängulaua suuruse
        if (e.getStateChange() == ItemEvent.SELECTED) {
           // System.out.println(e.getItem()); //TEST, et tuleks ainult valitud mängulaua suurus
            String number = e.getItem().toString(); //Võta väärtus tekstina (String)
            int size = Integer.parseInt(number); // Tee eelnev nr. string => täisarvuks
            view.getLblGameBoard().setText(number + " x " + number); //Kuvamine infoboardis "Laua suurus" 10x10/ 11x11 jnejne
            //view.getLblGameBoard().setText(String.format("%d x %d", size, size)); //Teine võimalus kuidas kuvama panna
            model.setBoardSize(size); //Määrab mängulaua suuruse
            view.pack();// Et suurus muutuks
            view.repaint(); //Joonista uuesti
        }
    }
}
