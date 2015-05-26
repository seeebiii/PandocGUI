package de.sebastianhesse.pandocgui.interfaces;

import java.util.List;

/**
 * This interface provides methods to retrieve input and output formats of pandoc.
 *
 * Created by SebastianHesse on 25.05.2015.
 */
public interface IFileFormatService {

    /**
     * Gets all input formats from Pandoc.
     * @param pandocLocation Pandoc location to use for getting formats
     * @return list of formats as string, e.g. docx
     * @throws IllegalArgumentException if pandocLocation is null or empty
     */
    List<String> getInputFormats(String pandocLocation);

    /**
     * Gets all output formats from Pandoc.
     * @param pandocLocation Pandoc location to use for getting formats
     * @return list of formats as string, e.g. docx
     * @throws IllegalArgumentException if pandocLocation is null or empty
     */
    List<String> getOutputFormats(String pandocLocation);

}
