package com.etiennek.cards.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "card")
public class CardSearch {
	@Id
	private Long id;
	private String name;
	private Integer attack;
	private Integer health;

	private int setId;
	private String set;

	private int typeId;
	private String type;

	private Integer rarityId;
	private String rarity;

	private Integer raceId;
	private String race;

	private Integer heroClassId;
	private String heroClass;

	private Integer cost;
	private Integer durability;
	private String text;

	private boolean collectible;
	private boolean elite;

	protected CardSearch() {
		super();
	}

	public CardSearch(Long id, String name, Integer attack, Integer health,
			int setId, String set, int typeId, String type, Integer rarityId,
			String rarity, Integer raceId, String race, Integer heroClassId,
			String heroClass, Integer cost, Integer durability, String text,
			boolean collectible, boolean elite) {
		super();
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.health = health;
		this.setId = setId;
		this.set = set;
		this.typeId = typeId;
		this.type = type;
		this.rarityId = rarityId;
		this.rarity = rarity;
		this.raceId = raceId;
		this.race = race;
		this.heroClassId = heroClassId;
		this.heroClass = heroClass;
		this.cost = cost;
		this.durability = durability;
		this.text = text;
		this.collectible = collectible;
		this.elite = elite;
	}

	public Long getId() {
		return id;
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

	public int getSetId() {
		return setId;
	}

	public String getSet() {
		return set;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getType() {
		return type;
	}

	public Integer getRarityId() {
		return rarityId;
	}

	public String getRarity() {
		return rarity;
	}

	public Integer getRaceId() {
		return raceId;
	}

	public String getRace() {
		return race;
	}

	public Integer getHeroClassId() {
		return heroClassId;
	}

	public String getHeroClass() {
		return heroClass;
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

	public boolean isCollectible() {
		return collectible;
	}

	public boolean isElite() {
		return elite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attack == null) ? 0 : attack.hashCode());
		result = prime * result + (collectible ? 1231 : 1237);
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result
				+ ((durability == null) ? 0 : durability.hashCode());
		result = prime * result + (elite ? 1231 : 1237);
		result = prime * result + ((health == null) ? 0 : health.hashCode());
		result = prime * result
				+ ((heroClass == null) ? 0 : heroClass.hashCode());
		result = prime * result
				+ ((heroClassId == null) ? 0 : heroClassId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((raceId == null) ? 0 : raceId.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result
				+ ((rarityId == null) ? 0 : rarityId.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		result = prime * result + setId;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeId;
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
		CardSearch other = (CardSearch) obj;
		if (attack == null) {
			if (other.attack != null)
				return false;
		} else if (!attack.equals(other.attack))
			return false;
		if (collectible != other.collectible)
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
		if (elite != other.elite)
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
		if (heroClassId == null) {
			if (other.heroClassId != null)
				return false;
		} else if (!heroClassId.equals(other.heroClassId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (raceId == null) {
			if (other.raceId != null)
				return false;
		} else if (!raceId.equals(other.raceId))
			return false;
		if (rarity == null) {
			if (other.rarity != null)
				return false;
		} else if (!rarity.equals(other.rarity))
			return false;
		if (rarityId == null) {
			if (other.rarityId != null)
				return false;
		} else if (!rarityId.equals(other.rarityId))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		if (setId != other.setId)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CardSearch [id=" + id + ", name=" + name + ", attack=" + attack
				+ ", health=" + health + ", setId=" + setId + ", set=" + set
				+ ", typeId=" + typeId + ", type=" + type + ", rarityId="
				+ rarityId + ", rarity=" + rarity + ", raceId=" + raceId
				+ ", race=" + race + ", heroClassId=" + heroClassId
				+ ", heroClass=" + heroClass + ", cost=" + cost
				+ ", durability=" + durability + ", text=" + text
				+ ", collectible=" + collectible + ", elite=" + elite + "]";
	}

}
