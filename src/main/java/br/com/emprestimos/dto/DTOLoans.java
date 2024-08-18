package br.com.emprestimos.dto;

import br.com.emprestimos.loans.Loans;

import java.util.List;
import java.util.stream.Collectors;

public record DTOLoans(String name,
                       String type,
                       Double interestRate) {

    public static List<DTOLoans> loansList(List<Loans> list, String nameEmprestimo) {
        return list.stream()
                .map(loans -> new DTOLoans(nameEmprestimo, loans.getType(), loans.getInterestRate()))
                .collect(Collectors.toList());
    }
}
