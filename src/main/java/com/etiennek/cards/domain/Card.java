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
	@Column(name = "`set`", nullable = false)
	private int set;
	@Column(nullable = false)
	private int type;
	private Integer faction;
	private Integer rarity;
	private Integer cost;
	private Integer durability;
	private String text;
	private String textInPlay;
	private String flavourText;
	private String artistName;
	private boolean isCollectible;
	private boolean isElite;
	private Integer race;
	private Integer heroClass;
	private String howToGet;
	private String howToGetGold;

	protected Card() {
		super();
	}

	public Card(Integer id, String gameId, String name, Integer attack,
			Integer health, int set, int type, Integer faction, Integer rarity,
			Integer cost, Integer durability, String text, String textInPlay,
			String flavourText, String artistName, boolean isCollectible,
			boolean isElite, Integer race, Integer heroClass, String howToGet,
			String howToGetGold) {
		super();
		this.id = id;
		this.gameId = gameId;
		this.name = name;
		this.attack = attack;
		this.health = health;
		this.set = set;
		this.type = type;
		this.faction = faction;
		this.rarity = rarity;
		this.cost = cost;
		this.durability = durability;
		this.text = text;
		this.textInPlay = textInPlay;
		this.flavourText = flavourText;
		this.artistName = artistName;
		this.isCollectible = isCollectible;
		this.isElite = isElite;
		this.race = race;
		this.heroClass = heroClass;
		this.howToGet = howToGet;
		this.howToGetGold = howToGetGold;
	}

	public Integer getId() {
		return id;
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

	public int getSet() {
		return set;
	}

	public int getType() {
		return type;
	}

	public Integer getFaction() {
		return faction;
	}

	public Integer getRarity() {
		return rarity;
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getDurability() {
		return durability;
	}

	public String getText() {
		return text;
	}

	public String getTextInPlay() {
		return textInPlay;
	}

	public String getFlavourText() {
		return flavourText;
	}

	public String getArtistName() {
		return artistName;
	}

	public boolean isCollectible() {
		return isCollectible;
	}

	public boolean isElite() {
		return isElite;
	}

	public Integer getRace() {
		return race;
	}

	public Integer getHeroClass() {
		return heroClass;
	}

	public String getHowToGet() {
		return howToGet;
	}

	public String getHowToGetGold() {
		return howToGetGold;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((artistName == null) ? 0 : artistName.hashCode());
		result = prime * result + ((attack == null) ? 0 : attack.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result
				+ ((durability == null) ? 0 : durability.hashCode());
		result = prime * result + ((faction == null) ? 0 : faction.hashCode());
		result = prime * result
				+ ((flavourText == null) ? 0 : flavourText.hashCode());
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		result = prime * result
				+ ((heroClass == null) ? 0 : heroClass.hashCode());
		result = prime * result
				+ ((howToGet == null) ? 0 : howToGet.hashCode());
		result = prime * result
				+ ((howToGetGold == null) ? 0 : howToGetGold.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isCollectible ? 1231 : 1237);
		result = prime * result + (isElite ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + set;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result
				+ ((textInPlay == null) ? 0 : textInPlay.hashCode());
		result = prime * result + type;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (artistName == null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(other.artistName))
			return false;
		if (attack == null) {
			if (other.attack != null)
				return false;
		} else if (!attack.equals(other.attack))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (durability == null) {
			if (other.durability != null)
				return false;
		} else if (!durability.equals(other.durability))
			return false;
		if (faction == null) {
			if (other.faction != null)
				return false;
		} else if (!faction.equals(other.faction))
			return false;
		if (flavourText == null) {
			if (other.flavourText != null)
				return false;
		} else if (!flavourText.equals(other.flavourText))
			return false;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (health == null) {
			if (other.health != null)
				return false;
		} else if (!health.equals(other.health))
			return false;
		if (heroClass == null) {
			if (other.heroClass != null)
				return false;
		} else if (!heroClass.equals(other.heroClass))
			return false;
		if (howToGet == null) {
			if (other.howToGet != null)
				return false;
		} else if (!howToGet.equals(other.howToGet))
			return false;
		if (howToGetGold == null) {
			if (other.howToGetGold != null)
				return false;
		} else if (!howToGetGold.equals(other.howToGetGold))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isCollectible != other.isCollectible)
			return false;
		if (isElite != other.isElite)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (rarity == null) {
			if (other.rarity != null)
				return false;
		} else if (!rarity.equals(other.rarity))
			return false;
		if (set != other.set)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (textInPlay == null) {
			if (other.textInPlay != null)
				return false;
		} else if (!textInPlay.equals(other.textInPlay))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", gameId=" + gameId + ", name=" + name
				+ ", attack=" + attack + ", health=" + health + ", set=" + set
				+ ", type=" + type + ", faction=" + faction + ", rarity="
				+ rarity + ", cost=" + cost + ", durability=" + durability
				+ ", text=" + text + ", textInPlay=" + textInPlay
				+ ", flavourText=" + flavourText + ", artistName=" + artistName
				+ ", isCollectible=" + isCollectible + ", isElite=" + isElite
				+ ", race=" + race + ", heroClass=" + heroClass + ", howToGet="
				+ howToGet + ", howToGetGold=" + howToGetGold + "]";
	}

}
