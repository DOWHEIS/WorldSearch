import bsu.edu.cs222.model.InputModifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class InputModifierTest {
    @Test
    public void createURLTest(){
        InputModifier inputModifier = new InputModifier();
        List<String> fullURL = inputModifier.createURL("FR");
        Assertions.assertEquals("https://api.worldbank.org/v2/country/FR?format=json", fullURL.get(0));
    }
    @Test
    public void createIndicatorURLSTest() {
        InputModifier inputModifier = new InputModifier();
        List<String> indicators = inputModifier.createIndicatorURLS("fr");
        Assertions.assertEquals("https://api.worldbank.org/v2/country/fr/indicator/NY.GDP.MKTP.CD?format=json&mrv=1", indicators.get(0));
    }
}
