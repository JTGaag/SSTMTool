package tool;

import data.controller.DomParser;
import data.controller.IOController;
import data.controller.ModelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Random;

public class MainController {
    public Label helloWorld;
    public TextArea textArea;
    public Label textFileLocation;
    @FXML private TextField fileNameTextField;
    private IOController ioController = new IOController();

    private ModelController.ModelControllerInterface modelControllerInterface = new ModelController.ModelControllerInterface() {
        @Override
        public void writeToLog(String logString) {
            textArea.appendText(logString+"\n");
        }
    };


    private DomParser domParser = new DomParser(modelControllerInterface);

    public void openPapyrusModelSelector(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select papyrus model");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("UML model", "*.uml")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader in = new BufferedReader(new FileReader(selectedFile));
                String line;
                while((line = in.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                in.close();
                textArea.appendText("SysML file opened\n");
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
            textArea.appendText("Directory selected\n");
        }
    }

    public void createOutputFile(ActionEvent actionEvent) {
        textArea.setText(fileNameTextField.getText());

    }

    public void writeSlimToFile(ActionEvent actionEvent) {
        boolean success = ioController.createOutputFile(fileNameTextField.getText());
        if (success) {
            textFileLocation.setText(ioController.getOutputFile().getAbsolutePath());
            ioController.writeToOutputFile(domParser.getSlimText(true));
            textArea.appendText("SLIM code written to file\n");
        }

    }

    public void writeSlimToFileTypesFirst(ActionEvent actionEvent) {
        boolean success = ioController.createOutputFile(fileNameTextField.getText());
        if (success) {
            textFileLocation.setText(ioController.getOutputFile().getAbsolutePath());
            ioController.writeToOutputFile(domParser.getSlimText(false));
            textArea.appendText("SLIM code written to file\n");
        }
    }
}
