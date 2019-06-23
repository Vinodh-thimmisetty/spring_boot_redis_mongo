package com.vinodh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author thimmv
 * @createdAt 22-06-2019 09:08
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Vinodh Kumar",
            "http://github.com/vinodh-thimmisetty",
            "vinodh5052@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Spring boot Mongo and Redis", "Sample spring boot application using Mongo and Redis. This will be hosted in Cloud",
            "1.0",
            "opensource:terms and conditions", DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            Collections.singletonList(new StringVendorExtension("vendor", "Vinodh Kumar")));

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            Stream.of("application/json",
                    "application/xml").collect(Collectors.toSet());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO);
//                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
//                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
}