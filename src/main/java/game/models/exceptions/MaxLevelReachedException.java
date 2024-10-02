package game.models.exceptions;

public class MaxLevelReachedException extends RuntimeException {
    public MaxLevelReachedException(String message) {
        super(message);
    }
}
