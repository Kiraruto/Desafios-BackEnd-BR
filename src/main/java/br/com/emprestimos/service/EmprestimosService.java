package br.com.emprestimos.service;

import br.com.emprestimos.dto.DTOCostumer;
import br.com.emprestimos.dto.DTOEmprestimo;
import br.com.emprestimos.dto.DTOLoans;
import br.com.emprestimos.loans.Loans;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmprestimosService {


    public DTOCostumer save(@Valid DTOEmprestimo dtoEmprestimo) {

        List<Loans> loansList = new ArrayList<>();

        if (dtoEmprestimo.income() <= 3000) {
            loansList.add(new Loans("PERSONAL", 4.0));
            loansList.add(new Loans("GUARANTEED", 3.0));

            var saveDTOLoans = DTOLoans.loansList(loansList, dtoEmprestimo.name());
            return new DTOCostumer(dtoEmprestimo.name(), saveDTOLoans);
        }

        if (dtoEmprestimo.income() > 3000 && dtoEmprestimo.income() < 5000 && dtoEmprestimo.age() < 30 && dtoEmprestimo.locate().equals("SP")) {
            loansList.add(new Loans("PERSONAL", 4.0));
            loansList.add(new Loans("GUARANTEED", 3.0));

            var saveDTOLoans = DTOLoans.loansList(loansList, dtoEmprestimo.name());
            return new DTOCostumer(dtoEmprestimo.name(), saveDTOLoans);
        }

        if (dtoEmprestimo.income() >= 5000) {
            loansList.add(new Loans("CONSIGNMENT", 2.0));

            var saveDTOLoans = DTOLoans.loansList(loansList, dtoEmprestimo.name());
            return new DTOCostumer(dtoEmprestimo.name(), saveDTOLoans);
        }

        var saveDTOLoans = DTOLoans.loansList(loansList, dtoEmprestimo.name());
        return new DTOCostumer(dtoEmprestimo.name(), saveDTOLoans);
    }
}
