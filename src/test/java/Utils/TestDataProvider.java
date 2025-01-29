package Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDataProvider {

        // Read JSON file as String
        public JsonArray readJsonArrayFromFile(String filePath) throws IOException {
            FileReader reader = new FileReader(filePath);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            reader.close();
            return jsonElement.getAsJsonArray();
        }

        @DataProvider(name = "testData")
        public Iterator<Object[]> getData() throws IOException {
            JsonArray jsonArray = readJsonArrayFromFile("./src/test/java/Utils/Ecommerce.json");
            List<Object[]> testData = new ArrayList<>();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String name = jsonObject.get("name").getAsString();
                String gender = jsonObject.get("gender").getAsString();
                String country = jsonObject.get("country").getAsString();
                testData.add(new Object[]{name, gender,country});
            }

            return testData.iterator();
        }
    }

