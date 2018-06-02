package com.dakkra.vizignore.gui;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {

    private String title;

    public TitlePanel(String title) {
        setTitle(title);
    }

    /**
     * Sets ths title for this panel and updates it
     */
    public void setTitle(String title) {
        this.title = title;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Background
        g2.setColor(UserInterfaceConstants.TITLE_BACKGROUND);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        //Title
        g2.setColor(UserInterfaceConstants.TITLE_TEXT);
        g2.setFont(new Font("monospace", Font.PLAIN, 30));
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(title);
        int fontHeight = fm.getAscent();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString(title, (this.getWidth() / 2) - (stringWidth / 2), fontHeight);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, UserInterfaceConstants.TITLE_FONT_SIZE);
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
