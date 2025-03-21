package contoso.gasolinera.contoso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import contoso.gasolinera.contoso.models.Producto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByTipoCombustible(String tipoCombustible);

}