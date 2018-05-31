package com.dakkra.vizignore;

import com.dakkra.vizignore.gui.MainWindow;
import com.dakkra.vizignore.tools.BasicUserInteraction;
import com.dakkra.vizignore.tools.SessionFilesUtil;
import com.dakkra.vizignore.tools.StartupArgumentHandler;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VizIgnore {

    public static final String APPLICATION_VERSION = "0.1.1-InDev";
    public static File homeDirectory = null;
    public static File sessionDirectory = null;
    private static Thread applicationThread;

    /**
     * Entry point
     */
    public static void main(String[] args) {
        System.out.println("Welcome to VizIgnore!");
        String homeDirectoryPath = System.getProperty("user.home");
        homeDirectory = new File(homeDirectoryPath);
        StartupArgumentHandler.handle(args);
        if (StartupArgumentHandler.shouldContinue()) initialize();
    }

    /**
     * Initializes the application
     */
    private static void initialize() {
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
        SessionFilesUtil.saveSessionFile(sessionDirectory);
        System.out.println("Session Directory: " + sessionDirectory.getAbsolutePath());
        SwingUtilities.invokeLater(VizIgnore::launchWindow);
    }

    /**
     * Launches a new window for this application. Should only be used by Swing Utilities
     */
    private static void launchWindow() {
        MainWindow mw = new MainWindow();
    }

    /**
     * Shuts down the program. Should be called when the application is done executing.
     */
    public static void shutdown() {
        System.out.println("Exiting VizIgnore...");
        //Dispose all frames left over by AWT layer
        for (Frame f : Frame.getFrames())
            f.dispose();
    }
}
