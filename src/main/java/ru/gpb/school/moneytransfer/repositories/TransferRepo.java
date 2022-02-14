package ru.gpb.school.moneytransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gpb.school.moneytransfer.model.Transfer;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferRepo extends JpaRepository<Transfer, Long> {

    List<Transfer> findAllByRecipientAccount(String recipientAccount);

    List<Transfer> findAllBySenderAccount(String senderAccount);

    List<Transfer> findAllByDateTime(LocalDateTime dateTime);

    List<Transfer> findByDateTimeGreaterThanAndDateTimeLessThan(LocalDateTime start, LocalDateTime end);

}
