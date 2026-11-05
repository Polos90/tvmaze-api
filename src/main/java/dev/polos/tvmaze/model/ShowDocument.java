package dev.polos.tvmaze.model;

import dev.polos.tvmaze.config.AppConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = AppConstants.SHOWS_COLLECTION)
public class ShowDocument {

    @Id
    private String id;
    private Map<String, Object> data;
    private Instant cachedAt;

    public ShowDocument() {
    }

    public ShowDocument(String id, Map<String, Object> data, Instant cachedAt) {
        this.id = id;
        this.data = data;
        this.cachedAt = cachedAt;
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Instant getCachedAt() {
        return cachedAt;
    }
}
