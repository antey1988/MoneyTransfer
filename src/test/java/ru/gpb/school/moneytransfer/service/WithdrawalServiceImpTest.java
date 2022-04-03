package ru.gpb.school.moneytransfer.service;

import org.junit.jupiter.api.Test;
import ru.gpb.school.moneytransfer.dto.WithdrawalDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;

import static org.assertj.core.api.Assertions.*;

class WithdrawalServiceImpTest {

    private final WithdrawalDto withdrawalDto = new WithdrawalDto();
    private final Transfer expected = new Transfer();
    private final WithdrawalServiceImp withdrawalServiceImp = new WithdrawalServiceImp();

    @Test
    void dtoToTransferEntity() {
        withdrawalDto.setAccount("123456789");
        withdrawalDto.setAmount(1000f);

        expected.setRecipientAccount("---");
        expected.setSenderAccount("123456789");
        expected.setAmountOfMoney(1000f);
        expected.setTransferType(TransferType.WITHDRAW);

        Transfer actual = withdrawalServiceImp.dtoToTransferEntity(withdrawalDto);
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }
}