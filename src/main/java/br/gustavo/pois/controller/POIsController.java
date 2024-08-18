package br.gustavo.pois.controller;

import br.gustavo.pois.dto.DtoPois;
import br.gustavo.pois.repository.POIsRepository;
import br.gustavo.pois.service.PoisService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pois")
public class POIsController {

    @Autowired
    private PoisService poisService;

    @Autowired
    private POIsRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postPois(@RequestBody @Valid DtoPois dtoPois) {

        poisService.savePois(dtoPois);

        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity GetPois(@PageableDefault(size = 10) Pageable pageable) {

        var allPois = repository.findAll();

        List<DtoPois> dtoPoisList = DtoPois.fromPoisList(allPois);

        return ResponseEntity.ok(dtoPoisList);
    }

    @GetMapping("/{X}/{Y}/{distancia}")
    public ResponseEntity<?> getPoisXY(@PathVariable Integer X, @PathVariable Integer Y, @PathVariable Double distancia) {

        var savePois= poisService.get(X,Y,distancia);

        return ResponseEntity.ok(savePois);
    }
}
