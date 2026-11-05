package dev.polos.tvmaze.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.polos.tvmaze.dto.CommentResponse;
import dev.polos.tvmaze.dto.ShowResponse;
import dev.polos.tvmaze.model.ShowDocument;
import dev.polos.tvmaze.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service
public class ShowService {

    private final TvMazeClientService tvMazeClient;
    private final ShowRepository showRepository;
    private final CommentService commentService;
    private final ObjectMapper objectMapper;

    public ShowService(TvMazeClientService tvMazeClient,
                       ShowRepository showRepository,
                       CommentService commentService,
                       ObjectMapper objectMapper) {
        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }

    public List<ShowResponse> search(String query) {
        JsonNode results = tvMazeClient.searchShows(query);
        return StreamSupport.stream(results.spliterator(), false)
                .map(result -> buildShowResponse(result.path("show")))
                .map(this::attachComments)
                .toList();
    }

    public ShowResponse getShow(Integer showId) {
        ShowResponse show = showRepository.findById(String.valueOf(showId))
                .map(document -> buildShowResponse(objectMapper.valueToTree(document.getData())))
                .orElseGet(() -> fetchAndCache(showId));
        return attachComments(show);
    }

    private ShowResponse fetchAndCache(Integer showId) {
        JsonNode show = tvMazeClient.getShow(showId);
        Map<String, Object> data = objectMapper.convertValue(show, Map.class);
        showRepository.save(new ShowDocument(String.valueOf(showId), data, Instant.now()));
        return buildShowResponse(show);
    }

    private ShowResponse attachComments(ShowResponse show) {
        List<CommentResponse> comments = commentService.findByShowId(show.id());
        return new ShowResponse(
                show.id(),
                show.name(),
                show.channel(),
                show.summary(),
                show.genres(),
                comments
        );
    }

    private ShowResponse buildShowResponse(JsonNode show) {
        return new ShowResponse(
                show.path("id").asInt(),
                textValue(show, "name"),
                extractChannel(show),
                textValue(show, "summary"),
                extractGenres(show),
                List.of()
        );
    }

    private String extractChannel(JsonNode show) {
        String networkName = textValue(show.path("network"), "name");
        if (networkName != null) {
            return networkName;
        }
        return textValue(show.path("webChannel"), "name");
    }

    private List<String> extractGenres(JsonNode show) {
        List<String> genres = new ArrayList<>();
        show.path("genres").forEach(genre -> genres.add(genre.asText()));
        return genres;
    }

    private String textValue(JsonNode node, String field) {
        JsonNode value = node.path(field);
        return value.isMissingNode() || value.isNull() ? null : value.asText();
    }
}
