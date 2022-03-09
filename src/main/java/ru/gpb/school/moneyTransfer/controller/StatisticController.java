package ru.gpb.school.moneyTransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.gpb.school.moneyTransfer.Dto.StatisticTransferDto;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;
import ru.gpb.school.moneyTransfer.repositories.TransferUserRepo;
import ru.gpb.school.moneyTransfer.service.TransferService;

import java.util.List;

@PreAuthorize("hasAuthority('STATISTICAL')")
@RestController
@RequestMapping("/api")
public class StatisticController {
    private TransferRepo transferRepo;
    private TransferService transferService;
    @Autowired
    public StatisticController(TransferRepo transferRepo, TransferService transferService){
        this.transferRepo=transferRepo;
        this.transferService = transferService;
    }

    @GetMapping("/transfers")
    public List<StatisticTransferDto> getStatisticalTransfers(){
        return transferService.createListStatisticTransferDto(transferRepo.findAll());
    }
}
