package dev.polos.tvmaze.exception;

public class ShowNotFoundException extends RuntimeException {

    public ShowNotFoundException(Integer showId) {
        super("Show no encontrado: " + showId);
    }
}
