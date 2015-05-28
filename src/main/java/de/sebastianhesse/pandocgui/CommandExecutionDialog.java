package de.sebastianhesse.pandocgui;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * This class represents a dialog to present the generated command to the user.
 * Command is put into a text area so that it is editable.
 * User can click "execute" (text from text area is executed) or "cancel" (nothing is going to happen).
 *
 * Created by SebastianHesse on 28.05.2015.
 */
public class CommandExecutionDialog extends Dialog<String> {

    private TextArea commandTextArea;

    /**
     * Creates a dialog and sets a text area with text as generatedCommand so that it's editable.
     * Also adds buttons so that user can confirm ("execute") or cancel the execution.
     *
     * @param generatedCommand generated command to put into text area
     */
    public CommandExecutionDialog(final String generatedCommand) {
        super();
        setTitle("Confirm command execution");
        setHeaderText("Check the generated command, change it if you want and execute it.");
        addTextAreaWithCommand(generatedCommand);
        addButtons();
        addResultConverter();
    }

    private void addTextAreaWithCommand(final String generatedCommand) {
        // add text area with generated command
        GridPane gridPane = new GridPane();
        commandTextArea = new TextArea(generatedCommand);
        commandTextArea.setEditable(true);
        commandTextArea.setWrapText(true);
        commandTextArea.setPrefWidth(700);
        commandTextArea.setPrefHeight(400);
        gridPane.getChildren().add(commandTextArea);
        getDialogPane().setContent(gridPane);
    }

    private void addButtons() {
        // add buttons
        getDialogPane().getButtonTypes().addAll(new ButtonType("Execute", ButtonBar.ButtonData.OK_DONE), ButtonType.CANCEL);
    }

    private void addResultConverter() {
        // add result converter
        setResultConverter(param -> {
            if (ButtonType.CANCEL.equals(param)) {
                return "";
            } else {
                return commandTextArea.getText();
            }
        });
    }

}
