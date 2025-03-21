package contoso.gasolinera.contoso.repositories;


import contoso.gasolinera.contoso.models.Surtidor;
import contoso.gasolinera.contoso.models.Tanque;
import org.springframework.data.jpa.repository.JpaRepository;
import contoso.gasolinera.contoso.models.Servicio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

   List<Servicio> findBySurtidor(Surtidor surtidor);
   List<Servicio> findByTanque(Tanque tanque);

}
