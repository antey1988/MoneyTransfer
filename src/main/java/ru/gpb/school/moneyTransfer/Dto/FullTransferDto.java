package ru.gpb.school.moneyTransfer.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FullTransferDto extends AbstractTransferDto{
    private Long senderAccount;
    private Long recipientAccount;
    private Float amountOfMoney;
    private LocalDateTime dateOfTransfer;
    private String typeOfTransfer;
}
