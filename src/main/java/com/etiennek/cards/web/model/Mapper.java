package com.etiennek.cards.web.model;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.etiennek.cards.domain.Card;
import com.etiennek.cards.search.domain.CardSearch;

public class Mapper {
	public static Page<CardModel> mapFromCardPage(Page<Card> from) {
		return new PageImpl<CardModel>(from.getContent().stream()
				.map(Mapper::map).collect(Collectors.toList()));
	}

	public static Page<CardModel> mapFromCardSearchPage(Page<CardSearch> from) {
		return new PageImpl<CardModel>(from.getContent().stream()
				.map(Mapper::map).collect(Collectors.toList()));
	}

	public static CardModel map(Card from) {
		return new CardModel(from.getName());
	}

	public static CardModel map(CardSearch from) {
		return new CardModel(from.getName());
	}
}
