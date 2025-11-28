package com.huncozyboy.banking;

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
			.packagesToExclude("com.huncozyboy.banking.common.exception")
			.build();
	}

	private Info apiInfo() {
		return new Info()
			.title("MSA Payment Service API")
			.description("MSA Payment Service API 문서")
			.version("1.0.0");
	}
}
