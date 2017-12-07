package com.dakkra.vizignore.tools;

import com.dakkra.vizignore.VizIgnore;

public class StartupArgumentHandler {
    private static boolean continueProgram = true;

    public static void handle(String... args) {
        for (String s : args) {
            switch (s) {
                case "--version": {
                    printVersion();
                    continueProgram = false;
                    break;
                }
                case "--help": {
                    printHelp();
                    continueProgram = false;
                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void printVersion() {
        System.out.println();
        System.out.println("VizIgnore v" + VizIgnore.APPLICATION_VERSION);
        System.out.println("Copyright (c) 2017 Christopher Soderquist");
        System.out.println("Source code licenced under the MIT license");
    }

    private static void printHelp() {
        printVersion();
        System.out.println();
        System.out.println("--help      Prints this help menu");
        System.out.println("--version   Prints the version number");
    }

    public static boolean shouldContinue() {
        return continueProgram;
    }
}
