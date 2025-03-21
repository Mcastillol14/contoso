package contoso.gasolinera.contoso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import contoso.gasolinera.contoso.models.Estacion;
import contoso.gasolinera.contoso.models.Tanque;
import contoso.gasolinera.contoso.services.EstacionService;
import contoso.gasolinera.contoso.services.TanqueService;

@RestController
@RequestMapping("/api/tanques")
public class TanqueController {

    @Autowired
    private TanqueService tanqueService;

    @Autowired
    private EstacionService estacionService;

    @GetMapping("/allTanques")
    public ResponseEntity<List<Tanque>> getAllTanques() {
        return ResponseEntity.ok(tanqueService.listadoTanques());
    }

    @GetMapping("/buscarTanque/{id}")
    public ResponseEntity<Tanque> getTanqueById(@PathVariable Long id) {
        return tanqueService.buscarTanquePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estacion/{estacionId}")
    public ResponseEntity<List<Tanque>> getTanquesByEstacion(@PathVariable Long estacionId) {
        try {
            Estacion estacion = estacionService.buscarEstacionPorId(estacionId)
                    .orElseThrow(() -> new RuntimeException("Estación no encontrada"));
            List<Tanque> tanques = tanqueService.buscarPorEstacion(estacion);
            return ResponseEntity.ok(tanques);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/disponibilidad")
    public ResponseEntity<Double> getDisponibilidad(@PathVariable Long id) {
        try {
            Double disponibilidad = tanqueService.obtenerDisponibilidad(id);
            return ResponseEntity.ok(disponibilidad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addTanque")
    public ResponseEntity<Tanque> crearTanque(
            @RequestParam Long estacionId,
            @RequestParam Long productoId,
            @RequestParam Integer capacidad) {
        try {
            Tanque nuevoTanque = tanqueService.crearTanque(estacionId, productoId, capacidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTanque);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/recargar/{id}")
    public ResponseEntity<Tanque> recargarTanque(
            @PathVariable Long id,
            @RequestParam Double cantidad) {
        try {
            Tanque tanqueRecargado = tanqueService.recargarTanque(id, cantidad);
            return ResponseEntity.ok(tanqueRecargado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteTanque/{id}")
    public ResponseEntity<Void> eliminarTanque(@PathVariable Long id) {
        try {
            tanqueService.eliminarTanque(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
