package com.jobseek;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.Conditions;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class JobportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobportalApplication.class, args);
	}

	@Bean //method level annotation - to tell SC , following method
	//rets an object  - which has to be managed as a spring bean
	//manages - life cycle +
	public ModelMapper modelMapper() {
		System.out.println("in model mapper creation");
		ModelMapper mapper = new ModelMapper();

		mapper.getConfiguration()
				/*
				 * to tell model mapper to map only those properties
				 *  which names match in src and dest. objects
				 */
				.setMatchingStrategy(MatchingStrategies.STRICT)
				/*
				 * to tell model mapper to map only not null values
				 */
				.setPropertyCondition(Conditions.isNotNull()); //required during put or patch
		return mapper;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:5173") // your Vite frontend origin
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true);
			}
		};
	}
}
