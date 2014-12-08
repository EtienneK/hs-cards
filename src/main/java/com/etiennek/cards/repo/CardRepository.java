package com.etiennek.cards.repo;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.etiennek.cards.domain.Card;

@Repository
public interface CardRepository extends ElasticsearchRepository<Card, String> {
	Card findFirstByGameId(String gameId);
	
	@Query("{ \"query_string\" : { \"query\" : \"?0\" } }")
	FacetedPage<Card> search(String query, Pageable pageable);
}
