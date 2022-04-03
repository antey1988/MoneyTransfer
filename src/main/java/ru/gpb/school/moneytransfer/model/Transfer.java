package ru.gpb.school.moneytransfer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Перевод, пополнение и снятие денежных средств")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор")
    private Long id;
    @Schema(description = "Счет отправитель, в случае пополнения \"---\"")
    private String senderAccount;
    @Schema(description = "Счет получатель, в случае снятия \"---\"")
    private String recipientAccount;
    @Schema(description = "Сумма")
    @JsonProperty(value = "amount")
    private Float amountOfMoney;
    @Schema(description = "Дата операции")
    private LocalDateTime dateTime;
    @Schema(description = "Тип операции")
    private TransferType transferType;
}
