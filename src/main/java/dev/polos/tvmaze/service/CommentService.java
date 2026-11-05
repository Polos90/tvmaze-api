package dev.polos.tvmaze.service;

import dev.polos.tvmaze.dto.CommentRequest;
import dev.polos.tvmaze.dto.CommentResponse;
import dev.polos.tvmaze.model.CommentDocument;
import dev.polos.tvmaze.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void create(CommentRequest request) {
        CommentDocument document = new CommentDocument();
        document.setShowId(request.showId());
        document.setComment(request.comment());
        document.setRating(request.rating());
        document.setCreatedAt(Instant.now());
        commentRepository.save(document);
    }

    public List<CommentResponse> findByShowId(Integer showId) {
        return commentRepository.findByShowIdOrderByCreatedAtDesc(showId).stream()
                .map(document -> new CommentResponse(document.getComment(), document.getRating()))
                .toList();
    }
}
