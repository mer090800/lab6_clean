package lr6;

public class InvalidDimensionException extends Exception {

    public InvalidDimensionException(String parameter, String value) {
        super("Invalid value for parameter '" + parameter + "': " + value +
                ". Value must be a positive number.");
    }
}
