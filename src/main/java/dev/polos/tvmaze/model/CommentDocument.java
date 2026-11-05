package dev.polos.tvmaze.model;

import dev.polos.tvmaze.config.AppConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = AppConstants.COMMENTS_COLLECTION)
public class CommentDocument {

    @Id
    private String id;
    private Integer showId;
    private String comment;
    private Integer rating;
    private Instant createdAt;

    public String getId() {
        return id;
    }

    public Integer getShowId() {
        return showId;
    }

    public String getComment() {
        return comment;
    }

    public Integer getRating() {
        return rating;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
