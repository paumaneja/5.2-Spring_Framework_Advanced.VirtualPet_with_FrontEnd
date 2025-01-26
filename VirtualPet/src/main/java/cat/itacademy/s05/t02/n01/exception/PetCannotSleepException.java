package cat.itacademy.s05.t02.n01.exception;

public class PetCannotSleepException extends RuntimeException {
    public PetCannotSleepException(String message) {
        super(message);
    }
}
