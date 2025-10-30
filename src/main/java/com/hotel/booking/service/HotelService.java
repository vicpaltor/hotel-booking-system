package com.hotel.booking.service;

import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import com.hotel.booking.mapper.HotelMapper;
import com.hotel.booking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
