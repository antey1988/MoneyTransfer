package ru.gpb.school.moneytransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gpb.school.moneytransfer.model.Transfer;

import java.util.List;
import java.util.Optional;

public interface TransferRepo extends JpaRepository<Transfer, String> {
    List<Transfer>findAll();
    Optional<Transfer> findById(int id);
    Optional<Transfer>findBySenderScore(Long senderScore);
    Optional<Transfer>findByRecipientScore(Long RecipientScore);

}
