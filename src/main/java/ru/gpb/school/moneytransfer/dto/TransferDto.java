package ru.gpb.school.moneytransfer.dto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Перевод со счета на счета")
public class TransferDto {

    @NotNull
    @Schema(description = "Номер счета отправителя")
    private String senderAccount;
    @NotNull
    @Schema(description = "Номер счета получателя")
    private String recipientAccount;
    @NotNull
    @Schema(description = "Сумма перевода")
    private Float amount;
}



