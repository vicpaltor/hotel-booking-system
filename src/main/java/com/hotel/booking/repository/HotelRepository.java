package com.hotel.booking.repository;

import com.hotel.booking.domain.hotel.Hotel;
import org.springframework.data.repository.CrudRepository;

/**
 * @Repository: Anotación que le indica a Spring que esta interfaz es un "Repositorio",
 *              un bean encargado del acceso a datos. También activa la traducción de
 *              excepciones de base de datos a excepciones de Spring.
 *
 * extends CrudRepository<Hotel, Long>:
 * - Le decimos a Spring Data que este repositorio manejará entidades de tipo 'Hotel'.
 * - Y que el tipo de la clave primaria (@Id) de 'Hotel' es 'Long'.
 * - Al extender esta interfaz, obtenemos gratis métodos como:
 *   - save(hotel): Guarda o actualiza un hotel.
 *   - findById(id): Busca un hotel por su ID.
 *   - findAll(): Devuelve todos los hoteles.
 *   - deleteById(id): Elimina un hotel por su ID.
 *   - count(): Cuenta el número de hoteles.
 *   - ... y más!
 */

public interface HotelRepository extends CrudRepository<Hotel, Long> {

}
