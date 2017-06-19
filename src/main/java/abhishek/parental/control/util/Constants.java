package abhishek.parental.control.util;

import java.util.Arrays;
import java.util.List;

public class Constants {

    // Messages
    public static final String MESSAGE_TECHNICAL_FAILURE = "Sorry, You can not watch this movie.";
    public static final String MESSAGE_TITLE_NOT_FOUND = "The movie service could not find the given movie.";

    // Error Messages
    public static final String MESSAGE_MOVIE_ALLOWED = "You can watch this movie.";
    public static final String MESSAGE_MOVIE_NOT_ALLOWED = "Sorry, You are not allowed to watch this movie.";

    // Parental Control Preferences
    public static final String U = "U";
    public static final String PG = "PG";
    public static final String TWELVE = "12";
    public static final String FIFTEEN = "15";
    public static final String EIGHTEEN = "18";

    public static final List<String> PARENTAL_CONTROL_LIST = Arrays.asList(U, PG, TWELVE, FIFTEEN, EIGHTEEN);
}
