package com.etiennek.cards.search.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.etiennek.cards.search.domain.CardSearch;

@Repository
public interface CardSearchRepository extends ElasticsearchRepository<CardSearch, String> {
	@Query("{ \"query_string\" : { \"query\" : \"?0\" } }")
	FacetedPage<CardSearch> search(String query, Pageable pageable);
}
