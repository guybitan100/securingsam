package com.reputation.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.*;
import java.util.List;

public class FileUtil {
    final static Logger log4j = Logger.getLogger(FileUtil.class);
    private BufferedWriter bufferedWriter = null;

    public FileUtil(String baseFileName) throws IOException {
        setFileWriter(baseFileName);
    }

    public void write(String str) throws IOException {
        this.bufferedWriter.write(str);
    }

    public void close() throws IOException {
        this.bufferedWriter.close();
    }

    public static String readFile(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        return String.join("", lines);

    }

    private void setFileWriter(String baseFileName) throws IOException {

        String filePath = System.getProperty("user.dir");
        Path path = Paths.get(filePath + "/Errors-Logs");

        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            System.out.println(e);
        }

        File file = new File(path + "/" + baseFileName + TimeUtil.getCurrentTime() + ".log");

        try {
            if (!file.exists()) {
                file.createNewFile();
                log4j.debug("File " + file.getName() + " created ...");
            } else {
                log4j.debug("File " + file.getName() + " already exist");
            }
        } catch (IOException e) {
            log4j.debug(e);
        }
        bufferedWriter = new BufferedWriter(new FileWriter(file));
    }

    public static void deleteAllFiles() {
        File folder = new File(System.getProperty("user.dir"));
        File[] files = folder.listFiles((dir, name) -> {
            return (name.endsWith(".csv") || name.endsWith(".jtl"));
        });
        for (final File file : files) {
            if (!file.delete()) {
                System.err.println("Can't remove " + file.getAbsolutePath());
            }
        }
    }


    public static void deleteCsvFiles() {
        File folder = new File(System.getProperty("user.dir"));
        File[] files = folder.listFiles((dir, name) -> {
            return (name.endsWith("csv") && name.contains("Past"));
        });
        for (final File file : files) {
            if (!file.delete()) {
                System.err.println("Can't remove " + file.getAbsolutePath());
            }
        }
    }
}
