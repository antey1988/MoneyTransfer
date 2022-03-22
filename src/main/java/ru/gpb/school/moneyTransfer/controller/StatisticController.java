package ru.gpb.school.moneyTransfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpb.school.moneyTransfer.Dto.StatisticTransferDto;
import ru.gpb.school.moneyTransfer.exeption_handling.NoSuchTransferException;
import ru.gpb.school.moneyTransfer.model.Transfer;
import ru.gpb.school.moneyTransfer.repositories.TransferRepo;
import ru.gpb.school.moneyTransfer.service.TransferService;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/statisticData/transfers")
    public List<StatisticTransferDto> getStatisticalTransfers(){
        return transferService.createListStatisticTransferDto(transferRepo.findAll());
    }
    @GetMapping("/statisticData/transfers/{Id}")
    public StatisticTransferDto getStatisticTransferId(@PathVariable String Id){
        Optional<Transfer>result  = transferRepo.findById(Id);
        if(result.isPresent()){
            throw new NoSuchTransferException("there is no transfer with Id = " + Id);
        }
        return transferService.createStatisticTransferDto(result.get());
    }
    @GetMapping("/statisticData/transfers/accountSender/{account}")
    public List<StatisticTransferDto> getStatisticTransferByAccountSender(@PathVariable Long account){
        List<Transfer> transfers = transferRepo.findAll();
        for(int i=0;i<transfers.size();i++){
            if(transfers.get(i).getSenderAccount()!=account){
                transfers.remove(i);
            }
        }
        return transferService.createListStatisticTransferDto(transfers);
    }
    @GetMapping("/statisticData/transfers/accountRecipient/{account}")
    public List<StatisticTransferDto> getStatisticTransferByAccountRecipient(@PathVariable Long account){
        List<Transfer> transfers = transferRepo.findAll();
        for(int i=0;i<transfers.size();i++){
            if(transfers.get(i).getRecipientAccount()!=account){
                transfers.remove(i);
            }
        }
        return transferService.createListStatisticTransferDto(transfers);
    }
}
