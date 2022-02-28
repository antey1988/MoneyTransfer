package ru.gpb.school.moneyTransfer.exeption_handling;

public class NoSuchTransferException extends RuntimeException{
  public   NoSuchTransferException(String message){
        super(message);
    }
}
