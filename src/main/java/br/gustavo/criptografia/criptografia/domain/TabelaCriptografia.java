package br.gustavo.criptografia.criptografia.domain;

import br.gustavo.criptografia.criptografia.dto.DTOTabelaCriptografia;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "crip")
@Table(name = "crip")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TabelaCriptografia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long value;

    public TabelaCriptografia(DTOTabelaCriptografia dtoTabelaCriptografia) {
        this.userDocument = dtoTabelaCriptografia.userDocument();
        this.creditCardToken = dtoTabelaCriptografia.creditCardToken();
        this.value = dtoTabelaCriptografia.value();
    }

    public void atualizarInformacoes(@Valid DTOTabelaCriptografia dados) {
        if (dados.userDocument() != null) {
            this.userDocument = dados.userDocument();
        }

        if (dados.creditCardToken() != null) {
            this.creditCardToken = dados.creditCardToken();
        }

        if (dados.value() != null) {
            this.value = dados.value();
        }
    }

}
