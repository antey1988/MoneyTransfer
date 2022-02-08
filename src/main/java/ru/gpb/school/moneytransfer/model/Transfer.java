package ru.gpb.school.moneytransfer.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer", schema = "public", catalog = "Transfer")
public class Transfer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id", nullable = false, length = -1)
    private String id;
    @Basic
    @Column(name = "sender_score", nullable = true, length = -1)
    private String senderScore;
    @Basic
    @Column(name = "recipient_score", nullable = true, length = -1)
    private String recipientScore;
    @Basic
    @Column(name = "amount_of_money", nullable = true, precision = 0)
    private BigDecimal amountOfMoney;
    @Basic
    @Column(name = "date_of_transfer", nullable = true)
    private Object dateOfTransfer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderScore() {
        return senderScore;
    }

    public void setSenderScore(String senderScore) {
        this.senderScore = senderScore;
    }

    public String getRecipientScore() {
        return recipientScore;
    }

    public void setRecipientScore(String recipientScore) {
        this.recipientScore = recipientScore;
    }

    public BigDecimal getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(BigDecimal amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Object getDateOfTransfer() {
        return dateOfTransfer;
    }

    public void setDateOfTransfer(Object dateOfTransfer) {
        this.dateOfTransfer = dateOfTransfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer that = (Transfer) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
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
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (senderScore != null ? senderScore.hashCode() : 0);
        result = 31 * result + (recipientScore != null ? recipientScore.hashCode() : 0);
        result = 31 * result + (amountOfMoney != null ? amountOfMoney.hashCode() : 0);
        result = 31 * result + (dateOfTransfer != null ? dateOfTransfer.hashCode() : 0);
        return result;
    }
}
