package ru.gpb.school.moneytransfer;

import ru.gpb.school.moneytransfer.model.Transfer;
import ru.gpb.school.moneytransfer.model.type.TransferType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public class TestUtils {

    private TestUtils() {
    }

    public static List<Transfer> getTestTransfers() {
        return List.of(
                new Transfer(1L, "1", "3", 1000f, LocalDateTime.of(2022, 3, 17, 12, 0), TransferType.TRANSFER),
                new Transfer(2L, "2", "3", 1000f, LocalDateTime.of(2022, 3, 17, 13, 0), TransferType.TRANSFER),
                new Transfer(3L, "1", "2", 1000f, LocalDateTime.of(2022, 3, 18, 10, 0), TransferType.TRANSFER),
                new Transfer(4L, "1", "2", 1000f, LocalDateTime.of(2022, 3, 18, 11, 0), TransferType.TRANSFER),
                new Transfer(5L, "1", "3", 1000f, LocalDateTime.of(2022, 3, 18, 11, 0), TransferType.TRANSFER),
                new Transfer(6L, "2", "---", 1000f, LocalDateTime.of(2022, 3, 18, 13, 0), TransferType.WITHDRAW),
                new Transfer(7L, "2", "4", 1000f, LocalDateTime.of(2022, 3, 18, 13, 0), TransferType.TRANSFER),
                new Transfer(8L, "---", "3", 1000f, LocalDateTime.of(2022, 3, 18, 16, 0), TransferType.REPLENISHMENT),
                new Transfer(9L, "---", "3", 1000f, LocalDateTime.of(2022, 3, 18, 19, 0), TransferType.REPLENISHMENT),
                new Transfer(10L, "2", "---", 1000f, LocalDateTime.of(2022, 3, 19, 12, 0), TransferType.WITHDRAW));
    }

    public static Predicate<Transfer> getBetweenStartDateAndStopDate(LocalDateTime start, LocalDateTime stop) {
        return (transfer) -> transfer.getDateTime().isAfter(start) && transfer.getDateTime().isBefore(stop);
    }

    public static Predicate<Transfer> getBySender(String account) {
        return (transfer) -> transfer.getSenderAccount().equals(account);
    }

    public static Predicate<Transfer> getByRecipient(String account) {
        return (transfer) -> transfer.getRecipientAccount().equals(account);
    }
}

