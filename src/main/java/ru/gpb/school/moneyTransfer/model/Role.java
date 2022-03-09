package ru.gpb.school.moneyTransfer.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CREDIT_RETAIL, CREDIT_CORPORATE, INVESTING_SHARES, INVESTING_OBLIGATION, STATISTICAL,ACCOUNT_SERVICE, DEPOSIT;

    @Override
    public String getAuthority() {
        return this.getClass().getName();
    }
}
