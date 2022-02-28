package ru.gpb.school.moneyTransfer.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TransferDto {
    public int transferId;
    public String typeOfTransfer;
    public Long senderAccount;
    public Long recipientAccount;
    public Float amountOfMoney;
}
