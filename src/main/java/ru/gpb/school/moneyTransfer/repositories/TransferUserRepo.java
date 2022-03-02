package ru.gpb.school.moneyTransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gpb.school.moneyTransfer.model.TransferUser;

import java.util.List;
import java.util.Optional;

public interface TransferUserRepo extends JpaRepository<TransferUser,String> {
    Optional<TransferUser> findTransferUsersById(String Id);
    List<TransferUser> findAll();
    TransferUser findByUserName(String username);
}
