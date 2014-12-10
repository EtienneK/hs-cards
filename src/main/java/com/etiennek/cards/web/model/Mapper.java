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
		return new CardModel(from.getId(), from.getName(), from.getAttack(),
				from.getHealth(), from.getSetId(), from.getSetLabel(),
				from.getTypeId(), from.getTypeLabel(), from.getRarityId(),
				from.getRarityLabel(), from.getRaceId(), from.getRaceLabel(),
				from.getHeroClassId(), from.getHeroClassLabel(),
				from.getCost(), from.getDurability(), from.getText(),
				from.isCollectible(), from.isElite());
	}

	public static CardModel map(CardSearch from) {
		return new CardModel(from.getId(), from.getName(), from.getAttack(),
				from.getHealth(), from.getSetId(), from.getSet(),
				from.getTypeId(), from.getType(), from.getRarityId(),
				from.getRarity(), from.getRaceId(), from.getRace(),
				from.getHeroClassId(), from.getHeroClass(), from.getCost(),
				from.getDurability(), from.getText(), from.isCollectible(),
				from.isElite());
	}
}
