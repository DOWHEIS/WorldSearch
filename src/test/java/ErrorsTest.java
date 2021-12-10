import bsu.edu.cs222.controller.Controller;
import bsu.edu.cs222.model.Errors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class ErrorsTest {
    Errors errors = new Errors();

    @Test
    public void testCheckNoInputNone() {
        String emptyInput = "";
        Assertions.assertFalse(errors.checkNoInput(emptyInput));
    }

    @Test
    public void testCheckNoInputWithInput() {
        String input = "Canada";
        Assertions.assertTrue(errors.checkNoInput(input));
    }

    @Test
    public void testNotACountryNone() {
        Controller controller = new Controller();
        Map<String, String> map = controller.getCountryISOMap();
        Assertions.assertFalse(errors.checkNotACountry("wonderland", map));
    }

    @Test
    public void testNotACountryCountry() {
        Controller controller = new Controller();
        Map<String, String> map = controller.getCountryISOMap();
        Assertions.assertTrue(errors.checkNotACountry("canada", map));
    }


}
