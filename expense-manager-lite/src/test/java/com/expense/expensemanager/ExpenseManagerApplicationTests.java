package com.expense.expensemanager;

import com.expense.expensemanager.config.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@Import(TestSecurityConfig.class)
class ExpenseManagerApplicationTests {

	@Test
	void contextLoads() {
		// Basic smoke test to ensure application context loads successfully
		// This verifies that all beans can be created and autowired correctly
	}

}
