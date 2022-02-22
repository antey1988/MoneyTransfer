package ru.gpb.school.moneytransfer.model;

import lombok.*;
import ru.gpb.school.moneytransfer.model.type.TransferType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderAccount;
    private String recipientAccount;
    private Float amountOfMoney;
    private LocalDateTime dateTime;
    private TransferType transferType;
}
