package cat.itacademy.s05.t02.n01.exception;

public class WeaponChangeNotAllowedException extends RuntimeException {
    public WeaponChangeNotAllowedException(String message) {
        super(message);
    }
}
