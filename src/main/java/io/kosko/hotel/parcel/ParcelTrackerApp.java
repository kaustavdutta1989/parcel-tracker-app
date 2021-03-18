package io.kosko.hotel.parcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import io.kosko.hotel.parcel.util.LoggingInterceptor;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ParcelTrackerApp extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(ParcelTrackerApp.class, args);
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }
	
	@Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(generateApiInfo());
    }
	
	private ApiInfo generateApiInfo()
    {
        return new ApiInfo("Receptionist Parcel Tracker App Service", "This service is to manage the parcels for the receptionist at the hotel", "Version 1.0 - mw",
            "urn:tos", "www.google.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }

}
