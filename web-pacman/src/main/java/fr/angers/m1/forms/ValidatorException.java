package fr.angers.m1.forms;

public class ValidatorException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param message
     */
    public ValidatorException(String message ) {
        super( message );
    }

}
