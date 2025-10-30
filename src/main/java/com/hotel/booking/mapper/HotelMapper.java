package com.hotel.booking.mapper;

import com.hotel.booking.domain.hotel.Hotel;
import com.hotel.booking.dto.CreateHotelRequestDto;
import com.hotel.booking.dto.HotelDto;
import org.mapstruct.Mapper;

import java.util.Optional;

/**
 * @Mapper(componentModel = "spring"): Esta es la anotación principal de MapStruct.
 *   - Le indica a MapStruct que procese esta interfaz.
 *   - 'componentModel = "spring"' es la instrucción clave: le dice a MapStruct que
 *     genere una implementación de esta interfaz que sea un Bean de Spring.
 *     Esto nos permitirá inyectarla (@Autowired) en otras clases como los servicios.
 */

@Mapper(componentModel = "spring")
public interface HotelMapper {
    /**
     * Convierte una entidad Hotel a un HotelDto.
     * MapStruct es inteligente: como los campos en ambas clases se llaman igual
     * (name, address, city, etc.), sabe cómo mapearlos automáticamente.
     * @param hotel La entidad de dominio a convertir.
     * @return El DTO correspondiente.
     */
    HotelDto toDto(Hotel hotel);
    /**
     * Convierte un DTO de creación (CreateHotelRequest) a una entidad Hotel.
     * De nuevo, los nombres de campo coinciden, por lo que el mapeo es automático.
     * @param request El DTO con los datos de entrada.
     * @return La entidad de dominio lista para ser guardada.
     */
    Hotel toEntity(CreateHotelRequestDto request);

}