package bsu.edu.cs222.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputModifier {
    public List<String> createURL(String encodedSearchString) {
        String urlStart = "https://api.worldbank.org/v2/country/";
        String urlEnd = "?format=json";
        List<String> fullURL = new ArrayList<>();
        fullURL.add(urlStart + encodedSearchString + urlEnd);
        return fullURL;
    }

    public List<String> createIndicatorURLS(String encodedSearchString) {
        List<String> indicators = Arrays.asList("NY.GDP.MKTP.CD", "SP.POP.TOTL","SP.POP.GROW","EN.POP.DNST","SP.DYN.CDRT.IN", "SL.UEM.TOTL.ZS","SI.POV.DDAY","VC.IHR.PSRC.P5", "SE.PRM.TENR", "NY.ADJ.NNTY.PC.CD",
                "SP.DYN.LE00.IN", "AG.LND.TOTL.K2");
        List<String> fullIndicatorURLS = new ArrayList<>();
        for (String indicator : indicators) {
            String urlStart = String.format("https://api.worldbank.org/v2/country/%s/indicator/%s?format=json&mrv=1", encodedSearchString, indicator);
            fullIndicatorURLS.add(urlStart);
        }
        return fullIndicatorURLS;
    }
}
