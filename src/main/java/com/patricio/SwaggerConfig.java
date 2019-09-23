package com.patricio;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("Yoel Patricio Ventocilla", "https://www.linkedin.com/in/yoel-patricio-ventocilla-41bb7710b/",
			"ypatriciov@gmail.com");
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Microservice Customers Api Documentation", "Microservice Customers Api Documentation", "1.0",
			"PREMIUM", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
			new ArrayList<VendorExtension>());

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
		        .apis(RequestHandlerSelectors.basePackage("com.patricio.controller")).paths(PathSelectors.regex("/.*"))
	            .build().apiInfo(DEFAULT_API_INFO);

	}
}
