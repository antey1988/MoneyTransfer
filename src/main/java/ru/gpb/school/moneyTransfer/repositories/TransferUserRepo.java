package ru.gpb.school.moneyTransfer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gpb.school.moneyTransfer.model.TransferUser;

import java.util.List;

@Repository
public interface TransferUserRepo extends JpaRepository<TransferUser,String> {
    List<TransferUser> findAll();
    TransferUser findByUserName(String username);
}
