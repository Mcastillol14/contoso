package contoso.gasolinera.contoso.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import contoso.gasolinera.contoso.models.Surtidor;
import contoso.gasolinera.contoso.models.enums.EstadoSurtidor;
import contoso.gasolinera.contoso.repositories.SurtidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import contoso.gasolinera.contoso.models.Producto;
import contoso.gasolinera.contoso.models.Servicio;
import contoso.gasolinera.contoso.models.Tanque;
import contoso.gasolinera.contoso.repositories.ProductoRepository;
import contoso.gasolinera.contoso.repositories.ServicioRepository;
import contoso.gasolinera.contoso.repositories.TanqueRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioService {

   @Autowired
   private final ServicioRepository servicioRepository;
   @Autowired
   private final TanqueRepository tanqueRepository;
   @Autowired
   private final SurtidorRepository surtidorRepository;
   @Autowired
   private final TanqueService tanqueService;

   public List<Servicio> listadoServicios() {
      return servicioRepository.findAll();
   }

   public Optional<Servicio> findById(Long id) {
      return servicioRepository.findById(id);
   }

   public List<Servicio> listadoServiciosPorTanque(Tanque tanque) {
      return servicioRepository.findByTanque(tanque);
   }

   public List<Servicio> listadoPorSurtidor(Surtidor surtidor) {
      return servicioRepository.findBySurtidor(surtidor);
   }

   @Transactional
   public Servicio crearServicio(Long surtidorId, long tanqueId, double cantidad) {
      if (cantidad <= 0) {
         throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
      }

      Surtidor surtidor = surtidorRepository.findById(surtidorId).orElseThrow(() -> new RuntimeException("El surtidor no existe"));
      if (surtidor.getEstado() != EstadoSurtidor.ACTIVO) {
         throw new RuntimeException("El surtidor no está activo");
      }

      surtidor.setEstado(EstadoSurtidor.EN_USO);
      surtidorRepository.save(surtidor);

      try {
         Tanque tanque = tanqueRepository.findById(tanqueId)
                 .orElseThrow(() -> new RuntimeException("Tanque no encontrado"));
         tanqueService.consumirCombustible(tanqueId, cantidad);

         Double precioTotal = cantidad * tanque.getProducto().getPrecio();
         Servicio servicio = Servicio.builder()
                 .surtidor(surtidor)
                 .tanque(tanque)
                 .fecha(LocalDateTime.now())
                 .cantidad(cantidad)
                 .precio(precioTotal)
                 .build();

         Servicio servicioGuardado = servicioRepository.save(servicio);

         surtidor.setEstado(EstadoSurtidor.ACTIVO);
         surtidorRepository.save(surtidor);
         return servicioGuardado;
      } catch (Exception e){
         surtidor.setEstado(EstadoSurtidor.ACTIVO);
         surtidorRepository.save(surtidor);
         throw e;
      }
   }
}

