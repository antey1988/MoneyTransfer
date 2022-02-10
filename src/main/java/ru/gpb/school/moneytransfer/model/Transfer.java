package ru.gpb.school.moneytransfer.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer", schema = "public", catalog = "Transfer")
public class Transfer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false)
    private int id;
    @Basic
    @Column(name = "sender_score", nullable = true, precision = 0)
    private Long senderScore;
    @Basic
    @Column(name = "recipient_score", nullable = true, precision = 0)
    private Long recipientScore;
    @Basic
    @Column(name = "amount_of_money", nullable = true, precision = 0)
    private Float amountOfMoney;
    @Basic
    @Column(name = "date_of_transfer", nullable = true)
    private LocalDateTime dateOfTransfer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getSenderScore() {
        return senderScore;
    }

    public void setSenderScore(Long senderScore) {
        this.senderScore = senderScore;
    }

    public Long getRecipientScore() {
        return recipientScore;
    }

    public void setRecipientScore(Long recipientScore) {
        this.recipientScore = recipientScore;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer that = (Transfer) o;

        if (id != that.id) return false;
        if (senderScore != null ? !senderScore.equals(that.senderScore) : that.senderScore != null) return false;
        if (recipientScore != null ? !recipientScore.equals(that.recipientScore) : that.recipientScore != null)
            return false;
        if (amountOfMoney != null ? !amountOfMoney.equals(that.amountOfMoney) : that.amountOfMoney != null)
            return false;
        if (dateOfTransfer != null ? !dateOfTransfer.equals(that.dateOfTransfer) : that.dateOfTransfer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (senderScore != null ? senderScore.hashCode() : 0);
        result = 31 * result + (recipientScore != null ? recipientScore.hashCode() : 0);
        result = 31 * result + (amountOfMoney != null ? amountOfMoney.hashCode() : 0);
        result = 31 * result + (dateOfTransfer != null ? dateOfTransfer.hashCode() : 0);
        return result;
    }
}
