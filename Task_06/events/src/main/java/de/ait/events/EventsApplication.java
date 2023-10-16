package de.ait.events;

import de.ait.events.dto.StandardResponseDto;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static de.ait.events.documentation.OpenApiDocumentation.*;

@SpringBootApplication
public class EventsApplication {

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public OpenAPI openAPI() {
		ResolvedSchema resolvedSchema = ModelConverters.getInstance()
				.resolveAsResolvedSchema(
						new AnnotatedType(StandardResponseDto.class).resolveAsRef(false));

		return new OpenAPI()
				.components(new Components()
						.addSchemas("EmailAndPassword", emailAndPassword())
						.addSecuritySchemes("cookieAuth", securityScheme())
						.addSchemas("StandardResponseDto", resolvedSchema.schema.description("StandardResponseDto")))
				.addSecurityItem(buildSecurity())
				.paths(buildAuthenticationPath())
				.info(new Info().title("Events API").version("0.1"));
	}
	@Bean
	public WebMvcConfigurer cors() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}

}
