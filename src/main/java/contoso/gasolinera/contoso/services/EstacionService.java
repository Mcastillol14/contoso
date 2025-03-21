package contoso.gasolinera.contoso.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contoso.gasolinera.contoso.models.Estacion;
import contoso.gasolinera.contoso.repositories.EstacionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstacionService {

    @Autowired
    private final EstacionRepository estacionRepository;

    public List<Estacion> listadoEstaciones(){
       return estacionRepository.findAll();
    }

    public Optional<Estacion> buscarEstacionPorId(Long id){
       return estacionRepository.findById(id);
    }

    @Transactional
   public Estacion createEstacion(Estacion estacion){

       if(estacion.getNombre()==null || estacion.getNombre().isEmpty()){
          throw new IllegalArgumentException("El nombre no puede ser vacio");
       }
       if(estacion.getDireccion()==null || estacion.getDireccion().isEmpty()){
          throw new IllegalArgumentException("la direccion no puede ser vacia");
       }
       return estacionRepository.save(estacion);
    }


}
