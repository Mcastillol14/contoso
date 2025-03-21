package contoso.gasolinera.contoso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import contoso.gasolinera.contoso.models.Estacion;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {

}
