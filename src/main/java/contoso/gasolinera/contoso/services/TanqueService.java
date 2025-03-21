package contoso.gasolinera.contoso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import contoso.gasolinera.contoso.models.Estacion;
import contoso.gasolinera.contoso.models.Producto;
import contoso.gasolinera.contoso.models.Tanque;
import contoso.gasolinera.contoso.repositories.EstacionRepository;
import contoso.gasolinera.contoso.repositories.ProductoRepository;
import contoso.gasolinera.contoso.repositories.TanqueRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TanqueService {

   @Autowired
   private final TanqueRepository tanqueRepository;

   @Autowired
   private final EstacionRepository estacionRepository;

   @Autowired
   private final ProductoRepository productoRepository;

   public List<Tanque> listadoTanques() {
      return tanqueRepository.findAll();
   }

   public Optional<Tanque> buscarTanquePorId(Long id) {
      return tanqueRepository.findById(id);
   }

   public List<Tanque> buscarPorEstacion(Estacion estacion) {
      return tanqueRepository.findByEstacion(estacion);
   }

   @Transactional
   public Tanque crearTanque(Long estacionId, Long productoId, Integer capacidad) {
      if (capacidad == null || capacidad <= 0) {
         throw new IllegalArgumentException("La capacidad debe ser mayor que cero");
      }

      Estacion estacion = estacionRepository.findById(estacionId)
              .orElseThrow(() -> new RuntimeException("Estación no encontrada"));

      Producto producto = productoRepository.findById(productoId)
              .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

      // Verificar si ya existe un tanque para este producto en esta estación
      Optional<Tanque> tanqueExistente = tanqueRepository.findByEstacionAndProducto(estacion, producto);
      if (tanqueExistente.isPresent()) {
         throw new RuntimeException("Ya existe un tanque para este producto en esta estación");
      }

      Tanque tanque = Tanque.builder()
              .estacion(estacion)
              .producto(producto)
              .capacidad(capacidad)
              .cantidad(0.0) // Inicialmente vacío
              .build();

      return tanqueRepository.save(tanque);
   }

   @Transactional
   public Tanque recargarTanque(Long tanqueId, Double cantidad) {
      if (cantidad == null || cantidad <= 0) {
         throw new IllegalArgumentException("La cantidad a recargar debe ser mayor que cero");
      }

      Tanque tanque = tanqueRepository.findById(tanqueId)
              .orElseThrow(() -> new RuntimeException("Tanque no encontrado"));

      Double nuevaCantidad = tanque.getCantidad() + cantidad;

      if (nuevaCantidad > tanque.getCapacidad()) {
         throw new RuntimeException("La cantidad excede la capacidad del tanque");
      }

      tanque.setCantidad(nuevaCantidad);
      return tanqueRepository.save(tanque);
   }

   @Transactional
   public Tanque consumirCombustible(Long tanqueId, Double cantidad) {
      if (cantidad == null || cantidad <= 0) {
         throw new IllegalArgumentException("La cantidad a consumir debe ser mayor que cero");
      }

      Tanque tanque = tanqueRepository.findById(tanqueId)
              .orElseThrow(() -> new RuntimeException("Tanque no encontrado"));

      if (tanque.getCantidad() < cantidad) {
         throw new RuntimeException("No hay suficiente combustible en el tanque");
      }

      tanque.setCantidad(tanque.getCantidad() - cantidad);
      return tanqueRepository.save(tanque);
   }

   public Double obtenerDisponibilidad(Long tanqueId) {
      Tanque tanque = tanqueRepository.findById(tanqueId)
              .orElseThrow(() -> new RuntimeException("Tanque no encontrado"));

      return tanque.getCantidad();
   }

   @Transactional
   public void eliminarTanque(Long tanqueId) {
      if (!tanqueRepository.existsById(tanqueId)) {
         throw new RuntimeException("Tanque no encontrado");
      }
      tanqueRepository.deleteById(tanqueId);
   }
}

