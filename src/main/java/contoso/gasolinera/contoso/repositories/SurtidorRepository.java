package contoso.gasolinera.contoso.repositories;

import contoso.gasolinera.contoso.models.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

import contoso.gasolinera.contoso.models.Surtidor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurtidorRepository extends JpaRepository<Surtidor, Long> {

    List<Surtidor> findByEstacion(Estacion estacion);

}
