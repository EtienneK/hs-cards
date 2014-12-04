package com.etiennek.cards.extract;

import info.ata4.io.util.PathUtils;
import info.ata4.unity.cli.DisUnityCli;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.FileUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.etiennek.cards.domain.Card;
import com.etiennek.cards.repo.CardRepository;

public class Extractor extends DefaultHandler {

	private CardRepository cardRepository;
	private Entity currentEntity;
	private String currentTagName;

	public Extractor(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
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
			currentTagName = attributes.getValue("name");
			if (currentTagName == null) {
				throw new IllegalStateException("Tag can't be null");
			}
			String tagValue = attributes.getValue("value");
			currentEntity.tags.put(currentTagName, tagValue == null ? ""
					: tagValue);
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (currentEntity == null || currentTagName == null) {
			return;
		}

		String value = new String(ch, start, length).trim();
		if (value.length() == 0) {
			return; // ignore white space
		}
		currentEntity.tags.put(currentTagName,
				currentEntity.tags.get(currentTagName) + value);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ("Entity".equals(qName)) {
			saveEntity();
			currentEntity = null;
		} else if ("Tag".equals(qName)) {
			currentTagName = null;
		}
	}

	private void saveEntity() {
		String id = currentEntity.id;
		String name = currentEntity.tagValue("CardName");
		Integer attack = toInt(currentEntity.tagValue("Atk"));
		Integer health = toInt(currentEntity.tagValue("Health"));
		Integer set = toInt(currentEntity.tagValue("CardSet"));
		Integer type = toInt(currentEntity.tagValue("CardType"));
		Integer faction = toInt(currentEntity.tagValue("Faction"));
		Integer rarity = toInt(currentEntity.tagValue("Rarity"));
		Integer cost = toInt(currentEntity.tagValue("Cost"));
		Integer durability = toInt(currentEntity.tagValue("Durability"));
		String text = currentEntity.tagValue("CardTextInHand");
		String textInPlay = currentEntity.tagValue("CardTextInPlay");
		String flavourText = currentEntity.tagValue("FlavorText");
		String artistName = currentEntity.tagValue("ArtistName");
		boolean isCollectible = toBool(currentEntity.tagValue("Collectible"));
		boolean isElite = toBool(currentEntity.tagValue("Elite"));
		Integer race = toInt(currentEntity.tagValue("Race"));
		Integer heroClass = toInt(currentEntity.tagValue("Class"));
		String howToGet = currentEntity.tagValue("HowToGetThisCard");
		String howToGetGold = currentEntity.tagValue("HowToGetThisGoldCard");
		// mechanics = null; // TODO

		Card card = cardRepository.findByGameId(id);
		Integer internalId = card == null ? null : card.getId();
		card = new Card(internalId, id, name, attack, health, set, type,
				faction, rarity, cost, durability, text, textInPlay,
				flavourText, artistName, isCollectible, isElite, race,
				heroClass, howToGet, howToGetGold);
		cardRepository.save(card);
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
		Map<String, String> tags = new HashMap<>();

		String tagValue(String tagName) {
			return tags.get(tagName);
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

}
