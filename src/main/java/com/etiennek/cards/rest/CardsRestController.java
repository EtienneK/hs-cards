package com.etiennek.cards.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiennek.cards.domain.Card;
import com.etiennek.cards.repo.CardRepository;

@RestController
@RequestMapping("/api/v1/cards")
public class CardsRestController {

	private CardRepository cardRepository;

	@Autowired
	CardsRestController(CardRepository cardRepository) {
		super();
		this.cardRepository = cardRepository;
	}

	@RequestMapping
	Page<Card> list(Pageable pageable) {
		return cardRepository.findAll(pageable);
	}

	@RequestMapping("/search")
	public Page<Card> search(@RequestParam String query, Pageable pageable) {
		return cardRepository.search("%" + query.toUpperCase() + "%", pageable);
	}

}
