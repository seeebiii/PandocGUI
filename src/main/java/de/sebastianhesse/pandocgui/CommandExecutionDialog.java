package de.sebastianhesse.pandocgui;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a dialog to present the generated command to the user. Command is put into a text area so that
 * it is editable. User can click "execute" (text from text area is executed) or "cancel" (nothing is going to happen).
 * <p>
 * Created by SebastianHesse on 28.05.2015.
 */
public class CommandExecutionDialog extends Dialog<String> {

    private TextArea commandTextArea;
    /**
     * key = short path; value = long path
     */
    private Map<String, String> pathStore;
    /**
     * false = long paths are shown; true = short paths (= file names) are shown
     */
    private boolean toggleShortenPaths;

    /**
     * Creates a dialog and sets a text area with text as generatedCommand so that it's editable. Also adds buttons so
     * that user can confirm ("execute") or cancel the execution.
     *
     * @param generatedCommand generated command to put into text area
     */
    public CommandExecutionDialog(final String generatedCommand, final Map<String, String> pathStore) {
        if (null == pathStore) {
            this.pathStore = new HashMap<>();
        } else {
            this.pathStore = pathStore;
        }
        setTitle("Confirm command execution");
        setHeaderText("Check the generated command, change it if you want and execute it.");
        addGridPane(generatedCommand);
        addButtons();
        addResultConverter();
    }

    /**
     * Adds a grid pane containing a checkbox for path shortening and a text area containing the generated command
     *
     * @param generatedCommand generated command
     */
    private void addGridPane(final String generatedCommand) {
        // add text area with generated command
        GridPane gridPane = new GridPane();

        CheckBox tick = new CheckBox("Shorten paths");
        tick.setOnAction(event -> toggleShortenPaths());
        gridPane.addRow(0, tick);

        this.commandTextArea = new TextArea(generatedCommand);
        this.commandTextArea.setEditable(true);
        this.commandTextArea.setWrapText(true);
        this.commandTextArea.setPrefWidth(700);
        this.commandTextArea.setPrefHeight(400);
        gridPane.addRow(1, commandTextArea);

        getDialogPane().setContent(gridPane);
    }

    private void toggleShortenPaths() {
        String currentText = this.commandTextArea.getText();
        for (String key : pathStore.keySet()) {
            if (this.toggleShortenPaths) {
                currentText = currentText.replace(key, pathStore.get(key));
            } else {
                currentText = currentText.replace(pathStore.get(key), key);
            }
            this.commandTextArea.setText(currentText);
        }
        this.toggleShortenPaths = !this.toggleShortenPaths;
    }

    private void addButtons() {
        // add buttons
        getDialogPane().getButtonTypes().addAll(new ButtonType("Execute", ButtonBar.ButtonData.OK_DONE), ButtonType.CANCEL);
    }

    private void addResultConverter() {
        // add result converter
        setResultConverter(param -> {
            if (param.getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
                // convert back to full path of files if short path is visible, otherwise errors may occur
                if (this.toggleShortenPaths) {
                    toggleShortenPaths();
                }
                return commandTextArea.getText();
            } else {
                return "";
            }
        });
    }

}
