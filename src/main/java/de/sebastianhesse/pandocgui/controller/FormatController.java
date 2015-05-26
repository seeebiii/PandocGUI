package de.sebastianhesse.pandocgui.controller;

import de.sebastianhesse.pandocgui.enums.Format;
import de.sebastianhesse.pandocgui.interfaces.IFileFormatService;
import de.sebastianhesse.pandocgui.model.FormatVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Observable;


/**
 * This controller is responsible for the left and right box of the GUI where the input and output formats are. It
 * populates two methods to create the input and output format after {@link #pandocLocation} is set.
 * <p>
 * Created by SebastianHesse on 26.05.2015.
 */
@Controller
public class FormatController extends Observable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatController.class);

    private String pandocLocation;

    @FXML
    private ComboBox<String> inputCombo;
    @FXML
    private ComboBox<String> outputCombo;

    @Autowired
    private IFileFormatService fileFormatService;

    public void inputFormatChanged() {
        if (!inputCombo.getValue().isEmpty()) {
            String outputFormat = inputCombo.getValue();
            setChanged();
            LOGGER.debug("notify observers...");
            notifyObservers(new FormatVO(Format.INPUT, outputFormat));
        }
    }

    public void outputFormatChanged() {
        if (!outputCombo.getValue().isEmpty()) {
            String outputFormat = outputCombo.getValue();
            setChanged();
            LOGGER.debug("notify observers...");
            notifyObservers(new FormatVO(Format.OUTPUT, outputFormat));
        }
    }

    /**
     * Sets the location of Pandoc executable. Directly retrieves available input and output formats.
     *
     * @param pandocLocation location of Pandoc executable
     */
    public void setPandocLocation(final String pandocLocation) {
        if (null != pandocLocation) {
            this.pandocLocation = pandocLocation;
            addInputFormats();
            addOutputFormats();
        }
    }

    private void addInputFormats() {
        addFormats(this.fileFormatService.getInputFormats(this.pandocLocation), this.inputCombo);
    }

    private void addOutputFormats() {
        addFormats(this.fileFormatService.getOutputFormats(this.pandocLocation), this.outputCombo);
    }

    private void addFormats(List<String> formats, ComboBox<String> comboBox) {
        comboBox.setItems(FXCollections.observableArrayList(formats));
    }
}
