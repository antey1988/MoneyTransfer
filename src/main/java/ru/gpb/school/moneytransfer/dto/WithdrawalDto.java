package ru.gpb.school.moneytransfer.dto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Снятие со счета")
public class WithdrawalDto {

    @NotNull
    @Schema(description = "Номер счета")
    private String account;
    @NotNull
    @Schema(description = "Сумма снятия")
    private Float amount;
}
