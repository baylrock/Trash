package test.sound;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by baylrock on 23.12.2015.
 */
public class SoundPanel extends JPanel implements ControllerEventListener {
    boolean mesg = false;
    @Override
    public void controlChange(ShortMessage event) {
        mesg=true;
        repaint();
    }


    public void paintComponent (Graphics g) {
        if (mesg) {
            Graphics2D graphics2d = (Graphics2D) g;
            Random rand = new Random();

            g.setColor(new Color(rand.nextInt(255)+1,rand.nextInt(255)+1,rand.nextInt(255)+1));
            g.fillRect(rand.nextInt(40)+10,rand.nextInt(40)+10,rand.nextInt(120)+10,rand.nextInt(120)+10);
            mesg=false;
        }
    }
}
