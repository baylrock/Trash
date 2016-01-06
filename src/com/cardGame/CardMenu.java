package com.cardGame;

import com.main.IOFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by baylrock on 27.12.2015.
 */
public class CardMenu extends JMenuBar {
    JMenu Main;
    JMenuItem Save;
    JMenuItem New;
    JMenuItem Load;
    CardFrame frame;
    boolean SaveStat = false;

    public CardMenu() {
        Main = new JMenu("File");

        Save = new JMenuItem("Save");
        Save.addActionListener(new SaveMenuListener());
        New = new JMenuItem("New");
        New.addActionListener(new NewMenuListener());
        Load = new JMenuItem("Load");
        Load.addActionListener(new LoadMenuListener());
        Main.add(New);
        Main.add(Save);
        Main.add(Load);

        this.add(Main);
    }

    class SaveMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (SaveStat) { return;}
            Card card = new Card(frame.mainPanel.question.getText(), frame.mainPanel.answer.getText());
            frame.cardList.add(card);
            frame.CurentCardIndex = card.ID;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(frame);
            try {
                IOFile.saveFile(fileChooser.getSelectedFile(),frame.cardList);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            SaveStat = true;
        }
    }

    class NewMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.cardList.clear();
            frame.mainPanel.question.setText("");
            frame.mainPanel.answer.setText("");
            frame.mainPanel.question.requestFocus();
        }
    }

    class LoadMenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(frame);
            frame.cardList = IOFile.OpenFile(fileChooser.getSelectedFile(),frame.cardList);
            frame.mainPanel.question.setText(frame.cardList.get(0).question);
            frame.mainPanel.answer.setText(frame.cardList.get(0).answer);

        }
    }

    public void setFrame(CardFrame frame) {
        this.frame = frame;
    }

}
