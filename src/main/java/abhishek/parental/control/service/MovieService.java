package abhishek.parental.control.service;

import abhishek.parental.control.exception.TechnicalFailureException;
import abhishek.parental.control.exception.TitleNotFoundException;

public interface MovieService {
    String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException;
}