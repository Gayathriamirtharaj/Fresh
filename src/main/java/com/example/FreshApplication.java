package com.example;
import com.example.model.Role;
import com.example.service.UserService;
import com.example.vo.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class FreshApplication implements CommandLineRunner {

	@Autowired
	UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(FreshApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public void run(String... params) throws Exception {
		/*User admin = new User();
		admin.setFirstName("admin");
		admin.setLastName("admin");
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@example.com");
		admin.setMobile("1000010000");
		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

		userService.register(admin);

		User user = new User();
		user.setFirstName("user");
		user.setLastName("user");
		user.setUsername("user");
		user.setPassword("user");
		user.setEmail("user@gmail.com");
		user.setMobile("9000090000");
		user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_USER)));

		userService.register(user);

		User agent=new User();
		agent.setFirstName("agent");
		agent.setLastName("agent");
		agent.setUsername("agent");
		agent.setPassword("agent");
		agent.setEmail("agent@gmail.com");
		agent.setMobile("9078986876");
		agent.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_AGENT)));

		userService.register(agent);*/

	}

}
