package test.sound;

import javax.sound.midi.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by baylrock on 15.11.2015.
 */
public class TSound {
    private SoundPanel panel = null;
    public Track track;
    public  Sequencer sequencer;
    public Sequence sequence;
    public ArrayList<JCheckBox> CBoxList;
    public Frame mainframe;


    public TSound(ArrayList<JCheckBox> list,Frame frame) {
        this.CBoxList = list;
        this.mainframe = frame;
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception ex) {}
    }

    public void buildAndPlay() {
        int[] trakList = null;
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i<16;i++) {
            trakList = new int[16];
            int key = Frame.instruments[i];

            for (int j=0; j<16; j++) {
                JCheckBox box = (JCheckBox) CBoxList.get(j+(16*i));
                if(box.isSelected()) {
                    trakList[j]=key;
                }
                else {
                    trakList[j]=0;
                }

            }
            makeTracks(trakList);
            track.add(makeEvent(176,1,127,0,16));
        }
        track.add(makeEvent(192,9,1,0,15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public MidiEvent makeEvent (int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;

        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(comd,chan,one,two);
            return new MidiEvent(message,tick);
        }
        catch (Exception ex) {}

        return event;
    }


    public void makeTracks(int[] list) {
        for (int i = 0; i<16; i++) {
            int key = list[i];
            if (0!=key) {
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+1));
            }
        }
    }





    public void setPanel (SoundPanel panel) {
        this.panel = panel;
    }





}

