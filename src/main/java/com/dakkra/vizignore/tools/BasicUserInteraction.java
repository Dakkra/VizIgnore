package com.dakkra.vizignore.tools;

import com.dakkra.vizignore.VizIgnore;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class BasicUserInteraction {

    /**
     * Asks the user to provide a directory by letting them choose with a file chooser
     *
     * @param cb FileCallback to be called when file has been obtained
     */
    public static void getDirectoryFromUser(FileCallback cb) {
        File dir = null;
        JFrame iconFrame = new JFrame();
        iconFrame.setIconImage(GeneralIO.readResourceImage("iconSmall.png"));
        JFileChooser jfc = new JFileChooser(VizIgnore.sessionDirectory);
        jfc.setDialogTitle("Choose initial git folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        while (dir == null) {
            int result = jfc.showDialog(iconFrame, "Select");
            if (result == JFileChooser.APPROVE_OPTION) {
                dir = jfc.getSelectedFile();
                dir = (SessionFilesUtil.hasGitIgnore(dir)) ? dir : null;
            } else {
                dir = null;
                break;
            }
        }
        cb.run(dir);
    }

}
