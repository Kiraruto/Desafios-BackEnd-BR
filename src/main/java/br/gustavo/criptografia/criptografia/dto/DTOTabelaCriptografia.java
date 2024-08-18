package br.gustavo.criptografia.criptografia.dto;

import jakarta.validation.constraints.NotNull;

public record DTOTabelaCriptografia(@NotNull String userDocument,
                                    @NotNull String creditCardToken,
                                    @NotNull Long value) {


    public DTOTabelaCriptografia withUserDocument(String hashDocument) {
        return new DTOTabelaCriptografia(hashDocument, this.creditCardToken, this.value);
    }

    public DTOTabelaCriptografia withCreditCardToken(String hashCredit) {
        return new DTOTabelaCriptografia(this.userDocument, hashCredit, this.value);
    }
}
