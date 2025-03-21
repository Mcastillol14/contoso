package contoso.gasolinera.contoso.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import contoso.gasolinera.contoso.models.enums.EstadoSurtidor;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "surtidores")
public class Surtidor {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "estacion_id", nullable = false)
   @JsonBackReference
   private Estacion estacion;

   private Integer numero;

   @Enumerated(EnumType.STRING)
   private EstadoSurtidor estado;
}
