package de.sebastianhesse.pandocgui;

import de.sebastianhesse.pandocgui.interfaces.IFileFormatService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;


/**
 * This controller is responsible for the left and right box of the GUI where the input and output formats are.
 * It populates two methods to create the input and output format after {@link #pandocLocation} is set.
 *
 * Created by SebastianHesse on 26.05.2015.
 */
@Controller
public class FormatController {

    private String pandocLocation;

    @FXML
    private VBox vBoxInput;
    @FXML
    private VBox vBoxOutput;
    @FXML
    private ToggleGroup toggleGroupInputFormats;
    @FXML
    private ToggleGroup toggleGroupOutputFormats;

    @Autowired
    private IFileFormatService fileFormatService;


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
        addFormats(this.fileFormatService.getInputFormats(this.pandocLocation), this.toggleGroupInputFormats, this.vBoxInput);
    }

    private void addOutputFormats() {
        addFormats(this.fileFormatService.getOutputFormats(this.pandocLocation), this.toggleGroupOutputFormats, this.vBoxOutput);
    }

    private void addFormats(List<String> formats, ToggleGroup toggleGroup, VBox vBox) {
        ObservableList<Node> vboxChildren = vBox.getChildren();
        // to avoid mutliple entries
        vboxChildren.clear();

        for (String format : formats) {
            RadioButton radioButton = new RadioButton(format);
            radioButton.setToggleGroup(toggleGroup);
            vboxChildren.add(radioButton);
        }
    }
}
