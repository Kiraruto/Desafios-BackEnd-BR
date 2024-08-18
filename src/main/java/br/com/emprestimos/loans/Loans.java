package br.com.emprestimos.loans;

import lombok.Getter;

@Getter
public class Loans {

    private String type;
    private Double interestRate;

    public Loans(String type, Double interestRate) {
        this.type = type;
        this.interestRate = interestRate;
    }

}
