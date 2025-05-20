package FondoRoyaleApplication.constants.commons;

public class ApiPathVariables {
    public static final String USER_BASE = "/user";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String INDEX = "/index";
    public static final String FIND_BY_NAME = "/name/{name}";
    public static final String DELETE_USER = "/delete/{id}";
    public static final String READ_USER = "/read/{id}";
    public static final String UPDATE_USER = "/update/{id}";
    public static final String GET_USER_FONDOCOINS = "/{id}/fondocoins";
    public static final String UPDATE_USER_FONDOCOINS = "/{id}/fondocoins";
    public static final String GET_USER_EXPERIENCE = "/{id}/experience";
    public static final String UPDATE_USER_EXPERIENCE = "/{id}/experience";
    public static final String GAME_BASE = "/sessionGame";
    public static final String SAVE = "/save";
    public static final String UPDATE_PROFILE_PICTURE = "/{id}/profile-picture";
    public static final String GET_PROFILE_PICTURE = "/{id}/profile-picture";
    public static final String DELETE_PROFILE_PICTURE = "/{id}/profile-picture";
    public static final String SESSIONS = "/{id}/sessions";
    
    public static final String GAMES_BASE = "/games";
    public static final String GET_ALL_GAMES = "/allGames";
    
    public static final String DAILY_REWARD = "/{id}/daily-reward";
    public static final String LAST_DAILY_REWARD = "/{id}/last-daily-reward";

}