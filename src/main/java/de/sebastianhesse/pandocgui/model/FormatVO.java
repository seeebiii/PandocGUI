package de.sebastianhesse.pandocgui.model;

import de.sebastianhesse.pandocgui.enums.Format;

/**
 * Created by SebastianHesse on 26.05.2015.
 */
public class FormatVO {

    public Format formatType;

    public String formatValue;

    public FormatVO(final Format formatType, final String formatValue) {
        if (null == formatType || null == formatValue) {
            throw new IllegalArgumentException("Constructor arguments may not be null.");
        }
        this.formatType = formatType;
        this.formatValue = formatValue;
    }
}
