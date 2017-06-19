package abhishek.parental.control.util;

import org.junit.Test;

import static abhishek.parental.control.util.Constants.EIGHTEEN;
import static abhishek.parental.control.util.Constants.FIFTEEN;
import static abhishek.parental.control.util.Constants.PARENTAL_CONTROL_LIST;
import static abhishek.parental.control.util.Constants.PG;
import static abhishek.parental.control.util.Constants.TWELVE;
import static abhishek.parental.control.util.Constants.U;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParentalControlComparatorTest {

    private final ParentalControlComparator underTest = new ParentalControlComparator(PARENTAL_CONTROL_LIST);

    @Test
    public void shouldCompareParentalControlLevels() {
        assertTrue("PG should have higher preference than U", underTest.compare(U, PG));
        assertTrue("12 should have higher preference than PG", underTest.compare(PG, TWELVE));
        assertTrue("15 should have higher preference than 12", underTest.compare(TWELVE, FIFTEEN));
        assertTrue("18 should have higher preference than 15", underTest.compare(FIFTEEN, EIGHTEEN));

        assertFalse("U should not have higher preference than PG", underTest.compare(PG, U));
        assertFalse("PG should not have higher preference than 12", underTest.compare(TWELVE, PG));
        assertFalse("12 should not have higher preference than 15", underTest.compare(FIFTEEN, TWELVE));
        assertFalse("15 should not have higher preference than 18", underTest.compare(EIGHTEEN, FIFTEEN));
    }
}
