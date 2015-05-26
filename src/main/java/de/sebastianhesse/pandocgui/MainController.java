package de.sebastianhesse.pandocgui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller class to control workflow, generate and execute Pandoc command.
 */
@Controller
public class MainController {

    @FXML
    private Stage stage;
    @FXML
    private ListView<File> inputFiles;
    @FXML
    private TextField pandocLocation;
    @FXML
    private TextArea commandOptions;
    @FXML
    private Label targetResult;
    @FXML
    private TextArea generatedCommand;
    @FXML
    private TextField outputFileLocation;

    @Autowired
    private FormatController formatController;

    /**
     * Different FileChooser
     */
    private FileChooser pandocLocationFileChooser = new FileChooser();
    private FileChooser outputFileLocationFileChooser = new FileChooser();
    private FileChooser inputFilesFileChooser = new FileChooser();

    private ObservableList<File> observableFileList = FXCollections.observableArrayList();


    /**
     * Opens a file dialog for location of Pandoc executable. Stores path in {@link #pandocLocation}.
     */
    @FXML
    public void openPandocLocationFileDialog() {
        File pandocExecutable = this.pandocLocationFileChooser.showOpenDialog(this.stage);
        if (null != pandocExecutable) {
            this.pandocLocation.setText(pandocExecutable.getPath());
            this.formatController.setPandocLocation(pandocExecutable.getPath());
        }
    }

    /**
     * Opens a file dialog for location of output file. Stores path in {@link #outputFileLocation}.
     */
    @FXML
    public void openOutputFileLocationFileDialog() {
        File outputFile = this.outputFileLocationFileChooser.showSaveDialog(this.stage);
        if (null != outputFile) {
            this.outputFileLocation.setText(outputFile.getPath());
        }
    }

    /**
     * Connects observable file list to list view of input files if not done yet. Opens a multiple file dialog for
     * locations of input files. Files are added to observable list and directly shown in {@link #inputFiles}.
     */
    @FXML
    public void openInputFilesDialog() {
        if (null == this.inputFiles.getItems() || this.inputFiles.getItems().isEmpty()) {
            this.inputFiles.setItems(this.observableFileList);
        }
        List<File> fileList = this.inputFilesFileChooser.showOpenMultipleDialog(this.stage);
        if (null != fileList && fileList.size() > 0) {
            this.observableFileList.addAll(fileList);
        }
    }

    /**
     * Generates the used Pandoc command and sets it to {@link #generatedCommand}.
     */
    @FXML
    public void generateCommand() {
        String generatedCommandAsString = this.pandocLocation.getText() + " " +
                getCommandOptions() + " -o " + this.outputFileLocation.getText() + " " +
                getInputFilesAsString();
        this.generatedCommand.setText(generatedCommandAsString);
    }

    /**
     * Check for empty locations/paths or empty command first. Execute command from {@link #generatedCommand} in new
     * process and wait for process.
     */
    @FXML
    public void startPandoc() {
        if (this.pandocLocation.getText().equals("")) {
            this.targetResult.setText("You must specify the Pandoc location.");
        } else if (null == this.inputFiles.getItems() || 0 == this.inputFiles.getItems().size()) {
            this.targetResult.setText("You must specify at least one input file.");
        } else if (this.generatedCommand.getText().isEmpty()) {
            this.targetResult.setText("You must generate the command first.");
        } else {
            try {
                Process pandocExec = Runtime.getRuntime().exec(this.generatedCommand.getText());
                pandocExec.waitFor();
                // TODO: read from error stream and show it to user
                this.targetResult.setText("Process complete.");
            } catch (IOException | InterruptedException e) {
                this.targetResult.setText("Process failed. Please check your input.");
            }
        }
    }

    private String getCommandOptions() {
        return this.commandOptions.getText();
    }

    private String getInputFilesAsString() {
        StringBuilder inputFiles = new StringBuilder();
        for (File file : this.inputFiles.getItems()) {
            inputFiles.append(file.getPath()).append(" ");
        }
        return inputFiles.toString();
    }

}
