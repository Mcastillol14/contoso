package contoso.gasolinera.contoso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import contoso.gasolinera.contoso.models.Servicio;
import contoso.gasolinera.contoso.models.Surtidor;
import contoso.gasolinera.contoso.models.Tanque;
import contoso.gasolinera.contoso.services.ServicioService;
import contoso.gasolinera.contoso.services.SurtidorService;
import contoso.gasolinera.contoso.services.TanqueService;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @Autowired
    private SurtidorService surtidorService;

    @Autowired
    private TanqueService tanqueService;

    @GetMapping("/allServicios")
    public ResponseEntity<List<Servicio>> getAllServicios() {
        return ResponseEntity.ok(servicioService.listadoServicios());
    }

    @GetMapping("/buscarServicio/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        return servicioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/surtidor/{surtidorId}")
    public ResponseEntity<List<Servicio>> getServiciosBySurtidor(@PathVariable Long surtidorId) {
        try {
            Surtidor surtidor = surtidorService.buscaSurtidorPorId(surtidorId)
                    .orElseThrow(() -> new RuntimeException("Surtidor no encontrado"));
            List<Servicio> servicios = servicioService.listadoPorSurtidor(surtidor);
            return ResponseEntity.ok(servicios);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tanque/{tanqueId}")
    public ResponseEntity<List<Servicio>> getServiciosByTanque(@PathVariable Long tanqueId) {
        try {
            Tanque tanque = tanqueService.buscarTanquePorId(tanqueId)
                    .orElseThrow(() -> new RuntimeException("Tanque no encontrado"));
            List<Servicio> servicios = servicioService.listadoServiciosPorTanque(tanque);
            return ResponseEntity.ok(servicios);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addServicio")
    public ResponseEntity<Servicio> registrarServicio(
            @RequestParam Long surtidorId,
            @RequestParam Long tanqueId,
            @RequestParam Double cantidad) {
        try {
            Servicio nuevoServicio = servicioService.crearServicio(surtidorId, tanqueId, cantidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

