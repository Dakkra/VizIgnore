package com.dakkra.vizignore.gui;

import com.dakkra.vizignore.VizIgnore;
import com.dakkra.vizignore.tools.SessionFilesUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MainPanel extends JPanel {

    private Color backgroundColor = UserInterfaceConstants.MAIN_BACKGROUND;
    private BoxLayout layout;

    public MainPanel() {
        init();
    }

    /**
     * Initialize this panel
     */
    private void init() {
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        ArrayList<File> fileList = new ArrayList<>();
        ArrayList<File> dirList = new ArrayList<>();
        for (File f : VizIgnore.sessionDirectory.listFiles())
            if (f.isDirectory()) dirList.add(f);
            else fileList.add(f);
        Collections.sort(fileList);
        Collections.sort(dirList);
        //Add directories first
        for (File f : dirList) {
            this.add(new FilePanel(f));
            this.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        //Files next, ignore .gitignore file
        for (File f : fileList) {
            //Skip the ignore file
            if (f.getName().equals(SessionFilesUtil.GITIGNORE_FILE_NAME))
                continue;
            this.add(new FilePanel(f));
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
