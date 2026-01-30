package util;

import Exceptions.ValidationException;

@FunctionalInterface
public interface Validation<String> {
    void validate(String value ) throws ValidationException;
}
