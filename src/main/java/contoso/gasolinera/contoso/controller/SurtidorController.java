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

import contoso.gasolinera.contoso.models.Surtidor;
import contoso.gasolinera.contoso.models.enums.EstadoSurtidor;
import contoso.gasolinera.contoso.services.SurtidorService;

@RestController
@RequestMapping("/api/surtidores")
public class SurtidorController {

    @Autowired
    private SurtidorService surtidorService;

    @GetMapping("/allSurtidores")
    public ResponseEntity<List<Surtidor>> getAllSurtidores() {
        return ResponseEntity.ok(surtidorService.listadoSurtidores());
    }

    @GetMapping("/buscarSurtidor/{id}")
    public ResponseEntity<Surtidor> getSurtidorById(@PathVariable Long id) {
        return surtidorService.buscaSurtidorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estacion/{estacionId}")
    public ResponseEntity<List<Surtidor>> getSurtidoresByEstacion(@PathVariable Long estacionId) {
        try {
            List<Surtidor> surtidores = surtidorService.buscaPorEstacion(estacionId);
            return ResponseEntity.ok(surtidores);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addSurtidor")
    public ResponseEntity<Surtidor> crearSurtidor(
            @RequestParam Long estacionId,
            @RequestParam Integer numero) {
        try {
            Surtidor nuevoSurtidor = surtidorService.crearSurtidor(estacionId, numero);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSurtidor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cambiarEstador/{id}")
    public ResponseEntity<Surtidor> actualizarEstadoSurtidor(
            @PathVariable Long id,
            @RequestParam EstadoSurtidor estado) {
        try {
            Surtidor surtidorActualizado = surtidorService.actualizarEstadoSurtidor(id, estado);
            return ResponseEntity.ok(surtidorActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/deleteSurtidor/{id}")
    public ResponseEntity<Void> eliminarSurtidor(@PathVariable Long id) {
        try {
            surtidorService.eliminarSurtidor(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

