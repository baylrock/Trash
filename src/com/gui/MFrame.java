package com.gui;

import com.main.Print;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


/**
 * Created by baylrock on 21.11.2015.
 */
public class MFrame implements ActionListener {
    static public JFrame frame = new JFrame();
    Random random = new Random();
    MyPanel drowpanel;
    public int x, y; double vectSpeed = 3.5;

    int WinWidth = 0, WinHeight = 0;

    public MFrame() {
        this.frame.setSize(900, 900);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MFrame(int width, int height) {
        this.frame.setSize(width, height);
        WinWidth = width; WinHeight=height;
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start();
    }

    public void start() {
        Button but_Paint = new Button("Restart!");
        but_Paint.addActionListener(this);
        drowpanel = new MyPanel();

        this.frame.getContentPane().add(BorderLayout.SOUTH, but_Paint);
        this.frame.getContentPane().add(BorderLayout.CENTER, drowpanel);
        this.frame.setVisible(true);
        x = 0;
        y = 0;
        int buffX=x, buffY=y;
        for (int i = 0; i < 930; i--) {
            buffX=x; buffY=y;
            drowpanel.repaint();


            try {
                Thread.sleep(20);
            } catch (Exception ex) {

            }
            if (buffX==x || buffY==y) {x++; y--; vectSpeed++; drowpanel.repaint();}

        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
         drowpanel.restart();

    }

    public class MyPanel extends JPanel {
        int sizeOv = new Random().nextInt(45)+5;
        int w = WinWidth-15, h = WinHeight-60;
        double vectX = 1, vectY = 1;
        boolean vUP = true, vRight = true;


        Rect rect = new Rect(WinWidth/2-WinWidth/5,WinHeight/2-WinHeight/4,(WinWidth/5)*2,(WinHeight/5)/2);
        Rect rect5 = new Rect(WinWidth/2-WinWidth/5,WinHeight/2+WinHeight/10,(WinWidth/5)*2,(WinHeight/5)/2);
        Rect rect2 = new Rect(WinWidth-WinWidth/5,0,(WinWidth/5),(WinHeight/5));
        Rect rect3 = new Rect(0,0,(WinWidth/5),(WinHeight/5));
        Rect rect4 = new Rect(WinWidth/8,WinHeight-WinHeight/5,(WinWidth/8),(WinHeight/5));



        public void paintComponent(Graphics g) {
            g.setColor(new Color(185, 241, 255));
            g.fillRect(0, 0, w, h);

            g.setColor(new Color(255, 60, 61));
            g.fillRect(rect.X, rect.Y, rect.W, rect.H);
            g.fillRect(rect2.X, rect2.Y, rect2.W, rect2.H);
            g.fillRect(rect3.X, rect3.Y, rect3.W, rect3.H);
            g.fillRect(rect4.X, rect4.Y, rect4.W, rect4.H);
            g.fillRect(rect5.X, rect5.Y, rect5.W, rect5.H);

            move();

            g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            g.fillOval(x, y, sizeOv, sizeOv);
        }

       public  void move () {

            if ((x + sizeOv >= w)) {
                vRight = false;
                vectX=rnd();
            }

            if (y+sizeOv >= h) {
                vUP = false;
                vectY=rnd();


            }

            if (x<=0) {
                vRight = true;
                vectX=rnd();

            }

            if (y<=0) {
                vUP = true;
                vectY=rnd();

            }
           colisChek(rect);
           colisChek(rect2);
           colisChek(rect3);
           colisChek(rect4);
           colisChek(rect5);


           //------------------------


            if (vUP) {
                y += vectY + vectSpeed;
            }
            if (!vUP) {
                y += vectY + -vectSpeed;
            }
            if (vRight) {
                x += vectX + vectSpeed;
            }
            if (!vRight) {
                x += vectX + -vectSpeed;
            }


        }

        double rnd() {
            return new Random().nextDouble()*4;
        }

        void restart () {
            sizeOv = new Random().nextInt(45)+5;
            x = 0;
            y = 0;

            w = WinWidth-15; h = WinHeight-60;
            vectX = 1; vectY = 1;
            vUP = true; vRight = true;
        }

        void colisChek(Rect rect) {

            if ((y <= rect.Y+rect.H) && (y+sizeOv>=rect.Y) && (x <= rect.X+vectSpeed) && (x+sizeOv >= rect.X-vectSpeed)) {
                vRight=false;
                vectX=rnd();
            }

            if ((y <= rect.Y+rect.H) && (y+sizeOv>=rect.Y) && (x <= rect.X+rect.W+vectSpeed) && (x+sizeOv >= rect.X+rect.W-vectSpeed)) {
                vRight=true;
                vectX=rnd();
            }

            if ((x+sizeOv <= rect.X+rect.W) && (x>=rect.X) && (y <= rect.Y+rect.H+vectSpeed) && (y+sizeOv >= rect.Y+rect.H-vectSpeed)) {
                vUP=true;
                vectX=rnd();
            }

            if ((x+sizeOv <= rect.X+rect.W) && (x>=rect.X) && (y <= rect.Y+vectSpeed) && (y+sizeOv >= rect.Y-vectSpeed)) {
                vUP=false;
                vectX=rnd();
            }

        }
    }

    class Rect {
        public int X,Y,W,H;

        Rect() {}
        Rect(int x,int y,int w,int h) {
            X=x; Y=y; W=w; H=h;
        }


    }



}
