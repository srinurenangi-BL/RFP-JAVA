package Day23;

@FunctionalInterface
public interface UserValidationFunction {
    boolean validate(String input) throws UserRegistrationException;
}
