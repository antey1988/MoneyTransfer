package ru.gpb.school.moneytransfer.dto;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Пополнение счета")
public class ReplenishmentDto {

    @NotNull
    @Schema(description = "Номер счета")
    private String account;
    @NotNull
    @Schema(description = "Сумма пополнения")
    private Float amount;
}
