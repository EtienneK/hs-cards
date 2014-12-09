package com.etiennek.cards;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSpringDataWebSupport
@EnableElasticsearchRepositories("com.etiennek.cards.search.repo")
@EnableJpaRepositories("com.etiennek.cards.repo")
public class App {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);

	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("25MB");
		return factory.createMultipartConfig();
	}
}
