package com.etiennek.cards.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Card {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true, nullable = false)
	private String gameId;

	@Column(nullable = false)
	private String name;

	private Integer attack;

	private Integer health;

	protected Card() {
		super();
	}

	public Card(Integer id, String gameId, String name, Integer attack,
			Integer health) {
		super();
		this.id = id;
		this.gameId = gameId;
		this.name = name;
		this.attack = attack;
		this.health = health;
	}

	public String getGameId() {
		return gameId;
	}

	public String getName() {
		return name;
	}

	public Integer getAttack() {
		return attack;
	}

	public Integer getHealth() {
		return health;
	}

	public Integer getId() {
		return id;
	}

}
