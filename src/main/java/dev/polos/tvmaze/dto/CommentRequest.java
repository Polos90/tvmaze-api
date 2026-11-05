package dev.polos.tvmaze.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @NotNull Integer showId,
        @NotBlank String comment,
        @NotNull @Min(0) @Max(5) Integer rating
) {
}
