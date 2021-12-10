import bsu.edu.cs222.model.GetDataFromJSON;
import bsu.edu.cs222.model.MapSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapSearcherTest {
    @Test
    public void getISOCodeTest() throws IOException {
        List<String> returnedJSON = new ArrayList<>();

        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));

        returnedJSON.add(json);

        GetDataFromJSON getDataFromJSON = new GetDataFromJSON();
        Map<String, String> map;
        map = getDataFromJSON.mapISO2Codes(returnedJSON);

        MapSearcher mapSearcher = new MapSearcher();
        String iso;
        iso = mapSearcher.getISOCode(map, "france");
        Assertions.assertEquals("FR", iso);
    }

    @Test
    public void getNameTest() throws IOException {
        List<String> returnedJSON = new ArrayList<>();

        String file = "src/test/resources/allCountryBasic.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));

        returnedJSON.add(json);

        GetDataFromJSON getDataFromJSON = new GetDataFromJSON();
        Map<String, String> map;
        map = getDataFromJSON.mapISO2Codes(returnedJSON);

        MapSearcher mapSearcher = new MapSearcher();
        String name;
        name = mapSearcher.getName(map, "FR");
        Assertions.assertEquals("france", name);
    }


}
