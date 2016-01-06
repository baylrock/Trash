package com.cardGame;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by baylrock on 27.12.2015.
 */
public class MainPanel extends JPanel {
    JTextArea question;
    JTextArea answer;
    JButton addButton;
    JButton NextButton;
    JScrollPane questionScroler;
    JScrollPane answerScroler;
    JLabel questionLabel;
    JLabel answerLabel;
    CardFrame frame;






    public MainPanel() {
        Font font = new Font("sanserif", Font.BOLD, 26 );

        question=new JTextArea(5,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(font);
        question.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                frame.cardMenu.SaveStat = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                frame.cardMenu.SaveStat = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                frame.cardMenu.SaveStat = false;
            }
        });
        answer=new JTextArea(5,20);
        question.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(font);
        answer.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                frame.cardMenu.SaveStat = false;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                frame.cardMenu.SaveStat = false;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                frame.cardMenu.SaveStat = false;
            }
        });

        questionScroler = new JScrollPane(question);
        questionScroler.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScroler.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(questionScroler);


        answerScroler = new JScrollPane(answer);
        answerScroler.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        answerScroler.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(answerScroler);

        addButton = new JButton("Add");
        addButton.addActionListener(new AddButtListener());
        this.add(addButton);
        NextButton = new JButton("Next");
        NextButton.addActionListener(new NextButtListener());
        this.add(NextButton);

        questionLabel = new JLabel("Question: ");
        answerLabel = new JLabel("Answer: ");

        add(questionLabel);
        add(questionScroler);
        add(answerLabel);
        add(answerScroler);
        add(addButton);



    }

    class AddButtListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Card card = new Card(frame.mainPanel.question.getText(), frame.mainPanel.answer.getText());
            frame.CurentCardIndex = card.ID;
            frame.cardList.add(card);
            frame.mainPanel.question.setText("");
            frame.mainPanel.answer.setText("");
            frame.mainPanel.question.requestFocus();

        }
    }

    class NextButtListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (frame.cardList.get(frame.CurentCardIndex++)!= null) {
                CardNull buffcard = frame.cardList.get(frame.CurentCardIndex);
                frame.mainPanel.question.setText(buffcard.question);
                frame.mainPanel.answer.setText(buffcard.answer);
                frame.CurentCardIndex++;
            }

        }
    }

    public void setFrame(CardFrame frame) {
        this.frame = frame;
    }

}
