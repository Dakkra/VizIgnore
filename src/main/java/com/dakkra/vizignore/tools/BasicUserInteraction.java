package com.dakkra.vizignore.tools;

import com.dakkra.vizignore.VizIgnore;

import javax.swing.*;
import java.io.File;

public class BasicUserInteraction {

    /**
     * Asks the user to provide a directory by letting them choose with a file chooser
     */
    public static void getDirectoryFromUser(FileCallback cb) {
        File dir = null;
        JFileChooser jfc = new JFileChooser(VizIgnore.sessionDirectory);
        jfc.setDialogTitle("Choose initial git folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        while (dir == null) {
            int result = jfc.showDialog(null, "Select");
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
