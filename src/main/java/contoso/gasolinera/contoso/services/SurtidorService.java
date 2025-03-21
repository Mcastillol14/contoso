package contoso.gasolinera.contoso.services;

import contoso.gasolinera.contoso.models.Estacion;
import contoso.gasolinera.contoso.models.enums.EstadoSurtidor;
import contoso.gasolinera.contoso.repositories.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import contoso.gasolinera.contoso.models.Surtidor;
import contoso.gasolinera.contoso.repositories.SurtidorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurtidorService {

   @Autowired
   private final SurtidorRepository surtidorRepository;

   @Autowired
   private final EstacionRepository estacionRepository;

   public List<Surtidor> listadoSurtidores(){
      return surtidorRepository.findAll();
   }

   public Optional<Surtidor> buscaSurtidorPorId(Long id){
      return surtidorRepository.findById(id);
   }

   public List<Surtidor> buscaPorEstacion(Long estacionId){
      Estacion estacion = estacionRepository.findById(estacionId)
              .orElseThrow(() -> new RuntimeException("Estación no encontrada"));
      return surtidorRepository.findByEstacion(estacion);
   }

   @Transactional
   public Surtidor crearSurtidor(Long estacionId, Integer numero){
      if (numero == null || numero <= 0) {
         throw new IllegalArgumentException("El número de surtidor debe ser mayor que cero");
      }

      Estacion estacion = estacionRepository.findById(estacionId)
              .orElseThrow(() -> new RuntimeException("Estación no encontrada"));

      // Verificar si ya existe un surtidor con ese número en la estación
      List<Surtidor> surtidoresExistentes = surtidorRepository.findByEstacion(estacion);
      for (Surtidor s : surtidoresExistentes) {
         if (s.getNumero().equals(numero)) {
            throw new RuntimeException("Ya existe un surtidor con ese número en esta estación");
         }
      }

      Surtidor surtidor = Surtidor.builder()
              .estacion(estacion)
              .numero(numero)
              .estado(EstadoSurtidor.ACTIVO)
              .build();

      return surtidorRepository.save(surtidor);
   }

   @Transactional
   public Surtidor actualizarEstadoSurtidor(Long surtidorId, EstadoSurtidor estado) {
      if (estado == null) {
         throw new IllegalArgumentException("El estado no puede ser nulo");
      }

      Surtidor surtidor = surtidorRepository.findById(surtidorId)
              .orElseThrow(() -> new RuntimeException("Surtidor no encontrado"));

      surtidor.setEstado(estado);
      return surtidorRepository.save(surtidor);
   }

   @Transactional
   public void eliminarSurtidor(Long surtidorId) {
      if (!surtidorRepository.existsById(surtidorId)) {
         throw new RuntimeException("Surtidor no encontrado");
      }
      surtidorRepository.deleteById(surtidorId);
   }
}

