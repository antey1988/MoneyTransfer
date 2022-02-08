package ru.gpb.school.moneytransfer.DTO;

import lombok.Data;

@Data
public class TransferDTO {
    public int transferId;
    public Long senderAccount;
    public Long recipientAccount;
    public Float amountOfMoney;
}
