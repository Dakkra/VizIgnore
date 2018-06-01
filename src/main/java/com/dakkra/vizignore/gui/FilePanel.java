package com.dakkra.vizignore.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FilePanel extends JPanel {

    private Color backgroundColor;
    private Random rand;

    public FilePanel() {
        rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        backgroundColor = new Color(r, g, b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(backgroundColor);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, 25);
    }
}
