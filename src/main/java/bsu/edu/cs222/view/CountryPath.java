package bsu.edu.cs222.view;

import javafx.scene.shape.SVGPath;

import java.util.Locale;

public class CountryPath extends SVGPath {
    //adding name/locale to each country on map
    private final String NAME;
    private final Locale LOCALE;


    public CountryPath(final String NAME, final String CONTENT) {
        super();
        this.NAME = NAME;
        this.LOCALE = new Locale("", NAME);
        if (null == CONTENT) return;
        setContent(CONTENT);
    }


    public String getName() {
        return NAME;
    }

    public Locale getLocale() {
        return LOCALE;
    }

}
