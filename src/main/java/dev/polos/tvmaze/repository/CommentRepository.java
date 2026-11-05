package dev.polos.tvmaze.repository;

import dev.polos.tvmaze.model.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentDocument, String> {

    List<CommentDocument> findByShowIdOrderByCreatedAtDesc(Integer showId);
}
