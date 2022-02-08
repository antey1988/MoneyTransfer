package ru.gpb.school.moneytransfer.DTO;

import lombok.Data;

@Data
public class SenderDTO {
    public int transferId;
    public Long senderAccount;
    public Float amountOfMoney;
}
