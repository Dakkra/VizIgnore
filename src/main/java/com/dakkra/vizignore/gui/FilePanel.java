package com.dakkra.vizignore.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FilePanel extends JPanel {

    public FilePanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(UserInterfaceConstants.FILE_PANEL_BACKGROUND);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, 25);
    }
}
