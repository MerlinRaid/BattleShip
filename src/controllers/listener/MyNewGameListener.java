package controllers.listener;

import models.Model;
import views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MyNewGameListener implements ActionListener {
    private Model model;
    private View view;
    public MyNewGameListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Ei kliki");
    }
}
