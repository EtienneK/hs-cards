package com.etiennek.cards.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.etiennek.cards.domain.Card;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Integer> {
	Card findByGameId(String gameId);

	@Query("FROM Card c WHERE UPPER(c.name) LIKE UPPER(:query) OR UPPER(c.text) LIKE UPPER(:query) ORDER BY c.name")
	Page<Card> search(@Param("query") String query, Pageable pageable);
}
