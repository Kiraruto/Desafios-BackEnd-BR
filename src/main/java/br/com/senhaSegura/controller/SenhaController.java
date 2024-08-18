package br.com.senhaSegura.controller;

import br.com.senhaSegura.DTO.DTOSenha;
import br.com.senhaSegura.service.SenhaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate-password")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    @PostMapping
    public ResponseEntity<?> postVerificarSenha(@RequestBody @Valid DTOSenha dtosenha) {

        if (dtosenha.password().length() >= 8) {

            return senhaService.verificarSenha(dtosenha);

        }

        return ResponseEntity.status(401).body("Senha deve ter pelo menos 8 caracteres");
    }
}
