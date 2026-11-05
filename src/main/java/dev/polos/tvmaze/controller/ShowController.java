package dev.polos.tvmaze.controller;

import dev.polos.tvmaze.config.AppConstants;
import dev.polos.tvmaze.dto.ShowResponse;
import dev.polos.tvmaze.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/search")
    public List<ShowResponse> search(@RequestParam(AppConstants.PARAM_SEARCH_QUERY) String searchQuery) {
        return showService.search(searchQuery);
    }

    @GetMapping("/show/{showId}")
    public ShowResponse getShow(@PathVariable Integer showId) {
        return showService.getShow(showId);
    }
}
