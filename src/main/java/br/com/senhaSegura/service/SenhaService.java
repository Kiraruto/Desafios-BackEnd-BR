package br.com.senhaSegura.service;

import br.com.senhaSegura.DTO.DTOErrosSenhas;
import br.com.senhaSegura.DTO.DTOSenha;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SenhaService {

    @Autowired
    private DTOErrosSenhas errosSenhas;

    public ResponseEntity verificarSenha(@Valid DTOSenha dtoSenha) {

        if (!verificarErros(dtoSenha).isEmpty()) {
            verificarErros(dtoSenha).clear();
        }

        var erros = verificarErros(dtoSenha);

        if (!erros.isEmpty()) {
            List<String> errorMessages = erros.stream()
                    .map(ResponseEntity::getBody)
                    .map(Object::toString)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(dtoSenha);
    }

    private List<ResponseEntity> verificarErros(DTOSenha dtoSenha) {

        var maiscula = verificarMaiscula(dtoSenha);
        if (maiscula.getStatusCode() == HttpStatus.BAD_REQUEST) errosSenhas.erros().add(maiscula);

        var minuscula = verificarMinuscula(dtoSenha);
        if (minuscula.getStatusCode() == HttpStatus.BAD_REQUEST) errosSenhas.erros().add(minuscula);

        var numeros = verificarNumeros(dtoSenha);
        if (numeros.getStatusCode() == HttpStatus.BAD_REQUEST) errosSenhas.erros().add(numeros);

        var caracteres = verificarCaracteresEspeciais(dtoSenha);
        if (caracteres.getStatusCode() == HttpStatus.BAD_REQUEST) errosSenhas.erros().add(caracteres);

        return errosSenhas.erros();
    }

    private ResponseEntity verificarMaiscula(DTOSenha dtosenha) {

        var saveSenha = dtosenha.password();

        int saveAsciiSenha;

        for (int i = 0; i < saveSenha.length(); i++) {

            saveAsciiSenha = saveSenha.charAt(i);

            if (saveAsciiSenha >= 65 && saveAsciiSenha <= 90) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(400).body("Não tem pelo menos 1 letra maiscula");
    }

    private ResponseEntity verificarMinuscula(DTOSenha dtosenha) {

        var saveSenha = dtosenha.password();

        int saveAsciiSenha;

        for (int i = 0; i < saveSenha.length(); i++) {

            saveAsciiSenha = saveSenha.charAt(i);

            if (saveAsciiSenha >= 97 && saveAsciiSenha <= 122) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(400).body("Não tem pelo menos 1 letra minuscula");
    }

    private ResponseEntity verificarNumeros(DTOSenha dtosenha) {

        var saveSenha = dtosenha.password();

        int saveAsciiSenha;

        for (int i = 0; i < saveSenha.length(); i++) {

            saveAsciiSenha = saveSenha.charAt(i);

            if (saveAsciiSenha >= 48 && saveAsciiSenha <= 57) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(400).body("Não tem pelo menos 1 um numero");
    }

    private ResponseEntity verificarCaracteresEspeciais(DTOSenha dtosenha) {

        var saveSenha = dtosenha.password();

        int saveAsciiSenha;

        for (int i = 0; i < saveSenha.length(); i++) {

            saveAsciiSenha = saveSenha.charAt(i);

            if ((saveAsciiSenha >= 33 && saveAsciiSenha <= 47) ||
                    (saveAsciiSenha >= 58 && saveAsciiSenha <= 64) ||
                    (saveAsciiSenha >= 91 && saveAsciiSenha <= 96) ||
                    (saveAsciiSenha >= 123 && saveAsciiSenha <= 126)) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(400).body("Não tem pelo menos 1 caracteres especiais");
    }
}
