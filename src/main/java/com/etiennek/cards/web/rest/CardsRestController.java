package com.etiennek.cards.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.etiennek.cards.web.model.Mapper.*;
import com.etiennek.cards.repo.CardRepository;
import com.etiennek.cards.search.repo.CardSearchRepository;
import com.etiennek.cards.web.model.CardModel;

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
	Page<CardModel> list(Pageable pageable) {
		return mapFromCardPage(cardRepository.findAll(pageable));
	}

	@RequestMapping("/search")
	public Page<CardModel> search(@RequestParam String query, Pageable pageable) {
		return mapFromCardSearchPage(cardSearchRepository.search(query,
				pageable));
	}

}
