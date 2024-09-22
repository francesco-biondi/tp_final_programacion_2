package com.tpfinalprogramacion2.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tpfinalprogramacion2.models.Save;

import java.io.*;
import java.util.Scanner;

public final class SaveManager {

    private static final String[] SLOT_PATHS = {
        "src/main/resources/saves/save1.json",
        "src/main/resources/saves/save2.json",
        "src/main/resources/saves/save3.json"
    };

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void newGame(int slotIndex) {
        if(!slotExists(slotIndex)) {
            String saveName = newSaveName();
            saveGame(new Save(saveName),slotIndex);
        }
    }

    public static void saveGame(Save save, int slotIndex){
        try (FileWriter writer = new FileWriter(SLOT_PATHS[slotIndex])) {
            gson.toJson(save, writer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Save loadGame(int slotIndex) {
        try (FileReader reader = new FileReader(SLOT_PATHS[slotIndex])) {
            return gson.fromJson(reader, Save.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean slotExists(int slotIndex) {
        return new java.io.File(SLOT_PATHS[slotIndex]).exists();
    }

    public static String newSaveName() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}

