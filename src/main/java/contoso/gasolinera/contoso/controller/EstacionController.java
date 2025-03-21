package contoso.gasolinera.contoso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import contoso.gasolinera.contoso.models.Estacion;
import contoso.gasolinera.contoso.services.EstacionService;

@RestController
@RequestMapping("/api/estaciones")
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @GetMapping("/allEstaciones")
    public ResponseEntity<List<Estacion>> getAllEstaciones() {
        return ResponseEntity.ok(estacionService.listadoEstaciones());
    }

    @GetMapping("/buscarEstacion/{id}")
    public ResponseEntity<Estacion> getEstacionById(@PathVariable Long id) {
        return estacionService.buscarEstacionPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addEstacion")
    public ResponseEntity<Estacion> crearEstacion(@RequestBody Estacion estacion) {
        try {
            Estacion nuevaEstacion = estacionService.createEstacion(estacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEstacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

