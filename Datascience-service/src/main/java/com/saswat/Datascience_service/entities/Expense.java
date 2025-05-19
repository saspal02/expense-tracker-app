package com.saswat.Datascience_service.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Expense {
    private String amount;
    private String merchant;
    private String currency;
    private String userId;
}
