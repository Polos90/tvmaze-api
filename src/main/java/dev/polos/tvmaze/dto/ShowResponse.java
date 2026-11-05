package dev.polos.tvmaze.dto;

import java.util.List;

public record ShowResponse(
        Integer id,
        String name,
        String channel,
        String summary,
        List<String> genres,
        List<CommentResponse> comments
) {
}
