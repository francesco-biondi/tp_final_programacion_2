package game.models.saves.dependencies;

import com.google.gson.*;
import game.models.abilities.Ability;
import game.models.abilities.AttackAbility;
import game.models.abilities.BuffAbility;
import game.models.exceptions.SaveNotFoundException;
import game.models.saves.Save;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

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
public abstract class SaveManager {

    /**
     * Instancia de Gson configurada con formateo de impresión amigable (pretty-printing).
     */
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Ability.class, new AbilityAdapter()).create();

    /**
     * Crea un nuevo juego en el slot de guardado especificado, asignándole el nombre proporcionado.
     *
     * @param saveName  Nombre del archivo de guardado.
     * @param slotIndex Índice del slot de guardado (0 a 2).
     * @throws IllegalArgumentException si el nombre de guardado es inválido.
     */
    public static void newGame(String saveName, int slotIndex) {
        saveGame(new Save(setSaveName(saveName), slotIndex));
    }

    /**
     * Guarda el estado del juego en un archivo JSON en el slot de guardado especificado.
     *
     * @param save      Objeto {@link Save} que representa el estado actual del juego.
     * @throws RuntimeException si ocurre un error de entrada/salida durante el guardado.
     */
    public static void saveGame(Save save) {
        try (FileWriter writer = new FileWriter(save.getSavePath())) {
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
        try (FileReader reader = new FileReader(Save.getSavePath(slotIndex))) {
            return gson.fromJson(reader, Save.class);
        } catch (FileNotFoundException e) {
            throw new SaveNotFoundException(Save.getSavePath(slotIndex));
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
        return new java.io.File(Save.getSavePath(slotIndex)).exists();
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

    public static <T> ArrayList<T> loadFile(String filePath, Type type) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> loadFileMap(String filePath, Type type) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class AbilityAdapter implements JsonDeserializer<Ability> {
        @Override
        public Ability deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String abilityType = jsonObject.get("type").getAsString();

            return switch (abilityType) {
                case "ATTACK" -> context.deserialize(jsonObject, AttackAbility.class);
                case "BUFF" -> context.deserialize(jsonObject, BuffAbility.class);
                default -> throw new JsonParseException("Unknown type: " + abilityType);
            };
        }
    }
}



