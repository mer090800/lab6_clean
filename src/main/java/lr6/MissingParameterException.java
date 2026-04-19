package lr6;

public class MissingParameterException extends Exception {

    public MissingParameterException(String parameter) {
        super("Parameter '" + parameter + "' is missing.");
    }
}
