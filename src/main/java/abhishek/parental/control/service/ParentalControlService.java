package abhishek.parental.control.service;


public interface ParentalControlService {
    boolean isClientAllowedToWatchMovie(String movieId, String clientPreferredLevel, MessageCallBack callBack);
}
