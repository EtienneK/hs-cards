package com.etiennek.cards.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.etiennek.cards.domain.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
	Card findByGameId(String gameId);
}
