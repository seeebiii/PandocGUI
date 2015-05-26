package de.sebastianhesse.pandocgui.services;

import de.sebastianhesse.pandocgui.interfaces.IFileFormatService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides methods to get the supported input and output formats of Pandoc.
 * <p>
 * Created by SebastianHesse on 25.05.2015.
 */
@Service
public class FileFormatService implements IFileFormatService {

    private String pandocHelpOutput = null;
    private List<String> inputFormats;
    private List<String> outputFormats;

    public FileFormatService() {
        this.inputFormats = new ArrayList<>();
        this.outputFormats = new ArrayList<>();
    }

    @Override
    public List<String> getInputFormats(final String pandocLocation) {
        if (null != this.inputFormats && this.inputFormats.size() >= 1) {
            return this.inputFormats;
        }
        // TODO: create regular expressions instead using split method
        String[] inputFormats = getPandocInputFormatsUnformatted(pandocLocation).replaceAll(" ", "").split(",");
        this.inputFormats = Arrays.asList(inputFormats);
        return this.inputFormats;
    }

    @Override
    public List<String> getOutputFormats(final String pandocLocation) {
        if (null != this.outputFormats && this.outputFormats.size() >= 1) {
            return this.outputFormats;
        }
        // TODO: create regular expressions instead using split method
        String[] outputFormats = getPandocOutputFormatsUnformatted(pandocLocation).replaceAll(" ", "").split("\\[")[0].split(",");
        this.outputFormats = Arrays.asList(outputFormats);
        return this.outputFormats;
    }

    private String getPandocInputFormatsUnformatted(final String pandocLocation) {
        if (null != this.pandocHelpOutput) {
            return this.pandocHelpOutput.split("formats:")[1];
        }
        return getPandocHelp(pandocLocation).split("formats:")[1];
    }

    private String getPandocOutputFormatsUnformatted(final String pandocLocation) {
        if (null != this.pandocHelpOutput) {
            return this.pandocHelpOutput.split("formats:")[2];
        }
        return getPandocHelp(pandocLocation).split("formats:")[2].split("Options:")[0];
    }

    private String getPandocHelp(final String pandocLocation) {
        String result = "";
        try {
            Process p = Runtime.getRuntime().exec(pandocLocation + " --help");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.replaceAll("\\*", "");
    }
}
