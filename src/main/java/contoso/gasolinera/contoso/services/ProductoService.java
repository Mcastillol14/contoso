package contoso.gasolinera.contoso.services;

import contoso.gasolinera.contoso.models.Producto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contoso.gasolinera.contoso.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

   @Autowired
   private final ProductoRepository productoRepository;

   public List<Producto> listadoProductos() {
      return productoRepository.findAll();
   }

   public Optional<Producto> buscarProductoPorId(Long id) {
      return productoRepository.findById(id);
   }

   public Optional<Producto> buscarPorCombustible(String tipoCombustible) {
      return productoRepository.findByTipoCombustible(tipoCombustible);
   }

   @Transactional
   public Producto crearProducto(Producto producto) {
      if (producto.getTipoCombustible() == null || producto.getTipoCombustible().isEmpty()) {
         throw new IllegalArgumentException("El tipo de combustible no puede ser nulo");
      }
      if(producto.getPrecio()==null || producto.getPrecio() <= 0) {
         throw new IllegalArgumentException("El precio no puede ser nulo o menor o igual a cero");
      }

      // Verificar si ya existe un producto con ese tipo de combustible
      Optional<Producto> productoExistente = productoRepository.findByTipoCombustible(producto.getTipoCombustible());
      if (productoExistente.isPresent()) {
         throw new RuntimeException("Ya existe un producto con ese tipo de combustible");
      }

      return productoRepository.save(producto);
   }

   @Transactional
   public Producto actualizarPrecio(Long productoId, Double nuevoPrecio) {
      if (nuevoPrecio == null || nuevoPrecio <= 0) {
         throw new IllegalArgumentException("El precio debe ser mayor que cero");
      }

      Producto producto = productoRepository.findById(productoId)
              .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

      producto.setPrecio(nuevoPrecio);
      return productoRepository.save(producto);
   }

   @Transactional
   public void eliminarProducto(Long productoId) {
      if (!productoRepository.existsById(productoId)) {
         throw new RuntimeException("Producto no encontrado");
      }
      productoRepository.deleteById(productoId);
   }
}

