package EcommerceApplication.utils;

import java.util.UUID;

public class Utils {
    public static String generateId() {
        // Universally Unique Identifier.
        // A UUID represents a 128-bit value that is unique. The standard representation of UUID uses hex digits.
        // randomUUID() static method is used to generate a random UUID.
        return UUID.randomUUID().toString();
    }
}
