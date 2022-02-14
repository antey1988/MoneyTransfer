package ru.gpb.school.moneytransfer.model;

import lombok.*;

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
}
