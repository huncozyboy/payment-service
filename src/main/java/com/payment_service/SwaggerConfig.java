package com.payment_service;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
			.group("public-api")
			.pathsToMatch("/**")
			.packagesToScan("com.payment_service")
			.packagesToExclude("com.payment_service.common.exception")
			.build();
	}

	private Info apiInfo() {
		return new Info()
			.title("MSA Payment Service API")
			.description("MSA Payment Service API 문서")
			.version("1.0.0");
	}
}
