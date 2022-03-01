package ru.gpb.school.moneyTransfer.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transfer", schema = "public", catalog = "Transfer")
public class Transfer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "sender_account", nullable = true, precision = 0)
    private Long senderAccount;
    @Basic
    @Column(name = "recipient_account", nullable = true, precision = 0)
    private Long recipientAccount;
    @Basic
    @Column(name = "amount_of_money", nullable = true, precision = 0)
    private Float amountOfMoney;
    @Basic
    @Column(name = "date_of_transfer", nullable = true)
    private LocalDateTime dateOfTransfer;
    @Basic
    @Column(name = "type_of_transfer", nullable = true, length = -1)
    private String typeOfTransfer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer that = (Transfer) o;

        if (id != that.id) return false;
        if (!Objects.equals(senderAccount, that.senderAccount)) return false;
        if (!Objects.equals(recipientAccount, that.recipientAccount))
            return false;
        if (!Objects.equals(amountOfMoney, that.amountOfMoney))
            return false;
        if (!Objects.equals(dateOfTransfer, that.dateOfTransfer))
            return false;
        if (!Objects.equals(typeOfTransfer, that.typeOfTransfer))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (senderAccount != null ? senderAccount.hashCode() : 0);
        result = 31 * result + (recipientAccount != null ? recipientAccount.hashCode() : 0);
        result = 31 * result + (amountOfMoney != null ? amountOfMoney.hashCode() : 0);
        result = 31 * result + (dateOfTransfer != null ? dateOfTransfer.hashCode() : 0);
        result = 31 * result + (typeOfTransfer != null ? typeOfTransfer.hashCode() : 0);
        return result;
    }

    @PrePersist
    void onCreate(){
        dateOfTransfer = LocalDateTime.now();
    }
}
