package com.dakkra.vizignore.gui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainPanel extends JPanel {

    private Color backgroundColor = new Color(120, 120, 120);
    private LinkedList<FilePanel> filePanels;
    private BoxLayout layout;

    public MainPanel() {
        init();
    }

    /**
     * Initialize this panel
     */
    private void init() {
        filePanels = new LinkedList<>();
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);

        setLayout(layout);
        //Test
        for (int i = 0; i < 10; i++) {
            this.add(new FilePanel());
            this.add(Box.createRigidArea(new Dimension(0, 5)));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
