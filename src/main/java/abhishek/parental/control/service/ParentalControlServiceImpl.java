package abhishek.parental.control.service;

import abhishek.parental.control.MovieService;
import abhishek.parental.control.exception.TechnicalFailureException;
import abhishek.parental.control.exception.TitleNotFoundException;
import abhishek.parental.control.util.ParentalControlComparator;

import static abhishek.parental.control.util.Constants.MESSAGE_MOVIE_ALLOWED;
import static abhishek.parental.control.util.Constants.MESSAGE_MOVIE_NOT_ALLOWED;

public class ParentalControlServiceImpl implements ParentalControlService {
    private final MovieService movieService;
    private final ParentalControlComparator comparator;


    public ParentalControlServiceImpl(final MovieService movieService, final ParentalControlComparator comparator) {
        this.movieService = movieService;
        this.comparator = comparator;
    }

    /*
     * Check if client is allowed to watch selected movie.
     *
     * @Param: movieId: id of the movie that client wants to watch
     * @Param: clientPreferredLevel: preferred level setup by the client
     * @Param: callBack: Callback for displaying message to client
     * @Return: Status flag, if client is allowed to watch movie
    */
    @Override
    public boolean isClientAllowedToWatchMovie(final String movieId, final String clientPreferredLevel,
                                               MessageCallBack callBack) {
        boolean status = false;
        try {
            final String movieParentalControlLevel = this.movieService.getParentalControlLevel(movieId);
            status = comparator.compare(movieParentalControlLevel, clientPreferredLevel);

            final String message = (status) ? MESSAGE_MOVIE_ALLOWED : MESSAGE_MOVIE_NOT_ALLOWED;
            callBack.saveMessage(message);
        } catch (TitleNotFoundException | TechnicalFailureException ex) {
            callBack.saveMessage(ex.getMessage());
        }
        return status;
    }
}
