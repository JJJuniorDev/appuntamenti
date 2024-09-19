package Main;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import Security.WebSecurityConfigurationS;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {
		"com.dentista.sicurezza",
		"com.dentista.gestionedentista",
		"Main",
		"Repository.Appuntamento",
		//"Security",
		"Services",
		//"Filters",
		//"Utils",
		"Controller",
		//"Dto",
		//"Services.Auth",
		"Model",
		//"Converter",
		//"MongoConfiguration",
		//"DatabaseHealth",
		})
@EnableMongoRepositories(basePackages = {"Repository.Appuntamento", "Repository.Sicurezza"})
@Import(WebSecurityConfigurationS.class)
public class GestioneDentistaAppuntamento {

	public static void main(String[] args) {
		/* System.out.println("Classpath:");
	    for (String path : System.getProperty("java.class.path").split(File.pathSeparator)) {
            System.out.println(path);
        }*/
		SpringApplication.run(GestioneDentistaAppuntamento.class, args);
	
	    
	}


}
