package br.gustavo.pois.service;

import br.gustavo.pois.domain.POIs;
import br.gustavo.pois.dto.DtoPois;
import br.gustavo.pois.repository.POIsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoisService {

    @Autowired
    private POIsRepository repository;

    @Transactional
    public void savePois(DtoPois dtoPois) {

        if (dtoPois.X() > 0 && dtoPois.Y() > 0) {
            repository.save(new POIs(dtoPois));
        }
    }

    public List<DtoPois> get(Integer x, Integer y, Double distacia) {

        var savePoisId = repository.findPoisWithinDistance(x,y,distacia);

        return DtoPois.fromPoisListIdOff(savePoisId);
    }
}
