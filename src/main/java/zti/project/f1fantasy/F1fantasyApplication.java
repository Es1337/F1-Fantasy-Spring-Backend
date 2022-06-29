package zti.project.f1fantasy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import zti.project.f1fantasy.security.ApplicationSecurityConfig;

import javax.persistence.EntityManager;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class F1fantasyApplication {
	@Autowired
	private EntityManager entityManager;

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext( ApplicationSecurityConfig.class);
		SpringApplication.run(F1fantasyApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
