package tool;

import data.controller.DomParser;
import data.controller.IOController;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Random;

public class MainController {
    public Label helloWorld;
    public TextArea textArea;
    public Label textFileLocation;
    private DomParser domParser = new DomParser();
    private IOController ioController = new IOController();

    public void sayHelloWorld(ActionEvent actionEvent) {
        int random = (new Random()).nextInt(10000);
        helloWorld.setText("Hello World! You are user: " + random);
    }

    public void openPapyrusModelSelector(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select papyrus model");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("UML model", "*.uml")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            helloWorld.setText("File selected: " + selectedFile.getName());
            try {
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader in = new BufferedReader(new FileReader(selectedFile));
                String line;
                while((line = in.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                in.close();
                textArea.setText(stringBuilder.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Use DOM Parser
            domParser.setFile(selectedFile);
            domParser.readFile();

        }
    }

    public void selectOutputDirectory(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose output file directory");
        File outputDirectory = directoryChooser.showDialog(null);
        if (outputDirectory != null) {
            textFileLocation.setText(outputDirectory.getAbsolutePath());
            ioController.setOutputDirectory(outputDirectory);
        }
    }

    public void createOutputFile(ActionEvent actionEvent) {
        boolean success = ioController.createOutputFile();
        if (success) {
            textFileLocation.setText(ioController.getOutputFile().getAbsolutePath());
        }
    }

    public void writeSlimToFile(ActionEvent actionEvent) {
        ioController.writeToOutputFile(domParser.getSlimText(true));
    }

    public void writeSlimToFileTypesFirst(ActionEvent actionEvent) {
        ioController.writeToOutputFile(domParser.getSlimText(false));
    }
}
