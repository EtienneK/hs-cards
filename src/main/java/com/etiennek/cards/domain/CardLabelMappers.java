package com.etiennek.cards.domain;

class CardLabelMappers {

	private static String DEFAULT = "Unknown";

	static class SetLabelMapper {
		public static String map(int id) {
			switch (id) {
			case 2:
				return "Basic";
			case 3:
				return "Expert";
			case 4:
				return "Reward";
			case 5:
				return "Missions";
			case 7:
				return "System";
			case 8:
				return "Debug";
			case 11:
				return "Promotion";
			case 12:
				return "Curse of Naxxramas";
			case 13:
				return "Goblins vs Gnomes";
			case 16:
				return "Credits";
			}
			return DEFAULT;
		}
	}

	static class TypeLabelMapper {
		public static String map(int id) {
			switch (id) {
			case 3:
				return "Hero";
			case 4:
				return "Minion";
			case 5:
				return "Spell";
			case 6:
				return "Enchantment";
			case 7:
				return "Weapon";
			case 10:
				return "Hero Power";
			}
			return DEFAULT;
		}
	}

	static class FactionLabelMapper {
		public static String map(Integer id) {
			if (id == null) {
				return null;
			}

			switch (id) {
			case 1:
				return "Horde";
			case 2:
				return "Alliance";
			case 3:
				return "Neutral";
			}
			return DEFAULT;
		}
	}

	static class RarityLabelMapper {
		public static String map(Integer id) {
			if (id == null) {
				return null;
			}

			switch (id) {
			case 0:
				return "Undefined";
			case 1:
				return "Common";
			case 2:
				return "Free";
			case 3:
				return "Rare";
			case 4:
				return "Epic";
			case 5:
				return "Legendary";
			}
			return DEFAULT;
		}
	}

	static class RaceLabelMapper {
		public static String map(Integer id) {
			if (id == null) {
				return null;
			}

			switch (id) {
			case 14:
				return "Murloc";
			case 15:
				return "Demon";
			case 20:
				return "Beast";
			case 21:
				return "Totem";
			case 23:
				return "Pirate";
			case 24:
				return "Dragon";
			case 17:
				return "Mech";
			}
			return DEFAULT;
		}
	}

	static class ClassLabelMapper {
		public static String map(Integer id) {
			if (id == null) {
				return "Neutral";
			}

			switch (id) {
			case 0:
				return "Undefined";
			case 2:
				return "Druid";
			case 3:
				return "Hunter";
			case 4:
				return "Mage";
			case 5:
				return "Paladin";
			case 6:
				return "Priest";
			case 7:
				return "Rogue";
			case 8:
				return "Shaman";
			case 9:
				return "Warlock";
			case 10:
				return "Warrior";
			case 11:
				return "Dream";
			}
			return DEFAULT;
		}
	}

}
