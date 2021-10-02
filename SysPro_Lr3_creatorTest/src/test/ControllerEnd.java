package test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ControllerEnd {

    @FXML
    public void onClick(ActionEvent e) throws Exception {
        String testFile = "test.json", testGrable = "testGrable.json", testAbout = "testAbout.json";
        JsonArray jsonTest = JsonParser.parseReader(new FileReader(testFile)).getAsJsonArray();
        JsonArray jsonGradle = JsonParser.parseReader(new FileReader(testGrable)).getAsJsonArray();
        JsonObject jsonAbout = JsonParser.parseReader(new FileReader(testAbout)).getAsJsonObject();

        JsonObject resultProduct = new JsonObject();
        resultProduct.add("about", jsonAbout);
        resultProduct.add("test", jsonTest);
        resultProduct.add("gradle", jsonGradle);

        try (FileWriter fileWriter = new FileWriter("resultProduct.json")) {
            fileWriter.write(resultProduct.toString());
            new File(testFile).delete();
            new File(testGrable).delete();
            new File(testAbout).delete();
        } catch (Exception error) {
            error.printStackTrace();
        }

        System.exit(0);
    }
}
