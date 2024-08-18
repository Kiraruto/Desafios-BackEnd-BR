package br.com.senhaSegura.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public record DTOSenha(@NotNull String password) {

    public static List<DTOSenha> salvarErros(List<ResponseEntity> responseEntities) {
        return responseEntities.stream()
                .map(responseEntity -> new DTOSenha(responseEntity.toString()))
                .collect(Collectors.toList());
    }
}
