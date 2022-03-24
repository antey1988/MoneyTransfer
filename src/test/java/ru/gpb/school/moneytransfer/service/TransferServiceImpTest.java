package ru.gpb.school.moneytransfer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gpb.school.moneytransfer.dto.TransferDto;
import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;
import ru.gpb.school.moneytransfer.repositories.TransferRepo;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gpb.school.moneytransfer.TestUtils.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceImpTest {
    @Mock
    private TransferRepo transferRepo;
    @InjectMocks
    private TransferServiceImp transferServiceImp;

    @Test
    void findAll() {
        List<Transfer> expected = getTestTransfers();
        Mockito.when(transferRepo.findAll()).thenReturn(expected);
        List<Transfer> actual = transferServiceImp.findAll();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    void findTransfersByRecipientAccount() {
        List<Transfer> expected = getTestTransfers();
        Mockito.when(transferRepo.findAllByRecipientAccount(Mockito.any())).thenReturn(expected);
        List<Transfer> actual = transferServiceImp.findTransfersByRecipientAccount("123456789");
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    void findTransfersBetween() {
        List<Transfer> expected = getTestTransfers();
        Mockito.when(transferRepo.findByDateTimeGreaterThanAndDateTimeLessThan(Mockito.any(), Mockito.any())).thenReturn(expected);
        List<Transfer> actual = transferServiceImp.findTransfersBetween(LocalDateTime.now().minusHours(25), LocalDateTime.now().plusHours(25));
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    void findTransfersBySenderAccount() {
        List<Transfer> expected = getTestTransfers();
        Mockito.when(transferRepo.findAllBySenderAccount(Mockito.any())).thenReturn(expected);
        List<Transfer> actual = transferServiceImp.findTransfersBySenderAccount("123456789");
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isEqualTo(expected.size());
    }

    @Test
    void dtoToTransferEntity() {
        TransferDto transferDto = new TransferDto();
        transferDto.setFrom("123456789");
        transferDto.setTo("987654321");
        transferDto.setAmount(100f);

        Transfer expected = new Transfer();
        expected.setSenderAccount("123456789");
        expected.setRecipientAccount("987654321");
        expected.setAmountOfMoney(100f);
        expected.setTransferType(TransferType.TRANSFER);

        Transfer actual = transferServiceImp.dtoToTransferEntity(transferDto);
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }
}