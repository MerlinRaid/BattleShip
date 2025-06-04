package views;

import controllers.Controller;
import models.Model;
import views.panels.GameBoard;
import views.panels.InfoBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

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

        //Test Frame ja Panel Layout Managerid
//        System.out.println("JFrame"       + this.getLayout());
//        System.out.println("container "   + container.getLayout());
//        System.out.println("GameBoard "   + gameBoard.getLayout());
//        System.out.println("infoBoard "   + infoBoard.getLayout());
//        System.out.println("pnlComponent" + infoBoard.getPnlComponent().getLayout());

//       Kuvab terminali :
//        JFramejava.awt.BorderLayout[hgap=0,vgap=0]
//        container java.awt.BorderLayout[hgap=0,vgap=0]
//        GameBoard java.awt.FlowLayout[hgap=5,vgap=5,align=center]
//        infoBoard java.awt.FlowLayout[hgap=5,vgap=5,align=left]
//        pnlComponentjava.awt.GridBagLayout

        //Mitu asja saab välja kommenteerida ctrl ja klahviatuuril numbriosas /

    }


    public JLabel getLblMouseXY() {
        return infoBoard.getLblMouseXY();
    }

    public JLabel getLblID() {
        return infoBoard.getLblID();
    }

    public JLabel getLblRowCol() {
        return infoBoard.getLblRowCol();
    }

    public JLabel getLblTime() {
        return infoBoard.getLblTime();
    }

    public JLabel getLblShip() {
        return infoBoard.getLblShip();
    }

    public JLabel getLblGameBoard() {
        return infoBoard.getLblGameBoard();
    }

    public JComboBox<String> getCmbSize() {
        return infoBoard.getCmbSize();
    }

    public JButton getBtnNewGame() {
        return infoBoard.getBtnNewGame();
    }

    public JButton getBtnScoreBoard() {
        return infoBoard.getBtnScoreBoard();
    }

    public JRadioButton getRdoFile() {
        return infoBoard.getRdoFile();
    }

    public JRadioButton getRdoDb() {
        return infoBoard.getRdoDb();
    }

    public JComboBox getChkWhere() {
        return infoBoard.getChkWhere();
    }

    public void registerGameBoardMouse(Controller controller) {
        gameBoard.addMouseListener(controller);
        gameBoard.addMouseMotionListener(controller);
    }

    public void registerComboBox(ItemListener itemListener) {
        infoBoard.getCmbSize().addItemListener(itemListener);
    }

    public void registerNewGameButton(ActionListener actionListener) {
        infoBoard.getBtnNewGame().addActionListener(actionListener);
    }
}
