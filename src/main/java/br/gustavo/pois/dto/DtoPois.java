package br.gustavo.pois.dto;

import br.gustavo.pois.domain.POIs;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record DtoPois(@NotNull String local,
                      @NotNull Integer X,
                      @NotNull Integer Y) {

    public static List<DtoPois> fromPoisListIdOff(List<POIs> savePoisId) {
        return savePoisId.stream()
                .map(poIs -> new DtoPois(poIs.getLocal(), poIs.getX(), poIs.getY()))
                .collect(Collectors.toList());
    }

    public static List<DtoPois> fromPoisList(List<POIs> allPois) {
        return allPois.stream()
                .map(poIs -> new DtoPois(poIs.getLocal(), poIs.getX(), poIs.getY()))
                .collect(Collectors.toList());
    }
}
