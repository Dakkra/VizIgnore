package com.dakkra.vizignore.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FilePanel extends JPanel {

    private File file;

    public FilePanel(File file) {
        this.file = file;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Background
        g2.setColor(UserInterfaceConstants.FILE_PANEL_BACKGROUND);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        //Title
        g2.setColor(UserInterfaceConstants.TITLE_TEXT);
        g2.setFont(new Font("monospace", Font.PLAIN, 30));
        FontMetrics fm = g2.getFontMetrics();
        String fileName = (file.isDirectory() ? "[DIR] " : "") + file.getName();
        int fontHeight = fm.getAscent();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString(fileName, 0, fontHeight);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, 40);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(UserInterfaceConstants.MIN_WIDTH, 40);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(UserInterfaceConstants.MIN_WIDTH, 40);
    }
}
