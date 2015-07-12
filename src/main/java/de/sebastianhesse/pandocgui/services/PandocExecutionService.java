package de.sebastianhesse.pandocgui.services;

import de.sebastianhesse.pandocgui.interfaces.IPandocExecutionService;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by SebastianHesse on 12.07.2015.
 */
@Service
public class PandocExecutionService implements IPandocExecutionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PandocExecutionService.class);

    @Override
    public void executeCommand(final String command, final Label resultLabel) {
        try {
            Process pandocExec = Runtime.getRuntime().exec(command);
            pandocExec.waitFor();
            InputStream errorStream = pandocExec.getErrorStream();
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
            // if error happened, print error message in Alert
            if (errorReader.ready()) {
                LOGGER.info("error stream available");
                String errorText = "";
                while (errorReader.ready()) {
                    errorText += errorReader.readLine();
                }
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, errorText);
                errorAlert.show();
            } else {
                resultLabel.setText("Process complete.");
            }
        } catch (IOException | InterruptedException e) {
            resultLabel.setText("Something failed while executing command.");
        }
    }
}
