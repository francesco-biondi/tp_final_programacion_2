package com.tpfinalprogramacion2.models.saves.dependencies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tpfinalprogramacion2.models.saves.Save;

import java.io.*;

/**
 * La clase {@code SaveManager} es responsable de gestionar la creación, guardado y carga de los archivos de
 * guardado de las partidas del juego. Utiliza el formato JSON para almacenar la información de las partidas.
 * Los archivos de guardado se gestionan mediante rutas predefinidas y se guardan como archivos .json.
 *
 * <p>El gestor de guardados utiliza la librería Gson para convertir los objetos {@link Save} en JSON,
 * y viceversa. También valida los nombres de los guardados y controla los errores de I/O.
 *
 * <p>Esta clase no puede ser instanciada ya que es final y todos sus métodos son estáticos.
 */
public final class SaveManager {

    /**
     * Rutas predeterminadas de los archivos de guardado.
     * Cada índice del array representa un slot de guardado.
     */
    private static final String[] SLOT_PATHS = {
            "src/main/resources/saves/save1.json",
            "src/main/resources/saves/save2.json",
            "src/main/resources/saves/save3.json"
    };

    /**
     * Instancia de Gson configurada con formateo de impresión amigable (pretty-printing).
     */
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Crea un nuevo juego en el slot de guardado especificado, asignándole el nombre proporcionado.
     *
     * @param saveName  Nombre del archivo de guardado.
     * @param slotIndex Índice del slot de guardado (0 a 2).
     * @throws IllegalArgumentException si el nombre de guardado es inválido.
     */
    public static void newGame(String saveName, int slotIndex) {
        saveGame(new Save(setSaveName(saveName)), slotIndex);
    }

    /**
     * Guarda el estado del juego en un archivo JSON en el slot de guardado especificado.
     *
     * @param save      Objeto {@link Save} que representa el estado actual del juego.
     * @param slotIndex Índice del slot de guardado (0 a 2).
     * @throws RuntimeException si ocurre un error de entrada/salida durante el guardado.
     */
    public static void saveGame(Save save, int slotIndex) {
        try (FileWriter writer = new FileWriter(SLOT_PATHS[slotIndex])) {
            gson.toJson(save, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carga el estado del juego desde un archivo JSON del slot de guardado especificado.
     *
     * @param slotIndex Índice del slot de guardado (0 a 2).
     * @return El objeto {@link Save} cargado desde el archivo JSON.
     * @throws RuntimeException si ocurre un error de entrada/salida durante la carga.
     */
    public static Save loadGame(int slotIndex) {
        try (FileReader reader = new FileReader(SLOT_PATHS[slotIndex])) {
            return gson.fromJson(reader, Save.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Verifica si existe un archivo de guardado en el slot especificado.
     *
     * @param slotIndex Índice del slot de guardado (0 a 2).
     * @return {@code true} si el archivo de guardado existe, {@code false} en caso contrario.
     */
    public static boolean saveExists(int slotIndex) {
        return new java.io.File(SLOT_PATHS[slotIndex]).exists();
    }

    /**
     * Valida si el nombre del guardado cumple con los requisitos de longitud y caracteres permitidos.
     *
     * @param name El nombre del archivo de guardado a validar.
     * @return {@code true} si el nombre es válido, {@code false} en caso contrario.
     */
    private static boolean isValidSaveName(String name) {
        return name.matches("[A-Za-z0-9_ ]{3,10}");
    }

    /**
     * Establece el nombre del archivo de guardado después de validarlo. Si el nombre no es válido,
     * lanza una excepción.
     *
     * @param saveName El nombre del archivo de guardado.
     * @return El nombre de guardado validado.
     * @throws IllegalArgumentException si el nombre de guardado es inválido.
     */
    public static String setSaveName(String saveName) {
        if (isValidSaveName(saveName)) {
            return saveName;
        } else {
            throw new IllegalArgumentException("Invalid save name");
        }
    }

}

