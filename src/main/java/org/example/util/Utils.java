package org.example.util;

import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 20:03 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
public class Utils {

    public static boolean isParsable(String timeAsString){
        try {
            LocalDateTime.parse(timeAsString);
            return true;
        }catch (DateTimeParseException e){
            return false;
        }
    }

    public static LocalDateTime toLocalDateTime(String timeAsString){
        try {
            return LocalDateTime.parse(timeAsString);
        }catch (DateTimeParseException e){
            return null;
        }
    }

    public static String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
