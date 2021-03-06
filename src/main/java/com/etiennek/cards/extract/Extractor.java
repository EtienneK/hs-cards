package com.etiennek.cards.extract;

import info.ata4.io.util.PathUtils;
import info.ata4.unity.cli.DisUnityCli;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.etiennek.cards.domain.Card;
import com.etiennek.cards.repo.CardRepository;
import com.etiennek.cards.search.domain.CardSearch;
import com.etiennek.cards.search.repo.CardSearchRepository;

public class Extractor extends DefaultHandler {

	private CardRepository cardRepository;
	private CardSearchRepository cardSearchRepository;

	private Entity currentEntity;
	private Integer currentTagEnumId;

	public Extractor(CardRepository cardRepository,
			CardSearchRepository cardSearchRepository) {
		this.cardRepository = cardRepository;
		this.cardSearchRepository = cardSearchRepository;
	}

	public void extract(Path cardXmlUnityFile) throws IOException {
		// TODO: Fix this so we can capture errors properly. If something goes
		// wrong now, we won't know it failed.
		DisUnityCli
				.main(new String[] { "extract", cardXmlUnityFile.toString() });
		Path xmlRootDirectory = PathUtils.removeExtension(cardXmlUnityFile);
		try (Stream<Path> cardXmls = getCardXmls(xmlRootDirectory)) {
			cardXmls.forEach(this::extractXml);
		} finally {
			FileUtils.deleteDirectory(xmlRootDirectory.toFile());
		}
	}

	private void extractXml(Path path) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try (InputStream xmlInput = Files.newInputStream(path)) {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlInput, this);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ("Entity".equals(qName)) {
			currentEntity = new Entity();
			currentEntity.id = attributes.getValue("CardID");
		} else if ("Tag".equals(qName)) {
			currentTagEnumId = Integer.parseInt(attributes.getValue("enumID"));
			String tagValue = attributes.getValue("value");
			currentEntity.tags.put(currentTagEnumId, tagValue == null ? ""
					: tagValue);
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (currentEntity == null || currentTagEnumId == null) {
			return;
		}

		String value = new String(ch, start, length);
		currentEntity.tags.put(currentTagEnumId,
				currentEntity.tags.get(currentTagEnumId) + value);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Entity".equals(qName)) {
			saveEntity();
			currentEntity = null;
		} else if ("Tag".equals(qName)) {
			currentTagEnumId = null;
		}
	}

	private static final int ENUM_ID_CARD_NAME = 185;
	private static final int ENUM_ID_ATTACK = 47;
	private static final int ENUM_ID_HEALTH = 45;
	private static final int ENUM_ID_CARD_SET = 183;
	private static final int ENUM_ID_CARD_TYPE = 202;
	private static final int ENUM_ID_FACTION = 201;
	private static final int ENUM_ID_RARITY = 203;
	private static final int ENUM_ID_COST = 48;
	private static final int ENUM_ID_DURABILITY = 187;
	private static final int ENUM_ID_CARD_TEXT_IN_HAND = 184;
	private static final int ENUM_ID_CARD_TEXT_IN_PLAY = 252;
	private static final int ENUM_ID_FLAVOUR_TEXT = 351;
	private static final int ENUM_ID_ARTIST_NAME = 342;
	private static final int ENUM_COLLECTIBLE = 321;
	private static final int ENUM_ID_ELITE = 114;
	private static final int ENUM_ID_RACE = 200;
	private static final int ENUM_ID_CLASS = 199;
	private static final int ENUM_ID_HOW_TO_GET_THIS_CARD = 364;
	private static final int ENUM_ID_HOW_TO_GET_THIS_GOLD_CARD = 365;

	private void saveEntity() {
		String id = currentEntity.id;
		String name = currentEntity.tagValue(ENUM_ID_CARD_NAME);
		Integer attack = toInt(currentEntity.tagValue(ENUM_ID_ATTACK));
		Integer health = toInt(currentEntity.tagValue(ENUM_ID_HEALTH));
		Integer set = toInt(currentEntity.tagValue(ENUM_ID_CARD_SET));
		Integer type = toInt(currentEntity.tagValue(ENUM_ID_CARD_TYPE));
		Integer faction = toInt(currentEntity.tagValue(ENUM_ID_FACTION));
		Integer rarity = toInt(currentEntity.tagValue(ENUM_ID_RARITY));
		Integer cost = toInt(currentEntity.tagValue(ENUM_ID_COST));
		Integer durability = toInt(currentEntity.tagValue(ENUM_ID_DURABILITY));
		String text = clean(currentEntity.tagValue(ENUM_ID_CARD_TEXT_IN_HAND));
		String textInPlay = clean(currentEntity
				.tagValue(ENUM_ID_CARD_TEXT_IN_PLAY));
		String flavourText = currentEntity.tagValue(ENUM_ID_FLAVOUR_TEXT);
		String artistName = currentEntity.tagValue(ENUM_ID_ARTIST_NAME);
		boolean isCollectible = toBool(currentEntity.tagValue(ENUM_COLLECTIBLE));
		boolean isElite = toBool(currentEntity.tagValue(ENUM_ID_ELITE));
		Integer race = toInt(currentEntity.tagValue(ENUM_ID_RACE));
		Integer heroClass = toInt(currentEntity.tagValue(ENUM_ID_CLASS));
		String howToGet = currentEntity.tagValue(ENUM_ID_HOW_TO_GET_THIS_CARD);
		String howToGetGold = currentEntity
				.tagValue(ENUM_ID_HOW_TO_GET_THIS_GOLD_CARD);
		// mechanics = null; // TODO

		Card card = cardRepository.findFirstByGameId(id);
		Long internalId = (card == null) ? null : card.getId();
		card = new Card(internalId, id, name, attack, health, set, type,
				faction, rarity, race, heroClass, cost, durability, text,
				textInPlay, flavourText, artistName, isCollectible, isElite,
				howToGet, howToGetGold);
		cardRepository.save(card);

		CardSearch cardSearch = new CardSearch(card.getId(), name, attack,
				health, set, card.getSetLabel(), type, card.getTypeLabel(),
				rarity, card.getRarityLabel(), race, card.getRaceLabel(),
				heroClass, card.getHeroClassLabel(), cost, durability, text,
				isCollectible, isElite);
		cardSearchRepository.save(cardSearch);
	}

	private Stream<Path> getCardXmls(Path path) throws IOException {
		return Files.find(path, 3, (p, a) -> {
			if (a.isRegularFile() && p.toString().endsWith("enGB.txt")) {
				// TODO: Add more languages
				return true;
			}
			return false;
		});
	}

	private class Entity {
		String id;
		Map<Integer, String> tags = new HashMap<>();

		String tagValue(int tagEnumId) {
			return tags.get(tagEnumId);
		}
	}

	private static Integer toInt(String s) {
		if (s == null) {
			return null;
		}
		return Integer.parseInt(s);
	}

	private static boolean toBool(String s) {
		if (s == null) {
			return false;
		}
		return "1".equals(s) || Boolean.parseBoolean(s);
	}

	private static final Pattern DOLLAR_PATTERN = Pattern
			.compile("\\$([0-9])+");
	private static final Pattern HASH_PATTERN = Pattern.compile("#([0-9])+");

	static String clean(String s) {
		if (s == null) {
			return null;
		}

		String ret = DOLLAR_PATTERN.matcher(s).replaceAll("$1");
		ret = HASH_PATTERN.matcher(ret).replaceAll("$1");

		return ret;
	}

}
