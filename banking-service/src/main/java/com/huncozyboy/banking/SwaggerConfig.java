package com.huncozyboy.banking;

import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
			.group("public-api")
			.pathsToMatch("/**")
			.packagesToScan("com.huncozyboy.banking")
			.build();
	}

	private Info apiInfo() {
		return new Info()
			.title("MSA Payment Service API")
			.description("MSA Payment Service API 문서")
			.version("1.0.0");
	}
}
