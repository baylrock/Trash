package com.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by baylrock on 03.12.2015.
 */
public class MDPanel extends JPanel {

    public void paintComponent (Graphics g) {

        int widh = this.getWidth(); int height = this.getHeight();
        int centerW = widh/2; int centerH = height/2;
        int sizeOv = (int)(widh/1.3);
        g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        g.fillRect(0,0,widh,height);


        g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        g.fillOval(centerW-sizeOv/2,centerH-sizeOv/2,sizeOv,sizeOv);
    }

}
