Guía para probar los endpoints en Postman con base URL: [https://contoso-czj1.onrender.com](https://contoso-czj1.onrender.com)

Aquí tienes un listado completo de todos los endpoints disponibles en la API, organizados por controlador, con ejemplos de cómo probarlos en Postman.

## EstacionController

### 1. Obtener todas las estaciones

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/estaciones/allEstaciones](https://contoso-czj1.onrender.com/api/estaciones/allEstaciones)
- **Headers**: Content-Type: application/json


### 2. Buscar estación por ID

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/estaciones/buscarEstacion/{id}](https://contoso-czj1.onrender.com/api/estaciones/buscarEstacion/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/estaciones/buscarEstacion/1](https://contoso-czj1.onrender.com/api/estaciones/buscarEstacion/1)


### 3. Crear nueva estación

- **Método**: POST
- **URL**: [https://contoso-czj1.onrender.com/api/estaciones/addEstacion](https://contoso-czj1.onrender.com/api/estaciones/addEstacion)
- **Headers**: Content-Type: application/json
- **Body** (raw JSON):


```json
{
  "nombre": "Estación Central",
  "direccion": "Av. Principal 123",
  "ciudad": "Madrid"
}
```

## TanqueController

### 1. Obtener todos los tanques

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/allTanques](https://contoso-czj1.onrender.com/api/tanques/allTanques)
- **Headers**: Content-Type: application/json


### 2. Buscar tanque por ID

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/buscarTanque/{id}](https://contoso-czj1.onrender.com/api/tanques/buscarTanque/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/tanques/buscarTanque/1](https://contoso-czj1.onrender.com/api/tanques/buscarTanque/1)


### 3. Obtener tanques por estación

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/estacion/{estacionId}](https://contoso-czj1.onrender.com/api/tanques/estacion/{estacionId})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/tanques/estacion/1](https://contoso-czj1.onrender.com/api/tanques/estacion/1)


### 4. Obtener disponibilidad de un tanque

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/{id}/disponibilidad](https://contoso-czj1.onrender.com/api/tanques/{id}/disponibilidad)
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/tanques/1/disponibilidad](https://contoso-czj1.onrender.com/api/tanques/1/disponibilidad)


### 5. Crear nuevo tanque

- **Método**: POST
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/addTanque](https://contoso-czj1.onrender.com/api/tanques/addTanque)
- **Headers**: Content-Type: application/x-www-form-urlencoded
- **Params**:

- estacionId: 1
- productoId: 1
- capacidad: 10000





### 6. Recargar tanque

- **Método**: PUT
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/recargar/{id}](https://contoso-czj1.onrender.com/api/tanques/recargar/{id})
- **Headers**: Content-Type: application/x-www-form-urlencoded
- **Params**:

- cantidad: 500



- **Ejemplo**: [https://contoso-czj1.onrender.com/api/tanques/recargar/1?cantidad=500](https://contoso-czj1.onrender.com/api/tanques/recargar/1?cantidad=500)


### 7. Eliminar tanque

- **Método**: DELETE
- **URL**: [https://contoso-czj1.onrender.com/api/tanques/deleteTanque/{id}](https://contoso-czj1.onrender.com/api/tanques/deleteTanque/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/tanques/deleteTanque/1](https://contoso-czj1.onrender.com/api/tanques/deleteTanque/1)


## ServicioController

### 1. Obtener todos los servicios

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/servicios/allServicios](https://contoso-czj1.onrender.com/api/servicios/allServicios)
- **Headers**: Content-Type: application/json


### 2. Buscar servicio por ID

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/servicios/buscarServicio/{id}](https://contoso-czj1.onrender.com/api/servicios/buscarServicio/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/servicios/buscarServicio/1](https://contoso-czj1.onrender.com/api/servicios/buscarServicio/1)


### 3. Obtener servicios por surtidor

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/servicios/surtidor/{surtidorId}](https://contoso-czj1.onrender.com/api/servicios/surtidor/{surtidorId})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/servicios/surtidor/1](https://contoso-czj1.onrender.com/api/servicios/surtidor/1)


### 4. Obtener servicios por tanque

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/servicios/tanque/{tanqueId}](https://contoso-czj1.onrender.com/api/servicios/tanque/{tanqueId})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/servicios/tanque/1](https://contoso-czj1.onrender.com/api/servicios/tanque/1)


### 5. Registrar nuevo servicio

- **Método**: POST
- **URL**: [https://contoso-czj1.onrender.com/api/servicios/addServicio](https://contoso-czj1.onrender.com/api/servicios/addServicio)
- **Headers**: Content-Type: application/x-www-form-urlencoded
- **Params**:

- surtidorId: 1
- tanqueId: 1
- cantidad: 50





## SurtidorController

### 1. Obtener todos los surtidores

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/surtidores/allSurtidores](https://contoso-czj1.onrender.com/api/surtidores/allSurtidores)
- **Headers**: Content-Type: application/json


### 2. Buscar surtidor por ID

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/surtidores/buscarSurtidor/{id}](https://contoso-czj1.onrender.com/api/surtidores/buscarSurtidor/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/surtidores/buscarSurtidor/1](https://contoso-czj1.onrender.com/api/surtidores/buscarSurtidor/1)


### 3. Obtener surtidores por estación

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/surtidores/estacion/{estacionId}](https://contoso-czj1.onrender.com/api/surtidores/estacion/{estacionId})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/surtidores/estacion/1](https://contoso-czj1.onrender.com/api/surtidores/estacion/1)


### 4. Crear nuevo surtidor

- **Método**: POST
- **URL**: [https://contoso-czj1.onrender.com/api/surtidores/addSurtidor](https://contoso-czj1.onrender.com/api/surtidores/addSurtidor)
- **Headers**: Content-Type: application/x-www-form-urlencoded
- **Params**:

- estacionId: 1
- numero: 2





### 5. Cambiar estado de surtidor

- **Método**: PUT
- **URL**: [https://contoso-czj1.onrender.com/api/surtidores/cambiarEstador/{id}](https://contoso-czj1.onrender.com/api/surtidores/cambiarEstador/{id})
- **Headers**: Content-Type: application/x-www-form-urlencoded
- **Params**:

- estado: DISPONIBLE (valores posibles: DISPONIBLE, OCUPADO, MANTENIMIENTO)



- **Ejemplo**: [https://contoso-czj1.onrender.com/api/surtidores/cambiarEstador/1?estado=DISPONIBLE](https://contoso-czj1.onrender.com/api/surtidores/cambiarEstador/1?estado=DISPONIBLE)


### 6. Eliminar surtidor

- **Método**: DELETE
- **URL**: [https://contoso-czj1.onrender.com/api/surtidores/deleteSurtidor/{id}](https://contoso-czj1.onrender.com/api/surtidores/deleteSurtidor/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/surtidores/deleteSurtidor/1](https://contoso-czj1.onrender.com/api/surtidores/deleteSurtidor/1)


## ProductoController

### 1. Obtener todos los productos

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/productos/allProductos](https://contoso-czj1.onrender.com/api/productos/allProductos)
- **Headers**: Content-Type: application/json


### 2. Buscar producto por ID

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/productos/buscarProducto/{id}](https://contoso-czj1.onrender.com/api/productos/buscarProducto/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/productos/buscarProducto/1](https://contoso-czj1.onrender.com/api/productos/buscarProducto/1)


### 3. Buscar producto por tipo de combustible

- **Método**: GET
- **URL**: [https://contoso-czj1.onrender.com/api/productos/tipo/{tipoCombustible}](https://contoso-czj1.onrender.com/api/productos/tipo/{tipoCombustible})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/productos/tipo/GASOLINA95](https://contoso-czj1.onrender.com/api/productos/tipo/GASOLINA95)


### 4. Crear nuevo producto

- **Método**: POST
- **URL**: [https://contoso-czj1.onrender.com/api/productos/addProducto](https://contoso-czj1.onrender.com/api/productos/addProducto)
- **Headers**: Content-Type: application/json
- **Body** (raw JSON):


```json
{
  "nombre": "Gasolina 95",
  "tipoCombustible": "GASOLINA95",
  "precio": 1.45
}
```

### 5. Cambiar precio de producto

- **Método**: PUT
- **URL**: [https://contoso-czj1.onrender.com/api/productos/cambiarPrecio/{id}](https://contoso-czj1.onrender.com/api/productos/cambiarPrecio/{id})
- **Headers**: Content-Type: application/x-www-form-urlencoded
- **Params**:

- precio: 1.55



- **Ejemplo**: [https://contoso-czj1.onrender.com/api/productos/cambiarPrecio/1?precio=1.55](https://contoso-czj1.onrender.com/api/productos/cambiarPrecio/1?precio=1.55)


### 6. Eliminar producto

- **Método**: DELETE
- **URL**: [https://contoso-czj1.onrender.com/api/productos/deleteProducto/{id}](https://contoso-czj1.onrender.com/api/productos/deleteProducto/{id})
- **Headers**: Content-Type: application/json
- **Ejemplo**: [https://contoso-czj1.onrender.com/api/productos/deleteProducto/1](https://contoso-czj1.onrender.com/api/productos/deleteProducto/1)
