package com.etiennek.cards.web.ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.etiennek.cards.extract.Extractor;
import com.etiennek.cards.repo.CardRepository;

@Controller
@RequestMapping("/")
public class CardsController {

	private CardRepository cardRepository;

	@Autowired
	CardsController(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	@RequestMapping
	ModelAndView index() {
		return new ModelAndView("cards/index");
	}

	@RequestMapping("/cards/upload")
	ModelAndView upload() {
		return new ModelAndView("cards/upload");
	}

	@RequestMapping(value = "/cards/upload", method = RequestMethod.POST)
	ModelAndView upload(
			@RequestParam("cardsXmlFileInput") MultipartFile cardsXmlFile,
			RedirectAttributes redirect) {

		Path tempCardsXml = null;
		try {
			tempCardsXml = Files.createTempFile(UUID.randomUUID().toString(),
					".unity3d");
			cardsXmlFile.transferTo(tempCardsXml.toFile());
			new Extractor(cardRepository).extract(tempCardsXml);
			redirect.addAttribute("success", true);
		} catch (Exception e) {
			// TODO
			redirect.addAttribute("success", false);
			e.printStackTrace();
		} finally {
			if (tempCardsXml != null) {
				try {
					Files.delete(tempCardsXml);
				} catch (IOException e) {
					// TODO
					e.printStackTrace();
				}
			}
		}

		return new ModelAndView("redirect:/cards/upload");
	}

}
