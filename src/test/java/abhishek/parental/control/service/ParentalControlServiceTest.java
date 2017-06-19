package abhishek.parental.control.service;

import abhishek.parental.control.MovieService;
import abhishek.parental.control.exception.TechnicalFailureException;
import abhishek.parental.control.exception.TitleNotFoundException;
import abhishek.parental.control.util.ParentalControlComparator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static abhishek.parental.control.util.Constants.FIFTEEN;
import static abhishek.parental.control.util.Constants.MESSAGE_MOVIE_ALLOWED;
import static abhishek.parental.control.util.Constants.MESSAGE_MOVIE_NOT_ALLOWED;
import static abhishek.parental.control.util.Constants.MESSAGE_TECHNICAL_FAILURE;
import static abhishek.parental.control.util.Constants.MESSAGE_TITLE_NOT_FOUND;
import static abhishek.parental.control.util.Constants.PARENTAL_CONTROL_LIST;
import static abhishek.parental.control.util.Constants.PG;
import static abhishek.parental.control.util.Constants.TWELVE;
import static abhishek.parental.control.util.Constants.U;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {

    @Mock
    private MovieService mockMovieService;
    @Mock
    private MessageCallBack mockCallBack;
    private ParentalControlService service;

    private static final String MOVIE_ID = "1";
    private final ParentalControlComparator comparator = new ParentalControlComparator(PARENTAL_CONTROL_LIST);

    @Before
    public void setup() {
        service = new ParentalControlServiceImpl(mockMovieService, comparator);
    }

    @Test
    public void shouldReturnFalseWhenMovieServiceThrowsTitleNotFoundException() throws TechnicalFailureException, TitleNotFoundException {
        //Given
        doThrow(new TitleNotFoundException(MESSAGE_TITLE_NOT_FOUND))
                .when(mockMovieService).getParentalControlLevel(MOVIE_ID);

        //When
        boolean isClientAllowedToWatchMovie = service.isClientAllowedToWatchMovie(MOVIE_ID, PG, mockCallBack);

        //Then
        assertFalse("Expected: false, Found: true", isClientAllowedToWatchMovie);
        verify(mockCallBack, times(1)).saveMessage(MESSAGE_TITLE_NOT_FOUND);
    }

    @Test
    public void shouldReturnFalseWhenMovieServiceThrowsTechnicalFailureException() throws TechnicalFailureException, TitleNotFoundException {
        //Given
        doThrow(new TechnicalFailureException(MESSAGE_TECHNICAL_FAILURE))
                .when(mockMovieService).getParentalControlLevel(MOVIE_ID);

        //When
        boolean isClientAllowedToWatchMovie = service.isClientAllowedToWatchMovie(MOVIE_ID, PG, mockCallBack);

        //Then
        assertFalse("Expected: false, Found: true", isClientAllowedToWatchMovie);
        verify(mockCallBack, times(1)).saveMessage(MESSAGE_TECHNICAL_FAILURE);
    }

    @Test
    public void shouldBeAllowedToWatchWhenMoviePreferredLevelIsSameAsSetByClient() throws TechnicalFailureException, TitleNotFoundException {
        //Given
        given(mockMovieService.getParentalControlLevel(MOVIE_ID)).willReturn(PG);

        //When
        final boolean isClientAllowedToWatchMovie = service.isClientAllowedToWatchMovie(MOVIE_ID, PG, mockCallBack);

        //Then
        assertTrue("Expected: true, Found: false", isClientAllowedToWatchMovie);
        verify(mockMovieService).getParentalControlLevel(MOVIE_ID);
        verify(mockCallBack).saveMessage(MESSAGE_MOVIE_ALLOWED);
    }

    @Test
    public void shouldBeAllowedToWatchWhenMoviePreferredLevelIsLowerThanSetByClient() throws TechnicalFailureException, TitleNotFoundException {
        //Given
        given(mockMovieService.getParentalControlLevel(MOVIE_ID)).willReturn(PG);

        //When
        final boolean isClientAllowedToWatchMovie = service.isClientAllowedToWatchMovie(MOVIE_ID, TWELVE, mockCallBack);

        //Then
        assertTrue("Expected: true, Found: false", isClientAllowedToWatchMovie);
        verify(mockMovieService).getParentalControlLevel(MOVIE_ID);
        verify(mockCallBack).saveMessage(MESSAGE_MOVIE_ALLOWED);
    }

    @Test
    public void shouldBeAllowedToWatchWhenMoviePreferredLevelIsHigherThanSetByClient() throws TechnicalFailureException, TitleNotFoundException {
        //Given
        given(mockMovieService.getParentalControlLevel(MOVIE_ID)).willReturn(FIFTEEN);

        //When
        final boolean isClientAllowedToWatchMovie = service.isClientAllowedToWatchMovie(MOVIE_ID, U, mockCallBack);

        //Then
        assertFalse("Expected: false, Found: true", isClientAllowedToWatchMovie);
        verify(mockMovieService).getParentalControlLevel(MOVIE_ID);
        verify(mockCallBack).saveMessage(MESSAGE_MOVIE_NOT_ALLOWED);
    }
}
