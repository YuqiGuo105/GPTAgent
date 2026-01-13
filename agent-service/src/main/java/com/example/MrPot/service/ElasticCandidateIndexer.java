package com.example.MrPot.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Schedulers;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ElasticCandidateIndexer {

    private static final Logger log = LoggerFactory.getLogger(ElasticCandidateIndexer.class);

    private final WebClient elasticWebClient;

    @Value("${app.candidate.es-index:mrpot_candidates}")
    private String indexName;

    public void upsertAsync(String docId, Map<String, Object> doc) {
        elasticWebClient.put()
                .uri("/{index}/_doc/{id}", indexName, docId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(doc)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(ex -> log.debug("ES candidate upsert failed", ex))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }
}
