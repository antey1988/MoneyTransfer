package ru.gpb.school.moneytransfer.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import ru.gpb.school.moneytransfer.model.Transfer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gpb.school.moneytransfer.TestUtils.*;

@DataJpaTest(properties = "spring.liquibase.enabled=false")
@Sql(scripts = "classpath:/populate.sql")
class TransferRepoTest {

    @Autowired
    TransferRepo repo;

    @Test
    void findAllByRecipientAccount() {
        List<Transfer> expected = getTestTransfers().stream().filter(getByRecipient("3")).collect(Collectors.toList());
        List<Transfer> actual = repo.findAllByRecipientAccount("3");
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    @Test
    void findAllBySenderAccount() {
        List<Transfer> expected = getTestTransfers().stream().filter(getBySender("1")).collect(Collectors.toList());
        List<Transfer> actual = repo.findAllBySenderAccount("1");
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    @Test
    void findByDateTimeGreaterThanAndDateTimeLessThan() {
        LocalDateTime begin = LocalDateTime.of(2022, 3, 18, 0, 0);
        LocalDateTime end = LocalDateTime.of(2022, 3, 18, 23, 59);
        List<Transfer> expected = getTestTransfers().stream().filter(getBetweenStartDateAndStopDate(begin, end)).collect(Collectors.toList());
        List<Transfer> actual = repo.findByDateTimeGreaterThanAndDateTimeLessThan(begin, end);
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}