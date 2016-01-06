package test.sound;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baylrock on 23.12.2015.
 */
public class LabelPanel extends JPanel {


    public LabelPanel(String[] Names) {
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        JLabel label;
        Font font = new Font("Arial",2,24);
        for(String Name : Names) {
            label = new JLabel(Name);
            label.setFont(font);
            this.add(label);
        }


    }

}
