package game;

import game.models.saves.Save;

import java.io.FileReader;
import java.io.IOException;

import static game.models.saves.dependencies.GsonManager.gson;

public class DataProvider {

    public static Save saveMock(){
        try (FileReader reader = new FileReader("src/test/java/mock/saveMock.json")) {
            return gson.fromJson(reader, Save.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
