package com.dakkra.vizignore.gui;

import com.dakkra.vizignore.VizIgnore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        JFrame mainFrame = new JFrame("VizIgnore");
        mainFrame.setSize(new Dimension(500, 500));
        mainFrame.addWindowListener(new FrameEar(mainFrame));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void readyToClose() {
        VizIgnore.shutdown();
    }

    class FrameEar implements WindowListener {
        JFrame frame;

        public FrameEar(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowClosing(WindowEvent e) {
            frame.dispose();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            readyToClose();
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }
}
