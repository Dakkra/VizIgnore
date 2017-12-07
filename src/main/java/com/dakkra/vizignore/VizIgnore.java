package com.dakkra.vizignore;

import com.dakkra.vizignore.tools.BasicUserInteraction;
import com.dakkra.vizignore.tools.SessionFilesUtil;
import com.dakkra.vizignore.tools.StartupArgumentHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VizIgnore {

    public static final String APPLICATION_VERSION = "0.1.1-Dev";
    public static File homeDirectory = null;
    public static File sessionDirectory = null;
    private static Thread applicationThread;

    /**
     * Entry point
     */
    public static void main(String[] args) {
        System.out.println("Welcome to VixIgnore!");
        StartupArgumentHandler.handle(args);
        if (StartupArgumentHandler.shouldContinue()) initialize();
    }

    /**
     * Initializes the application
     */
    private static void initialize() {
        String homeDirectoryPath = System.getProperty("user.home");
        homeDirectory = new File(homeDirectoryPath);
        sessionDirectory = SessionFilesUtil.restore();
        if (homeDirectory.equals(sessionDirectory))
            SwingUtilities.invokeLater(VizIgnore::initWorkingDir);
        else start(sessionDirectory);
    }

    /**
     * Makes the user select a working directory
     */
    private static void initWorkingDir() {
        BasicUserInteraction.getDirectoryFromUser(VizIgnore::start);
    }

    /**
     * Async launches run()
     */
    private static void start(File dir) {
        if (dir != null && dir.exists() && dir.isDirectory()) {
            sessionDirectory = dir;
            applicationThread = new Thread(VizIgnore::run);
            applicationThread.start();
        } else {
            System.out.println("User cancelled startup");
            shutdown();
        }
    }

    /**
     * Main Application loop
     * <p>
     * Should be ran on a separate thread
     */
    private static void run() {
        System.out.println(sessionDirectory.getAbsolutePath());
        SessionFilesUtil.saveSessionFile(sessionDirectory);
        //TODO main loop and gui
        shutdown();
    }

    /**
     * Shuts down the program. Should be called when the application is done executing.
     */
    private static void shutdown() {
        System.out.println("Exiting VizIgnore...");
        //Dispose all frames left over by AWT layer
        for (Frame f : Frame.getFrames()) {
            f.dispose();
        }
    }
}
