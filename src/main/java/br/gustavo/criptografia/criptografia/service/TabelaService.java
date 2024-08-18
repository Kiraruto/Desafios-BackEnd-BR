package br.gustavo.criptografia.criptografia.service;

import br.gustavo.criptografia.criptografia.criptografia.CriptografarTabela;
import br.gustavo.criptografia.criptografia.domain.TabelaCriptografia;
import br.gustavo.criptografia.criptografia.dto.DTOTabelaCriptografia;
import br.gustavo.criptografia.criptografia.repository.TabelaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class TabelaService {

    @Autowired
    private TabelaRepository tabelaRepository;

    @Autowired
    private CriptografarTabela criptografarTabela;

    private SecretKey chave;

    @Transactional
    public void Save(DTOTabelaCriptografia dtoTabelaCriptografia) {

        try {
            if (dtoTabelaCriptografia.userDocument() != null) {
                var hashDocument = criptografarTabela.criptografar(dtoTabelaCriptografia.userDocument(), criarChave());
                dtoTabelaCriptografia = dtoTabelaCriptografia.withUserDocument(hashDocument);
            }

            if (dtoTabelaCriptografia.creditCardToken() != null) {
                var hashCredit = criptografarTabela.criptografar(dtoTabelaCriptografia.creditCardToken(), criarChave());
                dtoTabelaCriptografia = dtoTabelaCriptografia.withCreditCardToken(hashCredit);
            }

            tabelaRepository.save(new TabelaCriptografia(dtoTabelaCriptografia));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public DTOTabelaCriptografia get(Optional<TabelaCriptografia> dados) {

        try {
            var userDocumentDescriptografado = criptografarTabela.descriptografar(dados.get().getUserDocument(), criarChave());
            var creditCardTokenDescriptografado = criptografarTabela.descriptografar(dados.get().getCreditCardToken(), criarChave());

            return new DTOTabelaCriptografia(userDocumentDescriptografado, creditCardTokenDescriptografado, dados.get().getValue());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public DTOTabelaCriptografia put(DTOTabelaCriptografia dtoTabelaCriptografia) {

        try {
            var userDocumentDescriptografado = criptografarTabela.criptografar(dtoTabelaCriptografia.userDocument(), criarChave());
            var creditCardTokenDescriptografado = criptografarTabela.criptografar(dtoTabelaCriptografia.creditCardToken(), criarChave());

            var atualizarPut = new DTOTabelaCriptografia(userDocumentDescriptografado, creditCardTokenDescriptografado, dtoTabelaCriptografia.value());

            return atualizarPut;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey criarChave() {

        if (this.chave == null) {
            this.chave = criptografarTabela.gerarChave();
        }
        return this.chave;
    }
}
