package dev.polos.tvmaze.controller;

import dev.polos.tvmaze.config.AppConstants;
import dev.polos.tvmaze.dto.CommentRequest;
import dev.polos.tvmaze.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> create(@Valid @RequestBody CommentRequest request) {
        commentService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(AppConstants.JSON_STATUS, AppConstants.STATUS_CREATED));
    }
}
