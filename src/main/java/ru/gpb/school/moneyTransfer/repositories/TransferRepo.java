package ru.gpb.school.moneyTransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gpb.school.moneyTransfer.model.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferRepo extends JpaRepository<Transfer, String> {
    List<Transfer>findAll();
    Optional<Transfer> findById(int id);
    List<Transfer>findBySenderAccount(Long senderAccount);
    List<Transfer>findByRecipientAccount(Long RecipientAccount);

}
