package org.app.srms.exceptions;

// Thrown when trying to create/update with an email or username
// that already belongs to another student. Mapped to 409 CONFLICT.
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}