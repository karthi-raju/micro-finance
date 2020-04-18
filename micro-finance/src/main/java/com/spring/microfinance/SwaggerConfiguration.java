package com.spring.microfinance;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	private String swaggerFlag = "true";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("micro-finance").apiInfo(apiInfo())
				.enable(Boolean.valueOf(swaggerFlag)).select().apis(RequestHandlerSelectors.any()).paths(regex("/.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("micro-finance",
				"This WebUI describes the REST APIs that can be used to manage user deatils, debit, credit etc", "v1.0",
				"", "", "", "");
	}

}
