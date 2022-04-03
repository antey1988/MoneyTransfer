package ru.gpb.school.moneytransfer.service;

import org.junit.jupiter.api.Test;
import ru.gpb.school.moneytransfer.dto.ReplenishmentDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;

import static org.assertj.core.api.Assertions.assertThat;

class ReplenishmentServiceImpTest {

    private final ReplenishmentDto replenishmentDto = new ReplenishmentDto();
    private final Transfer expected = new Transfer();

    private ReplenishmentService replenishmentService = new ReplenishmentServiceImp();

    @Test
    void dtoToTransferEntity() {
        replenishmentDto.setAccount("123456789");
        replenishmentDto.setAmount(1000f);

        expected.setRecipientAccount("123456789");
        expected.setSenderAccount("---");
        expected.setAmountOfMoney(1000f);
        expected.setTransferType(TransferType.REPLENISHMENT);

        Transfer actual = replenishmentService.dtoToTransferEntity(replenishmentDto);
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }
}