package ru.gpb.school.moneyTransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gpb.school.moneyTransfer.model.Transfer;

import java.util.List;
import java.util.Optional;
@Repository
public interface TransferRepo extends JpaRepository<Transfer, String> {
    List<Transfer>findAll();
    List<Transfer>findBySenderAccount(Long senderAccount);
    List<Transfer>findByRecipientAccount(Long RecipientAccount);

}
