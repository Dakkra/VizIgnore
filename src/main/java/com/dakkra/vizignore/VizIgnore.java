package com.dakkra.vizignore;

import java.io.File;

public class VizIgnore {

    public static final String APPLICATION_VERSION = "0.1.1-Dev";
    public static File homeDirectory = null;
    public static File sessionDirectory = null;

    public static void main(String[] args) {
        System.out.println("Welcome to VixIgnore " + APPLICATION_VERSION + "!");
        StartupArgumentHandler.handle(args);
        if (StartupArgumentHandler.shouldContinue()) startProgram();
    }

    private static void startProgram() {
        initialize();
    }

    private static void initialize() {
        String hPath = System.getProperty("user.home");
        homeDirectory = new File(hPath);
        sessionDirectory = SessionFilesUtil.restore();
    }
}
