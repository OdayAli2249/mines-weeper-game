package com.damascusuniversity.io.outputdisplay;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class ScreenDisplayer {

    private static final int FRAMEWIDTH = 850;
    private static final int FRAMEHIEGHT = 670;

    private JFrame frame;
    private Canvas canvas;

    public ScreenDisplayer() {

        InitializeFrame();
        InitializeCanvas();

    }

    private void InitializeFrame() {

        frame = new JFrame();

        frame.setSize(new Dimension(FRAMEWIDTH, FRAMEHIEGHT));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setSize(new Dimension(FRAMEWIDTH, FRAMEHIEGHT));

    }

    private void InitializeCanvas() {

        canvas = new Canvas();

        canvas.setMaximumSize(new Dimension(FRAMEWIDTH, FRAMEHIEGHT));
        canvas.setMinimumSize(new Dimension(FRAMEWIDTH, FRAMEHIEGHT));
        canvas.setPreferredSize(new Dimension(FRAMEWIDTH, FRAMEHIEGHT));

        canvas.setFocusable(false);

    }

    private void LinkCanvasWithFrane() {

        frame.add(canvas);

    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
    
    public int getFrameWidth(){
    return FRAMEWIDTH;
    }
    
    public int getFrameHieght(){
    return FRAMEHIEGHT;
    }

}
