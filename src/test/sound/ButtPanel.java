package test.sound;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by baylrock on 23.12.2015.
 */
public class ButtPanel extends JPanel {
    public JButton Start;
    public JButton Stop;
    public JButton upTempo;
    public JButton downTempo;
    public JButton clearField;
    public JButton SaveMidi;
    public JButton LoadMidi;
    public TSound soundDrive;

    public ButtPanel(TSound soundDrive) {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.Start = new JButton("Start");
        this.Start.addActionListener(new StartButtListener());

        this.Stop = new JButton("Stop");
        this.Stop.addActionListener(new StopButtListener());

        this.upTempo = new JButton("upTempo");
        this.upTempo.addActionListener(new upTempoButtListener());

        this.downTempo = new JButton("downTempo");
        this.downTempo.addActionListener(new downTempoButtListener());

        this.clearField = new JButton("Clear Fields");
        this.clearField.addActionListener(new ClearFieldButtListener());

        this.SaveMidi = new JButton("Save saund");
        this.SaveMidi.addActionListener(new SaveMidiButtListener());

        this.LoadMidi = new JButton("Load saund");
        this.LoadMidi.addActionListener(new LoadMidiButtListener());

        this.add(Start);
        this.add(Stop);
        this.add(upTempo);
        this.add(downTempo);
        this.add(clearField);
        add(SaveMidi);
        add(LoadMidi);


        this.soundDrive = soundDrive;
    }

    class StartButtListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            soundDrive.buildAndPlay();
        }
    }

    class StopButtListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            soundDrive.sequencer.stop();
        }
    }

    class upTempoButtListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            soundDrive.sequencer.setTempoFactor((float)(soundDrive.sequencer.getTempoFactor()*1.03));
        }
    }

    class downTempoButtListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            soundDrive.sequencer.setTempoFactor((float)(soundDrive.sequencer.getTempoFactor()*.97));
        }
    }

    class ClearFieldButtListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (JCheckBox box : soundDrive.CBoxList) {
                box.setSelected(false);
            }
        }
    }

    class SaveMidiButtListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] CBoxState = new boolean[256];
            int id = 0;
            for (JCheckBox box:soundDrive.CBoxList) {
                CBoxState[id] = box.isSelected();
                id++;
            }

            try {
                File file = new File("save");
                if (!file.exists()) { file.mkdir();}
                file = new File("save/Mid"+new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())+".dram");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(CBoxState);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
         }
    }

    class LoadMidiButtListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean[] CBoxState = null;
            try {

                File file = new File("save");
                if (!file.exists()) { System.out.println("No Saves!"); return;}
                else {
                    JFileChooser fileChooser = new JFileChooser("save");
                    fileChooser.showOpenDialog(soundDrive.mainframe);
                    file = fileChooser.getSelectedFile();
                    if(file==null) {return;}
                    if(!file.getName().contains(".dram")) {return;}
                }
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                CBoxState = (boolean[]) objectInputStream.readObject();



            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (CBoxState == null) {return;}
            int id=0;
            for (JCheckBox box : soundDrive.CBoxList) {
                box.setSelected(CBoxState[id]);
                id++;
            }

            soundDrive.sequencer.stop();
            soundDrive.buildAndPlay();

        }
    }




}
