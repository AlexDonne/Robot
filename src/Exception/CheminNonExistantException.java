package Exception;

/**
 * Exception levée lorsqu'un robot ne peut pas accéder à une case
 */
public class CheminNonExistantException extends Exception {
    public CheminNonExistantException(String message) {
        super(message);
    }
}
