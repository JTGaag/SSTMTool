package tool;

import data.controller.DomParser;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Random;

public class MainController {
    public Label helloWorld;
    public TextArea textArea;
    private DomParser domParser = new DomParser();

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
}
