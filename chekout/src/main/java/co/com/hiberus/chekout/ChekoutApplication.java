package co.com.hiberus.chekout;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChekoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChekoutApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean register = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/rest/api/v1.0/checkouts/*");
		register.setName("CamelServlet");
		return register;
	}
}
