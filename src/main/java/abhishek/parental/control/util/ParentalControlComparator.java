package abhishek.parental.control.util;

import java.util.Comparator;
import java.util.List;

public class ParentalControlComparator {
    private List<String> parentalControlLevels;

    public ParentalControlComparator(List<String> parentalControlLevels) {
        this.parentalControlLevels = parentalControlLevels;
    }

    //Lambda for comparing 'Parental control levels'
    private Comparator<String> compareParentalControl = (o1, o2) -> (parentalControlLevels.indexOf(o1) - parentalControlLevels.indexOf(o2));

    /*
     * Compares the Preferred control levels.
     *
     * @Param: movieParentalControlLevel: parental control level of the movie
     * @Param: clientPreferredLevel:  parental control level set by client
     * @Return: Returns flag if client set preferred level is higher or equals to movie preferred level
    */
    public boolean compare(final String movieParentalControlLevel,
                           final String clientPreferredLevel) {
        return compareParentalControl.compare(movieParentalControlLevel, clientPreferredLevel) <= 0;
    }

}
