package org.toledano.pusinex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.toledano.pusinex.models.repository.IDistritoFederalRepository;

@SpringBootApplication
public class PusinexApplication {
	private static final Logger log = LoggerFactory.getLogger(PusinexApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PusinexApplication.class, args);
		log.info("Iniciando la aplicación PUSINEX");
	}

	@Bean
	public CommandLineRunner demo(IDistritoFederalRepository repository) {
		return (args) -> {
			log.info("Buscando Tlaxcala");
			log.info("-----------------");
			log.info(String.valueOf(repository.findByEntidadIdAndDistrito(29, 1)));
		};
	}
}
