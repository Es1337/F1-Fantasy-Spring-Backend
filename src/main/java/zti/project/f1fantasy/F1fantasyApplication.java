package zti.project.f1fantasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import zti.project.f1fantasy.security.ApplicationSecurityConfig;

@SpringBootApplication
public class F1fantasyApplication {

	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext( ApplicationSecurityConfig.class);
		SpringApplication.run(F1fantasyApplication.class, args);
	}

}
