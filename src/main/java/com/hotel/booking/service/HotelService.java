package com.hotel.booking.service;

import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.exception.HotelNotFoundException;
import com.hotel.booking.mapper.HotelMapper;
import com.hotel.booking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Le dice a Spring que esta clase contiene lógica de negocio
@RequiredArgsConstructor // Crea un constructor con los campos 'final'
public class HotelService {

    private final HotelMapper hotelMapper;

    private final HotelRepository hotelRepository;

//      // 1. PASO ¡Nuestro método! Por ahora devuelve null para que compile.
//    // El test fallará, pero compilará.
//    public HotelDto createHotel(CreateHotelRequest request){
//        return null;
//    }

        // 2. PASO ¡Nuestro método! Por ahora devuelve funcionalidad
    public HotelDto createHotel(CreateHotelRequestDto request) {

        // Paso 1: Convertir el DTO de entrada a una entidad de dominio (la receta empieza).
        // Le pedimos al mapper que prepare los ingredientes.

        Hotel hotelEntity = hotelMapper.toEntity(request);

        // Paso 2: Persistir la entidad en la base de datos.
        // Le decimos al almacén que guarde el nuevo ingrediente.

        Hotel savedHotelEntity = hotelRepository.save(hotelEntity);

        // Paso 3: Convertir la entidad guardada (que ahora tiene un ID) a un DTO de respuesta.
        // Preparamos el plato final para el cliente.

        return hotelMapper.toDto(savedHotelEntity);

    }

    public HotelDto getHotelById(Long id){
//  PRIMERA OPCION

//        Paso 1: Busca el hotel. El resultado es un Optional.
//        Optional<Hotel> hotelOptional  = hotelRepository.findById(id);
//
//        // Paso 2: Si el Optional está vacío, lanza la excepción.
//        // Si no, devuelve el Hotel que contiene.
//
//        Hotel hotel = hotelOptional.orElseThrow(()-> new HotelNotFoundException("Hotel no encontrado con id: "+ id));
//
//        return hotelMapper.toDto(hotel);
    return hotelRepository.findById(id)
            .map(hotelMapper::toDto)
            .orElseThrow(()-> new HotelNotFoundException("Hotel no encontrado con id: "+ id));
    }

    /**
     ```Las dos versiones son correctas. La primera es más fácil de leer al principio, la segunda es más concisa.

     ### Resumen y Plan de Acción

     1.  **Corrige `HotelNotFoundException`** para que extienda de `RuntimeException`, llame a `super(message)` y tenga la anotación `@ResponseStatus(HttpStatus.NOT_FOUND)`.
     2.  **Elimina el método `toDtoOpcional`** de tu `HotelMapper`.
     3.  **Reemplaza tu método `getHotelById` en `HotelService`** con una de las versiones corregidas que te he mostrado.
     4.  Vuelve a tu test `getHotelById_shouldReturnHotelWhenFound()`, quita el `throws Exception` de la firma y **ejecútalo**. Debería pasar en **verde**.
     **/
}
