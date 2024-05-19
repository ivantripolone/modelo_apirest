# Modelo apiREST Springboot
# API de Gestión de Clientes
Esta API proporciona una interfaz RESTful para realizar operaciones básicas de Crear, Leer, Actualizar y Eliminar (CRUD) sobre los registros de clientes.
Utilizando el formato JSON para la transferencia de datos y siguiendo los estándares de diseño de REST





# ClienteController

Este controlador REST en Java Spring maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad de cliente (`Cliente`).

## Métodos

### Crear Cliente

- Método: `POST`
- Ruta: `/api/v1/cliente`
- Descripción: Crea un nuevo cliente utilizando los datos proporcionados en el cuerpo de la solicitud.
- Parámetros de Entrada: Objeto `ClienteDto` en el cuerpo de la solicitud.
- Respuesta Exitosa: Código de estado `201 Created` con el objeto `ClienteDto` creado.
- Respuesta de Error: Código de estado `405 Method Not Allowed` si hay una excepción de acceso a datos.

### Actualizar Cliente

- Método: `PUT`
- Ruta: `/api/v1/cliente/{id}`
- Descripción: Actualiza un cliente existente identificado por su `id` con los datos proporcionados en el cuerpo de la solicitud.
- Parámetros de Entrada: Objeto `ClienteDto` en el cuerpo de la solicitud y `id` en la URL.
- Respuesta Exitosa: Código de estado `201 Created` con el objeto `ClienteDto` actualizado.
- Respuesta de Error: Código de estado `404 Not Found` si el cliente no existe o `405 Method Not Allowed` si hay una excepción de acceso a datos.

### Eliminar Cliente

- Método: `DELETE`
- Ruta: `/api/v1/cliente/{id}`
- Descripción: Elimina un cliente existente identificado por su `id`.
- Parámetros de Entrada: `id` en la URL.
- Respuesta Exitosa: Código de estado `204 No Content`.
- Respuesta de Error: Código de estado `405 Method Not Allowed` si hay una excepción de acceso a datos.

### Mostrar Todos los Clientes

- Método: `GET`
- Ruta: `/api/v1/clientes`
- Descripción: Obtiene todos los clientes almacenados.
- Respuesta Exitosa: Código de estado `200 OK` con una lista de objetos `ClienteDto`.
- Respuesta de Error: Código de estado `200 OK` con un mensaje indicando que no hay registros si no hay clientes almacenados.

### Mostrar Cliente por Id

- Método: `GET`
- Ruta: `/api/v1/cliente/{id}`
- Descripción: Obtiene un cliente por su `id`.
- Parámetros de Entrada: `id` en la URL.
- Respuesta Exitosa: Código de estado `200 OK` con el objeto `ClienteDto`.
- Respuesta de Error: Código de estado `404 Not Found` si el cliente no existe.

## Consideraciones

- Este controlador utiliza DTOs (`ClienteDto`) para la transferencia de datos.
- Las excepciones de acceso a datos se manejan adecuadamente devolviendo mensajes de error con los códigos de estado HTTP correspondientes.

# ClienteDto

La clase `ClienteDto` es un objeto de transferencia de datos (DTO) utilizado para transportar información relacionada con un cliente a través de la API. Esta clase está diseñada para ser utilizada como un modelo de datos ligero y portátil para la comunicación entre diferentes componentes del sistema.

## Atributos

- `idCliente` (Integer): El identificador único del cliente.
- `nombre` (String): El nombre del cliente.
- `apellido` (String): El apellido del cliente.
- `correo` (String): La dirección de correo electrónico del cliente.
- `fechaRegistro` (Date): La fecha de registro del cliente.

## Anotaciones

- `@Data`: Esta anotación de Lombok genera automáticamente los métodos `toString`, `equals`, `hashCode`, y getters/setters para todos los campos de la clase.
- `@ToString`: Esta anotación de Lombok genera automáticamente el método `toString` para la clase.
- `@Builder`: Esta anotación de Lombok facilita la creación de instancias de la clase utilizando el patrón de diseño Builder.

## Uso

La clase `ClienteDto` se utiliza principalmente para enviar y recibir datos relacionados con los clientes a través de la API. Puede ser utilizado como un contenedor de datos para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en la gestión de clientes.


## Script de la base de datos
```
create database db_springboot_dev;
CREATE TABLE `db_springboot_dev`.`clientes` (
  `id_cliente` INT ZEROFILL NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `fecha_registro` date  NOT NULL,
  PRIMARY KEY (`id_cliente`));
INSERT INTO clientes (nombre, apellido, correo, fecha_registro) VALUES('Joel', 'Jurado', 'juradoec@yahoo.com', '2023-08-01');
INSERT INTO clientes (nombre, apellido, correo, fecha_registro) VALUES('Carlos', 'Miranda', 'mirandaTr98@gmail.com', '2023-08-02');
INSERT INTO clientes (nombre, apellido, correo, fecha_registro) VALUES('Marcela', 'Sanchez', 'schMarce@itb.com', '2023-08-03');
INSERT INTO clientes (nombre, apellido, correo, fecha_registro) VALUES('Ben', 'Tennyson', 'ben10@cn.com', '2023-08-04');

```
