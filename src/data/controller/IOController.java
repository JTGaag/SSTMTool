package data.controller;

import java.io.*;

/**
 * Created by Joost on 17-Feb-17.
 */
public class IOController {
    File outputDirectory;
    String fileName = "output";
    String fileExtension = "slim";
    File outputFile;

    public boolean createOutputFile() {
        if (outputDirectory == null) return false;
        outputFile = new File(outputDirectory.getAbsolutePath() + File.separator + fileName + "." + fileExtension);
        System.out.println(outputFile.getAbsolutePath());
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createOutputFile(String fileName) {
        if (fileName == null || fileName.trim().equals("")) fileName = this.fileName;
        if (outputDirectory == null) return false;
        outputFile = new File(outputDirectory.getAbsolutePath() + File.separator + fileName + "." + fileExtension);
        System.out.println(outputFile.getAbsolutePath());
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void writeToOutputFile(String string) {
        if (outputFile == null) return;
        PrintWriter writer = null;
        try {
            BufferedReader reader = new BufferedReader(new StringReader(string));
            writer = new PrintWriter(outputFile);

            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}
