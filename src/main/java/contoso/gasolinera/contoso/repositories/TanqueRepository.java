package contoso.gasolinera.contoso.repositories;

import contoso.gasolinera.contoso.models.Estacion;
import contoso.gasolinera.contoso.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import contoso.gasolinera.contoso.models.Tanque;

import java.util.List;
import java.util.Optional;

public interface TanqueRepository extends JpaRepository<Tanque, Long> {

   List<Tanque> findByEstacion(Estacion estacion);
   Optional<Tanque> findByEstacionAndProducto(Estacion estacion, Producto producto);

}
