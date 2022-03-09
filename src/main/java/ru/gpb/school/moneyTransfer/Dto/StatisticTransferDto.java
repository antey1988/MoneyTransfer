package ru.gpb.school.moneyTransfer.Dto;

import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatisticTransferDto extends AbstractTransferDto{
    private Float amountOfMoney;
    private LocalDateTime dateOfTransfer;
    private String typeOfTransfer;
}
