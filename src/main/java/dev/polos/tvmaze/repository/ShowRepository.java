package dev.polos.tvmaze.repository;

import dev.polos.tvmaze.model.ShowDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShowRepository extends MongoRepository<ShowDocument, String> {
}
