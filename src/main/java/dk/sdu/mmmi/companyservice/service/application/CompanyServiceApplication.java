package dk.sdu.mmmi.companyservice.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= "dk.sdu.mmmi.companyservice")
@EntityScan("dk.sdu.mmmi.companyservice.service.model")
@EnableJpaRepositories("dk.sdu.mmmi.companyservice.outbound.repository")
public class CompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
	}

}
