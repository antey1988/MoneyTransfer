package ru.gpb.school.moneyTransfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gpb.school.moneyTransfer.model.TransferUser;
import ru.gpb.school.moneyTransfer.repositories.TransferUserRepo;

public class TransferUserDetailService implements UserDetailsService {
    @Autowired
    TransferUserRepo transferUserRepo;
    public TransferUserDetailService(TransferUserRepo transferUserRepo){
        this.transferUserRepo=transferUserRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
        TransferUser user = transferUserRepo.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("такого пользователя нет в базе данных");
        }else {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String password = passwordEncoder.encode(user.getPassword());
            return User.withUsername(user.getUsername())
                    .accountLocked(user.isEnable())
                    .password(password)
                    .authorities(user.getRoles())
                    .build();
        }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        throw new UsernameNotFoundException(username);
    }

}
