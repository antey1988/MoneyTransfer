package ru.gpb.school.moneytransfer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.liquibase.enabled=false")
@AutoConfigureTestDatabase
class MoneyTransferApplicationTests {

    @Test
    void contextLoads() {
    }

}
