package com.etiennek.cards.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.etiennek.cards.domain.Card;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {
	Card findFirstByGameId(String gameId);
}
