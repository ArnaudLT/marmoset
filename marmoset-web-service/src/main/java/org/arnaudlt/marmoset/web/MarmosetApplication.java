package org.arnaudlt.marmoset.web;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(scanBasePackages = {"org.arnaudlt.marmoset"})
@EnableWebFlux
public class MarmosetApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder(MarmosetApplication.class)
				.web(WebApplicationType.REACTIVE)
				.run(args);
	}

}
