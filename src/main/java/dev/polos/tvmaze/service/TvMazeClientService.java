package dev.polos.tvmaze.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.polos.tvmaze.config.AppConstants;
import dev.polos.tvmaze.exception.ShowNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class TvMazeClientService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public TvMazeClientService(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    public JsonNode searchShows(String query) {
        String body = restClient.get()
                .uri(AppConstants.TVMAZE_SEARCH_URL, query)
                .retrieve()
                .body(String.class);
        return readTree(body);
    }

    public JsonNode getShow(Integer showId) {
        try {
            String body = restClient.get()
                    .uri(AppConstants.TVMAZE_SHOW_BY_ID_URL, showId)
                    .retrieve()
                    .body(String.class);
            return readTree(body);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ShowNotFoundException(showId);
        }
    }

    private JsonNode readTree(String body) {
        try {
            return objectMapper.readTree(body);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Respuesta invalida de TV Maze", ex);
        }
    }
}
