package spring.boot.exemples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class EventListenerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventListenerDemoApplication.class, args);
	}
}
