package br.com.emprestimos.dto;

import java.util.List;

public record DTOCostumer(String name,
                          List<DTOLoans> loansList) {

}
