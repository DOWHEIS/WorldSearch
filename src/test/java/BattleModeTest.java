import bsu.edu.cs222.model.BattleMode;
import bsu.edu.cs222.model.GetDataFromJSON;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BattleModeTest {
    @Test
    public void getTopFourTest() throws IOException {
        String file = "src/main/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));
        List<String> isoJSON = new ArrayList<>();
        isoJSON.add(json);

        GetDataFromJSON getDataFromJSON = new GetDataFromJSON();
        List<String> isoCodes = getDataFromJSON.listISO2Codes(isoJSON);

        BattleMode battleMode = new BattleMode();
        List<String> topFour = battleMode.getTopFour(isoCodes);

        Assertions.assertEquals(topFour.size(), 4);
    }
}
