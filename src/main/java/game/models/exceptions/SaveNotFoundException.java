package game.models.exceptions;

public class SaveNotFoundException extends RuntimeException {
    public SaveNotFoundException(String path) {
        super("No se encontro una partida guardada en la ruta especificada: " + path);
    }
}
