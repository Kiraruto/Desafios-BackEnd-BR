package br.com.emprestimos.controller;

import br.com.emprestimos.dto.DTOEmprestimo;
import br.com.emprestimos.service.EmprestimosService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer-loans")
public class EmprestimosController {

    @Autowired
    private EmprestimosService emprestimosService;

    @PostMapping
    @Transactional
    public ResponseEntity postEmprestimo(@RequestBody @Valid DTOEmprestimo dtoEmprestimo) {

        var saveEmprestimos = emprestimosService.save(dtoEmprestimo);

        return ResponseEntity.ok(saveEmprestimos);
    }
}
