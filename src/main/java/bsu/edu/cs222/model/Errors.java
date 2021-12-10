
package bsu.edu.cs222.model;


import java.util.Locale;
import java.util.Map;

public class Errors {
    public Boolean checkNoInput(String search) {
        return !search.equals("");
    }

    public Boolean checkNotACountry(String country, Map<String, String> map) {
        return map.containsKey(country.toLowerCase(Locale.ROOT));
    }

}