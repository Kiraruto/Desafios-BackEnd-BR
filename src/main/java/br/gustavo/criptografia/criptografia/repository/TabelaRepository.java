package br.gustavo.criptografia.criptografia.repository;

import br.gustavo.criptografia.criptografia.domain.TabelaCriptografia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TabelaRepository extends JpaRepository<TabelaCriptografia, Long> {

    boolean existsByUserDocument(String userDocument);

    boolean existsByCreditCardToken(String creditCardToken);
}
