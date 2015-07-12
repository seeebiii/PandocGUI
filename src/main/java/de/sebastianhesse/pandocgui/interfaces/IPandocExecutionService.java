package de.sebastianhesse.pandocgui.interfaces;

import javafx.scene.control.Label;

/**
 * Created by SebastianHesse on 12.07.2015.
 */
public interface IPandocExecutionService {

    /**
     * Execute {@code command} and write result to {@code resultLabel}.
     * @param command String of command to execute
     * @param resultLabel label to write result of execution
     */
    void executeCommand(String command, Label resultLabel);
}
