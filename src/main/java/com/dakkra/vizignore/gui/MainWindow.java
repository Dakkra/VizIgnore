package com.dakkra.vizignore.gui;

import com.dakkra.vizignore.VizIgnore;
import com.dakkra.vizignore.tools.GeneralIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame {

    public MainWindow() {
        init();
        setVisible(true);
    }

    /**
     * Initialize this window and it's contents
     */
    private void init() {
        setTitle("VizIgnore");
        setSize(new Dimension(500, 500));
        setIconImage(GeneralIO.readResourceImage("iconSmall.png"));
        addWindowListener(new FrameEar(this));
        setLocationRelativeTo(null);

        //Title pane
        this.add(new TitlePanel(VizIgnore.sessionDirectory.getName()), BorderLayout.NORTH);
        //Add main panel
        MainPanel mainPane = new MainPanel();
        JScrollPane sp = new JScrollPane(mainPane);
        this.add(sp, BorderLayout.CENTER);
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
