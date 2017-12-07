package com.dakkra.vizignore.tools;

import com.dakkra.vizignore.VizIgnore;

import java.io.*;
import java.util.ArrayList;

public class GitIgnoreUtil {
    public static final String GITIGNORE_FILE_NAME = ".gitignore";

    /**
     * Returns the .gitignore file in session directory
     * Creates the file if it has been deleted
     */
    public static File getFile() {
        File ignoreFile = new File(VizIgnore.sessionDirectory.getAbsolutePath() + File.separator + GITIGNORE_FILE_NAME);
        if (!ignoreFile.exists()) {
            try {
                ignoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ignoreFile;
    }

    /**
     * Returns all files ignored by the .gitignore file
     * <p>
     * Excludes directories
     */
    public static ArrayList<File> getIgnoredFile() {
        ArrayList<File> allFiles = getAllIgnored();
        ArrayList<File> files = new ArrayList<>();
        for (File f : allFiles)
            if (f.isFile()) files.add(f);
        return files;
    }

    /**
     * Returns a list of all directories ignored by the .gitignore file
     */
    public static ArrayList<File> getIgnoredDirectories() {
        ArrayList<File> files = getAllIgnored();
        ArrayList<File> directories = new ArrayList<>();
        for (File f : files)
            if (f.isDirectory()) directories.add(f);
        return directories;
    }

    /**
     * Returns an ArrayList of Files that represent the files and directories ignored by the .gitignore file
     */
    public static ArrayList<File> getAllIgnored() {
        ArrayList<File> files = new ArrayList<>();
        ArrayList<String> names = getAllIgnoredNames();
        for (String s : names) {
            files.add(new File(VizIgnore.sessionDirectory.getAbsolutePath() + File.separator + s));
        }
        return files;
    }

    /**
     * Returns names of all files ignored by the .gitignore file
     * <p>
     * Excludes directories
     */
    public static ArrayList<String> getIgnoredFileNames() {
        ArrayList<String> names = getAllIgnoredNames();
        ArrayList<String> files = new ArrayList<>();
        for (String s : names) {
            File f = new File(VizIgnore.sessionDirectory.getAbsolutePath() + File.separator + s);
            if (f.exists() && f.isFile()) files.add(f.getName());
        }
        return files;
    }

    /**
     * Returns names of all directories ignored by the .gitignore file
     */
    public static ArrayList<String> getIgnoredDirectoryNames() {
        ArrayList<String> names = getAllIgnoredNames();
        ArrayList<String> directories = new ArrayList<>();
        for (String s : names) {
            File f = new File(VizIgnore.sessionDirectory.getAbsolutePath() + File.separator + s);
            if (f.exists() && f.isDirectory()) directories.add(f.getName());
        }
        return directories;
    }

    /**
     * Returns an ArrayList of Strings that represent the files and directories ignored by the .gitignore file
     */
    public static ArrayList<String> getAllIgnoredNames() {
        File ignoreFile = getFile();
        ArrayList<String> fileNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ignoreFile))) {
            String buff;
            while ((buff = reader.readLine()) != null) {
                File f = new File(VizIgnore.sessionDirectory.getAbsolutePath() + File.separator + buff);
                if (f.exists()) fileNames.add(buff);
                System.out.println("Buff: " + buff);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }
}
