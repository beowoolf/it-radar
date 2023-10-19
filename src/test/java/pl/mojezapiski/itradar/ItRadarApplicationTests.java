package pl.mojezapiski.itradar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
@Testcontainers
class ItRadarApplicationTests {

	@Container
	public static final MySQLContainer mongoDBContainer = new MySQLContainer(DockerImageName.parse("mysql"));

	@DynamicPropertySource
	public static void propertyOverride(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", mongoDBContainer::getJdbcUrl);
		registry.add("spring.datasource.username", mongoDBContainer::getUsername);
		registry.add("spring.datasource.password", mongoDBContainer::getPassword);
	}

	@Test
	void contextLoads() {
	}

}
