package co.ao.sigp.catequese;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import co.ao.sigp.catequese.infrastructure.repository.CustomJpaRepositoryImpl;


@SpringBootApplication
/*
 * @EnableJpaRepositories - define que a implementacao do repositorio base do projecto seja o CustomJpaRepositoryImpl
 */
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class SigpCatequeseApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(SigpCatequeseApplication.class, args);
	}
	
	public void applicationTodo() {
		/*
		 * TODO: implementar o messages.properties, verificar no projecto algafood	
		 * TODO: implementar relatorios	
		 */
	}
}
