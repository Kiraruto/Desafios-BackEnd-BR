package br.gustavo.pois.repository;

import br.gustavo.pois.domain.POIs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface POIsRepository extends JpaRepository<POIs, Long> {

    @Query("SELECT p FROM pois p WHERE ( (p.X - :xRef) * (p.X - :xRef) + (p.Y - :yRef) * (p.Y - :yRef) ) <= (:maxDistance * :maxDistance)")
    List<POIs> findPoisWithinDistance(@Param("xRef") Integer xRef,
                                     @Param("yRef") Integer yRef,
                                     @Param("maxDistance") Double maxDistance);
}

