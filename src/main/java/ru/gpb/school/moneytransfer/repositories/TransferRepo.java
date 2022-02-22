package ru.gpb.school.moneytransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gpb.school.moneytransfer.model.Transfer;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferRepo extends JpaRepository<Transfer, Long> {

    List<Transfer> findAllByRecipientAccount(String recipientAccount);

    List<Transfer> findAllBySenderAccount(String senderAccount);

    List<Transfer> findByDateTimeGreaterThanAndDateTimeLessThan(LocalDateTime start, LocalDateTime end);

}
