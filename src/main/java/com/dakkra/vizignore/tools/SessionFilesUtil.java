package com.dakkra.vizignore.tools;

import com.dakkra.vizignore.VizIgnore;

import java.io.*;
import java.util.Objects;

public class SessionFilesUtil {
    public static final String SESSION_DIRECTORY_NAME = ".VizIgnore";
    public static final String GITIGNORE_FILE_NAME = GitIgnoreUtil.GITIGNORE_FILE_NAME;
    private static final String SESSION_FILE_NAME = "session";

    public static File applicationDirectory = null;

    /**
     * Restores the last session by returning the session directory saved in the session file
     */
    public static File restore() {
        ensureApplicationDirectory();
        return getSessionDirectory();
    }

    /**
     * Deletes the session file
     */
    public static void clearSession() {
        File sFile = getSessionFile();
        sFile.delete();
    }

    /**
     * Checks to see if the file has a .gitignore file
     * <p>
     * Returns false if there is no gitignore file or if the supplied file is not a directory
     */
    public static boolean hasGitIgnore(File d) {
        if (d != null && d.exists() && d.isDirectory()) {
            try {
                for (File f : d.listFiles()) {
                    if (f.getName().equals(GITIGNORE_FILE_NAME)) return true;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Gets the session file. If it doesn't exist, creates it.
     */
    public static File getSessionFile() {
        ensureApplicationDirectory();
        File sessionFile = null;
        for (File f : applicationDirectory.listFiles()) {
            if (f.getName().equals(SESSION_FILE_NAME)) {
                sessionFile = f;
                break;
            }
        }
        if (sessionFile == null) try {
            sessionFile = new File(applicationDirectory.getAbsolutePath() + File.separator + SESSION_FILE_NAME);
            sessionFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileEmpty(sessionFile)) {
            initSessionFile(sessionFile, VizIgnore.homeDirectory);
        }
        return sessionFile;
    }

    /**
     * Saves the session file with the session directory
     */
    public static void saveSessionFile(File sessionDirectory) {
        if (sessionDirectory.exists() && sessionDirectory.isDirectory()) {
            save(sessionDirectory);
        } else {
            save(VizIgnore.homeDirectory);
        }
    }

    /**
     * Returns the session directory written in the session file
     */
    public static File getSessionDirectory() {
        ensureApplicationDirectory();
        String path = read();
        File sessionDirectory = new File(path);
        if (sessionDirectory.exists() && sessionDirectory.isDirectory()) return sessionDirectory;
        else return VizIgnore.homeDirectory;
    }

    /**
     * Ensures that the application directory exists
     */
    private static void ensureApplicationDirectory() {
        if (VizIgnore.homeDirectory != null) {
            for (File f : VizIgnore.homeDirectory.listFiles()) {
                if (Objects.equals(f.getName(), SESSION_DIRECTORY_NAME) && f.isDirectory()) {
                    // Application directory exists
                    applicationDirectory = f;
                    return;
                }
            }
            //Only here if application directory doesn't exist
            File appDir = new File(VizIgnore.homeDirectory.getAbsolutePath() + File.separator + SESSION_DIRECTORY_NAME);
            appDir.mkdir();
            applicationDirectory = appDir;
        }
    }

    /**
     * Writes the sessionDirectory to the sessionfile
     * <p>
     * Doesn't check the directory
     */
    private static void save(File sessionDirectory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getSessionFile()))) {
            writer.write(sessionDirectory.getAbsolutePath());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save session file!");
        }
    }

    /**
     * Reads the directory string from the sessionFile
     * <p>
     * Doesn't verify that directory is real
     */
    private static String read() {
        String directoryPath = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(getSessionFile()))) {
            directoryPath = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return directoryPath;
    }

    /**
     * Checks if the session file is empty (no content)
     */
    private static boolean fileEmpty(File sessionFile) {
        String buffer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(sessionFile))) {
            buffer = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer == null;
    }

    /**
     * Initializes the session file
     */
    private static void initSessionFile(File sFile, File sessionDirectory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sFile))) {
            writer.write(sessionDirectory.getAbsolutePath());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save session file!");
        }
    }
}
