package com.stackroute.favouriteservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket api() { 
		
		return new Docket(DocumentationType.SWAGGER_2)  
				.select()                                  
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.favouriteservice"))              
				.paths(PathSelectors.any())			
				.build().apiInfo(apiInfo());  
		
	}
	
	private ApiInfo apiInfo() {
		
	    return new ApiInfoBuilder()
	    		.description("Provides endpoints to perform CRUD operations in Books DB")
	    		.title("My Favourite Books App")
	    		.version("1.0.0")
	    		.license("Cognizant")
	    		.contact(new Contact("Cognizant. All rights reserved", "www.cts.com", "arunkumaran.elango@cognizant.com"))
	    		.build();
	    		
	
	}

}
