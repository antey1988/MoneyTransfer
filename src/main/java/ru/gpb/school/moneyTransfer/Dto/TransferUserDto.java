package ru.gpb.school.moneyTransfer.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferUserDto {
    private String userName;
    private String password;
    private String description;
}
