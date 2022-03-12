package org.arnaudlt.marmoset.web.configuration;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi streamOpenApi() {
        String[] paths = { "/**" };
        String[] packagedToMatch = { "org.arnaudlt.marmoset.web.controller" };
        return GroupedOpenApi.builder()
                .group("Marmoset")
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Marmoset API")))
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch)
                .build();
    }
}
