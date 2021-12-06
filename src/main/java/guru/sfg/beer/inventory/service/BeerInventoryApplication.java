package guru.sfg.beer.inventory.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeerInventoryApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BeerInventoryApplication.class);
        // application.setBannerMode(Banner.Mode.CONSOLE);
        // application.setWebApplicationType(WebApplicationType.SERVLET);
        application.run(args);
        // AnnotationConfigServletWebServerApplicationContext context = (AnnotationConfigServletWebServerApplicationContext) application.run(args);
        // SpringApplication.run(BeerInventoryApplication.class, args);
    }
}
