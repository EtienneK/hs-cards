package com.etiennek.cards.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiennek.cards.domain.Card;
import com.etiennek.cards.repo.CardRepository;
import com.etiennek.cards.search.domain.CardSearch;
import com.etiennek.cards.search.repo.CardSearchRepository;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsRestController {

	private CardRepository cardRepository;
	private CardSearchRepository cardSearchRepository;

	@Autowired
	public CardsRestController(CardRepository cardRepository,
			CardSearchRepository cardSearchRepository) {
		super();
		this.cardRepository = cardRepository;
		this.cardSearchRepository = cardSearchRepository;
	}

	@RequestMapping
	Page<Card> list(Pageable pageable) {
		return cardRepository.findAll(pageable);
	}

	@RequestMapping("/search")
	public Page<CardSearch> search(@RequestParam String query, Pageable pageable) {
		return cardSearchRepository.search(query, pageable);
	}

}
