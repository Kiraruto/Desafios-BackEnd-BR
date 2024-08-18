package br.gustavo.pois.domain;

import br.gustavo.pois.dto.DtoPois;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "pois")
@Table(name = "pois")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class POIs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String local;
    private Integer X;
    private Integer Y;

    public POIs(DtoPois dtoPois) {
        this.local = dtoPois.local();
        this.X = dtoPois.X();
        this.Y = dtoPois.Y();
    }
}
