package ru.gpb.school.moneyTransfer.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
@Builder
public class Transfer {
    @javax.persistence.Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private int Id;
    private Long senderAccount;
    private Long recipientAccount;
    private Float amountOfMoney;
    private LocalDateTime dateOfTransfer;
    private String typeOfTransfer;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Long getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Long senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Long getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(Long recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public Float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public LocalDateTime getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(LocalDateTime dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    public String getTypeOfTransfer() {
        return typeOfTransfer;
    }

    public void setTypeOfTransfer(String typeOfTransfer) {
        this.typeOfTransfer = typeOfTransfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer that = (Transfer) o;

        if (Id != that.Id) return false;
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
        int result = Id;
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
