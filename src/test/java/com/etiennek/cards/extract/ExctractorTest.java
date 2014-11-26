package com.etiennek.cards.extract;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.etiennek.cards.App;
import com.etiennek.cards.domain.Card;
import com.etiennek.cards.repo.CardRepository;
import com.etiennek.cards.extract.Extractor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ExctractorTest {

	@Autowired
	CardRepository cardRepository;

	@Test
	public void test() throws IOException {
		// Arrange
		Extractor extractor = new Extractor(cardRepository);
		String expectedGameId = "EX1_414";

		// Act
		extractor.extract(Paths.get("D:/temp/extractor/cardxml0.unity3d"));
		extractor.extract(Paths.get("D:/temp/extractor/cardxml0.unity3d"));
		Card card = cardRepository.findByGameId(expectedGameId);

		// Assert
		assertThat(card, is(notNullValue()));

		assertThat(card.getGameId(), is(equalTo("EX1_414")));
		assertThat(card.getName(), is(equalTo("Grommash Hellscream")));
		assertThat(card.getAttack(), is(equalTo(4)));
		assertThat(card.getHealth(), is(equalTo(9)));

		assertThat(cardRepository.count(), is(equalTo(882L)));
	}
}
