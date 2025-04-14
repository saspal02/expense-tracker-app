package com.saswat.Datascience_service.entities;

import lombok.Data;

@Data
public class Expense {
    private String amount;
    private String merchant;
    private String currency;
}
