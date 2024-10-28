package game.models.saves.dependencies;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import game.models.abilities.Ability;
import game.models.abilities.AttackAbility;
import game.models.abilities.BuffAbility;
import javafx.beans.property.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase principal que proporciona un gestor de Gson para la serialización y deserialización
 * de archivos JSON, con adaptadores personalizados para propiedades y habilidades.
 */
public abstract class GsonManager {

    /**
     * Instancia de Gson configurada con los adaptadores de Ability, SimpleStringProperty, SimpleIntegerProperty, SimpleDoubleProperty y SimpleBooleanProperty.
     */
    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Ability.class, new AbilityAdapter())
            .registerTypeAdapter(SimpleStringProperty.class, new StringPropertyAdapter())
            .registerTypeAdapter(SimpleIntegerProperty.class, new IntegerPropertyAdapter())
            .registerTypeAdapter(SimpleDoubleProperty.class, new DoublePropertyAdapter())
            .registerTypeAdapter(SimpleBooleanProperty.class, new BooleanPropertyAdapter())
            .create();

    /**
     * Lee una lista de objetos desde un archivo JSON.
     *
     * @param filePath Ruta del archivo JSON desde el cual se cargará la lista.
     * @param type     Tipo de datos en el ArrayList para la deserialización.
     * @param <T>      Tipo genérico de los elementos en la lista.
     * @return ArrayList de objetos deserializados desde el archivo JSON.
     * @throws RuntimeException Si ocurre un error al leer el archivo.
     */
    public static <T> ArrayList<T> loadFileList(String filePath, Type type) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lee un mapa de objetos desde un archivo JSON.
     *
     * @param filePath Ruta del archivo JSON desde el cual se cargará el mapa.
     * @param type     Tipo de datos en el Map para la deserialización.
     * @param <K>      Tipo de las claves en el mapa.
     * @param <V>      Tipo de los valores en el mapa.
     * @return Mapa de objetos deserializados desde el archivo JSON.
     * @throws RuntimeException Si ocurre un error al leer el archivo.
     */
    public static <K, V> Map<K, V> loadFileMap(String filePath, Type type) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adaptador personalizado para deserializar instancias de Ability en función del tipo de habilidad.
     */
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

    /**
     * Adaptador personalizado para serializar y deserializar instancias de StringProperty.
     */
    private static class StringPropertyAdapter extends TypeAdapter<StringProperty> {
        @Override
        public void write(JsonWriter out, StringProperty value) throws IOException {
            out.value(value.get());
        }

        @Override
        public StringProperty read(JsonReader in) throws IOException {
            return new SimpleStringProperty(in.nextString());
        }
    }

    /**
     * Adaptador personalizado para serializar y deserializar instancias de IntegerProperty.
     */
    private static class IntegerPropertyAdapter extends TypeAdapter<IntegerProperty> {
        @Override
        public void write(JsonWriter out, IntegerProperty value) throws IOException {
            out.value(value.get());
        }

        @Override
        public IntegerProperty read(JsonReader in) throws IOException {
            return new SimpleIntegerProperty(in.nextInt());
        }
    }

    /**
     * Adaptador personalizado para serializar y deserializar instancias de DoubleProperty.
     */
    private static class DoublePropertyAdapter extends TypeAdapter<DoubleProperty> {
        @Override
        public void write(JsonWriter out, DoubleProperty value) throws IOException {
            out.value(value.get());
        }

        @Override
        public DoubleProperty read(JsonReader in) throws IOException {
            return new SimpleDoubleProperty(in.nextDouble());
        }
    }

    /**
     * Adaptador personalizado para serializar y deserializar instancias de BooleanProperty.
     */
    private static class BooleanPropertyAdapter extends TypeAdapter<BooleanProperty> {
        @Override
        public void write(JsonWriter out, BooleanProperty value) throws IOException {
            out.value(value.get());
        }

        @Override
        public BooleanProperty read(JsonReader in) throws IOException {
            return new SimpleBooleanProperty(in.nextBoolean());
        }
    }
}
