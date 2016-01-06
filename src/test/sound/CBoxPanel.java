package test.sound;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by baylrock on 23.12.2015.
 */
public class CBoxPanel extends JPanel {
    public ArrayList<JCheckBox> checkBoxes;


    CBoxPanel() {
        GridLayout gridLayout = new GridLayout(16,16);
        gridLayout.setVgap(1);
        gridLayout.setHgap(1);
        setLayout(gridLayout);
        this.checkBoxes = new ArrayList<JCheckBox>();
        JCheckBox box = null;
        for (int i = 0; i<256; i++) {
            box = new JCheckBox();
            box.setSelected(false);
            box.setName(Integer.toString(i+1));
            this.add(box);
            checkBoxes.add(box);

        }
        box = null;
    }
}
