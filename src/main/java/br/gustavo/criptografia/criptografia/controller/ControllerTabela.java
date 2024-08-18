package br.gustavo.criptografia.criptografia.controller;

import br.gustavo.criptografia.criptografia.domain.TabelaCriptografia;
import br.gustavo.criptografia.criptografia.dto.DTOTabelaCriptografia;
import br.gustavo.criptografia.criptografia.repository.TabelaRepository;
import br.gustavo.criptografia.criptografia.service.TabelaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tabela")
public class ControllerTabela {

    @Autowired
    private TabelaService tabelaService;

    @Autowired
    private TabelaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity saveTabela(@Valid @RequestBody DTOTabelaCriptografia dtoTabelaCriptografia) {

        tabelaService.Save(dtoTabelaCriptografia);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getTabela(@PathVariable Long id) {

        var dados = repository.findById(id);

        if (!dados.isPresent()) {
            return ResponseEntity.status(404).body("Tabela não encontrada");
        }

        var dadosTabelaGet = tabelaService.get(dados);

        return ResponseEntity.ok(dadosTabelaGet);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTabela(@Valid @RequestBody DTOTabelaCriptografia dtoTabelaCriptografia, @PathVariable Long id) {

        Optional<TabelaCriptografia> tabelaOpcional = repository.findById(id);

        if (!tabelaOpcional.isPresent()) {
            return ResponseEntity.status(404).body("Tabela não encontrada");
        }

        TabelaCriptografia tabela = tabelaOpcional.get();

        var tabelaPut = tabelaService.put(dtoTabelaCriptografia);

        tabela.atualizarInformacoes(tabelaPut);

        return ResponseEntity.ok(tabelaPut);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarPorId(@PathVariable Long id) {
        Optional<TabelaCriptografia> tabelaOpcional = repository.findById(id);

        if (!tabelaOpcional.isPresent()) {
            return ResponseEntity.status(404).body("Tabela não encontrada");
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
