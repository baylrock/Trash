package test.sound;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baylrock on 23.12.2015.
 */
public class Frame extends JFrame{

        CBoxPanel cBoxPanel;
        ButtPanel buttPanel;
        LabelPanel labelPanel;
        JPanel backGround;
        TSound soundDrive;
        public static final String[] Instruments = {"Bass Drum", "Closed Hi-Hat",
        "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap",
        "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga",
        "Cowbell", "Vibraslap", "Low-mid Tom", "High Agogo",
        "Open Hi Conga"};
        public static final int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

        public Frame (int w, int h) {
            this.setSize(w,h);
            this.setName("CyberBox");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.buildGUI();

        }

        public Frame(){

            this.setName("CyberBox");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.buildGUI();
            this.setBounds(50,50,300,300);
            this.pack();
            this.setVisible(true);

        }

        public void buildGUI() {
            this.backGround = new JPanel(new BorderLayout());
            this.backGround.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


            cBoxPanel = new CBoxPanel();
            labelPanel = new LabelPanel(Instruments);
            soundDrive = new TSound(cBoxPanel.checkBoxes,this);
            buttPanel = new ButtPanel(soundDrive);

            this.backGround.add(BorderLayout.EAST,buttPanel);
            this.backGround.add(BorderLayout.WEST,labelPanel);
            this.backGround.add(BorderLayout.CENTER,cBoxPanel);

            this.getContentPane().add(this.backGround);



        }

}
