package dev.polos.tvmaze.config;

public final class AppConstants {

    public static final String TVMAZE_SEARCH_URL = "http://api.tvmaze.com/search/shows?q={query}";
    public static final String TVMAZE_SHOW_BY_ID_URL = "https://api.tvmaze.com/shows/{showId}";

    public static final String SHOWS_COLLECTION = "shows";
    public static final String COMMENTS_COLLECTION = "comments";

    public static final String PARAM_SEARCH_QUERY = "search_query";
    public static final String JSON_STATUS = "status";
    public static final String STATUS_CREATED = "created";

    private AppConstants() {
    }
}
