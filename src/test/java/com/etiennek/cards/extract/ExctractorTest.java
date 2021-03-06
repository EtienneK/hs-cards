package com.etiennek.cards.extract;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.etiennek.cards.App;
import com.etiennek.cards.domain.Card;
import com.etiennek.cards.repo.CardRepository;
import com.etiennek.cards.search.repo.CardSearchRepository;
import com.etiennek.cards.extract.Extractor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ExctractorTest {

	@Autowired
	CardRepository cardRepository;

	@Autowired
	CardSearchRepository cardSearchRepository;

	@Before
	public void start() {
		cardRepository.deleteAll();
	}

	@Test
	public void Full_extraction_test() throws IOException {
		// Arrange
		Extractor extractor = new Extractor(cardRepository,
				cardSearchRepository);

		// Act
		extractor.extract(Paths.get("C:/Temp/extract/cardxml0.unity3d"));
		extractor.extract(Paths.get("C:/Temp/extract/cardxml0.unity3d"));

		Card actualGrommash = cardRepository
				.findFirstByGameId(IN_GAME_ID_GROMMASH);
		Card expectedGrommash = expectedGrommash(actualGrommash.getId());

		Card actualAssBlade = cardRepository
				.findFirstByGameId(IN_GAME_ID_ASSBLADE);
		Card expectedAssBlade = expectedAssassinsBlade(actualAssBlade.getId());

		Card actualTwisNeth = cardRepository
				.findFirstByGameId(IN_GAME_ID_TWIS_NETH);
		Card expectedTwisNeth = expectedTwistingNether(actualTwisNeth.getId());

		// Assert
		assertThat(actualGrommash, is(equalTo(expectedGrommash)));
		assertThat(actualAssBlade, is(equalTo(expectedAssBlade)));
		assertThat(actualTwisNeth, is(equalTo(expectedTwisNeth)));
		assertThat(cardRepository.count(), is(equalTo(NUMBER_OF_CARDS)));
	}

	// Card Data

	private long NUMBER_OF_CARDS = 1063L;
	private static String IN_GAME_ID_GROMMASH = "EX1_414";
	private static String IN_GAME_ID_ASSBLADE = "CS2_080";
	private static String IN_GAME_ID_TWIS_NETH = "EX1_312";

	private Card expectedGrommash(Long id) {
		return new Card(
				id,
				IN_GAME_ID_GROMMASH,
				"Grommash Hellscream",
				4,
				9,
				3,
				4,
				3,
				5,
				null,
				10,
				8,
				null,
				"<b>Charge</b>\n<b>Enrage:</b> +6 Attack",
				null,
				"Grommash drank the tainted blood of Mannoroth, dooming the orcs to green skin and red eyes!  Maybe not his best decision.",
				"Glenn Rane", true, true, null, null);
	}

	private Card expectedAssassinsBlade(Long id) {
		return new Card(
				id,
				IN_GAME_ID_ASSBLADE,
				"Assassin's Blade",
				3,
				null,
				2,
				7,
				3,
				1,
				null,
				7,
				5,
				4,
				null,
				null,
				"Guaranteed to have been owned by a real assassin.   Certificate of authenticity included.",
				"Brian Huang", true, false, "Unlocked at Level 2.",
				"Unlocked at Level 32.");
	}

	private Card expectedTwistingNether(Long id) {
		return new Card(
				id,
				IN_GAME_ID_TWIS_NETH,
				"Twisting Nether",
				null,
				null,
				3,
				5,
				3,
				4,
				null,
				9,
				8,
				null,
				"Destroy all minions.",
				null,
				"The Twisting Nether is a formless place of magic and illusion and destroyed minions.",
				"Dave Allsop", true, false, null, null);
	}

	@Test
	public void clean_Unit_Test() {
		// Arrange
		String toClean = "Deal $2 damage to all enemies. Restore #3 Health to all friendly characters.";

		// Act
		String actual = Extractor.clean(toClean);

		// Assert
		assertThat(
				actual,
				is(equalTo("Deal 2 damage to all enemies. Restore 3 Health to all friendly characters.")));
	}
}
