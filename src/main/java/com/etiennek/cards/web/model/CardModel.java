package com.etiennek.cards.web.model;

public class CardModel {

	private long id;
	private String name;
	private Integer attack;
	private Integer health;

	private int setId;
	private String setLabel;

	private int typeId;
	private String typeLabel;

	private Integer rarityId;
	private String rarityLabel;

	private Integer raceId;
	private String raceLabel;

	private Integer heroClassId;
	private String heroClassLabel;

	private Integer cost;
	private Integer durability;
	private String text;

	private boolean collectible;
	private boolean elite;

	public CardModel(long id, String name, Integer attack, Integer health,
			int setId, String setLabel, int typeId, String typeLabel,
			Integer rarityId, String rarityLabel, Integer raceId,
			String raceLabel, Integer heroClassId, String heroClassLabel,
			Integer cost, Integer durability, String text, boolean collectible,
			boolean elite) {
		super();
		this.id = id;
		this.name = name;
		this.attack = attack;
		this.health = health;
		this.setId = setId;
		this.setLabel = setLabel;
		this.typeId = typeId;
		this.typeLabel = typeLabel;
		this.rarityId = rarityId;
		this.rarityLabel = rarityLabel;
		this.raceId = raceId;
		this.raceLabel = raceLabel;
		this.heroClassId = heroClassId;
		this.heroClassLabel = heroClassLabel;
		this.cost = cost;
		this.durability = durability;
		this.text = text;
		this.collectible = collectible;
		this.elite = elite;
	}

	public long getId() {
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

	public String getSetLabel() {
		return setLabel;
	}

	public int getTypeId() {
		return typeId;
	}

	public String getTypeLabel() {
		return typeLabel;
	}

	public Integer getRarityId() {
		return rarityId;
	}

	public String getRarityLabel() {
		return rarityLabel;
	}

	public Integer getRaceId() {
		return raceId;
	}

	public String getRaceLabel() {
		return raceLabel;
	}

	public Integer getHeroClassId() {
		return heroClassId;
	}

	public String getHeroClassLabel() {
		return heroClassLabel;
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
				+ ((heroClassId == null) ? 0 : heroClassId.hashCode());
		result = prime * result
				+ ((heroClassLabel == null) ? 0 : heroClassLabel.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((raceId == null) ? 0 : raceId.hashCode());
		result = prime * result
				+ ((raceLabel == null) ? 0 : raceLabel.hashCode());
		result = prime * result
				+ ((rarityId == null) ? 0 : rarityId.hashCode());
		result = prime * result
				+ ((rarityLabel == null) ? 0 : rarityLabel.hashCode());
		result = prime * result + setId;
		result = prime * result
				+ ((setLabel == null) ? 0 : setLabel.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + typeId;
		result = prime * result
				+ ((typeLabel == null) ? 0 : typeLabel.hashCode());
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
		CardModel other = (CardModel) obj;
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
		if (heroClassId == null) {
			if (other.heroClassId != null)
				return false;
		} else if (!heroClassId.equals(other.heroClassId))
			return false;
		if (heroClassLabel == null) {
			if (other.heroClassLabel != null)
				return false;
		} else if (!heroClassLabel.equals(other.heroClassLabel))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (raceId == null) {
			if (other.raceId != null)
				return false;
		} else if (!raceId.equals(other.raceId))
			return false;
		if (raceLabel == null) {
			if (other.raceLabel != null)
				return false;
		} else if (!raceLabel.equals(other.raceLabel))
			return false;
		if (rarityId == null) {
			if (other.rarityId != null)
				return false;
		} else if (!rarityId.equals(other.rarityId))
			return false;
		if (rarityLabel == null) {
			if (other.rarityLabel != null)
				return false;
		} else if (!rarityLabel.equals(other.rarityLabel))
			return false;
		if (setId != other.setId)
			return false;
		if (setLabel == null) {
			if (other.setLabel != null)
				return false;
		} else if (!setLabel.equals(other.setLabel))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (typeId != other.typeId)
			return false;
		if (typeLabel == null) {
			if (other.typeLabel != null)
				return false;
		} else if (!typeLabel.equals(other.typeLabel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CardModel [id=" + id + ", name=" + name + ", attack=" + attack
				+ ", health=" + health + ", setId=" + setId + ", setLabel="
				+ setLabel + ", typeId=" + typeId + ", typeLabel=" + typeLabel
				+ ", rarityId=" + rarityId + ", rarityLabel=" + rarityLabel
				+ ", raceId=" + raceId + ", raceLabel=" + raceLabel
				+ ", heroClassId=" + heroClassId + ", heroClassLabel="
				+ heroClassLabel + ", cost=" + cost + ", durability="
				+ durability + ", text=" + text + ", collectible="
				+ collectible + ", elite=" + elite + "]";
	}

}
