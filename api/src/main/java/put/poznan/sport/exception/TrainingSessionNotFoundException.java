package put.poznan.sport.exception;

public class TrainingSessionNotFoundException extends RuntimeException {

    public TrainingSessionNotFoundException(String message) {
        super(message);
    }
}
