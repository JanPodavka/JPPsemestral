package ppj.assignments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ppj.assignments.beans.WeatherService;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "ppj.assignments.repositories")
public class Main {


    public static void main(String[] args) throws Exception {


        SpringApplication app = new SpringApplication(Main.class);
        app.setAdditionalProfiles("devel");
        ApplicationContext ctx = app.run(args);

    }
    @Bean
    public WeatherService apiService() {
        return new WeatherService();
    }



}