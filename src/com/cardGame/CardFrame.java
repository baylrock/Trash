package com.cardGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by baylrock on 27.12.2015.
 */
public class CardFrame extends JFrame{

    private JPanel bgPanel;
    CardMenu cardMenu;
    MainPanel mainPanel;
    ArrayList<Card> cardList;
    public int CurentCardIndex;

    public CardFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildGUI();
        setBounds(50,50,300,300);
        pack();
        setVisible(true);

    }

    public void buildGUI() {
        bgPanel = new JPanel(new BorderLayout());
        cardMenu = new CardMenu();
        mainPanel = new MainPanel();
        cardMenu.setFrame(this);
        mainPanel.setFrame(this);
        cardList = new ArrayList<Card>();

        bgPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        setJMenuBar(cardMenu);
        getContentPane().add(bgPanel);
        getContentPane().add(mainPanel);

    }

    public void CardClear() {
        cardList.clear();
        mainPanel.question.setText("");
        mainPanel.answer.setText("");
    }



}
