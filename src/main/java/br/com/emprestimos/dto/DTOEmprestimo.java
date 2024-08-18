package br.com.emprestimos.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DTOEmprestimo(@NotNull Integer age,
                            @NotNull String name,
                            @NotNull String cpf,
                            @NotNull Long income,
                            @NotNull @JsonAlias("location") String locate) {
}
