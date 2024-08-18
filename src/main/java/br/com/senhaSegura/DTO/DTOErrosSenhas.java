package br.com.senhaSegura.DTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record DTOErrosSenhas(List<ResponseEntity> erros) {
}
