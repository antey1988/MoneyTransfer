package ru.gpb.school.moneyTransfer.exeption_handling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferIncorrectData {
    private String info;

    public String getInfo() {
        return info;
    }

    public  void setInfo(String info) {
        this.info = info;
    }
}
